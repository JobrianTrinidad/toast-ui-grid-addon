# Toast-ui-grid component for Vaadin Flow

Toast-ui-grid component uses [vis-timeline](https://visjs.github.io/vis-timeline/docs/timeline/) library to display data in time.

This component is part of Vaadin Component Factory.

## Description 

Toast-ui-grid component provides support to the following features:

- Create a timeline with a defined visible range.
- Make musicItems readonly.
- Update musicItems content.
- Give musicItems a style by defining a class name.
- Edit an item by resizing it.
- Edit an item by drag and drop.
- Multiple musicItems selection.
- Possiblity to define zoom options (e.g. 1 day, 3 days, 5 days).
- Items are shown connected by an horizontal line between them. (*)
- Show tooltips for musicItems.
- Possibility to revert resizing or dragging if condition is not met.
- Autoscrolling when reaching limits of visible range.
- Tooltip on item update.

(*) Horizontal lines implementation is based on [timeline-arrows](https://github.com/javdome/timeline-arrows).

## Development instructions

- Build the project and install the add-on locally:
```
mvn clean install
```
- For starting the demo server go to timeline-demo and run:
```
mvn jetty:run
```
This deploys demo at http://localhost:8080

## How to use it

Create a new component Toast-ui-grid:

```java
Toast-ui-grid timeline = new Toast-ui-grid(musicItems);
```

## Examples

See examples on [timeline-demo](https://github.com/vaadin-component-factory/toast-ui-grid/tree/master/timeline-demo/src/main/java/com/vaadin/componentfactory/timeline).

## Missing features or bugs

You can report any issue or missing feature on [GitHub](https://github.com/vaadin-component-factory/toast-ui-grid/issues).
