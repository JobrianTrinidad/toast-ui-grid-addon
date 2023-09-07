package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
public class BasicExample extends Div {

    public BasicExample() {
        // create items
        TuiGrid grid = new TuiGrid();
        grid.setColumns(this.getColumns());
        grid.setItems(this.getTableData());
        grid.setRowHeaders(List.of("checkbox"));
        grid.setHeaderHeight(100);
//        grid.setTableWidth(950);
        grid.setTableHeight(600);


        HorizontalLayout layout = new HorizontalLayout();
        layout.add(grid);

        Button delBtn = new Button("Delete");
        delBtn.addClickListener(listener -> {
            grid.deleteItems(grid.getCheckedItems());
//            add(layout);
        });

        add(delBtn, layout);
    }

    private List<Item> getTableData() {

        List<Item> TableData = new ArrayList<>();
        List<String> headers = List.of("name", "artist", "type", "genre", "release");

        TableData.add(new GuiItem(List.of("Beautiful Lies", "Birdy", "Deluxe;", "Pop", "2016-03-26", "10000", "1000", "10050"), headers));
        TableData.add(new GuiItem(List.of("X", "Ed Sheeran", "Deluxe;", "", "", "20000", "1900", "2005"), headers));
        TableData.add(new GuiItem(List.of("Moves Like Jagger", "Maroon5", "Single;", "Pop,Rock", "2011-08-08", "7000", "11000", "3100"), headers));
        TableData.add(new GuiItem(List.of("A Head Full Of Dreams", "Coldplay", "Deluxe;", "Rock", "2015-12-04", "25000", "2230", "4030"), headers));
        TableData.add(new GuiItem(List.of("21", "Adele", "Deluxe;", "Pop,R&B", "2011-01-21", "15000", "1007", "12000"), headers));
        TableData.add(new GuiItem(List.of("Warm On A Cold Night", "HONNE", "EP;", "R&B,Electronic", "2016-07-22", "11000", "1502", "5000"), headers));
        TableData.add(new GuiItem(List.of("Take Me To The Alley", "Gregory Porter", "Deluxe;", "Jazz", "2016-09-02", "30000", "1200", "5003"), headers));
        TableData.add(new GuiItem(List.of("Make Out", "LANY", "EP;", "Electronic", "2015-12-11", "12000", "8005", "9000"), headers));
        TableData.add(new GuiItem(List.of("Get Lucky", "Daft Punk", "Single", "Pop,Funk", "2013-04-23", "9000", "11000", "1500"), headers));
        TableData.add(new GuiItem(List.of("Valtari", "Sigur Rós", "EP;", "Rock", "2012-05-31", "10000", "9000", "8010"), headers));
        TableData.add(new GuiItem(List.of("Bush", "Snoop Dogg", "EP", "Hiphop", "2015-05-12", "18000", "3000", "2005"), headers));
        TableData.add(new GuiItem(List.of("Chaos And The Calm", "James Bay", "EP", "Pop,Rock", "2015-03-23", "12000", "8007", "9000"), headers));
        TableData.add(new GuiItem(List.of("4", "Beyoncé", "Deluxe", "Pop", "2011-07-26", "12000", "7000", "11002"), headers));
        TableData.add(new GuiItem(List.of("I Won't Give Up", "Jason Mraz", "Single", "Pop", "2012-01-03", "7000", "8000", "2000"), headers));
        TableData.add(new GuiItem(List.of("Following My Intuition", "Craig David", "Deluxe", "R&B,Electronic", "2016-10-01", "15000", "9001", "8100"), headers));
        TableData.add(new GuiItem(List.of("Blue Skies", "Lenka", "Single", "Pop,Rock", "2015-03-18", "6000", "11000", "9000"), headers));
        TableData.add(new GuiItem(List.of("This Is Acting", "Sia", "EP", "Pop", "2016-10-22", "20000", "11400", "5800"), headers));
        TableData.add(new GuiItem(List.of("Blurryface", "Twenty One Pilots", "EP", "Rock", "2015-05-19", "13000", "6010", "3020"), headers));
        TableData.add(new GuiItem(List.of("I am Not The Only One", "Sam Smith", "Single", "Pop,R&B", "", "", "", ""), headers));
        return TableData;
    }

    private List<Column> getColumns() {
        List<Column> columns = List.of(
                new Column(new ColumnBaseOption(0, "Name", "name", 0, "center", "")),
                new Column(new ColumnBaseOption(1, "Artist", "artist", 0, "center", "")),
                new Column(new ColumnBaseOption(2, "Type", "type", 0, "center", "")),
                new Column(new ColumnBaseOption(3, "Genre", "genre", 0, "center", ""), false, "input", 10),
                new Column(new ColumnBaseOption(4, "Release", "release", 0, "center", ""), false, "datePicker", new DateOption("yyyy-MM-dd", false)));
        return columns;
    }
}