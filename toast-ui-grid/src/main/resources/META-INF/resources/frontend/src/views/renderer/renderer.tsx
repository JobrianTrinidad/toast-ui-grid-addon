export class RowNumberRenderer {
    public el: HTMLElement;

    constructor(props: { formattedValue: string }) {
        const el = document.createElement('span');
        el.innerHTML = `No.${props.formattedValue}`;
        this.el = el;
    }

    getElement(): HTMLElement {
        return this.el;
    }

    render(props: { formattedValue: string }) {
        this.el.innerHTML = `No.${props.formattedValue}`;
    }
}
export class CheckboxRenderer {
    public el: HTMLElement;

    constructor(props: { value: any; grid: any; rowKey: any }) {
        const { grid, rowKey } = props;

        const label = document.createElement('label');
        label.className = 'checkbox tui-grid-row-header-checkbox';
        label.setAttribute('for', String(rowKey));

        const hiddenInput = document.createElement('input');
        hiddenInput.className = 'hidden-input';
        hiddenInput.id = String(rowKey);

        const customInput = document.createElement('span');
        customInput.className = 'custom-input';

        label.appendChild(hiddenInput);
        label.appendChild(customInput);

        hiddenInput.type = 'checkbox';
        label.addEventListener('click', (ev) => {
            ev.preventDefault();

            if (ev.shiftKey) {
                grid[!hiddenInput.checked ? 'checkBetween' : 'uncheckBetween'](rowKey);
                return;
            }

            grid[!hiddenInput.checked ? 'check' : 'uncheck'](rowKey);
        });

        this.el = label;

        this.render(props);
    }

    getElement(): HTMLElement {
        return this.el;
    }

    render(props: { value: any }) {
        const hiddenInput = this.el.querySelector('.hidden-input') as HTMLInputElement;
        const checked = Boolean(props.value);

        hiddenInput.checked = checked;
    }
}
