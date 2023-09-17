import React, {FC} from "react";

interface Option {
    value: string;
    name: string;
}

interface DropDownProps {
    width: string;
    placeholder: string;
    backgroundColor: string;
    border: string;
    outline: string;
    optionGroup: Option[];
}

const DropDown: FC<DropDownProps> = ({
                                         width,
                                         placeholder,
                                         backgroundColor,
                                         border,
                                         outline,
                                         optionGroup,
                                     }) => {
    return (
        <select
            placeholder={placeholder}
            style={{
                width: `${width}`,
                backgroundColor: `${backgroundColor}`,
                border: `${border}`,
                outline: `${outline}`
            }}
        >
            {optionGroup.map((item) => (
                <option value={item.value}>{item.name}</option>
            ))}
        </select>
    );
};

export default DropDown;