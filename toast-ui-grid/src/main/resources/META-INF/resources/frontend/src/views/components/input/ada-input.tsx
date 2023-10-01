import React, {ChangeEvent, createRef} from "react";
import ReactDOMServer from "react-dom/server";
import {Input} from "@chakra-ui/react";
import {CellEditor, CellEditorProps} from 'tui-grid/types/editor';
import {HTMLChakraProps} from "@chakra-ui/system";
import {InputProps} from "@chakra-ui/input/dist/input";

export interface InputComponentProps extends InputProps, CellEditorProps {
    container: any;
    value: string;
    handleChange: (event: ChangeEvent<HTMLInputElement>) => void;
    columnInfo: {
        editor: {
            maxLength?: number
            backgroundColor: string,
            opacity: number,
            width: number | string;
            height: number | string;
            border: string;
            outline: string;
            butBackground: string;
            size: any;
        }
    },

}

class InputComponent implements CellEditor {
    public el: ChildNode;
    private props: InputComponentProps;

    constructor(props: InputComponentProps) {
        this.props = props;
        this.el = this.renderInput();
    }

    getElement(): ChildNode {
        return this.el;
    }

    getValue(): string {
        return (this.el as HTMLInputElement).value;
    }

    mounted(): void {
        (this.el as HTMLInputElement).select();
    }

    renderInput(): ChildNode {
        const {
            value,
            handleChange,
            columnInfo: {
                editor: {
                    maxLength,
                    backgroundColor,
                    opacity,
                    border,
                    width,
                    height,
                    size,
                    outline,
                    butBackground
                }
            },
        } = this.props;

        const inputRef = createRef<HTMLInputElement>();
        const inputElement = (
            <Input
                ref={inputRef}
                defaultValue={value?.toString()}
                type="text"
                maxLength={maxLength}
                onChange={handleChange}
                size={size}
                style={{
                    backgroundColor: backgroundColor,
                    opacity: opacity ? opacity : 1,
                    width: width,
                    height: height,
                    border: border,
                    outline: outline,
                }}
                _focusVisible={{outline: "none"}}
                _hover={{outline: "none"}}
            />
        );
        const parser: DOMParser = new DOMParser();
        const doc: Document = parser.parseFromString(ReactDOMServer.renderToString(inputElement), 'text/html');
        const parentElement: ParentNode = doc.body.firstChild.parentNode;
        return doc.body.firstChild;
    }
}

export default InputComponent;

