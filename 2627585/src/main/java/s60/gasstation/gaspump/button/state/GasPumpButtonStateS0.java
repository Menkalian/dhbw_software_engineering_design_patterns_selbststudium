package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public class GasPumpButtonStateS0 extends GasPumpButtonState {
    public GasPumpButtonStateS0 (GasPumpButtonB03 button) {
        super(button);
    }

    @Override
    public void b01Pressed () {
        button.getPump().increaseAmount();
    }

    @Override
    public void b02Pressed () {
        button.getPump().decreaseAmount();
    }

    @Override
    public void b03Pressed () {
        button.setState(new GasPumpButtonStateS1(button));
        button.getPump().getConnector().getSensor().addListener(button.getOnConnectedListenerS1());
    }
}
