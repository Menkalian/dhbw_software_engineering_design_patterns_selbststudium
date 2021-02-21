package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.IGasPump;

public abstract class GasPumpButton {
    protected final String id;
    protected final IGasPumpButtonPanel panel;
    protected final IGasPump pump;

    protected GasPumpButton (String id, IGasPumpButtonPanel panel, IGasPump pump) {
        this.id = id;
        this.panel = panel;
        this.pump = pump;
    }

    public IGasPump getPump () {
        return pump;
    }

    public abstract void press ();
}
