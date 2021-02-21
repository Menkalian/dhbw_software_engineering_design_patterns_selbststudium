package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public class GasPumpButtonStateS0 extends GasPumpButtonState {
    public GasPumpButtonStateS0 (GasPumpButtonB03 button) {
        super(button);
    }

    @Override
    public void b01Pressed () {
        System.out.println("GPBS: B01 pressed in State S0");
        button.getPump().increaseAmount();
    }

    @Override
    public void b02Pressed () {
        System.out.println("GPBS: B02 pressed in State S0");
        button.getPump().decreaseAmount();
    }

    @Override
    public void b03Pressed () {
        System.out.println("GPBS: B03 pressed in State S0");
        button.setState(new GasPumpButtonStateS1(button));
        button.getPump().getConnector().getSensor().addListener(button.getOnConnectedListenerS1());
    }
}
