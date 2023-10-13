//To use this code, you would need to have the required dependencies installed
// and set up a container element in your HTML.
// Then, you can call the functions of the toastuigrid object to
// create, update, and manipulate the grid component.

import "@vaadin/button";
import "@vaadin/text-field";
import React, {useEffect, useRef} from 'react';
import type {JSX} from 'react';
import {createRoot, Root} from 'react-dom/client';
import InputComponent from "../components/input/ada-input";
import CheckboxComponent from "../components/checkbox/ada-checkbox";
import {CheckboxRenderer, RowNumberRenderer} from '../renderer/renderer';
import DropDown from "../components/dropdown/index";
import FeatureTable from "../components/Table/FeaturesTable";
import TuiGrid, {RowKey} from 'tui-grid';
import {TuiGridEvent} from "tui-grid/types/event";

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
    _createGrid: function (container: HTMLElement & {
        $server: any,
        grid: JSX.Element & { table: TuiGrid }
    }, itemsJson: string, optionsJson: string): void {
        let parsedItems = JSON.parse(itemsJson);
        let parsedOptions = JSON.parse(optionsJson);
        let editingRowKey: string | number = -1;
        let columns = this.getColumns(JSON.parse(parsedOptions.columns));
        let prevColumnName: string = "";
        let gridInst: TuiGrid;
        let rangeSelected: number[] = [];
        console.log("TableData: ", this.getTableData(parsedItems));
        // console.log("Column: ", columns);
        // Implementation goes here
        const onSelection = (ev: TuiGridEvent): void => {
            rangeSelected = [];
            const data: any[] = gridInst.getData();

            for (let i: number = Number(ev.range.row[0]); i <= Number(ev.range.row[1]); i++) {
                rangeSelected.push(Number(data[i]['rowKey']));
            }
        }
        const onCheck = (ev: TuiGridEvent): void => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key: string, value): null | string => {
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }))
            container.$server.onCheck(cleanedObject);
        };
        const onUncheck = (ev: TuiGridEvent): void => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key: string, value): null | string => {
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }))
            container.$server.onUncheck(cleanedObject);
        };
        const onCheckAll = (ev: TuiGridEvent): void => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key: string, value): null | string => {
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }))
            container.$server.onCheckAll(cleanedObject);
        };
        const onUncheckAll = (ev: TuiGridEvent): void => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key: string, value): null | string => {
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }))
            container.$server.onUncheckAll(cleanedObject);
        };
        const onEditingStart = (ev: TuiGridEvent): void => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key: string, value): string => {
                if (value instanceof Node) {
                    return 'null'; // Remove the DOM node reference
                }
                return value;
            }));

            if (gridInst) {
                editingRowKey = gridInst.getFocusedCell()['rowKey'];
            }

