import * as React from "react";
import TuiGrid, { GridEventName } from 'tui-grid';

interface GridProps {
    oneTimeBindingProps?: string[];
    [key: string]: any;
}

const reactivePropSetterMap: { [key: string]: string } = {
    data: 'resetData',
    columns: 'setColumns',
    bodyHeight: 'setBodyHeight',
    frozenColumnCount: 'setFrozenColumnCount',
};

export default class Grid extends React.Component<GridProps> {
    rootEl: React.RefObject<HTMLDivElement> = React.createRef();

    gridInst: TuiGrid | null = null;

    bindEventHandlers(props: GridProps) {
        Object.keys(props)
            .filter((key) => /^on[A-Z][a-zA-Z]+/.test(key))
            .forEach((key) => {
                const eventName = key[2].toLowerCase() + key.slice(3) as GridEventName;
                if (this.gridInst) {
                    this.gridInst.off(eventName);
                    this.gridInst.on(eventName, props[key]);
                }
            });
    }

    getInstance() {
        return this.gridInst;
    }

    getRootElement() {
        return this.rootEl.current;
    }

    componentDidMount() {
        const { columns, ...otherProps } = this.props;
        this.gridInst = new TuiGrid({
            el: this.rootEl.current,
            columns,
            ...otherProps,
        });

        this.bindEventHandlers(this.props);
    }

    shouldComponentUpdate(nextProps: GridProps) {
        const { oneTimeBindingProps = [] } = this.props;
        const reactiveProps = Object.keys(reactivePropSetterMap).filter(
            (propName) => oneTimeBindingProps.indexOf(propName) === -1
        );

        reactiveProps.forEach((propName) => {
            const currentValue = this.props[propName];
            const nextValue = nextProps[propName];
            if (currentValue !== nextValue && this.gridInst) {
                const setterName = reactivePropSetterMap[propName];
                this.gridInst[setterName](nextValue);
            }
        });

        this.bindEventHandlers(nextProps);

        return false;
    }

    componentWillUnmount() {
        if (this.gridInst) {
            this.gridInst.destroy();
        }
    }

    render() {
        return <div ref={this.rootEl} />;
    }
}