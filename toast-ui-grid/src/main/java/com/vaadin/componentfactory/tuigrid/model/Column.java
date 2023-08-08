package com.vaadin.componentfactory.tuigrid.model;
/*-
 * #%L
 * TuiGrid
 * %%
 * Copyright (C) 2021 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import elemental.json.Json;
import elemental.json.JsonObject;

import java.util.Objects;
import java.util.Optional;

public class Column {
    private int id;
    private String headerName;
    private String name;
    private int width;
    private String align;
    private String className;
    private boolean editable = false;
    private String type;
    private int maxLength;
    private String format;
    private boolean timepicker = false;
    private int fromYear;
    private int fromMonth;
    private int fromDay;
    private int toYear;
    private int toMonth;
    private int toDay;
    private String layoutType;
    private String inputType;
    private String optionType;
    private String sortingType;
    private boolean sortable;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public String getType() {
        return type;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTimepicker() {
        return timepicker;
    }

    public void setTimepicker(boolean timepicker) {
        this.timepicker = timepicker;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    public int getFromDay() {
        return fromDay;
    }

    public void setFromDay(int fromDay) {
        this.fromDay = fromDay;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public int getToMonth() {
        return toMonth;
    }

    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    public int getToDay() {
        return toDay;
    }

    public void setToDay(int toDay) {
        this.toDay = toDay;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Column other = (Column) obj;
        return Objects.equals(id, other.id);
    }

    public String toJSON() {
        JsonObject js = Json.createObject();
        Optional.ofNullable(getId()).ifPresent(v -> js.put("id", v));
        Optional.ofNullable(getName()).ifPresent(v -> js.put("name", v));
        Optional.ofNullable(getHeaderName()).ifPresent(v -> js.put("headerName", v));
        Optional.ofNullable(getWidth()).ifPresent(v -> js.put("width", v));
        Optional.ofNullable(getAlign()).ifPresent(v -> js.put("align", v));
        Optional.ofNullable(getClassName()).ifPresent(v -> js.put("className", v));
        if (getSortingType() != "") {
            Optional.ofNullable(getSortingType()).ifPresent(v -> js.put("sortingType", v));
            Optional.ofNullable(isSortable()).ifPresent(v -> js.put("sortable", v));
        }
        if (this.editable) {
            JsonObject editableJs = Json.createObject();
            Optional.ofNullable(getType()).ifPresent(v -> editableJs.put("type", String.valueOf(v)));
            JsonObject optionsJs = Json.createObject();
            if (getType() == "input")
                Optional.ofNullable(getMaxLength()).ifPresent(v -> optionsJs.put("maxLength", v));
            else {
                Optional.ofNullable(getFormat()).ifPresent(v -> optionsJs.put("format", v));

                if (getFromYear() != 0) {
                    Optional.ofNullable(getFromYear()).ifPresent(v -> optionsJs.put("fromYear", v));
                    Optional.ofNullable(getFromMonth()).ifPresent(v -> optionsJs.put("fromMonth", v));
                    Optional.ofNullable(getFromDay()).ifPresent(v -> optionsJs.put("fromDay", v));
                    Optional.ofNullable(getToYear()).ifPresent(v -> optionsJs.put("toYear", v));
                    Optional.ofNullable(getToMonth()).ifPresent(v -> optionsJs.put("toMonth", v));
                    Optional.ofNullable(getToDay()).ifPresent(v -> optionsJs.put("toDay", v));
                }

                if (getLayoutType() == "")
                    Optional.ofNullable(isTimepicker()).ifPresent(v -> optionsJs.put("timepicker", v));
                else {
                    JsonObject timepickerJs = Json.createObject();
                    Optional.ofNullable(getLayoutType()).ifPresent(v -> timepickerJs.put("layoutType", v));
                    Optional.ofNullable(getInputType()).ifPresent(v -> timepickerJs.put("inputType", v));
                    optionsJs.put("timepicker", timepickerJs);
                }
                if (getOptionType() != "")
                    Optional.ofNullable(getOptionType()).ifPresent(v -> optionsJs.put("type", v));

            }
            editableJs.put("options", optionsJs);
            js.put("editor", editableJs);
        }

        return js.toJson();
    }


    public Column(int id, String headerName, String name, int width, String align) {
        this(id, headerName, name, width, align, "", false, "input", 0);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, boolean editable, String type, int maxLength) {
        this(id, headerName, name, width, align, className, editable, type, maxLength,
                "", false, 0, 0, 0,
                0, 0, 0, "", "", "", "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, boolean editable, String type, String format, boolean timepicker) {
        this(id, headerName, name, width, align, className, editable, type, 0,
                format, timepicker, 0, 0, 0,
                0, 0, 0, "", "", "", "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, String sortingType, boolean sortable) {
        this(id, headerName, name, width, align, className, false, "", 0,
                "", false, 0, 0, 0,
                0, 0, 0, "", "", "", sortingType, sortable);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  boolean editable, String type,
                  int fromYear, int fromMonth, int fromDay,
                  int toYear, int toMonth, int toDay) {
        this(id, headerName, name, width, align, "", editable, type, 0,
                "", false, fromYear, fromMonth, fromDay,
                toYear, toMonth, toDay, "", "", "", "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  boolean editable, String type, String format,
                  boolean timepicker, String layoutType, String inputType) {
        this(id, headerName, name, width, align, "", editable, type, 0,
                format, timepicker, 0, 0, 0,
                0, 0, 0, layoutType, inputType, "", "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  boolean editable, String type, String format, String optionType) {
        this(id, headerName, name, width, align, "", editable, type, 0,
                format, false, 0, 0, 0,
                0, 0, 0, "", "", optionType, "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, boolean editable, String type, int maxLength,
                  String format, boolean timepicker,
                  int fromYear, int fromMonth, int fromDay,
                  int toYear, int toMonth, int toDay,
                  String layoutType, String inputType, String optionType,
                  String sortingType, boolean sortable) {
        this.id = id;
        this.headerName = headerName;
        this.name = name;
        this.width = width;
        this.align = align;
        this.className = className;
        this.editable = editable;
        this.type = type;
        this.maxLength = maxLength;
        this.format = format;
        this.timepicker = timepicker;
        this.fromYear = fromYear;
        this.fromMonth = fromMonth;
        this.fromDay = fromDay;
        this.toYear = toYear;
        this.toMonth = toMonth;
        this.toDay = toDay;
        this.layoutType = layoutType;
        this.inputType = inputType;
        this.optionType = optionType;
        this.sortingType = sortingType;
        this.sortable = sortable;
    }
}
