import React, {useEffect, useRef, forwardRef} from 'react';
import ExcelSheet from "./ExcelSheet";
import TuiGrid from 'tui-grid';
import {TuiGridEvent} from "tui-grid/types/event";

interface FeatureTableProps {
    getGridInstance: (gridInstance: TuiGrid) => void;
    TableData: any;
    columns: any;
    summary?: any;
    columnOptions?: any;
    header?: any;
    width?: any;
    bodyHeight?: any;
    scrollX?: any;
    scrollY?: any;
    rowHeaders?: any;
    treeColumnOptions?: any;
    rowHeight?: any;
    minBodyHeight?: any;
    onEditingStart?: any;
    onEditingFinish?: any;
    onSelection?: any;
    onCheck?: any;
    onCheckAll?: any;
    onUncheck?: any;
    onUncheckAll?: any;
    onFocusChange?: any;
    handleSearchResult?: any;
}

const FeatureTable: React.FC<FeatureTableProps> = React.forwardRef<HTMLDivElement, FeatureTableProps>(
    (
        {
            getGridInstance,
            TableData,
            columns,
            summary,
            columnOptions,
            header,
            width,
            bodyHeight,
            scrollX,
            scrollY,
            rowHeaders,
            treeColumnOptions,
            rowHeight,
            minBodyHeight,
            onEditingStart,
            onEditingFinish,
            onSelection,
            onCheck,
            onCheckAll,
            onUncheck,
            onUncheckAll,
            onFocusChange,
            handleSearchResult,
        }: FeatureTableProps,
        ref: React.Ref<HTMLDivElement>
    ) => {
        const gridRef = useRef<HTMLDivElement>(null);
        const excelRef = useRef<HTMLDivElement>(null);
        const gridInstanceRef = useRef<TuiGrid | null>(null);

        function loadRows(lengthOfLoaded: number) {
            const rows: { [key: string]: string }[] = [];
            let endPoint: number = lengthOfLoaded + 50 <= TableData.length ? lengthOfLoaded + 50 : TableData.length
            for (let i: number = lengthOfLoaded; i < endPoint; i += 1) {
                const row: { [key: string]: string } = {};
                for (let j: number = 0; j < columns.length; j += 1) {
                    row[columns[j].name] = TableData[i][columns[j].name];
                }
                rows.push(row);
            }
            return rows;
        }

        useEffect(() => {
            const grid = new TuiGrid({
                el: gridRef.current!,
                data: loadRows(0),
                columns: columns,
                className: 'table-center',
                ...(summary && {summary}),
                ...(columnOptions && {columnOptions}),
                ...(header && {header}),
                ...(width && {width}),
                bodyHeight: 500,
                scrollX: scrollX,
                scrollY: scrollY,
                ...(rowHeaders && {rowHeaders}),
                ...(treeColumnOptions && {treeColumnOptions}),
                ...(rowHeight && {rowHeight}),
                ...(minBodyHeight && {minBodyHeight}),
            });
            gridInstanceRef.current = grid;
            grid.on('scrollEnd', (): void => {
                console.log("ScrollEnd");
                grid.appendRows(loadRows(grid.store.data.viewData.length));
            });

            grid.on('editingStart', (ev: TuiGridEvent): void => {
                onEditingStart(ev);
            });

            grid.on('editingFinish', (ev: TuiGridEvent): void => {
                onEditingFinish(ev);
            });

            grid.on('selection', (ev: TuiGridEvent): void => {
                onSelection(ev);
            });

            grid.on('check', (ev: TuiGridEvent): void => {
                onCheck(ev);
            });

            grid.on('uncheck', (ev: TuiGridEvent): void => {
                onUncheck(ev);
            });

            grid.on('checkAll', (ev: TuiGridEvent): void => {
                onCheckAll(ev);
            });

            grid.on('uncheckAll', (ev: TuiGridEvent): void => {
                onUncheckAll(ev);
            });

            grid.on('onFocusChange', (ev: TuiGridEvent): void => {
                onFocusChange(ev);
            });

            getGridInstance(grid);

            return (): void => {
                if (grid) {
                    grid.destroy();
                }
            };
        }, []);

        let range: any[] = [];
        for (let row: number = 0; row < TableData.length; row++) {
            let temp: any[] = [];
            for (let col: number = 0; col < Object.keys(TableData[row]).length; col++) {
                const cellValue = TableData[row][Object.keys(TableData[row])[col]];
                temp.push({row: row, column: col, value: cellValue});
            }
            range.push(temp);
        }

        return (
            <div>
                <div ref={gridRef}></div>
                <ExcelSheet
                    ref={excelRef}
                    range={range}
                    onSearchResult={handleSearchResult}
                />
            </div>
        );
    }
);

export default FeatureTable;