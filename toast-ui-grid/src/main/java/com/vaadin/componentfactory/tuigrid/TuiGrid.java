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

import com.vaadin.componentfactory.tuigrid.model.Music;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Toast-ui-grid component definition. Toast-ui-grid uses vis-timeline component to display data in time (see
 * more at https://github.com/visjs/vis-timeline).
 */
@SuppressWarnings("serial")
//@NpmPackage(value = "vis-timeline", version = "7.4.9")
//@NpmPackage(value = "moment", version = "2.29.1")

@JsModule("./src/views/toastuigrid/toast-ui-grid-view.ts")
//@JsModule("./src/views/components/Table/FeatureTable.tsx")
//@CssImport("vis-timeline/styles/vis-timeline-graph2d.min.css")
public class TuiGrid extends Div {

    private List<Music> musics = new ArrayList<>();
    private int frozenCount = 2;

    public List<Music> getMusics() {
        return musics;
    }

    public int getFrozenCount() {
        return frozenCount;
    }

    public int getFrozenBorderWidth() {
        return frozenBorderWidth;
    }

    private int frozenBorderWidth = 2;

    public TuiGrid() {
        setId("visualization" + this.hashCode());
        setWidthFull();
        setClassName("timeline");
    }

    public TuiGrid(List<Music> musics) {
        this();
        this.musics = musics;
        initTuiGrid();
    }
    private void initTuiGrid(){
        this.getElement()
                .executeJs(
                        "toastuigrid.create($0, $1);",
                        this, "[" + convertMusicsToJson() + "]");
//        this.getElement()
//                .executeJs(
//                        "vcftimeline.create($0, $1, $2)",
//                        this,
//                        "[" + convertItemsToJson() + "]",
//                        getTimelineOptions().toJSON());
    }
    private String convertMusicsToJson() {
        return this.musics != null
                ? this.musics.stream().map(music -> music.toJSON()).collect(Collectors.joining(","))
                : "";
    }

}
