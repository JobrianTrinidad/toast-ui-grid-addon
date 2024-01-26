package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "grid", layout = MainLayout.class)
public class GridExample extends Div {
    TuiGrid grid;

    public GridExample() {
        Span sp = new Span("Clicked table!");
        sp.setId("iam");
        Theme inputTheme = new Theme();
//        inputTheme.setMaxLength(10);
        inputTheme.setBorder("1px solid #326f70");
        inputTheme.setBackgroundColor("#66878858");
        inputTheme.setOutline("none");
        inputTheme.setWidth("90%");
        inputTheme.setHeight("100%");
        inputTheme.setOpacity(1);
        // create items
        grid = new TuiGrid();

        grid.setColumns(this.getColumns());
        grid.setInputTheme(inputTheme);
        grid.setItems(this.getTableData());
        grid.setRowHeaders(List.of("checkbox"));
        grid.setComplexColumns(this.getCustomHeader());
        grid.setSummaries(this.getSummaries());
        grid.setHeaderHeight(100);
        grid.setSummaryHeight(40);
        grid.setHeight("calc(100vh - 106px");
        grid.addItemChangeListener(ev -> {
            sp.add(ev.getColName() + ": " + grid.getData().get(1).toJSON());
        });

        grid.addItemSaveListener(ev -> {
            sp.add("Here is: " + ev.getUpdatedRows().getObject(0) + " " + ev.getUpdatedRows().length());
        });

        Button btnReload = new Button("Reload");
        // Assuming 'btnReload' is a Button and 'grid' is a Grid component
        btnReload.addClickListener(e -> grid.setIDToGridRow(1, 101));
//        this.getElement().getParent().appendChild(btnReload.getElement());
        this.getParent().ifPresent(parent -> parent.getElement().appendChild(btnReload.getElement()));
        add(sp, btnReload, grid);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setRowCountOnElement("iam");
    }

