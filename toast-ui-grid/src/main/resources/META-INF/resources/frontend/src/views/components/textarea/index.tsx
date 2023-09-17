import React, { ChangeEvent } from "react";
import { Textarea, TextareaProps, border } from "@chakra-ui/react";

interface TextareaComponentProps extends Omit<TextareaProps, "value" | "onChange"> {
  value: string;
  handleInputChange: (event: ChangeEvent<HTMLTextAreaElement>) => void;
}

function TextareaComponent({
  value,
  handleInputChange,
  placeholder,
  size,
  width,
  backgroundColor,
  border
}: TextareaComponentProps) {
  return (
    <Textarea
      value={value}
      onChange={handleInputChange}
      placeholder={placeholder}
      size={size}
      width={width}
      backgroundColor={backgroundColor}
      border={border}
      _focusVisible={{outline: 'none'}}
      _hover={{outline: 'none'}}
    />
  );
}

export default TextareaComponent;