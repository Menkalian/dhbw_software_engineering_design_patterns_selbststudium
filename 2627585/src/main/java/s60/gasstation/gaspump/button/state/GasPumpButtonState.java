package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public abstract class GasPumpButtonState {
    protected final GasPumpButtonB03 button;

    protected GasPumpButtonState (GasPumpButtonB03 button) {
        this.button = button;
    }

    public abstract void b01Pressed ();

    public abstract void b02Pressed ();

    public abstract void b03Pressed ();
}
