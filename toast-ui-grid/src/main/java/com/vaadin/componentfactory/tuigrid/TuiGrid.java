/*-
 * #%L
 * Toast-ui-grid
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

package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.event.SelectionEvent;
import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Toast-ui-grid component definition. Toast-ui-grid uses vis-timeline component to display data in time (see
 * more at https://github.com/toast-ui-grid).
 */

@SuppressWarnings("serial")
@NpmPackage(value = "@types/react", version = "^16.7.0")
@NpmPackage(value = "@types/react-dom", version = "^16.7.0")
@NpmPackage(value = "@toast-ui/react-grid", version = "^4.21.15")
@JsModule("./src/views/toastuigrid/toast-ui-grid-view.ts")
@JsModule("./src/views/toastuigrid/components/Table/FeaturesTable.ts")
@JsModule("./src/views/toastuigrid/components/Table/CustomeEditor.ts")
@CssImport("tui-grid/dist/tui-grid.css")
@CssImport("tui-date-picker/dist/tui-date-picker.css")
@CssImport("tui-time-picker/dist/tui-time-picker.css")
public class TuiGrid extends Div {
    private List<Item> items = new ArrayList<>();
    //    private List<Column> columns = new ArrayList<>();
    protected TuiGridOption tuiGridOption = new TuiGridOption();

    public TuiGrid() {
        setId("visualization" + this.hashCode());
        setWidthFull();
        setClassName("grid");
    }

    public TuiGrid(List<Item> items, List<Column> columns) {
        this();
        this.items = items;
        tuiGridOption.columns = columns;
        initTuiGrid();
    }

    public TuiGrid(List<Item> items, List<Column> columns, List<Summary> summaries) {
        this();
        this.items = items;
        tuiGridOption.columns = columns;
        tuiGridOption.summaryList = summaries;
        initTuiGrid();
    }

    public TuiGrid(List<ComplexColumn> customHeader, List<Item> items, List<Column> columns, List<Summary> summaries) {
        this();
        this.items = items;
        tuiGridOption.columns = columns;
        tuiGridOption.summaryList = summaries;
        tuiGridOption.header = customHeader;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        initTuiGrid();
    }

    public int getHeaderHeight() {
        return tuiGridOption.headerHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        tuiGridOption.headerHeight = headerHeight;
        this.updateTuiGridOptions();
    }

    public void setSummaryHeight(int summaryHeight) {
        tuiGridOption.summaryHeight = summaryHeight;
        this.updateTuiGridOptions();
    }

    public void setvScroll(boolean vScroll) {
        tuiGridOption.vScroll = vScroll;
        this.updateTuiGridOptions();
    }

    public void sethScroll(boolean hScroll) {
        tuiGridOption.hScroll = hScroll;
        this.updateTuiGridOptions();
    }

    public void setFrozenCount(int frozenCount) {
        tuiGridOption.frozenCount = frozenCount;
        this.updateTuiGridOptions();
    }

    public void setFrozenBorderWidth(int frozenBorderWidth) {
        tuiGridOption.frozenBorderWidth = frozenBorderWidth;
        this.updateTuiGridOptions();
    }

    public void setTableWidth(int tableWidth) {
        tuiGridOption.tableWidth = tableWidth;
        this.updateTuiGridOptions();
    }

    public void setTableHeight(int tableHeight) {
        tuiGridOption.tableHeight = tableHeight;
        this.updateTuiGridOptions();
    }

    public void setRowHeaders(List<String> rowHeaders) {
        tuiGridOption.rowHeaders = rowHeaders;
        this.updateTuiGridOptions();
    }

    public void setTreeOption(TreeOption treeOption) {
        tuiGridOption.treeOption = treeOption;
    }

    private void initTuiGrid() {
        this.getElement()
                .executeJs(
                        "toastuigrid.create($0, $1, $2);",
                        this, "[" + convertItemsToJson() + "]",
                        tuiGridOption.toJSON());
    }

    /**
     * Updates tuigrid options after tuigrid creation.
     */
    private void updateTuiGridOptions() {
        if (this.getElement().getNode().isAttached()) {
            this.getElement().executeJs("toastuigrid.setOptions($0, $1)", this, tuiGridOption.toJSON());
        }
    }

    private String convertItemsToJson() {
        return this.items != null
                ? this.items.stream().map(item -> item.toJSON()).collect(Collectors.joining(","))
                : "";
    }

    public void setSelectItem(String colName) {
        fireItemSelectEvent(colName, true);
    }

    protected void fireItemSelectEvent(
            String colName, boolean fromClient) {
        SelectionEvent event = new SelectionEvent(this, colName, 1, fromClient);
        RuntimeException exception = null;

        try {
            fireEvent(event);
        } catch (RuntimeException e) {
            exception = e;
            event.setCancelled(true);
        }
    }//

    @ClientCallable
    public void onClick(String colName, int row) {
        this.getElement()
                .executeJs(
                        "toastuigrid.setTest($0, $1);",
                        this, colName);
    }
}