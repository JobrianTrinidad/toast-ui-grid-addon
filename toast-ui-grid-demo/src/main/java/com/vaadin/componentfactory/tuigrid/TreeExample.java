package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "tree", layout = MainLayout.class)
public class TreeExample extends Div {
    public TreeExample() {
        Span sp = new Span("Clicked table!");
        // create items
        TuiGrid grid = new TuiGrid(null, this.getTableData(),
                this.getColumns(), null);
        grid.setHeaderHeight(100);
        grid.setTableWidth(1000);
        grid.sethScroll(true);
//        grid.setRowHeaders(List.of("rowNum", "checkbox"));
        grid.setRowHeaders(List.of("checkbox"));
        grid.setTreeOption(new TreeOption(true, "name", true));
        add(grid);
    }

    private List<Item> getTableData() {

//        List<Item> TableData = List.of(
        MusicItem item1 = new MusicItem("Beautiful Lies", "Birdy", "Deluxe;", "Pop", "2016-03-26", "10000", "1000", "10050", true);
        MusicItem item2 = new MusicItem("X", "Ed Sheeran", "Deluxe;", "", "", "20000", "1900", "2005");
        MusicItem item3 = new MusicItem("Moves Like Jagger", "Maroon5", "Single;", "Pop,Rock", "2011-08-08", "7000", "11000", "3100");
        MusicItem item4 = new MusicItem("A Head Full Of Dreams", "Coldplay", "Deluxe;", "Rock", "2015-12-04", "25000", "2230", "4030", true);
        MusicItem item5 = new MusicItem("21", "Adele", "Deluxe;", "Pop,R&B", "2011-01-21", "15000", "1007", "12000");
        MusicItem item6 = new MusicItem("Warm On A Cold Night", "HONNE", "EP;", "R&B,Electronic", "2016-07-22", "11000", "1502", "5000");
        MusicItem item7 = new MusicItem("Take Me To The Alley", "Gregory Porter", "Deluxe;", "Jazz", "2016-09-02", "30000", "1200", "5003");
        MusicItem item8 = new MusicItem("Make Out", "LANY", "EP;", "Electronic", "2015-12-11", "12000", "8005", "9000");
        MusicItem item9 = new MusicItem("Get Lucky", "Daft Punk", "Single", "Pop,Funk", "2013-04-23", "9000", "11000", "1500");
        MusicItem item10 = new MusicItem("Valtari", "Sigur Rós", "EP;", "Rock", "2012-05-31", "10000", "9000", "8010");
        MusicItem item11 = new MusicItem("The Magic Whip", "Blur", "EP", "Rok", "2015-04-27", "15000", "1000", "5000");
        MusicItem item12 = new MusicItem("Chaos And The Calm", "James Bay", "EP", "Pop,Rock", "2015-03-23", "12000", "8007", "9000");
        MusicItem item13 = new MusicItem("4", "Beyoncé", "Deluxe", "Pop", "2011-07-26", "12000", "7000", "11002");
        MusicItem item14 = new MusicItem("I Won't Give Up", "Jason Mraz", "Single", "Pop", "2012-01-03", "7000", "8000", "2000");
        MusicItem item15 = new MusicItem("Following My Intuition", "Craig David", "Deluxe", "R&B,Electronic", "2016-10-01", "15000", "9001", "8100");
        MusicItem item16 = new MusicItem("Blue Skies", "Lenka", "Single", "Pop,Rock", "2015-03-18", "6000", "11000", "9000");
        MusicItem item17 = new MusicItem("This Is Acting", "Sia", "EP", "Pop", "2016-10-22", "20000", "11400", "5800", true);
        MusicItem item18 = new MusicItem("Blurryface", "Twenty One Pilots", "EP", "Rock", "2015-05-19", "13000", "6010", "3020");
        MusicItem item19 = new MusicItem("I am Not The Only One", "Sam Smith", "Single", "Pop,R&B", "", "", "", "", true);

        item1.set_children(List.of(item12, item11, item19));
        item4.set_children(List.of(item13, item5));
        item19.set_children(List.of(item17));
        item17.set_children(List.of(item16, item14, item15));
        List<Item> TableData = List.of(item1, item2, item3, item4, item6, item7, item8, item9, item10);
        return TableData;
    }

    private List<Column> getColumns() {
        List<Column> columns = List.of(
                new Column(new ColumnBaseOption(0, "Name", "name", 250, "center", "")),
                new Column(new ColumnBaseOption(1, "Artist", "artist", 250, "center", ""), false, "input", 10),
                new Column(new ColumnBaseOption(2, "Type", "type", 150, "center", ""), false, "input", 10),
                new Column(new ColumnBaseOption(3, "Genre", "genre", 150, "center", ""), false, "input", 10),
                new Column(new ColumnBaseOption(4, "Release", "release", 150, "center", ""), false, "datePicker", new DateOption("yyyy-MM-dd", false))
        );
        return columns;
    }
}