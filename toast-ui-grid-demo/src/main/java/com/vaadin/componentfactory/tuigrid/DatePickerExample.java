package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Route(value = "datepicker", layout = MainLayout.class)
public class DatePickerExample extends Div {

    public DatePickerExample() {
        // create DateItems
        TuiGrid grid = new TuiGrid(null, this.getTableData(),
                this.getColumns(), null);
        grid.setHeaderHeight(100);
        grid.setSummaryHeight(40);

        Button addBtn = new Button("Add");
        addBtn.addClickListener(listener -> {
//            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate startDate = LocalDate.parse("2019-11-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            grid.setDateFilter("timepicker", startDate.toString(), LocalDateTime.now().toString());
//            grid.addItem(0, List.of(new GuiItem(0, List.of("", "", "", ""), headers)));
//            add(layout);
//            grid.refreshGrid();
        });

        add(addBtn, grid);
    }

    private List<Item> getTableData() {

        List<Item> TableData = new ArrayList<>();
        List<String> headers = List.of("default", "options", "ranges", "timepicker", "timepickerwithtab", "month", "year");
        TableData.add(new GuiItem(0, List.of("1992/03/25", "12/06/2019",
                "2014-04-16", "2019-11-19 09:00 AM",
                "2019-11-19 09:00 AM", "2019-01",
                "2019"), headers));
        return TableData;
    }

    private List<Column> getColumns() {
        List<Column> columns = List.of(
                new Column(new ColumnBaseOption(0, "DatePicker (Default)", "default", 150, "center", ""), true, "datePicker", new DateOption("yyyy/MM/dd", false)),
                new Column(new ColumnBaseOption(1, "DatePicker (Using options)", "options", 150, "center", ""), true, "datePicker", new DateOption("dd/MM/yyyy", false)),
                new Column(new ColumnBaseOption(2, "DatePicker (selectableRanges)", "ranges", 150, "center", ""), true, "datePicker", new DateOption(2023, 7, 10, 2023, 8, 20)),
                new Column(new ColumnBaseOption(3, "Date-TimePicker", "timepicker", 150, "center", ""), true, "datePicker", new DateOption("yyyy-MM-dd HH:mm A", true)),
                new Column(new ColumnBaseOption(4, "Date-TimePicker With tab", "timepickerwithtab", 150, "center", ""), true, "datePicker", new DateOption("yyyy-MM-dd HH:mm A", true, "tab", "spinbox")),
                new Column(new ColumnBaseOption(5, "MonthPicker", "month", 150, "center", ""), true, "datePicker", new DateOption("yyyy-MM", "month")),
                new Column(new ColumnBaseOption(6, "YearPicker", "year", 150, "center", ""), true, "datePicker", new DateOption("yyyy", "year")));
        return columns;
    }
}
