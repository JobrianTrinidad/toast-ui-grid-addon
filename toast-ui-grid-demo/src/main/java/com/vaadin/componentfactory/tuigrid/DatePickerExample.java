package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.Column;
import com.vaadin.componentfactory.tuigrid.model.DateItem;
import com.vaadin.componentfactory.tuigrid.model.Item;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "datepicker", layout = MainLayout.class)
public class DatePickerExample extends Div {

    public DatePickerExample() {
        // create DateItems
        TuiGrid grid = new TuiGrid(null, this.getTableData(),
                this.getColumns(), null);
        grid.setHeaderHeight(100);
        grid.setSummaryHeight(40);
        add(grid);
    }

    private List<Item> getTableData() {

        List<Item> TableData = new ArrayList<>();
        TableData.add(new DateItem("1992/03/25", "12/06/2019",
                "2014-04-16", "2019-11-19 09:00 AM",
                "2019-11-19 09:00 AM", "2019-01",
                "2019"));
        return TableData;
    }

    private List<Column> getColumns() {
        List<Column> columns = List.of(
                new Column(0, "DatePicker (Default)", "default", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false),
                new Column(1, "DatePicker (Using options)", "options", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false),
                new Column(2, "DatePicker (selectableRanges)", "ranges", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false),
                new Column(3, "Date-TimePicker", "timepicker", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false),
                new Column(4, "Date-TimePicker With tab", "timepickerwithtab", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false),
                new Column(5, "MonthPicker", "month", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false),
                new Column(6, "YearPicker", "year", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd", false));
        return columns;
    }
}