// Send the cleaned object to the server
            container.$server.onEditingStart(cleanedObject);
        };
        const onEditingFinish = (ev: TuiGridEvent): void => {
            let cleanedObject = JSON.parse(JSON.stringify(ev, (key: string, value): null | string => {
                if (value instanceof Node) {
                    return null; // Remove the DOM node reference
                }
                return value;
            }));
// Send the cleaned object to the server getRowSpanData
            if (gridInst) {
                let record: {} = {};
                for (const column of columns) {
                    let key = column.name;
                    record = {...record, [key]: gridInst.getValue(editingRowKey, column.name)}
                }
                cleanedObject = {...cleanedObject, record: record};
                if (gridInst.getValue(editingRowKey, columns[0].name) !== "")
                    container.$server.onEditingFinish(cleanedObject);
            }
        };
        const onFocusChange = (ev: TuiGridEvent): void => {
            const firstCol = JSON.parse(parsedOptions.columns)[0];
            if (firstCol.hasOwnProperty('editor') &&
                firstCol.editor.hasOwnProperty('type') &&
                firstCol.editor.type === 'select') {
                editingRowKey = -1;
                return;
            }
            if (ev.prevRowKey !== ev.rowKey && editingRowKey !== -1)
                if (gridInst.getValue(editingRowKey, columns[0].name) === "" ||
                    gridInst.getValue(editingRowKey, columns[0].name) === null) {
                    gridInst.removeRow(editingRowKey);
                    editingRowKey = -1;
                }
        };
        const handleSearchResult = (result: any): void => {
            gridInst = container.grid.table;
            gridInst.focusAt(result.row, result.column);
        }
        const handleKeyDown = (event: KeyboardEvent): void => {
            gridInst = container.grid.table;
            if (event.shiftKey === false
                && event.altKey === false
                && event.ctrlKey === false
                && (event.code === "ArrowDown"
                    || event.code === "ArrowUp"
                    || event.code === "ArrowLeft"
                    || event.code === "ArrowRight"))
                rangeSelected = [];
            else if (event.shiftKey === true
                && event.code === "Delete") {
                if (gridInst.getCheckedRowKeys().length > 0) {
                    rangeSelected = [];
                    container.$server.deleteItems(gridInst.getCheckedRowKeys());
                }
            } else if (event.shiftKey === true
                && event.code === "Space") {
                event.preventDefault();
                if (rangeSelected.length === 0) {
                    let selectedIndex = gridInst.getFocusedCell()["rowKey"];
                    if (gridInst.getCheckedRowKeys().includes(selectedIndex))
                        gridInst.uncheck(selectedIndex);
                    else
                        gridInst.check(selectedIndex);
                } else {
                    let bChecked: boolean = true;
                    for (let i: number = 0; i <= rangeSelected.length; i++) {
                        if (gridInst.getCheckedRowKeys().includes(i))
                            bChecked = false;
                    }
                    for (let i: number = 0; i <= rangeSelected.length; i++) {
                        if (bChecked)
                            gridInst.check(rangeSelected[i]);
                        else {
                            gridInst.uncheck(rangeSelected[i]);
                        }
                    }
                }
            } else if (parsedOptions.autoSave === true
                && event.shiftKey === true
                && event.code === "Insert") {
                event.preventDefault();
                container.$server.addItem();
            } else if (parsedOptions.autoSave === true
                && event.code === "Tab"
                && gridInst.getFocusedCell()['rowKey'] === gridInst.getRowCount() - 1
                && prevColumnName === columns[columns.length - 1].name) {
                prevColumnName = columns[0].name;
                container.$server.addItem();
            } else
                if(gridInst)
                    prevColumnName = gridInst.getFocusedCell()['columnName'];
        };
        const handleMouseDown = (event: MouseEvent): void => {
            gridInst = container.grid.table;
            // if (event.defaultPrevented === false) {
            //     gridInst.restore();
            // }
            const targetElement: HTMLElement = event.target as HTMLElement;
            if (targetElement.tagName === "VAADIN-APP-LAYOUT" || targetElement.tagName === "DIV") {
                gridInst.finishEditing(editingRowKey, prevColumnName);

                if (gridInst.getValue(editingRowKey, columns[0].name) === "" ||
                    gridInst.getValue(editingRowKey, columns[0].name) === null) {
                    gridInst.removeRow(editingRowKey);
                }
            } else {
                return;
            }

            if (!event.shiftKey
                && !targetElement.className.includes('tui-grid-cell-header'))
                rangeSelected = [];
        };
        const handleGetGridInstance = (gridInstance: TuiGrid): void => {
            gridInst = gridInstance;
            if (Object.isExtensible(container.grid)) {
                container.grid.table = gridInstance;
            } else {
                // If container.grid is not extensible, create a new object with the desired properties
                container.grid = {
                    ...container.grid,
                    table: gridInstance,
                };
            }
        };
        let gridTable: JSX.Element = (
            <FeatureTable
                getGridInstance={handleGetGridInstance}
                el={document.getElementsByClassName("grid")[0]}
                TableData={this.getTableData(parsedItems)}
                columns={columns}
                summary={this.getSummary(parsedOptions.summary)}
                columnOptions={parsedOptions.columnOptions}
                header={this.getHeader(parsedOptions.header)}
                width={parsedOptions.width}
                bodyHeight={parsedOptions.bodyHeight}
                scrollX={parsedOptions.scrollX}
                scrollY={parsedOptions.scrollY}
                rowHeight={40}
                minBodyHeight={120}
                rowHeaders={parsedOptions.rowHeaders ? this.getRowHeaders(parsedOptions.rowHeaders) : null}
                treeColumnOptions={parsedOptions.treeColumnOptions ? JSON.parse(parsedOptions.treeColumnOptions) : null}
                onEditingStart={onEditingStart}
                onEditingFinish={onEditingFinish}
                onSelection={onSelection}
                onCheck={onCheck}
                onCheckAll={onCheckAll}
                onUncheck={onUncheck}
                onUncheckAll={onUncheckAll}
                onFocusChange={onFocusChange}
                handleSearchResult={handleSearchResult}
            ></FeatureTable>
        );
        container.grid = gridTable;

        document.addEventListener("keydown", handleKeyDown);
        document.addEventListener("mousedown", handleMouseDown);

        this.updateGrid(container);
    },
    //This function is a wrapper around _createGrid that delays the execution using setTimeout.
    // It takes a container element, JSON data for items, and JSON data for options.
    create
    (container: HTMLElement, itemsJson: string, optionsJson: string): void {
        setTimeout(() => this._createGrid(container, itemsJson, optionsJson, null));
    },

    //This function updates the table data of an existing grid. It takes a container element with a grid property,
    // and JSON data for the new data. The function updates the table data, and then updates the grid.
    setTableData(container: HTMLElement & { grid: JSX.Element & { table: TuiGrid } }, data: string): void {
        let parsedItems = JSON.parse(data);
        if (container && container.grid && container.grid.table)
            container.grid.table.resetData(this.getTableData(parsedItems));
        // this.updateGrid(container);
    },
    //This function adds new data to the existing table data of a grid.
    // It takes a container element with a grid property, and JSON data for the new data.
    // The function appends the new data to the existing table data, and then updates the grid.
    addTableData(container: HTMLElement & { grid: JSX.Element & { table: TuiGrid } }, data: string): void {
        let gridInst: TuiGrid = container.grid.table;
        gridInst.appendRow();
        gridInst.startEditingAt(gridInst.getRowCount() - 1, 0);
        // this.updateGrid(container);
    },
    //This internal function is used to set the column content based on a matched name.
    // It takes an object columnContent and modifies it based on its value.
    // The modified object is used for displaying summary information in the grid.
    _setColumnContentMatchedName(columnContent: any): void {
        const onSum = (): { template: (valueMap: any) => string } => {
            return {
                template: (valueMap: any): string => {
                    return `Sum: ${valueMap.sum}`;
                }
            }
        };
        const onAvg = (): { template: (valueMap: any) => string } => {
            return {
                template: (valueMap: any): string => {
                    return `AVG: ${valueMap.avg.toFixed(2)}`;
                }
            }
        };
        const onMax = (): { template: (valueMap: any) => string } => {
            return {
                template: (valueMap: any): string => {
                    return `MAX: ${valueMap.max}`;
                }
            }
        };
        const onMin = (): { template: (valueMap: any) => string } => {
            return {
                template: (valueMap: any): string => {
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
    getHeader(parsedHeader: {
        height?: number,
        complexColumns: any[]
    }): {
        height?: number,
        complexColumns: any[]
    } | null {
        if (!parsedHeader.hasOwnProperty('complexColumns'))
            return null;
        else {
            let header: { height?: number, complexColumns: any[] } = parsedHeader.height != 0 ? {
                height: parsedHeader.height,
                complexColumns: this.getComplexColumns(parsedHeader.complexColumns)
            } : {
                complexColumns: this.getComplexColumns(parsedHeader.complexColumns)
            };
            return header;
        }
    },
    //This function retrieves the data from the grid and sends it to the server using the onGetData function.
    getData(container: HTMLElement & { $server: any, grid: JSX.Element & { table: TuiGrid } }): void {
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
        return parsedRowHeaders.slice(1, -1).split(",").map((item: any) => {
            item.trim();
            if (item === 'rowNum') {
                item = {
                    type: 'rowNum',
                    renderer: {
                        type: RowNumberRenderer
                    }
                };
            }

            if (item === 'checkbox') {
                item = {
                    type: 'checkbox',
                    header: `
                              <label for="all-checkbox" class="checkbox" 
                                    style="display: flex;
                                    justify-content: center;"
                               >
                                <input type="checkbox" id="all-checkbox" class="hidden-input" name="_checked" />
                                <span class="custom-input"></span>
                              </label>
                            `,
                    renderer: {
                        type: CheckboxRenderer
                    }
                };
            }
            return item;
        });
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
        if (columnContents.length === 0)
            return null;
        for (const columnContent of columnContents) {
            this._setColumnContentMatchedName(columnContent);
        }

        return summaries = {
            height: summaries.height ? summaries.height : 40,
            position: summaries.position,
            columnContent: columnContents.reduce((acc: any, obj: any) => {
                const key: string = Object.keys(obj)[0];
                const value = obj[key];
                acc[key] = value;
                return acc;
            }, {})
        }
    },
    //This function parses the JSON data for the columns and returns the parsed columns.
    // It handles special cases for input and select editors, and also handles depth0 and depth1 data for select editors.
    getColumns(parsedColumn: any[]): any[] {
        let columns: any[] = parsedColumn;
        let tempColumns: any[] = [];

        for (let column of columns) {
            if (column.editor && column.editor.type == "input") {
                column.editor.type = InputComponent;
            }

            if (column.hasOwnProperty('editor') &&
                column.editor.hasOwnProperty('options') &&
                !column.editor.options.hasOwnProperty('maxLength')) {

                column.editor.options = JSON.parse(column.editor.options);
                if (column.editor.options.hasOwnProperty('fromYear') &&
                    parseInt(column.editor.options.fromYear) > 0) {
                    const fromYear: number = parseInt(column.editor.options.fromYear);
                    const fromMonth: number = parseInt(column.editor.options.fromMonth);
                    const fromDay: number = parseInt(column.editor.options.fromDay);
                    const toYear: number = parseInt(column.editor.options.toYear);
                    const toMonth: number = parseInt(column.editor.options.toMonth);
                    const toDay: number = parseInt(column.editor.options.toDay);
                    column.editor.options = {
                        selectableRanges: [[new Date(fromYear, fromMonth - 1, fromDay), new Date(toYear, toMonth - 1, toDay)]]
                    };
                }
            }

            if (column.editor && column.editor.type == "select") {
                const tempColumn = {
                    header: column.header,
                    name: column.name,
                    align: column.align,
                    formatter: "listItemText",
                    editor: {
                        type: DropDown,//"select"
                        options: {
                            ...column.editor.options,
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
            } else
                tempColumns.push(column);
        }
        return tempColumns;
    },
    //This function updates the options of an existing grid.
    // It takes a container element with a grid property, and JSON data for the new options.
    // The function updates the options, and then updates the grid.
    setOptions: function (container: HTMLElement & {
        grid: JSX.Element & { table: TuiGrid }
    }, optionsJson: string): void {
        let parsedOptions = JSON.parse(optionsJson);
        container.grid.setOption(parsedOptions);
    },
    setTest: function (container: HTMLElement, content: any): void {
        // console.log("Event Test: ", content);
    },

    removeRows: function (container: HTMLElement & {
        $server: any
        grid: JSX.Element & { table: TuiGrid }
    }, rowKeys: string): void {
        // const rows = JSON.parse(rowKeys);
        // gridInst.reloadData();
        // for (let i = 0; i < rows.length; i++) {

        let gridInst: TuiGrid = container.grid.table;
        const rows: RowKey[] = gridInst.getCheckedRowKeys();
        const rowLength: number = rows.length;
        if (rowLength === 0)
            return;
        let min: RowKey = 999999999999;
        for (let i: number = 0; i < rowLength; i++) {
            if (Number(min) > Number(rows[i]))
                min = rows[i];
        }
        const focusedRow: number = gridInst.getIndexOfRow(min);
        gridInst.removeCheckedRows(false);
        if (gridInst.getData().length > 0)
            if (gridInst.getData().length > focusedRow)
                gridInst.focusAt(focusedRow, 0);
            else
                gridInst.focusAt(gridInst.getData().length - 1, 0);
    }
    ,
    //This function updates the grid by rendering the grid component using ReactDOM.render.
    updateGrid: function (container: HTMLElement & {
        $server: any,
        grid: JSX.Element & { table: TuiGrid }
        $root?: Root
    }): void {
        createRoot(container).render(container.grid);
    }
}
