import "@vaadin/button";
import "@vaadin/text-field";
import {FeatureTable} from "../../views/components/Table/FeaturesTable";
import "tui-grid/dist/tui-grid.css";
import "tui-date-picker/dist/tui-date-picker.css";
import {createRoot} from "react-dom/client";
import {OptColumn, OptSummaryData} from "tui-grid/types/options";
import CustomTextEditor from "../../views/components/Table/CustomeEditor";

window["toastuigrid"] = {
    _createGrid: function (container: HTMLElement, itemsJson: any, optionsJson: any, _: any) {
        let parsedItems = JSON.parse(itemsJson);
        let parsedOptions = JSON.parse(optionsJson);
        console.log("AAA: ", parsedItems);
        const onClick = (e: any) => {
            // console.log(e);
        }
        const onDblclick = (e: any) => {
            console.log(e);
        }
        const onMousedown = (e: any) => {
            // console.log(e);
        }
        const onMouseover = (e: any) => {
            // console.log(e);
        }
        const onMouseout = (e: any) => {
            // console.log(e);
        }
        const onFocusChange = (e: any) => {
            console.log(e);
        }
        const onColumnResize = (e: any) => {
            console.log(e);
        }
        const onCheck = (e: any) => {
            console.log(e);
        }
        const onUncheck = (e: any) => {
            console.log(e);
        }
        const onCheckAll = (e: any) => {
            console.log(e);
        }
        const onUncheckAll = (e: any) => {
            console.log(e);
        }
        const onSelection = (e: any) => {
            console.log(e);
        }
        const onEditingStart = (e: any) => {
            console.log(e);
        }
        const onEditingFinish = (e: any) => {
            console.log(e);
        }
        const onSort = (e: any) => {
            console.log(e);
        }
        const onFilter = (e: any) => {
            console.log(e);
        }
        const onScrollEnd = (e: any) => {
            console.log(e);
        }
        const onBeforeRequest = (e: any) => {
            console.log(e);
        }
        const onResponse = (e: any) => {
            console.log(e);
        }
        const onSuccessResponse = (e: any) => {
            console.log(e);
        }
        const onFailResponse = (e: any) => {
            console.log(e);
        }
        const onErrorResponse = (e: any) => {
            console.log(e);
        }

        // Implementation goes here
        let gridTable: FeatureTable = new FeatureTable({
            TableData: parsedItems,
            columns: this.getColumns(JSON.parse(parsedOptions.columns)),
            summary: this.getSummary(parsedOptions.summary),
            columnOptions: parsedOptions.columnOptions,
            header: this.getHeader(parsedOptions.header),
            onClick: onClick,
            onDblclick: onDblclick,
            onMousedown: onMousedown,
            onMouseover: onMouseover,
            onMouseout: onMouseout,
            onFocusChange: onFocusChange,
            onColumnResize: onColumnResize,
            onCheck: onCheck,
            onUncheck: onUncheck,
            onCheckAll: onCheckAll,
            onUncheckAll: onUncheckAll,
            onSelection: onSelection,
            onEditingStart: onEditingStart,
            onEditingFinish: onEditingFinish,
            onSort: onSort,
            onFilter: onFilter,
            onScrollEnd: onScrollEnd,
            onBeforeRequest: onBeforeRequest,
            onResponse: onResponse,
            onSuccessResponse: onSuccessResponse,
            onFailResponse: onFailResponse,
            onErrorResponse: onErrorResponse,
        });
        container.grid = gridTable;

        createRoot(container).render(gridTable.render());
    },
    create(container: HTMLElement, itemsJson: any, optionsJson: any) {
        setTimeout(() => this._createGrid(container, itemsJson, optionsJson, null));
    },
    _setColumnContentMatchedName(columnContent) {
        const onSum = () => {
            return {
                template: (valueMap) => {
                    return `Sum: ${valueMap.sum}`;
                }
            }
        };
        const onAvg = () => {
            return {
                template: (valueMap) => {
                    return `AVG: ${valueMap.avg.toFixed(2)}`;
                }
            }
        };
        const onMax = () => {
            return {
                template: (valueMap) => {
                    return `MAX: ${valueMap.max}`;
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
            default:
                columnContent[Object.keys(columnContent)[0]] = onSum();
                break;
        }
    },
    getHeader(parsedHeader) {
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
    getComplexColumns(parsedColumnContent) {
        let complexColumns = JSON.parse(parsedColumnContent);
        for (const complexColumn of complexColumns) {
            complexColumn.childNames = complexColumn.childNames.slice(1, -1).split(", ").map((item) => item.trim());
        }
        return complexColumns;
    },
    getSummary(parsedSummary) {
        if (parsedSummary == undefined || !parsedSummary.hasOwnProperty('columnContent'))
            return null;
        let summaries: OptSummaryData = parsedSummary;
        let columnContents = JSON.parse(summaries.columnContent);
        for (const columnContent of columnContents) {
            this._setColumnContentMatchedName(columnContent);
        }

        return summaries = {
            height: summaries.height ? summaries.height : 40,
            position: summaries.position,
            columnContent: columnContents.reduce((acc, obj) => {
                const key = Object.keys(obj)[0];
                const value = obj[key];
                acc[key] = value;
                return acc;
            }, {})
        }
    },
    getColumns(parsedColumn) {
        let columns: OptColumn[] = parsedColumn;
        for (const column of columns) {
            if (column.editor && column.editor.type == "input") {
                column.editor.type = CustomTextEditor;
            }
        }
        return columns;
    },
    setOptions: function (container, optionsJson) {
        container.grid.setOption(optionsJson);
    },
}