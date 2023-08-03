package com.vaadin.componentfactory.tuigrid;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        final DrawerToggle drawerToggle = new DrawerToggle();

        final RouterLink gridExample =
                new RouterLink("Tui-Grid Example", GridExample.class);

        final VerticalLayout menuLayout = new VerticalLayout(gridExample);
        addToDrawer(menuLayout);
        addToNavbar(drawerToggle);
    }
}
