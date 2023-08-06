import Grid from '@toast-ui/react-grid';
import {OptColumn} from 'tui-grid/types/options';

import {CellEditor, CellEditorProps} from 'tui-grid/types/editor';
import {OptSummaryData} from 'tui-grid/types/options';
import React from "react";

const select = (e: any) => {
    console.log(e);
}

export class FeatureTable extends React.Component<any, any> {
    TableData;
    columns;
    summary;
    columnOptions;
    header;
    onClick;
    onDblclick;
    onMousedown;
    onMouseover;
    onMouseout;
    onFocusChange;
    onColumnResize;
    onCheck;
    onUncheck;
    onCheckAll;
    onUncheckAll;
    onSelection;
    onEditingStart;
    onEditingFinish;
    onSort;
    onFilter;
    onScrollEnd;
    onBeforeRequest;
    onResponse;
    onSuccessResponse;
    onFailResponse;
    onErrorResponse;

    constructor(props: any) {
        super(props);
        this.TableData = props.TableData;
        this.columns = props.columns;
        this.summary = props.summary;
        this.columnOptions = props.columnOptions;
        this.header = props.header;
        this.onClick = props.onClick;
        this.onDblclick = props.onDblclick;
        this.onMousedown = props.onMousedown;
        this.onMouseover = props.onMouseover;
        this.onMouseout = props.onMouseout;
        this.onFocusChange = props.onFocusChange;
        this.onColumnResize = props.onColumnResize;
        this.onCheck = props.onCheck;
        this.onUncheck = props.onUncheck;
        this.onCheckAll = props.onCheckAll;
        this.onUncheckAll = props.onUncheckAll;
        this.onSelection = props.onSelection;
        this.onEditingStart = props.onEditingStart;
        this.onEditingFinish = props.onEditingFinish;
        this.onSort = props.onSort;
        this.onFilter = props.onFilter;
        this.onScrollEnd = props.onScrollEnd;
        this.onBeforeRequest = props.onBeforeRequest;
        this.onResponse = props.onResponse;
        this.onSuccessResponse = props.onSuccessResponse;
        this.onFailResponse = props.onFailResponse;
        this.onErrorResponse = props.onErrorResponse;
    }

    setOption(props: any) {
        this.TableData = props.TableData;
        this.columns = props.columns;
        this.summary = props.summary;
        this.columnOptions = props.columnOptions;
        this.header = props.header;
    }

    render() {
        return (
            <Grid
                data={this.TableData}
                columns={this.columns}
                onClick={this.onClick}
                onDblclick={this.onDblclick}
                onMousedown={this.onMousedown}
                onMouseover={this.onMouseover}
                onMouseout={this.onMouseout}
                onFocusChange={this.onFocusChange}
                onColumnResize={this.onColumnResize}
                onCheck={this.onCheck}
                onUncheck={this.onUncheck}
                onCheckAll={this.onCheckAll}
                onUncheckAll={this.onUncheckAll}
                onSelection={this.onSelection}
                onEditingStart={this.onEditingStart}
                onEditingFinish={this.onEditingFinish}
                onSort={this.onSort}
                onFilter={this.onFilter}
                onScrollEnd={this.onScrollEnd}
                onBeforeRequest={this.onBeforeRequest}
                onResponse={this.onResponse}
                onSuccessResponse={this.onSuccessResponse}
                onFailResponse={this.onFailResponse}
                onErrorResponse={this.onErrorResponse}
                className="table-center"
                width={1200}
                bodyHeight={500}
                scrollX={true}
                scrollY={true}
                rowHeaders={["rowNum", "checkbox"]}
                summary={this.summary}
                header={this.header}
                columnOptions={this.columnOptions}
            />
        );
    }
}
