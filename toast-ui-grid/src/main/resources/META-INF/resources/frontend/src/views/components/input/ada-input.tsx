import React, {ChangeEvent, createRef} from "react";
import ReactDOMServer from "react-dom/server";
import {Input} from "@chakra-ui/react";
import {CellEditor, CellEditorProps} from 'tui-grid/types/editor';
import {HTMLChakraProps} from "@chakra-ui/system";
import {InputProps} from "@chakra-ui/input/dist/input";

interface InputComponentProps extends InputProps, CellEditorProps {
    container: any;
    options?: { maxLength?: number };
    value: string;
    handleChange: (event: ChangeEvent<HTMLInputElement>) => void;
    backgroundColor: string;
    width: number;
    height: number;
    border: string;
    outline: string;
    butBackground: string;
    opacity: any;
    size: any;
}

class InputComponent implements CellEditor {
    public el: ChildNode;
    private props: InputComponentProps;

    constructor(props: InputComponentProps) {
        this.props = props;
        this.el = this.renderInput();
    }

    getElement() {
        return this.el;
    }

    getValue() {
        return (this.el as HTMLInputElement).value;
    }

    mounted() {
        (this.el as HTMLInputElement).select();
    }

    renderInput() {
        const {
            value,
            handleChange,
            backgroundColor,
            opacity,
            width,
            height,
            size,
            border,
            options,
        } = this.props;

        const inputRef = createRef<HTMLInputElement>();

        const inputElement = (
            <Input
                ref={inputRef}
                defaultValue={value?.toString()}
                type="text"
                maxLength={options?.maxLength}
                // value={value}
                onChange={handleChange}
                size={size}
                style={{
                    backgroundColor: '#66878859',
                    opacity,
                    width: '90%',
                    height: height,
                    border: '1px solid #326f70',
                    outline: "none",
                }}
                _focusVisible={{outline: "none"}}
                _hover={{outline: "none"}}
            />
        );
        const parser = new DOMParser();
        const doc = parser.parseFromString(ReactDOMServer.renderToString(inputElement), 'text/html');
        const parentElement = doc.body.firstChild.parentNode;
        return doc.body.firstChild;
    }
}

export default InputComponent;