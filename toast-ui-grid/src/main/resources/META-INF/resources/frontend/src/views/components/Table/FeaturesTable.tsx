import Grid from '@toast-ui/react-grid';
import React from "react";

export class FeatureTable extends React.Component<any, any> {
    TableData;
    columns;
    summary;
    columnOptions;
    header;
    width;
    bodyHeight;
    scrollX;
    scrollY;
    rowHeaders;
    treeColumnOptions;

    constructor(props: any) {
        super(props);
        this.setOption(props);
    }

    setOption(props: any) {
        this.TableData = props.TableData;
        this.columns = props.columns;
        this.summary = props.summary;
        this.columnOptions = props.columnOptions;
        this.header = props.header;
        this.width = props.width;
        this.bodyHeight = props.bodyHeight;
        this.scrollX = props.scrollX;
        this.scrollY = props.scrollY;
        this.rowHeaders = props.rowHeaders;
        this.treeColumnOptions = props.treeColumnOptions;
    }

    render() {
        return (
            <Grid
                data={this.TableData}
                columns={this.columns}
                {...(this.treeColumnOptions && {treeColumnOptions: this.treeColumnOptions})}
                className="table-center"
                width={this.width}
                bodyHeight={this.bodyHeight}
                scrollX={this.scrollX}
                scrollY={this.scrollY}
                {...(this.rowHeaders && {rowHeaders: this.rowHeaders})}
                {...(this.summary && {summary: this.summary})}
                {...(this.header && {header: this.header})}
                {...(this.columnOptions && {columnOptions: this.columnOptions})}
            />
        );
    }
}
