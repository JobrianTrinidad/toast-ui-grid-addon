package com.vaadin.componentfactory.tuigrid;

import com.vaadin.componentfactory.tuigrid.model.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "relation", layout = MainLayout.class)
public class RelationBetweenColumnsExample extends Div {

    public RelationBetweenColumnsExample() {
        Span sp = new Span("Clicked table!");
        Theme selectTheme = new Theme();
        selectTheme.setMaxLength(10);
        selectTheme.setBorder("1px solid #326f70");
        selectTheme.setBackgroundColor("#66878858");
        selectTheme.setOutline("none");
        selectTheme.setWidth("90%");
        selectTheme.setHeight("100%");
        selectTheme.setOpacity(1);
        // create items
        TuiGrid grid = new TuiGrid(null, this.getTableData(),
                this.getColumns(), null);
        grid.setHeaderHeight(100);
        grid.setSummaryHeight(40);
        grid.setSelectTheme(selectTheme);
        grid.addItemChangeListener(ev -> {
            sp.add(ev.getColName() + ": "+ ev.getColValue() + ": "+ ev.getRow());
        });
        add(grid, sp);
    }

    private List<Item> getTableData() {

        List<String> headers = List.of("category1", "category2", "category3");
        GuiItem item1 = new GuiItem(List.of("", "", ""), headers);
        GuiItem item2 = new GuiItem(List.of("2", "2_3", "2_3_1"), headers);
        GuiItem item3 = new GuiItem(List.of("3", "3_1", "3_1_1"), headers);
        List<Item> TableData = List.of(item1, item2, item3);
        return TableData;
    }

