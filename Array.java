class Array<E> {
    private E[] items;
    private int numItems;

    public Array(int velikost) {
        this.items = (E[])(new Object[velikost]);
        this.numItems = 0;
    }

    public boolean pridejPolozku(E polozka) {
        if (this.items.length <= this.numItems) {
            return false;
        }
        this.items[this.numItems++] = polozka;
        return true;
    }

    public E vratPolozku(int index) {
        if (index < 0 || this.numItems <= index) {
            return null;
        }
        return this.items[index];
    }

    public E odeberPolozku(int index) {
        E odebrano = this.vratPolozku(index);
        if (odebrano == null) {
            return null;
        }
        for (int i = index; i < this.numItems - 1; i++) {
            this.items[i] = this.items[i + 1];
        }
        this.items[--this.numItems] = null;
        return odebrano;
    }

    @Override
    public String toString() {
        String tisk = "Polozky\n";
        for (int i = 0; i < this.numItems; i++) {
            tisk += i + ": " + this.items[i] + "\n";
        }
        return tisk;
    }
}