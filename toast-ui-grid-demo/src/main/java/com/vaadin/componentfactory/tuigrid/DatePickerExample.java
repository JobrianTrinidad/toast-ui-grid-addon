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
                new Column(0, "default", "DatePicker (Default)", 150, "center", "", true, "datePicker", "yyyy/MM/dd", false),
                new Column(1, "options", "DatePicker (Using options)", 150, "center", "", true, "datePicker", "dd/MM/yyyy", false),
                new Column(2, "ranges", "DatePicker (selectableRanges)", 150, "center", true, "datePicker", 2023, 7, 10, 2023, 8, 20),
                new Column(3, "timepicker", "Date-TimePicker", 150, "center", "", true, "datePicker", "yyyy-MM-dd HH:mm A", true),
                new Column(4, "timepickerwithtab", "Date-TimePicker With tab", 150, "center", true, "datePicker", "yyyy-MM-dd HH:mm A", true, "tab", "spinbox"),
                new Column(5, "month", "MonthPicker", 150, "center", true, "datePicker", "yyyy-MM", "month"),
                new Column(6, "year", "YearPicker", 150, "center", true, "datePicker", "yyyy", "year"));
        return columns;
    }
}
