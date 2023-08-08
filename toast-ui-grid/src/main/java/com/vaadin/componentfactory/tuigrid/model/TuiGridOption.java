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
    public enum SummaryPosition {
        top,
        bottom
    }

    /* Minimum Date for the visible range.*/
    public List<Column> columns;

    /* Maximum Date for the visible range (excluded).*/
    public List<ComplexColumn> header;
    public int headerHeight = 0;
    public List<Summary> summaryList;
    public int summaryHeight = 0;
    public SummaryPosition pos = SummaryPosition.bottom;
    public int frozenCount = 0;
    public int frozenBorderWidth = 1;
    public boolean vScroll = false;
    public boolean hScroll = false;
    public int tableWidth = 1200;
    public int tableHeight = 500;


    public String toJSON() {
        JsonObject js = Json.createObject();
        Optional.ofNullable(convertColumnsToJson()).ifPresent(v -> js.put("columns", "[" + v + "]"));
        Optional.ofNullable(convertHeaderToJson()).ifPresent(v -> js.put("header", v));
        js.put("width", tableWidth);
        js.put("bodyHeight", tableHeight);
        js.put("scrollX", vScroll);
        js.put("scrollY", hScroll);
        if (header != null) {
            JsonObject headerJs = Json.createObject();
            if (headerHeight != 0) {
                headerJs.put("height", headerHeight);
            }
//            headerJs.put("height", 100);
            Optional.ofNullable(convertHeaderToJson()).ifPresent(v -> headerJs.put("columnContent", "[" + v + "]"));
            js.put("header", headerJs);
        }

        if (frozenCount > 0) {
            JsonObject columnOptionsJs = Json.createObject();
            columnOptionsJs.put("frozenCount", frozenCount);
            columnOptionsJs.put("frozenBorderWidth", frozenBorderWidth);
            js.put("columnOptions", columnOptionsJs);
        }

        if (summaryList != null) {
            JsonObject summaryJs = Json.createObject();
            if (summaryHeight != 0)
                summaryJs.put("height", summaryHeight);
            else
                summaryJs.put("height", 40);
            summaryJs.put("position", pos.name());
            Optional.ofNullable(convertSummaryToJson()).ifPresent(v -> summaryJs.put("columnContent", "[" + v + "]"));
            js.put("summary", summaryJs);
        }

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

    private String convertSummaryToJson() {
        return this.summaryList != null
                ? this.summaryList.stream().map(header -> header.toJSON()).collect(Collectors.joining(","))
                : "";
    }
}