package com.vaadin.componentfactory.tuigrid.model;

/*-
 * #%L
 * Timeline
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Representation of the different options that can be configure for a {@link com.vaadin.componentfactory.tuigrid.TuiGrid} component.
 */
public class TuiGridOption {

    /* Minimum Date for the visible range.*/
    public List<Column> columns;

    /* Maximum Date for the visible range (excluded).*/
    public List<ComplexColumn> header;
    public int frozenCount = 2;
    public int frozenBorderWidth = 2;

    public String toJSON() {
        JsonObject js = Json.createObject();
        Optional.ofNullable(convertColumnsToJson()).ifPresent(v -> js.put("columns", "[" + v + "]"));
        Optional.ofNullable(convertHeaderToJson()).ifPresent(v -> js.put("header", v));
        JsonObject columnOptionsJs = Json.createObject();
        columnOptionsJs.put("frozenCount", frozenCount);
        columnOptionsJs.put("frozenBorderWidth", frozenBorderWidth);
        js.put("columnOptions", columnOptionsJs);

        return js.toJson();
    }

    private String convertColumnsToJson() {
        return this.columns != null
                ? this.columns.stream().map(column -> column.toJSON()).collect(Collectors.joining(","))
                : "";
    }

    private String convertHeaderToJson() {
        return this.header != null
                ? this.header.stream().map(header -> header.toJSON()).collect(Collectors.joining(","))
                : "";
    }
}