    private List<Item> getTableData() {
        List<String> headers = List.of("name", "artist", "type", "genre", "release", "price", "download", "listen", "check", "check2");
        List<Item> TableData = List.of(
                new GuiItem(0, List.of("Beautiful Lies", "Birdy", "Deluxe;", "Pop", "2016-03-26", "10000", "1000", "10050",
                        "true", "true"), headers),
                new GuiItem(1, List.of(
                        "X",
                        "Ed Sheeran",
                        "Deluxe;",
                        "",
                        "",
                        "20000",
                        "1900",
                        "2005",
                        "false",
                        "false"), headers),
                new GuiItem(2, List.of(
                        "Moves Like Jagger",
                        "Maroon5",
                        "Single;",
                        "Pop,Rock",
                        "2011-08-08",
                        "7000",
                        "11000",
                        "3100",
                        "false",
                        "false"
                ), headers),
                new GuiItem(3, List.of(
                        "A Head Full Of Dreams",
                        "Coldplay",
                        "Deluxe;",
                        "Rock",
                        "2015-12-04",
                        "25000",
                        "2230",
                        "4030",
                        "false",
                        "false"
                ), headers),
                new GuiItem(4, List.of(
                        "21",
                        "Adele",
                        "Deluxe;",
                        "Pop,R&B",
                        "2011-01-21",
                        "15000",
                        "1007",
                        "12000",
                        "false",
                        "false"
                ), headers),
                new GuiItem(5, List.of(
                        "Warm On A Cold Night",
                        "HONNE",
                        "EP;",
                        "R&B,Electronic",
                        "2016-07-22",
                        "11000",
                        "1502",
                        "5000",
                        "false",
                        "false"
                ), headers),
                new GuiItem(6, List.of(
                        "Take Me To The Alley",
                        "Gregory Porter",
                        "Deluxe;",
                        "Jazz",
                        "2016-09-02",
                        "30000",
                        "1200",
                        "5003",
                        "false",
                        "false"
                ), headers),
                new GuiItem(7, List.of(
                        "Make Out",
                        "LANY",
                        "EP;",
                        "Electronic",
                        "2015-12-11",
                        "12000",
                        "8005",
                        "9000",
                        "false",
                        "false"
                ), headers),
                new GuiItem(8, List.of(
                        "Get Lucky",
                        "Daft Punk",
                        "Single",
                        "Pop,Funk",
                        "2013-04-23",
                        "9000",
                        "11000",
                        "1500",
                        "false",
                        "false"
                ), headers),
                new GuiItem(9, List.of(
                        "Valtari",
                        "Sigur Rós",
                        "EP;",
                        "Rock",
                        "2012-05-31",
                        "10000",
                        "9000",
                        "8010",
                        "false",
                        "false"
                ), headers),
                new GuiItem(10, List.of(
                        "Bush",
                        "Snoop Dogg",
                        "EP",
                        "Hiphop",
                        "2015-05-12",
                        "18000",
                        "3000",
                        "2005",
                        "false",
                        "false"
                ), headers),
                new GuiItem(11, List.of(
                        "Chaos And The Calm",
                        "James Bay",
                        "EP",
                        "Pop,Rock",
                        "2015-03-23",
                        "12000",
                        "8007",
                        "9000",
                        "false",
                        "false"
                ), headers),
                new GuiItem(12, List.of(
                        "4",
                        "Beyoncé",
                        "Deluxe",
                        "Pop",
                        "2011-07-26",
                        "12000",
                        "7000",
                        "11002",
                        "false",
                        "false"
                ), headers),
                new GuiItem(13, List.of(
                        "I Won't Give Up",
                        "Jason Mraz",
                        "Single",
                        "Pop",
                        "2012-01-03",
                        "7000",
                        "8000",
                        "2000",
                        "false",
                        "false"
                ), headers),
                new GuiItem(14, List.of(
                        "Following My Intuition",
                        "Craig David",
                        "Deluxe",
                        "R&B,Electronic",
                        "2016-10-01",
                        "15000",
                        "9001",
                        "8100",
                        "false",
                        "false"
                ), headers),
                new GuiItem(15, List.of(
                        "Blue Skies",
                        "Lenka",
                        "Single",
                        "Pop,Rock",
                        "2015-03-18",
                        "6000",
                        "11000",
                        "9000",
                        "false",
                        "false"
                ), headers),
                new GuiItem(16, List.of(
                        "This Is Acting",
                        "Sia",
                        "EP",
                        "Pop",
                        "2016-10-22",
                        "20000",
                        "11400",
                        "5800",
                        "false",
                        "false"
                ), headers),
                new GuiItem(17, List.of(
                        "Blurryface",
                        "Twenty One Pilots",
                        "EP",
                        "Rock",
                        "2015-05-19",
                        "13000",
                        "6010",
                        "3020",
                        "false",
                        "false"
                ), headers),
                new GuiItem(18, List.of(
                        "I am Not The Only One",
                        "Sam Smith",
                        "Single",
                        "Pop,R&B",
                        "",
                        "",
                        "",
                        "",
                        "false",
                        "false"
                ), headers));

        return TableData;
    }

    private List<Column> getColumns() {
        List<Column> columns = List.of(
                new Column(new ColumnBaseOption(0, "Name", "name", 250, "center", "")),
                new Column(new ColumnBaseOption(1, "Artist", "artist", 250, "center", ""), true, "input"),
                new Column(new ColumnBaseOption(2, "Type", "type", 150, "center", ""), true, "input"),
                new Column(new ColumnBaseOption(3, "Genre", "genre", 150, "center", "tui-grid-cell-required"), true, "input"),
                new Column(new ColumnBaseOption(4, "Release", "release", 150, "center", "tui-grid-cell-required"), true, "datePicker", new DateOption("yyyy-MM-dd", false)),
                new Column(new ColumnBaseOption(5, "Price", "price", 150, "center", ""), "asc", true),
                new Column(new ColumnBaseOption(6, "Download", "download", 150, "center", "")),
                new Column(new ColumnBaseOption(7, "Listen", "listen", 150, "center", "")),
                new Column(new ColumnBaseOption(8, "Check", "check", 150, "center", ""), true, "check"),
                new Column(new ColumnBaseOption(9, "Check2", "check2", 150, "center", ""), true, "check"));

        return columns;
    }

    private List<Summary> getSummaries() {
        List<Summary> summaries = List.of(
                new Summary("price", Summary.OperationType.sum),
                new Summary("download", Summary.OperationType.avg),
                new Summary("listen", Summary.OperationType.rowcount));
        return summaries;
    }

    private List<ComplexColumn> getCustomHeader() {
        List<ComplexColumn> customHeaders = List.of(
                new ComplexColumn("Details Info", "Details Info", List.of("type", "genre", "release")),
                new ComplexColumn("Count", "Count", List.of("download", "listen")),
                new ComplexColumn("Extra Info", "Extra Info", List.of("price", "Count")));
        return customHeaders;
    }
}
