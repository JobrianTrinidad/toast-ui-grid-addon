//To use this code, you would need to have the required dependencies installed
// and set up a container element in your HTML.
// Then, you can call the functions of the toastuigrid object to
// create, update, and manipulate the grid component.

import "@vaadin/button";
import "@vaadin/text-field";
// import "tui-grid/dist/tui-grid.css";
// import "tui-date-picker/dist/tui-date-picker.css";
// import "tui-time-picker/dist/tui-time-picker.css";
// import {createRoot} from "react-dom/client";
import * as ReactDOM from "react-dom";
import CustomTextEditor from "../components/Table/CustomeEditor";
import {FeatureTable} from "../components/Table/FeaturesTable";

declare global {
    interface Window {
        toastuigrid: { [key: string]: any };
    }
}

window.toastuigrid = {
    // This function is responsible for creating a grid component.
    // It takes a container element, JSON data for items, JSON data for options, and an unused parameter _.
    // The function parses the JSON data, sets up event handlers, creates an instance of the FeatureTable component,
    // and updates the grid.
    _createGrid: function (container: HTMLElement & { grid: any }, itemsJson: any, optionsJson: any, _: any) {
        let parsedItems = JSON.parse(itemsJson);
        let parsedOptions = JSON.parse(optionsJson);
        console.log("options: ", parsedOptions);
        console.log("Items: ", parsedItems);
        // Implementation goes here
        const onSelection = (ev: any) => {
            console.log("selection: ", ev);
            console.log("grid: ", container.grid);
        };
        const onEditingStart = (ev: any) => {
            console.log("Editing is start: ", ev);
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key, value) => {
                console.log("Editing is start: ", value);
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }));

