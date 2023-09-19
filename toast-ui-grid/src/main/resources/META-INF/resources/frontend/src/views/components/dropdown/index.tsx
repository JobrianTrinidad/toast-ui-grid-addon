import React, {Component, createRef} from "react";
import ReactDOMServer from "react-dom/server";
import {CellEditor} from 'tui-grid/types/editor';
import {Select} from "@chakra-ui/react";

interface Option {
    value: string;
    name: string;
}

interface DropDownProps {
    width: string;
    height: any,
    value: any,
    placeholder: string;
    backgroundColor: string;
    border: string;
    outline: string;
    columnInfo: { editor: { options: { listItems: any } } };
}

class DropDown implements CellEditor {
    public el: HTMLElement;
    private props: DropDownProps;

    constructor(props: DropDownProps) {
        this.props = props;
        this.el = this.renderDropDown();
    }

    getElement(): HTMLElement {
        return this.el;
    }

    getValue() {
        return (this.el as HTMLSelectElement).value;
    }

    mounted() {
        const selectElement = this.el as HTMLSelectElement;
        const { columnInfo } = this.props;
        const { listItems } = columnInfo.editor.options;

        // Find the index of the item with the desired value
        const selectedIndex = listItems.findIndex(item => item.value === selectElement.value);

        // Set the selectedIndex to the desired value
        selectElement.selectedIndex = this.props.value;
    }

    renderDropDown() {
        const {
            width,
            height,
            value: value,
            placeholder,
            backgroundColor,
            border,
            outline,
            columnInfo,
        } = this.props;
        const inputRef = createRef<HTMLSelectElement>();
        const inputElement = (
                // <select
                //     ref={inputRef}
                //     placeholder={placeholder}
                //     style={{
                //         width: `50%`,
                //         height: `100%`,
                //         backgroundColor: `#66878859`,
                //         border: '1px solid #326f70',
                //         outline: `none`
                //     }}
                // >
                //     {columnInfo.editor.options.listItems.map((item) => (
                //         <option key={item.value} value={item.value}>{item.text}</option>
                //     ))}
                // </select>

                <Select
                    ref={inputRef}
                    placeholder=""
                    borderRadius={"5px"}
                    boxShadow={"none !important"}
                    style={{
                        width: `90%`,
                        height: height,
                        backgroundColor: `#66878859`,
                        border: '1px solid #326f70',
                        outline: `none`
                    }}
                    // margin={"0 0 8px"}
                >
                    {
                        columnInfo.editor.options.listItems.map((item) => (
                            <option key={item.value} value={item.value}>
                                {item.text}
                            </option>
                        ))
                    }
                </Select>
            )
        ;
        const parser = new DOMParser();
        const doc = parser.parseFromString(ReactDOMServer.renderToString(inputElement), 'text/html');
        const parentNode = doc.body.firstChild as HTMLElement;
        parentNode.style.display = 'flex';
        return doc.body.firstChild.firstChild as HTMLElement;
    }
}

export default DropDown;