package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.IGasPump;

public class GasPumpButtonB02 extends GasPumpButton {
    protected GasPumpButtonB02 (IGasPumpButtonPanel panel, IGasPump pump) {
        super("B02", panel, pump);
    }

    @Override
    public void press () {
        System.out.println("PBTN: Button B02 pressed!");
        panel.getB03State().b02Pressed();
    }
}
