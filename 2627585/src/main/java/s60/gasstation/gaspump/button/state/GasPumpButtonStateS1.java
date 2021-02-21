package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public class GasPumpButtonStateS1 extends GasPumpButtonState {
    public GasPumpButtonStateS1 (GasPumpButtonB03 button) {
        super(button);
    }

    @Override
    public void b01Pressed () {
        System.out.println("GPBS: B01 pressed in State S1");
        System.out.println("\033[38;5;221mWARN: Buttons are deactivated in S1.\033[0m");
    }

    @Override
    public void b02Pressed () {
        System.out.println("GPBS: B02 pressed in State S1");
        System.out.println("\033[38;5;221mWARN: Buttons are deactivated in S1.\033[0m");
    }

    @Override
    public void b03Pressed () {
        System.out.println("GPBS: B03 pressed in State S1");
        System.out.println("\033[38;5;221mWARN: Buttons are deactivated in S1.\033[0m");
    }
}
