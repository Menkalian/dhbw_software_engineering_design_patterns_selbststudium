package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.button.state.GasPumpButtonState;

public interface IGasPumpButtonPanel {
    void pressB01 ();

    void pressB02 ();

    void pressB03 ();

    GasPumpButtonState getB03State ();
}
