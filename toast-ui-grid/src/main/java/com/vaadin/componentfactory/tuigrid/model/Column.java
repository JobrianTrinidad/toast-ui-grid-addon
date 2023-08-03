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

import com.vaadin.flow.component.notification.Notification;
import elemental.json.Json;
import elemental.json.JsonObject;
import jakarta.persistence.GeneratedValue;

import java.util.Objects;
import java.util.Optional;

public class Column {
    public enum Type {
        input,
        datePicker
    };

    public enum Options{
        maxLength,
        format
    };
    private long Id;
    private String headerName;
    private String name;
    private String width;
    private String align;
    private String className;
    private String sortingType;
    private boolean sortable;
    private Type type;
    private Options options;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Column other = (Column) obj;
        return Objects.equals(Id, other.Id);
    }
    public String toJSON() {
        JsonObject js = Json.createObject();
        Optional.ofNullable(getId()).ifPresent(v -> js.put("id", v));
        Optional.ofNullable(getName()).ifPresent(v -> js.put("name", v));
        Optional.ofNullable(getHeaderName()).ifPresent(v -> js.put("headerName", v.toString()));
        Optional.ofNullable(getWidth()).ifPresent(v -> js.put("width", v.toString()));
        Optional.ofNullable(getAlign()).ifPresent(v -> js.put("align", v));
        Optional.ofNullable(getClassName()).ifPresent(v -> js.put("className", v));
        Optional.ofNullable(getSortingType()).ifPresent(v -> js.put("sortingType", v));
        Optional.ofNullable(isSortable()).ifPresent(v -> js.put("sortable", v));
        Optional.ofNullable(getType()).ifPresent(v -> js.put("type", String.valueOf(v)));
        Optional.ofNullable(getOptions()).ifPresent(v -> js.put("options", String.valueOf(v)));

        return js.toJson();
    }
    public Column() {

    }
}