// Send the cleaned object to the server
            container.$server.onEditingStart(cleanedObject);
        };
        const onEditingFinish = (ev: any) => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key, value) => {
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }));
// Send the cleaned object to the server
            container.$server.onEditingFinish(cleanedObject);
        };
        let gridTable: FeatureTable = new FeatureTable({
            el: document.getElementsByClassName("grid")[0],
            TableData: this.getTableData(parsedItems),
            columns: this.getColumns(JSON.parse(parsedOptions.columns)),
            summary: this.getSummary(parsedOptions.summary),
            columnOptions: parsedOptions.columnOptions,
            header: this.getHeader(parsedOptions.header),
            width: parsedOptions.width,
            bodyHeight: parsedOptions.bodyHeight,
            scrollX: parsedOptions.scrollX,
            scrollY: parsedOptions.scrollY,
            rowHeight: 40,
            minBodyHeight: 120,
            rowHeaders: parsedOptions.rowHeaders ? this.getRowHeaders(parsedOptions.rowHeaders) : null,
            treeColumnOptions: parsedOptions.treeColumnOptions ? JSON.parse(parsedOptions.treeColumnOptions) : null,
            onEditingStart: onEditingStart,
            onEditingFinish: onEditingFinish,
            onSelection: onSelection,
        });
        container.grid = gridTable;

        this.updateGrid(container);
    },
    //This function is a wrapper around _createGrid that delays the execution using setTimeout.
    // It takes a container element, JSON data for items, and JSON data for options.
    create(container: HTMLElement, itemsJson: any, optionsJson: any) {
        setTimeout(() => this._createGrid(container, itemsJson, optionsJson, null));
    },
    //This function updates the table data of an existing grid. It takes a container element with a grid property,
    // and JSON data for the new data. The function updates the table data, and then updates the grid.
    setTableData(container: HTMLElement & { grid: FeatureTable }, data: any) {
        let parsedItems = JSON.parse(data);
        container.grid.setOption({TableData: this.getTableData(parsedItems)});
        // this.updateGrid(container);
    },
    //This function adds new data to the existing table data of a grid.
    // It takes a container element with a grid property, and JSON data for the new data.
    // The function appends the new data to the existing table data, and then updates the grid.
    addTableData(container: HTMLElement & { grid: FeatureTable }, data: any) {
        let parsedItems = JSON.parse(data);
        // container.grid.TableData = [...container.grid.TableData, ...this.getTableData(parsedItems)];
        container.grid.setOption({TableData: [...container.grid.TableData, ...this.getTableData(parsedItems)]});
        // this.updateGrid(container);
    },
    //This internal function is used to set the column content based on a matched name.
    // It takes an object columnContent and modifies it based on its value.
    // The modified object is used for displaying summary information in the grid.
    _setColumnContentMatchedName(columnContent: any) {
        const onSum = () => {
            return {
                template: (valueMap: any) => {
                    return `Sum: ${valueMap.sum}`;
                }
            }
        };
        const onAvg = () => {
            return {
                template: (valueMap: any) => {
                    return `AVG: ${valueMap.avg.toFixed(2)}`;
                }
            }
        };
        const onMax = () => {
            return {
                template: (valueMap: any) => {
                    return `MAX: ${valueMap.max}`;
                }
            }
        };
        const onMin = () => {
            return {
                template: (valueMap: any) => {
                    return `MIN: ${valueMap.min}`;
                }
            }
        };

        switch (columnContent[Object.keys(columnContent)[0]]) {
            case "sum":
                columnContent[Object.keys(columnContent)[0]] = onSum();
                break;
            case "avg":
                columnContent[Object.keys(columnContent)[0]] = onAvg();
                break;
            case "max":
                columnContent[Object.keys(columnContent)[0]] = onMax();
                break;
            case "min":
                columnContent[Object.keys(columnContent)[0]] = onMin();
                break;
            default:
                columnContent[Object.keys(columnContent)[0]] = onSum();
                break;
        }
    },
    //This function parses the JSON data for the header and returns the header object.
    // If the header does not have complex columns, it returns null.
    // Otherwise, it constructs the header object with the specified height and complex columns.
    getHeader(parsedHeader: any) {
        if (!parsedHeader.hasOwnProperty('complexColumns'))
            return null;
        else {
            let header = parsedHeader.height != 0 ? {
                height: parsedHeader.height,
                complexColumns: this.getComplexColumns(parsedHeader.complexColumns)
            } : {
                complexColumns: this.getComplexColumns(parsedHeader.complexColumns)
            };
            return header;
        }
    },
    //This function retrieves the data from the grid and sends it to the server using the onGetData function.
    getData(container: HTMLElement & { grid: FeatureTable }) {
        let cleanedObject = JSON.parse(JSON.stringify(container.grid.TableData, (key, value) => {
            if (value instanceof Node) {
                return null; // Remove the DOM node reference
            }
            return value;
        }))
        container.$server.onGetData(cleanedObject);
    },
    //This function recursively parses the JSON data for the table data and returns the parsed data.
    // It handles nested data structures by recursively calling itself.
    getTableData(parsedData: any) {
        let listData = parsedData;
        for (const data of listData) {

            if (data.hasOwnProperty('_attributes') &&
                data.hasOwnProperty('_children')) {
                data._children = this.getTableData(JSON.parse(data._children));
            }
        }
        return listData;
    },
    //This function parses the JSON data for row headers and returns an array of trimmed row header names.
    getRowHeaders(parsedRowHeaders: any) {
        return parsedRowHeaders.slice(1, -1).split(",").map((item: any) => item.trim());
    },
    //This function parses the JSON data for complex columns and returns the parsed complex columns.
    // It also trims the child names.
    getComplexColumns(parsedColumnContent: any) {
        let complexColumns = JSON.parse(parsedColumnContent);
        for (const complexColumn of complexColumns) {
            complexColumn.childNames = complexColumn.childNames.slice(1, -1).split(", ").map((item: any) => item.trim());
        }
        return complexColumns;
    },
    //This function parses the JSON data for the summary and returns the parsed summary object.
    // It handles column content by calling _setColumnContentMatchedName to modify the column content based on its value.
    getSummary(parsedSummary: any) {
        if (parsedSummary == undefined || !parsedSummary.hasOwnProperty('columnContent'))
            return null;
        let summaries: any = parsedSummary;
        let columnContents = JSON.parse(summaries.columnContent);
        for (const columnContent of columnContents) {
            this._setColumnContentMatchedName(columnContent);
        }

        return summaries = {
            height: summaries.height ? summaries.height : 40,
            position: summaries.position,
            columnContent: columnContents.reduce((acc: any, obj: any) => {
                const key = Object.keys(obj)[0];
                const value = obj[key];
                acc[key] = value;
                return acc;
            }, {})
        }
    },
    //This function parses the JSON data for the columns and returns the parsed columns.
    // It handles special cases for input and select editors, and also handles depth0 and depth1 data for select editors.
    getColumns(parsedColumn: any) {
        let columns: any[] = parsedColumn;
        let tempColumns: any[] = [];

        for (let column of columns) {
            if (column.editor && column.editor.type == "input") {
                column.editor.type = CustomTextEditor;
            }

            if (column.hasOwnProperty('editor') &&
                column.editor.hasOwnProperty('options') &&
                !column.editor.options.hasOwnProperty('maxLength')) {

                column.editor.options = JSON.parse(column.editor.options);
                if (column.editor.options.hasOwnProperty('fromYear') &&
                    parseInt(column.editor.options.fromYear) > 0) {
                    let fromYear = parseInt(column.editor.options.fromYear);
                    let fromMonth = parseInt(column.editor.options.fromMonth);
                    let fromDay = parseInt(column.editor.options.fromDay);
                    let toYear = parseInt(column.editor.options.toYear);
                    let toMonth = parseInt(column.editor.options.toMonth);
                    let toDay = parseInt(column.editor.options.toDay);
                    column.editor.options = {
                        selectableRanges: [[new Date(fromYear, fromMonth - 1, fromDay), new Date(toYear, toMonth - 1, toDay)]]
                    };
                }
            }

            if (column.editor && column.editor.type == "select") {
                let tempColumn = {
                    header: column.headerName,
                    name: column.name,
                    align: column.align,
                    formatter: "listItemText",
                    editor: {
                        type: "select",
                        options: {
                            listItems: column["depth0"] ? JSON.parse(column["depth0"]) : []
                        }
                    },
                    ...(column["depth1"] != "[]" && {
                        relations: [{
                            targetNames: [column.targetNames],
                            listItems({value}: any) {
                                return column["depth1"][value] ? JSON.parse(column["depth1"][value]) : [];
                            },
                            disabled({value}: any) {
                                return !value;
                            }
                        }]
                    })
                };
                tempColumns.push(tempColumn);
            }
            else
                tempColumns.push(column);
        }
        return tempColumns;
    },
    //This function updates the options of an existing grid.
    // It takes a container element with a grid property, and JSON data for the new options.
    // The function updates the options, and then updates the grid.
    setOptions: function (container: HTMLElement & { grid: FeatureTable }, optionsJson: any) {
        let parsedOptions = JSON.parse(optionsJson);
        container.grid.setOption(parsedOptions);
        // this.updateGrid(container);
    },
    setTest: function (container: any, content: any) {
        console.log("Event Test: ", content);
    },
    //This function updates the grid by rendering the grid component using ReactDOM.render.
    updateGrid: function (container: any) {
        console.log("Grid: ", container.grid);
        ReactDOM.render(container.grid.render(), container);
    }
}
