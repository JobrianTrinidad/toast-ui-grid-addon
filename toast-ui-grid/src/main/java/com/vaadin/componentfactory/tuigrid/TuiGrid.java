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

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Toast-ui-grid component definition. Toast-ui-grid uses vis-timeline component to display data in time (see
 * more at https://github.com/toast-ui-grid).
 */

@JsModule("./src/views/toastuigrid/toast-ui-grid-view.ts")
public class TuiGrid extends Div {
    private List<Music> musics = new ArrayList<>();
    //    private List<Column> columns = new ArrayList<>();
    protected TuiGridOption tuiGridOption = new TuiGridOption();
    private int frozenCount = 2;
    private int frozenBorderWidth = 2;

    public TuiGrid() {
        setId("visualization" + this.hashCode());
        setWidthFull();
        setClassName("grid");
    }

    public TuiGrid(List<Music> musics, List<Column> columns) {
        this();
        this.musics = musics;
        tuiGridOption.columns = columns;
        initTuiGrid();
    }

    public TuiGrid(List<Music> musics, List<Column> columns, List<Summary> summaries) {
        this();
        this.musics = musics;
        tuiGridOption.columns = columns;
        tuiGridOption.summaryList = summaries;
        initTuiGrid();
    }

    public TuiGrid(List<ComplexColumn> customHeader, List<Music> musics, List<Column> columns, List<Summary> summaries) {
        this();
        this.musics = musics;
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

    private void initTuiGrid() {
        this.getElement()
                .executeJs(
                        "toastuigrid.create($0, $1, $2);",
                        this, "[" + convertMusicsToJson() + "]",
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

    private String convertMusicsToJson() {
        return this.musics != null
                ? this.musics.stream().map(music -> music.toJSON()).collect(Collectors.joining(","))
                : "";
    }
}