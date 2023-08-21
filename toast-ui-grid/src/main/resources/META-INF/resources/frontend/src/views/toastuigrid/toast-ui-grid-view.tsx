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
    _createGrid: function (container: HTMLElement & { grid: any }, itemsJson: any, optionsJson: any, _: any) {
        let parsedItems = JSON.parse(itemsJson);
        let parsedOptions = JSON.parse(optionsJson);
        console.log("AAA: ", parsedOptions);
        // Implementation goes here
        const onEditingStart = (ev: any) => {
            console.log("ABCDEF_start: ", ev);
        };
        const onEditingFinish = (ev: any) => {
            var cleanedObject = JSON.parse(JSON.stringify(ev, (key, value) => {
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
        });

        container.grid = gridTable;

        container.grid.expand = (ev: any) => {
            const {rowKey} = ev;
            const descendantRows = container.grid.getDescendantRows(rowKey);

            if (!descendantRows.length) {
                container.grid.appendRow(
                    {
                        name: 'dynamic loading data',
                        _children: [
                            {
                                name: 'leaf row'
                            },
                            {
                                name: 'internal row',
                                _children: []
                            }
                        ]
                    },
                    {parentRowKey: rowKey}
                );
            }
        };
        container.grid.collapse = (ev: any) => {
            const {rowKey} = ev;
            const descendantRows = container.grid.getDescendantRows(rowKey);
        };

        this.updateGrid(container);
    },
    create(container: HTMLElement, itemsJson: any, optionsJson: any) {
        setTimeout(() => this._createGrid(container, itemsJson, optionsJson, null));
    },
    setTableData(container: HTMLElement & { grid: FeatureTable }, data: any) {
        let parsedItems = JSON.parse(data);
        container.grid.setOption({TableData: this.getTableData(parsedItems)});
        this.updateGrid(container);
    },
    addTableData(container: HTMLElement & { grid: FeatureTable }, data: any) {
        console.log("BBB: ", container);
        console.log("AAA: ", container.grid);
        let parsedItems = JSON.parse(data);
        container.grid.TableData = [...container.grid.TableData, ...this.getTableData(parsedItems)];
        this.updateGrid(container);
    },
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
    getHeader(parsedHeader: any) {
        if (!parsedHeader.hasOwnProperty('complexColumns'))
            return null;
        else {
            let header = parsedHeader.height != 0 ? {
                height: parsedHeader.height,
                complexColumns: this.getComplexColumns(parsedHeader.columnContent)
            } : {
                complexColumns: this.getComplexColumns(parsedHeader.columnContent)
            };
            return header;
        }
    },
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
    getRowHeaders(parsedRowHeaders: any) {
        return parsedRowHeaders.slice(1, -1).split(",").map((item: any) => item.trim());
    },
    getComplexColumns(parsedColumnContent: any) {
        let complexColumns = JSON.parse(parsedColumnContent);
        for (const complexColumn of complexColumns) {
            complexColumn.childNames = complexColumn.childNames.slice(1, -1).split(", ").map((item: any) => item.trim());
        }
        return complexColumns;
    },
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
    getColumns(parsedColumn: any) {
        let columns: any[] = parsedColumn;
        let tempColumns: any[] = [];

        for (const column of columns) {
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
        }
        if (tempColumns.length != 0)
            return tempColumns;
        return columns;
    },
    setOptions: function (container: HTMLElement & { grid: FeatureTable }, optionsJson: any) {
        let parsedOptions = JSON.parse(optionsJson);
        container.grid.setOption(parsedOptions);
        this.updateGrid(container);
    },
    insertColumn: function (container: HTMLElement & { grid: FeatureTable }, optionsJson: any, itemsJson: any) {
        let parsedOptions = JSON.parse(optionsJson);
        let parsedItems = JSON.parse(itemsJson);

        container.grid.setOption({
            TableData: this.getTableData(parsedItems),
            columns: this.getColumns(parsedOptions),
        });
        this.updateGrid(container);
    },
    setTest: function (container: any, content: any) {
        console.log("Event Test: ", content);
    },
    updateGrid: function (container: any) {
        ReactDOM.render(container.grid.render(), container);
    }
}