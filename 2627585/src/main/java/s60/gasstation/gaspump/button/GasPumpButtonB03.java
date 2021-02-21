package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.gaspump.button.state.GasPumpButtonState;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS0;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS2;
import s60.gasstation.gaspump.connector.sensor.IConnectorSensor;
import s60.gasstation.gaspump.sensor.IConnectorInPumpSensor;

public class GasPumpButtonB03 extends GasPumpButton {
    private GasPumpButtonState state;
    // Listeners for the Sensors
    private final IConnectorInPumpSensor.ISensorListener onConnectorBackListener = () -> {
        System.out.println("BTN3: Connector back in GasPump");
        setState(new GasPumpButtonStateS2(this));
        removeListeners();
    };
    private final IConnectorSensor.ISensorListener onConnectedListenerS1 = () -> {
        System.out.println("BTN3: Connector connected to vehicle");
        pump.doFueling();
        pump.getConnectorInPumpSensor().addListener(onConnectorBackListener);
    };

    protected GasPumpButtonB03 (IGasPumpButtonPanel panel, IGasPump pump) {
        super("B03", panel, pump);
        System.out.println("INIT: Initializing GasPumpButtonB03");
        state = new GasPumpButtonStateS0(this);
    }

    public GasPumpButtonState getState () {
        return state;
    }

    public void setState (GasPumpButtonState state) {
        this.state = state;
    }

    @Override
    public void press () {
        System.out.println("PBTN: Button B03 pressed!");
        panel.getB03State().b03Pressed();
    }

    public IConnectorSensor.ISensorListener getOnConnectedListenerS1 () {
        return onConnectedListenerS1;
    }

    // Needed since the listener can't reference itself
    private void removeListeners () {
        System.out.println("BTN3: Removing listeners from sensors");
        pump.getConnectorInPumpSensor().removeListener(onConnectorBackListener);
        pump.getConnector().getSensor().removeListener(onConnectedListenerS1);
    }
}
