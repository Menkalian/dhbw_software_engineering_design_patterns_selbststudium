package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.IGasPump;

public class GasPumpButtonB01 extends GasPumpButton {
    protected GasPumpButtonB01 (IGasPumpButtonPanel panel, IGasPump pump) {
        super("B01", panel, pump);
    }

    @Override
    public void press () {
        System.out.println("PBTN: Button B01 pressed!");
        panel.getB03State().b01Pressed();
    }
}
