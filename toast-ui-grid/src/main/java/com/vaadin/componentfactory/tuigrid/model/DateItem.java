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

import java.util.Optional;

public class DateItem implements Item{
    private String defaultDate;
    private String optionsDate;
    private String selectableRange;
    private String timePicker;
    private String timePickerWithTab;
    private String monthPicker;
    private String yearPicker;

    public DateItem(String defaultDate, String optionsDate, String selectableRange, String timePicker, String timePickerWithTab, String monthPicker, String yearPicker) {
        this.defaultDate = defaultDate;
        this.optionsDate = optionsDate;
        this.selectableRange = selectableRange;
        this.timePicker = timePicker;
        this.timePickerWithTab = timePickerWithTab;
        this.monthPicker = monthPicker;
        this.yearPicker = yearPicker;
    }


    public String toJSON() {
        JsonObject js = Json.createObject();
        Optional.ofNullable(getDefaultDate()).ifPresent(v -> js.put("DatePicker (Default)", v.toString()));
        Optional.ofNullable(getOptionsDate()).ifPresent(v -> js.put("DatePicker (Using options)", v.toString()));
        Optional.ofNullable(getSelectableRange()).ifPresent(v -> js.put("DatePicker (selectableRanges)", v.toString()));
        Optional.ofNullable(getTimePicker()).ifPresent(v -> js.put("Date-TimePicker", v.toString()));
        Optional.ofNullable(getTimePickerWithTab()).ifPresent(v -> js.put("Date-TimePicker With tab", v.toString()));
        Optional.ofNullable(getMonthPicker()).ifPresent(v -> js.put("MonthPicker", v.toString()));
        Optional.ofNullable(getYearPicker()).ifPresent(v -> js.put("YearPicker", v.toString()));

        return js.toJson();
    }

    @Override
    public void addHeader(String headerName) {

    }

    public String getDefaultDate() {
        return defaultDate;
    }

    public void setDefaultDate(String defaultDate) {
        this.defaultDate = defaultDate;
    }

    public String getOptionsDate() {
        return optionsDate;
    }

    public void setOptionsDate(String optionsDate) {
        this.optionsDate = optionsDate;
    }

    public String getSelectableRange() {
        return selectableRange;
    }

    public void setSelectableRange(String selectableRange) {
        this.selectableRange = selectableRange;
    }

    public String getTimePicker() {
        return timePicker;
    }

    public void setTimePicker(String timePicker) {
        this.timePicker = timePicker;
    }

    public String getTimePickerWithTab() {
        return timePickerWithTab;
    }

    public void setTimePickerWithTab(String timePickerWithTab) {
        this.timePickerWithTab = timePickerWithTab;
    }

    public String getMonthPicker() {
        return monthPicker;
    }

    public void setMonthPicker(String monthPicker) {
        this.monthPicker = monthPicker;
    }

    public String getYearPicker() {
        return yearPicker;
    }

    public void setYearPicker(String yearPicker) {
        this.yearPicker = yearPicker;
    }
}
