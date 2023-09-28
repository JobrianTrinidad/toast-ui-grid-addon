import React, { createRef } from "react";
import ReactDOMServer from "react-dom/server";
import { Input } from "@chakra-ui/react";
import { CellEditor, CellEditorProps } from "tui-grid/types/editor";
import { HTMLChakraProps } from "@chakra-ui/system";
import { InputProps } from "@chakra-ui/input/dist/input";

interface InputComponentProps extends InputProps, CellEditorProps {
    value: string;
    options?: { maxLength?: number };
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    backgroundColor?: string;
    width?: number;
    height?: number;
    border?: string;
    outline?: string;
    butBackground?: string;
    opacity?: any;
    size?: any;
}

class InputComponent extends React.Component<InputComponentProps> implements CellEditor {
    private el: ChildNode;
    inputRef: React.RefObject<HTMLInputElement>;

    constructor(props: InputComponentProps) {
        super(props);
        this.el = this.renderInput();
        this.inputRef = createRef<HTMLInputElement>();
    }

    getElement(): ChildNode {
        return this.el;
    }

    getValue(): string {
        return this.inputRef.current?.value || "";
    }

    componentDidMount(): void {
        this.inputRef.current?.select();
    }

    renderInput(): ChildNode {
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

        const inputElement = (
            <Input
                ref={this.inputRef}
                defaultValue={value?.toString()}
                type="text"
                maxLength={options?.maxLength}
                onChange={handleChange}
                size={size}
                style={{
                    backgroundColor: "#66878859",
                    opacity,
                    width: "90%",
                    height: height,
                    border: "1px solid #326f70",
                    outline: "none",
                }}
                _focusVisible={{ outline: "none" }}
                _hover={{ outline: "none" }}
            />
        );

        const parser:DOMParser = new DOMParser();
        const doc:Document = parser.parseFromString(
            ReactDOMServer.renderToString(inputElement),
            "text/html"
        );
        const parentElement = doc.body.firstChild.parentNode;
        return doc.body.firstChild;
    }

    render(): ChildNode {
        return this.el;
    }
}

export default InputComponent;