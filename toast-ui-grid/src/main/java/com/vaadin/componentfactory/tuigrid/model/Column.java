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
    private ColumnBaseOption columnBaseOption;
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

    public String toJSON() {
        JsonObject js = columnBaseOption.toJSON();

        if (getSortingType() != "") {
            Optional.ofNullable(getSortingType()).ifPresent(v -> js.put("sortingType", v));
            Optional.ofNullable(isSortable()).ifPresent(v -> js.put("sortable", v));
        }
        if (this.isEditable()) {
            JsonObject editableJs = Json.createObject();
            Optional.ofNullable(getType()).ifPresent(v -> editableJs.put("type", String.valueOf(v)));
            if (getType() == "input") {
                JsonObject optionsJs = Json.createObject();
                Optional.ofNullable(getMaxLength()).ifPresent(v -> optionsJs.put("maxLength", v));
                editableJs.put("options", optionsJs);
            } else {
                editableJs.put("options", dateOption.toJSON());
            }
            js.put("editor", editableJs);
        }

        return js.toJson();
    }


    public Column(ColumnBaseOption columnBaseOption) {
        this(columnBaseOption, false, "input", 0);
    }

    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, int maxLength) {
        this(columnBaseOption, editable, type, maxLength, null, "", false);
    }

    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, DateOption option) {
        this(columnBaseOption, editable, type, 0, option, "", false);
    }

    public Column(ColumnBaseOption columnBaseOption, String sortingType, boolean sortable) {
        this(columnBaseOption, false, "", 0, null, sortingType, sortable);
    }

    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, int maxLength,
                  DateOption dateOption,
                  String sortingType, boolean sortable) {
        this.columnBaseOption = columnBaseOption;
        this.editable = editable;
        this.type = type;
        this.maxLength = maxLength;
        this.dateOption = dateOption;
        this.sortingType = sortingType;
        this.sortable = sortable;
    }
}
