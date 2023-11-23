package com.vaadin.componentfactory.tuigrid;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class MainLayout extends AppLayout {

    public MainLayout() {
        final DrawerToggle drawerToggle = new DrawerToggle();
        drawerToggle.addClickListener(event -> {
            EventBus.getInstance().post("DrawerToggleClicked");
        });

        final RouterLink basicExample =
                new RouterLink("1. Basic", BasicExample.class);
        final RouterLink relationBetweenColumnsExample =
                new RouterLink("2. Relation between columns", RelationBetweenColumnsExample.class);
        final RouterLink datePickerExample =
                new RouterLink("3. DatePicker", DatePickerExample.class);
        final RouterLink treeExample =
                new RouterLink("4. Tree", TreeExample.class);
        final RouterLink gridExample =
                new RouterLink("5. GridExample", GridExample.class);

        final VerticalLayout menuLayout = new VerticalLayout(basicExample, relationBetweenColumnsExample,
                datePickerExample, treeExample, gridExample);
        addToDrawer(menuLayout);
        addToNavbar(drawerToggle);
    }
}
