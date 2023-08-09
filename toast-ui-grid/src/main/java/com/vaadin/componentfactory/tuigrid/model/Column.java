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
    private DateOption dateOption;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
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

    public DateOption getDateOption() {
        return dateOption;
    }

    public void setDateOption(DateOption dateOption) {
        this.dateOption = dateOption;
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
            if (getType() == "input") {
                JsonObject optionsJs = Json.createObject();
                Optional.ofNullable(getMaxLength()).ifPresent(v -> optionsJs.put("maxLength", v));
                editableJs.put("options", optionsJs);
            }
            else {
                editableJs.put("options", dateOption.toJSON());
            }
            js.put("editor", editableJs);
        }

        return js.toJson();
    }


    public Column(int id, String headerName, String name, int width, String align) {
        this(id, headerName, name, width, align, "", false, "input", 0);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, boolean editable, String type, int maxLength) {
        this(id, headerName, name, width, align, className,
                editable, type, maxLength, null, "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, boolean editable, String type, DateOption option) {
        this(id, headerName, name, width, align, className,
                editable, type, 0, option, "", false);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, String sortingType, boolean sortable) {
        this(id, headerName, name, width, align, className,
                false, "", 0, null, sortingType, sortable);
    }

    public Column(int id, String headerName, String name, int width, String align,
                  String className, boolean editable, String type, int maxLength,
                  DateOption dateOption,
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
        this.dateOption = dateOption;
        this.sortingType = sortingType;
        this.sortable = sortable;
    }
}
