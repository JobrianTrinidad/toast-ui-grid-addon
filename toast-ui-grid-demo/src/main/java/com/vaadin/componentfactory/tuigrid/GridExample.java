package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.Column;
import com.vaadin.componentfactory.tuigrid.model.Music;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
public class GridExample extends Div {

    public GridExample() {
        // create items
        TuiGrid grid = new TuiGrid(this.getGroupItems(), this.getColumns());
        add(grid);
    }

    private List<Music> getGroupItems() {

        List<Music> TableData = List.of(
                new Music("Beautiful Lies", "Birdy", "Deluxe;", "Pop", "2016-03-26", "10000", "1000", "10050"),
                new Music(
                        "X",
                        "Ed Sheeran",
                        "Deluxe;",
                        "",
                        "",
                        "20000",
                        "1900",
                        "2005"),
                new Music(
                        "Moves Like Jagger",
                        "Maroon5",
                        "Single;",
                        "Pop,Rock",
                        "2011-08-08",
                        "7000",
                        "11000",
                        "3100"
                ),
                new Music(
                        "A Head Full Of Dreams",
                        "Coldplay",
                        "Deluxe;",
                        "Rock",
                        "2015-12-04",
                        "25000",
                        "2230",
                        "4030"
                ),
                new Music(
                        "21",
                        "Adele",
                        "Deluxe;",
                        "Pop,R&B",
                        "2011-01-21",
                        "15000",
                        "1007",
                        "12000"
                ),
                new Music(
                        "Warm On A Cold Night",
                        "HONNE",
                        "EP;",
                        "R&B,Electronic",
                        "2016-07-22",
                        "11000",
                        "1502",
                        "5000"
                ),
                new Music(
                        "Take Me To The Alley",
                        "Gregory Porter",
                        "Deluxe;",
                        "Jazz",
                        "2016-09-02",
                        "30000",
                        "1200",
                        "5003"
                ),
                new Music(
                        "Make Out",
                        "LANY",
                        "EP;",
                        "Electronic",
                        "2015-12-11",
                        "12000",
                        "8005",
                        "9000"
                ),
                new Music(
                        "Get Lucky",
                        "Daft Punk",
                        "Single",
                        "Pop,Funk",
                        "2013-04-23",
                        "9000",
                        "11000",
                        "1500"
                ),
                new Music(
                        "Valtari",
                        "Sigur Rós",
                        "EP;",
                        "Rock",
                        "2012-05-31",
                        "10000",
                        "9000",
                        "8010"
                ),
                new Music(
                        "Bush",
                        "Snoop Dogg",
                        "EP",
                        "Hiphop",
                        "2015-05-12",
                        "18000",
                        "3000",
                        "2005"
                ),
                new Music(
                        "Chaos And The Calm",
                        "James Bay",
                        "EP",
                        "Pop,Rock",
                        "2015-03-23",
                        "12000",
                        "8007",
                        "9000"
                ),
                new Music(
                        "4",
                        "Beyoncé",
                        "Deluxe",
                        "Pop",
                        "2011-07-26",
                        "12000",
                        "7000",
                        "11002"
                ),
                new Music(
                        "I Won't Give Up",
                        "Jason Mraz",
                        "Single",
                        "Pop",
                        "2012-01-03",
                        "7000",
                        "8000",
                        "2000"
                ),
                new Music(
                        "Following My Intuition",
                        "Craig David",
                        "Deluxe",
                        "R&B,Electronic",
                        "2016-10-01",
                        "15000",
                        "9001",
                        "8100"
                ),
                new Music(
                        "Blue Skies",
                        "Lenka",
                        "Single",
                        "Pop,Rock",
                        "2015-03-18",
                        "6000",
                        "11000",
                        "9000"
                ),
                new Music(
                        "This Is Acting",
                        "Sia",
                        "EP",
                        "Pop",
                        "2016-10-22",
                        "20000",
                        "11400",
                        "5800"
                ),
                new Music(
                        "Blurryface",
                        "Twenty One Pilots",
                        "EP",
                        "Rock",
                        "2015-05-19",
                        "13000",
                        "6010",
                        "3020"
                ),
                new Music(
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
                new Column(0, "Name", "name", 250, "center"),
                new Column(1, "Artist", "artist", 250, "center", "", true, "input", 10),
                new Column(2, "Type", "type", 150, "center", "", true, "input", 10),
                new Column(3, "Genre", "genre", 150, "center", "tui-grid-cell-required", true, "input", 10),
                new Column(4, "Release", "release", 150, "center", "tui-grid-cell-required", true, "datePicker", "yyyy-MM-dd",  false),
                new Column(5, "Price", "price", 150, "center", "", "asc", true),
                new Column(6, "Download", "download", 150, "center"),
                new Column(7, "Listen", "listen", 150, "center"));
        return columns;
    }
}