    private List<Column> getColumns() {
//        RelationOption select = new RelationOption("Select", "");
        RelationOption root1 = new RelationOption("Domestic", "1");
        RelationOption root2 = new RelationOption("Overseas", "2");
        RelationOption root3 = new RelationOption("Etc", "3");
        RelationOption child1_1 = new RelationOption("Balad/Dance/Pop", "1_1");
        RelationOption child1_2 = new RelationOption("Hiphop/R&B", "1_2");
        RelationOption child1_3 = new RelationOption("Indie", "1_3");
        RelationOption child2_1 = new RelationOption("Pop", "2_1");
        RelationOption child2_2 = new RelationOption("Hiphop", "2_2");
        RelationOption child2_3 = new RelationOption("R&B", "2_3");
        RelationOption child3_1 = new RelationOption("OST", "3_1");
        RelationOption child3_2 = new RelationOption("Classic", "3_2");
        RelationOption child3_3 = new RelationOption("New Age", "3_3");
        RelationOption child1_1_1 = new RelationOption("I Miss You", "1_1_1");
        RelationOption child1_1_2 = new RelationOption("Russian Roulette", "1_1_2");
        RelationOption child1_1_3 = new RelationOption("TT", "1_1_3");
        RelationOption child1_1_4 = new RelationOption("Beautiful", "1_1_4");
        RelationOption child1_1_5 = new RelationOption("KNOCK KNOCK", "1_1_5");
        RelationOption child1_1_6 = new RelationOption("Rookie", "1_1_6");
        RelationOption child1_1_7 = new RelationOption("Round And Round", "1_1_7");
        RelationOption child1_2_1 = new RelationOption("BERMUDA TRIANGLE", "1_2_1");
        RelationOption child1_2_2 = new RelationOption("Day Day", "1_2_2");
        RelationOption child1_2_3 = new RelationOption("Bye bye my blue", "1_2_3");
        RelationOption child1_2_4 = new RelationOption("Comedian", "1_2_4");
        RelationOption child1_2_5 = new RelationOption("what2do", "1_2_5");
        RelationOption child1_2_6 = new RelationOption("Bye bye my blue", "1_2_6");
        RelationOption child1_2_7 = new RelationOption("I NEED U", "1_2_7");
        RelationOption child1_3_1 = new RelationOption("Madeleine Love", "1_3_1");
        RelationOption child1_3_2 = new RelationOption("Mood Indigo", "1_3_2");
        RelationOption child1_3_3 = new RelationOption("I Don't Wanna Love You", "1_3_3");
        RelationOption child1_3_4 = new RelationOption("Come Back Home", "1_3_4");
        RelationOption child1_3_5 = new RelationOption("MONSTER", "1_3_5");
        RelationOption child1_3_6 = new RelationOption("Free Somebody", "1_3_6");
        RelationOption child1_3_7 = new RelationOption("Vineyard", "1_3_7");
        RelationOption child2_1_1 = new RelationOption("Cave Me In", "2_1_1");
        RelationOption child2_1_2 = new RelationOption("Hello", "2_1_2");
        RelationOption child2_1_3 = new RelationOption("When We Were Young", "2_1_3");
        RelationOption child2_1_4 = new RelationOption("Stay With Me", "2_1_4");
        RelationOption child2_1_5 = new RelationOption("I'm Not The Only One", "2_1_5");
        RelationOption child2_1_6 = new RelationOption("Youth", "2_1_6");
        RelationOption child2_1_7 = new RelationOption("Love On Top", "2_1_7");
        RelationOption child2_2_1 = new RelationOption("Love The Way You Lie", "2_2_1");
        RelationOption child2_2_2 = new RelationOption("Flower Dance", "2_2_2");
        RelationOption child2_2_3 = new RelationOption("I Feel It Coming", "2_2_3");
        RelationOption child2_2_4 = new RelationOption("Love The Way You Lie", "2_2_4");
        RelationOption child2_2_5 = new RelationOption("I Feel It Coming", "2_2_5");
        RelationOption child2_2_6 = new RelationOption("GDFR", "2_2_6");
        RelationOption child2_2_7 = new RelationOption("Love Me In Everything", "2_2_7");
        RelationOption child2_3_1 = new RelationOption("Marry You", "2_3_1");
        RelationOption child2_3_2 = new RelationOption("Hello", "2_3_2");
        RelationOption child2_3_3 = new RelationOption("Coastal Love", "2_3_3");
        RelationOption child2_3_4 = new RelationOption("Happy", "2_3_4");
        RelationOption child2_3_5 = new RelationOption("If You Wonder", "2_3_5");
        RelationOption child2_3_6 = new RelationOption("Want To Want Me", "2_3_6");
        RelationOption child2_3_7 = new RelationOption("Get Lucky", "2_3_7");
        RelationOption child3_1_1 = new RelationOption("City Of Stars", "3_1_1");
        RelationOption child3_1_2 = new RelationOption("You Are My Everything", "3_1_2");
        RelationOption child3_1_3 = new RelationOption("Summer Montage / Madeline", "3_1_3");
        RelationOption child3_1_4 = new RelationOption("Memory", "3_1_4");
        RelationOption child3_1_5 = new RelationOption("A Lovely Night", "3_1_5");
        RelationOption child3_2_1 = new RelationOption("Ravel: Pavane Pour Une Infant Defunte", "3_2_1");
        RelationOption child3_2_2 = new RelationOption("Elgar: Salut D`amour", "3_2_2");
        RelationOption child3_2_3 = new RelationOption("Refuse", "3_2_3");
        RelationOption child3_2_4 = new RelationOption("Liebestraume s541: No. 3 in A flat", "3_2_4");
        RelationOption child3_2_5 = new RelationOption("three Romances Op94: n 2 in A", "3_2_5");
        RelationOption child3_3_1 = new RelationOption("Kiss The Rain", "3_3_1");
        RelationOption child3_3_2 = new RelationOption("Blind Flim", "3_3_2");
        RelationOption child3_3_3 = new RelationOption("Merry Christmas Mr.Lawrence", "3_3_3");
        RelationOption child3_3_4 = new RelationOption("After The Play", "3_3_4");
        RelationOption child3_3_5 = new RelationOption("River Flows In You", "3_3_5");
        root1.setChildren(List.of(child1_1, child1_2, child1_3));
        root2.setChildren(List.of(child2_1, child2_2, child2_3));
        root3.setChildren(List.of(child3_1, child3_2, child3_3));
        child1_1.setChildren(List.of(child1_1_1, child1_1_2, child1_1_3, child1_1_4, child1_1_5, child1_1_6, child1_1_7));
        child1_2.setChildren(List.of(child1_2_1, child1_2_2, child1_2_3, child1_2_4, child1_2_5, child1_2_6, child1_2_7));
        child1_3.setChildren(List.of(child1_3_1, child1_3_2, child1_3_3, child1_3_4, child1_3_5, child1_3_6, child1_3_7));
        child2_1.setChildren(List.of(child2_1_1, child2_1_2, child2_1_3, child2_1_4, child2_1_5, child2_1_6, child2_1_7));
        child2_2.setChildren(List.of(child2_2_1, child2_2_2, child2_2_3, child2_2_4, child2_2_5, child2_2_6, child2_2_7));
        child2_3.setChildren(List.of(child2_3_1, child2_3_2, child2_3_3, child2_3_4, child2_3_5, child2_3_6, child2_3_7));
        child3_1.setChildren(List.of(child3_1_1, child3_1_2, child3_1_3, child3_1_4, child3_1_5));
        child3_2.setChildren(List.of(child3_2_1, child3_2_2, child3_2_3, child3_2_4, child3_2_5));
        child3_3.setChildren(List.of(child3_3_1, child3_3_2, child3_3_3, child3_3_4, child3_3_5));

        List<Column> columns = List.of(
                new Column(new ColumnBaseOption(0, "Category1", "category1", 250, "center", ""),
                        true, "select", true, "category2", List.of(root1, root2, root3)),
                new Column(new ColumnBaseOption(1, "Category2", "category2", 250, "center", ""),
                        true, "select", false, "category3", List.of(child1_1, child1_2, child1_3, child2_1, child2_2, child2_3, child3_1, child3_2, child3_3)),
                new Column(new ColumnBaseOption(2, "Category3", "category3", 250, "center", ""),
                        true, "select", false, "", List.of(child1_1_1, child1_1_2, child1_1_3, child1_1_4, child1_1_5, child1_1_6, child1_1_7,
                        child1_2_1, child1_2_2, child1_2_3, child1_2_4, child1_2_5, child1_2_6, child1_2_7,
                        child1_3_1, child1_3_2, child1_3_3, child1_3_4, child1_3_5, child1_3_6, child1_3_7,
                        child2_1_1, child2_1_2, child2_1_3, child2_1_4, child2_1_5, child2_1_6, child2_1_7,
                        child2_2_1, child2_2_2, child2_2_3, child2_2_4, child2_2_5, child2_2_6, child2_2_7,
                        child2_3_1, child2_3_2, child2_3_3, child2_3_4, child2_3_5, child2_3_6, child2_3_7,
                        child3_1_1, child3_1_2, child3_1_3, child3_1_4, child3_1_5,
                        child3_2_1, child3_2_2, child3_2_3, child3_2_4, child3_2_5,
                        child3_3_1, child3_3_2, child3_3_3, child3_3_4, child3_3_5)));
        return columns;
    }
}
