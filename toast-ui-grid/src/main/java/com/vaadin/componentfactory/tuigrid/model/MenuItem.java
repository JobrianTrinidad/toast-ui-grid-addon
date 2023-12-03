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
package com.vaadin.componentfactory.tuigrid.model;

import com.vaadin.componentfactory.tuigrid.event.ClickListener;
import elemental.json.Json;
import elemental.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuItem {
    private String caption;
    private List<MenuItem> subItems;
    private List<ClickListener> clickListeners;

    public MenuItem(String caption) {
        this.caption = caption;
        this.subItems = new ArrayList<>();
        this.clickListeners = new ArrayList<>();
    }

    public String toJSON() {
        JsonObject js = Json.createObject();
        js.put("title", this.caption);
        Optional.ofNullable(convertChildrenToJson()).ifPresent(v -> js.put("menu", "[" + v + "]"));
        return js.toJson();
    }

    private String convertChildrenToJson() {
        return this.subItems != null && !this.subItems.isEmpty()
                ? this.subItems.stream().map(MenuItem::toJSON).collect(Collectors.joining(","))
                : null;
    }

    public String getCaption() {
        return caption;
    }

    public void addClickListener(ClickListener listener) {
        clickListeners.add(listener);
    }

    public List<ClickListener> getClickListeners() {
        return clickListeners;
    }

    public void click() {
        for (ClickListener listener : clickListeners) {
            listener.onClick();
        }
    }

    public MenuItem addSubItem(String caption) {
        MenuItem subItem = new MenuItem(caption);
        subItems.add(subItem);
        return subItem;
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }
}
