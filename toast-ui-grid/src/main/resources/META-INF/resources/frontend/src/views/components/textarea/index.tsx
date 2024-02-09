import React, {ChangeEvent, createRef} from "react";
import {Textarea, TextareaProps} from "@chakra-ui/react";
import ReactDOMServer from "react-dom/server";
import {CellEditor, CellEditorProps} from 'tui-grid/types/editor';
import TuiGrid, {ColumnInfo} from 'tui-grid';

interface TextareaComponentProps extends TextareaProps, CellEditorProps {
    grid: TuiGrid & { usageStatistics: boolean };
    value: string;
    handleInputChange: (event: ChangeEvent<HTMLTextAreaElement>) => void;
    columnInfo: ColumnInfo;
}

class TextareaComponent implements CellEditor {
    public el: ChildNode;
    private props: TextareaComponentProps;

    constructor(props: TextareaComponentProps) {
        this.props = props;
        this.el = this.renderInput();
    }

    getElement(): ChildNode {
        return this.el;
    }

    getValue(): string {
        return (this.el as HTMLTextAreaElement).value;
    }

    mounted(): void {
        (this.el as HTMLTextAreaElement).select();
    }

    renderInput(): ChildNode {
        const {
            value,
            handleInputChange,
            columnInfo
        } = this.props;

        console.log("props: ", this.props);

        const inputRef = createRef<HTMLTextAreaElement>();
        const inputElement = (
            <textarea
                ref={inputRef}
                defaultValue={value?.toString()}
                onChange={(): void => {
                    console.log("inputRef: ", inputRef);
                    const textarea = inputRef.current;
                    if (textarea) {
                        textarea.style.height = "auto"; // Reset the height
                        textarea.style.height = `${textarea.scrollHeight}px`; // Set the height to the scroll height
                    }
                }}
                style={{
                    backgroundColor: columnInfo.editor.options.backgroundColor,
                    opacity: columnInfo.editor.options.opacity,
                    width: columnInfo.editor.options.width,
                    border: columnInfo.editor.options.border,
                    outline: columnInfo.editor.options.outline,
                }}
                onKeyUp={(): void => {
                    console.log("inputRef: ", inputRef);
                    const textarea = inputRef.current;
                    if (textarea) {
                        textarea.style.height = "auto"; // Reset the height
                        textarea.style.height = `${textarea.scrollHeight}px`; // Set the height to the scroll height
                    }
                }}
            />
        );
        console.log("inputElement: ", inputElement);
        const parser: DOMParser = new DOMParser();
        const doc: Document = parser.parseFromString(ReactDOMServer.renderToString(inputElement), 'text/html');
        const parentElement: ParentNode = doc.body.firstChild.parentNode;
        return doc.body.firstChild;
    }
}

export default TextareaComponent;
