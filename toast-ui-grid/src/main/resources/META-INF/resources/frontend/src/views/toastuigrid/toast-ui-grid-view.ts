import '@vaadin/button';
import '@vaadin/text-field';
// import {View} from '../../views/view.js';
import {FeatureTable} from "../../views/components/Table/FeaturesTable";
import 'tui-grid/dist/tui-grid.css';
import 'tui-date-picker/dist/tui-date-picker.css';
import {createRoot} from 'react-dom/client';
import {OptColumn, OptSummaryData} from "tui-grid/types/options";
import CustomTextEditor from "../../views/components/Table/CustomeEditor";

window["toastuigrid"] = {

    create(container: HTMLElement, itemsJson: any) {
        setTimeout(() => this._createGrid(container, itemsJson, null));
    },

    _createGrid(container: HTMLElement, itemsJson: any, _: any) {
        let parsedItems = JSON.parse(itemsJson);
        let tableData: any;
        let columns: OptColumn[] = [
            {
                header: 'Name',
                name: 'name',
                width: 250,
                align: 'center'
            },
            {
                header: 'Artist',
                name: 'artist',
                width: 250,
                align: 'center',
                editor: {
                    type: CustomTextEditor,
                    options: {
                        maxLength: 10
                    }
                }
            },
            {
                header: 'Type',
                name: 'type',
                width: 150,
                align: 'center',
                editor: {
                    type: CustomTextEditor,
                    options: {
                        maxLength: 10
                    }
                }
            },
            {
                header: 'Genre',
                name: 'genre',
                width: 150,
                align: 'center',
                className: 'tui-grid-cell-required',
                editor: {
                    type: CustomTextEditor,
                    options: {
                        maxLength: 10
                    }
                }
            },
            {
                header: 'Release',
                name: 'release',
                width: 150,
                align: 'center',
                className: 'tui-grid-cell-required',
                editor: {
                    type: 'datePicker',
                    options: {
                        //   selectableRanges: [[new Date(2014, 3, 10), new Date(2014, 5, 20)]]
                        format: 'yyyy-MM-dd',
                        timepicker: false
                    }
                }
            },
            {
                header: 'Price',
                name: 'price',
                width: 150,
                align: 'center',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: 'Download',
                name: 'download',
                width: 150,
                align: 'center',
            },
            {
                header: 'Listen',
                name: 'listen',
                width: 150,
                align: 'center',
            },
        ];
        let summary: OptSummaryData = {
            height: 40,
            position: 'bottom', // or 'top'

            columnContent: {
                price: {
                    template: function (valueMap) {
                        return `Sum: ${valueMap.sum}`;
                    }
                },
                download: {
                    template: function (valueMap) {
                        return `AVG: ${valueMap.avg.toFixed(2)}`;
                    }
                },
                listen: {
                    template: function (valueMap) {
                        return `MAX: ${valueMap.max}`;
                    }
                }
            }
        }
        let columnOptions = {
            frozenCount: 2,
            frozenBorderWidth: 2,
        }
        let header = {
            height: 100,
            complexColumns: [
                {
                    header: 'Details Info',
                    name: 'mergeColumn1',
                    childNames: ['type', 'genre', 'release']
                },
                {
                    header: 'Count',
                    name: 'mergeColumn2',
                    childNames: ['download', 'listen']
                },
                {
                    header: 'Extra Info',
                    name: 'mergeColumn3',
                    childNames: ['price', 'mergeColumn2']
                }
            ]
        }
        // Implementation goes here
        let gridTable = new FeatureTable({
            TableData: parsedItems,
            columns: columns,
            summary: summary,
            columnOptions:columnOptions,
            header: header
        });
        createRoot(container).render(gridTable.render());
    },


    // name = '';
    // connectedCallback() {
    //     super.connectedCallback();
    //     // this.classList.add('flex', 'p-m', 'gap-m', 'items-end');
    // }
    // constructor(container:any, props: any) {
    //     super(props);
    //     console.log("AAAA: ", props);
    //     this.tableData = props;
    // }

    // async render() {
    //
    //     let gridTable = new FeatureTable({
    //         TableData: this.tableData,
    //         columns: this.columns,
    //         summary: this.summary,
    //         columnOptions: this.columnOptions,
    //         header: this.header
    //     });
    //     createRoot(this).render(gridTable.render());
    //     return ``
    // }

    // async getData() {
    //     Notification.show('serverResponse');
    //     return await ToastUIGridEndpoint.findAll();
    // }

}

// export { toastuigrid };