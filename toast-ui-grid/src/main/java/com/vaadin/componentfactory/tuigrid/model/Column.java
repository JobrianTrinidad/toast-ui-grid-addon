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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Column {

    private ColumnBaseOption columnBaseOption;
    private boolean editable = false;
    private String type;
    private int maxLength;
    private DateOption dateOption;
    private List<RelationOption> relationOptions;
    private String target;
    private boolean root;
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

    public ColumnBaseOption getColumnBaseOption() {
        return columnBaseOption;
    }

    public void setColumnBaseOption(ColumnBaseOption columnBaseOption) {
        this.columnBaseOption = columnBaseOption;
    }

    public List<RelationOption> getRelationOptions() {
        return relationOptions;
    }

    public void setRelationOptions(List<RelationOption> relationOptions) {
        this.relationOptions = relationOptions;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String toJSON(boolean bResizable) {
        JsonObject js = columnBaseOption.toJSON();
        if (!bResizable)
            js.put("resizable", false);
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
            } else if (getType() == "select") {
                this.toRelationJSON(js, this.relationOptions);
                js.put("targetNames", this.getTarget());
            } else {
                editableJs.put("options", dateOption.toJSON());
            }
            js.put("editor", editableJs);
        }
//        }
        return js.toJson();
    }

    String toRelationJSON(JsonObject js, List<RelationOption> relationOptions) {
        RelationOption select = new RelationOption("Select", "");
//        if (relationOptions.get(0).getChildren().size() > 0) {
        if (relationOptions.get(0).getChildren().size() > 0) {
            JsonObject tempJs = Json.createObject();

            for (RelationOption relationOption : relationOptions) {
                if (relationOption.getName() != "Select")
                    tempJs.put(relationOption.getValue(), "[" + select.toSelfJSON() + "," + relationOption.toJSON() + "]");
            }

            js.put("depth1", tempJs);
        } else {
            js.put("depth1", "[]");
        }

        if (isRoot())
            js.put("depth0", "[" + select.toSelfJSON() + "," + this.convertRelationOptionsToJson() + "]");
        else
            js.put("depth0", "[]");

        return js.toJson();
    }

    private String convertRelationOptionsToJson() {
        return this.relationOptions != null
                ? this.relationOptions.stream().map(relationOption -> relationOption.toSelfJSON()).collect(Collectors.joining(","))
                : "";
    }

    /**
     * This is a constructor for creating a column object with the column base option, editable, type, and maximum length specified.
     *
     * @param columnBaseOption the ColumnBaseOption to be set
     * @param editable         the boolean value whether cell is edit enabled
     * @param type             the string value whether cell is type of input, or date...
     * @param root             the boolean whether cell is root of relation
     * @param target           the string value to be set child column of this column
     * @param relationOptions  the RelationOption List value to be set list of RelationOption
     */
    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, boolean root, String target, List<RelationOption> relationOptions) {
        this.columnBaseOption = columnBaseOption;
        this.editable = editable;
        this.type = type;
        this.root = root;
        this.target = target;
        this.relationOptions = relationOptions;
    }

    /**
     * This is a constructor for creating a column object with only the column base option specified.
     *
     * @param columnBaseOption the ColumnBaseOption to be set
     */
    public Column(ColumnBaseOption columnBaseOption) {
        this(columnBaseOption, false, "input", 0);
    }

    /**
     * This is a constructor for creating a column object with the column base option, editable, type, and maximum length specified.
     *
     * @param columnBaseOption the ColumnBaseOption to be set
     * @param editable         the boolean value whether cell is edit enabled
     * @param type             the string value whether cell is type of input, or date...
     * @param maxLength        the letter length to be set
     */
    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, int maxLength) {
        this(columnBaseOption, editable, type, maxLength, null, "", false);
    }

    /**
     * This is a constructor for creating a column object with the column base option, editable, type, and DateOption specified.
     *
     * @param columnBaseOption the ColumnBaseOption to be set
     * @param editable         the boolean value whether cell is edit enabled
     * @param type             the string value whether cell is type of input, or date...
     * @param option           the DateOption value when cell is type of date
     */
    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, DateOption option) {
        this(columnBaseOption, editable, type, 0, option, "", false);
    }

    /**
     * This is a constructor for creating a column object with the column base option, sorting type, and sortable specified.
     *
     * @param columnBaseOption the ColumnBaseOption to be set
     * @param sortingType      the string value whether is asc or dec
     * @param sortable         the boolean value whether cell is sortable
     */
    public Column(ColumnBaseOption columnBaseOption, String sortingType, boolean sortable) {
        this(columnBaseOption, false, "", 0, null, sortingType, sortable);
    }

    /**
     * This is a constructor for creating a column object with all the specified properties.
     *
     * @param columnBaseOption the ColumnBaseOption to be set
     * @param editable         the boolean value whether cell is edit enabled
     * @param type             the string value whether cell is type of input, or date...
     * @param maxLength        the letter length to be set
     * @param dateOption       the DateOption value when cell is type of date
     * @param sortingType      the string value whether is asc or dec
     * @param sortable         the boolean value whether cell is sortable
     */
    public Column(ColumnBaseOption columnBaseOption, boolean editable, String type, int maxLength, DateOption dateOption, String sortingType, boolean sortable) {
        this.columnBaseOption = columnBaseOption;
        this.editable = editable;
        this.type = type;
        this.maxLength = maxLength;
        this.dateOption = dateOption;
        this.sortingType = sortingType;
        this.sortable = sortable;
    }
}
