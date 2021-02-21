package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public class GasPumpButtonStateS1 extends GasPumpButtonState {
    public GasPumpButtonStateS1 (GasPumpButtonB03 button) {
        super(button);
    }

    @Override
    public void b01Pressed () {
        System.out.println("WARN: Buttons are deactivated in S1.");
    }

    @Override
    public void b02Pressed () {
        System.out.println("WARN: Buttons are deactivated in S1.");
    }

    @Override
    public void b03Pressed () {
        System.out.println("WARN: Buttons are deactivated in S1.");
    }
}
