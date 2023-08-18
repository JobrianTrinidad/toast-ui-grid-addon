package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
public class BasicExample extends Div {

    public BasicExample() {
        // create items
        TuiGrid grid = new TuiGrid(null, null,
                this.getColumns(), null);
        grid.setHeaderHeight(100);
        grid.setTableWidth(950);
        grid.setTableHeight(600);


        Button addBtn = new Button("Add Item", e -> {
            grid.addData(this.getTableData());
        });
        add(addBtn, grid);
    }

    private List<Item> getTableData() {

        List<Item> TableData = new ArrayList<>();

        TableData.add(new MusicItem(
                "Beautiful Lies",
                "Birdy", "Deluxe;",
                "Pop", "2016-03-26",
                "10000",
                "1000",
                "10050"));
        TableData.add(new MusicItem(
                "X",
                "Ed Sheeran",
                "Deluxe;",
                "",
                "",
                "20000",
                "1900",
                "2005"));
        TableData.add(new MusicItem(
                "Moves Like Jagger",
                "Maroon5",
                "Single;",
                "Pop,Rock",
                "2011-08-08",
                "7000",
                "11000",
                "3100"
        ));
        TableData.add(new MusicItem(
                "A Head Full Of Dreams",
                "Coldplay",
                "Deluxe;",
                "Rock",
                "2015-12-04",
                "25000",
                "2230",
                "4030"
        ));
        TableData.add(new MusicItem(
                "21",
                "Adele",
                "Deluxe;",
                "Pop,R&B",
                "2011-01-21",
                "15000",
                "1007",
                "12000"
        ));
        TableData.add(new MusicItem(
                "Warm On A Cold Night",
                "HONNE",
                "EP;",
                "R&B,Electronic",
                "2016-07-22",
                "11000",
                "1502",
                "5000"
        ));
        TableData.add(new MusicItem(
                "Take Me To The Alley",
                "Gregory Porter",
                "Deluxe;",
                "Jazz",
                "2016-09-02",
                "30000",
                "1200",
                "5003"
        ));
        TableData.add(new MusicItem(
                "Make Out",
                "LANY",
                "EP;",
                "Electronic",
                "2015-12-11",
                "12000",
                "8005",
                "9000"
        ));
        TableData.add(new MusicItem(
                "Get Lucky",
                "Daft Punk",
                "Single",
                "Pop,Funk",
                "2013-04-23",
                "9000",
                "11000",
                "1500"
        ));
        TableData.add(new MusicItem(
                "Valtari",
                "Sigur Rós",
                "EP;",
                "Rock",
                "2012-05-31",
                "10000",
                "9000",
                "8010"
        ));
        TableData.add(new MusicItem(
                "Bush",
                "Snoop Dogg",
                "EP",
                "Hiphop",
                "2015-05-12",
                "18000",
                "3000",
                "2005"
        ));
        TableData.add(new MusicItem(
                "Chaos And The Calm",
                "James Bay",
                "EP",
                "Pop,Rock",
                "2015-03-23",
                "12000",
                "8007",
                "9000"
        ));
        TableData.add(new MusicItem(
                "4",
                "Beyoncé",
                "Deluxe",
                "Pop",
                "2011-07-26",
                "12000",
                "7000",
                "11002"
        ));
        TableData.add(new MusicItem(
                "I Won't Give Up",
                "Jason Mraz",
                "Single",
                "Pop",
                "2012-01-03",
                "7000",
                "8000",
                "2000"
        ));
        TableData.add(new MusicItem(
                "Following My Intuition",
                "Craig David",
                "Deluxe",
                "R&B,Electronic",
                "2016-10-01",
                "15000",
                "9001",
                "8100"
        ));
        TableData.add(new MusicItem(
                "Blue Skies",
                "Lenka",
                "Single",
                "Pop,Rock",
                "2015-03-18",
                "6000",
                "11000",
                "9000"
        ));
        TableData.add(new MusicItem(
                "This Is Acting",
                "Sia",
                "EP",
                "Pop",
                "2016-10-22",
                "20000",
                "11400",
                "5800"
        ));
        TableData.add(new MusicItem(
                "Blurryface",
                "Twenty One Pilots",
                "EP",
                "Rock",
                "2015-05-19",
                "13000",
                "6010",
                "3020"
        ));
        TableData.add(new MusicItem(
                "I am Not The Only One",
                "Sam Smith",
                "Single",
                "Pop,R&B",
                "",
                "",
                "",
                ""
        ));

        return TableData;
    }

    private List<Column> getColumns() {
        List<Column> columns = List.of(
                new Column(new ColumnBaseOption(0, "Name", "name", 250, "center", "")),
                new Column(new ColumnBaseOption(1, "Artist", "artist", 250, "center", "")),
                new Column(new ColumnBaseOption(2, "Type", "type", 150, "center", "")),
                new Column(new ColumnBaseOption(3, "Genre", "genre", 150, "center", ""), false, "input", 10),
                new Column(new ColumnBaseOption(4, "Release", "release", 150, "center", ""), false, "datePicker", new DateOption("yyyy-MM-dd", false)));
        return columns;
    }
}