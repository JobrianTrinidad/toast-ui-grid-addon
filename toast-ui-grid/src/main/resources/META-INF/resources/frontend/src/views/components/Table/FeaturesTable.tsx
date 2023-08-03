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

    constructor(props: any) {
        super(props);
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
                onCheck={select}
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
