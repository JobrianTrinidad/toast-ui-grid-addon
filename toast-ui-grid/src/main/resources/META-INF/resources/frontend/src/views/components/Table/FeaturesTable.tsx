import Grid from '@toast-ui/react-grid';
import * as React from "react";

interface ErrorBoundaryState {
    hasError: boolean;
}

class ErrorBoundary extends React.Component<{}, ErrorBoundaryState> {
    constructor(props: any) {
        super(props);
        this.state = {hasError: false};
    }

    componentDidCatch(error: Error, errorInfo: React.ErrorInfo) {
        // Log the error or perform any necessary actions
        console.error(error, errorInfo);
        this.setState({hasError: true});
    }

    render() {
        if (this.state.hasError == true) {
            // Render a fallback UI when an error occurs
            return <div>Something went wrong.</div>;
        }

        // Render the children components normally
        return this.props.children;
    }
}

export class FeatureTable extends React.Component<any, any> {
    TableData: any;
    columns: any;
    summary: any;
    columnOptions: any;
    header: any;
    width: any;
    bodyHeight: any;
    scrollX: any;
    scrollY: any;
    rowHeaders: any;
    treeColumnOptions: any;
    rowHeight: any;
    minBodyHeight: any;
    el: any;
    private onEditingStart: any;
    private onEditingFinish: any;

    constructor(props: any) {
        super(props);
        this.setOption(props);
    }

    setOption(props: any) {
        this.el = props.el;
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
        this.rowHeight = props.rowHeight;
        this.minBodyHeight = props.minBodyHeight;
        this.onEditingStart = props.onEditingStart;
        this.onEditingFinish = props.onEditingFinish;
    }

    render(): JSX.Element {
        return (
            <ErrorBoundary>
                <div>
                    <Grid
                        {...(this.el && {el: this.el})}
                        data={this.TableData}
                        columns={this.columns}
                        {...(this.treeColumnOptions && {treeColumnOptions: this.treeColumnOptions})}
                        className="table-center"
                        {...(this.width && {width: this.width})}
                        bodyHeight={this.bodyHeight}
                        {...(this.rowHeight && {rowHeight: this.rowHeight})}
                        {...(this.minBodyHeight && {minBodyHeight: this.minBodyHeight})}
                        scrollX={this.scrollX}
                        scrollY={this.scrollY}
                        {...(this.rowHeaders && {rowHeaders: this.rowHeaders})}
                        {...(this.summary && {summary: this.summary})}
                        {...(this.header && {header: this.header})}
                        {...(this.columnOptions && {columnOptions: this.columnOptions})}
                        onEditingStart={this.onEditingStart}
                        onEditingFinish={this.onEditingFinish}
                    />
                </div>
            </ErrorBoundary>
        );
    }


}
