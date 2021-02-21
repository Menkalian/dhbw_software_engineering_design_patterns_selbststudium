package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.gaspump.button.state.GasPumpButtonState;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS0;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS2;
import s60.gasstation.gaspump.connector.sensor.IConnectorSensor;
import s60.gasstation.gaspump.sensor.IConnectorInPumpSensor;

public class GasPumpButtonB03 extends GasPumpButton {
    private GasPumpButtonState state;
    private final IConnectorInPumpSensor.ISensorListener onConnectorBackListener = () -> {
        setState(new GasPumpButtonStateS2(this));
        removeListeners();
    };
    private final IConnectorSensor.ISensorListener onConnectedListenerS1 = () -> {
        pump.doFueling();
        pump.getConnectorInPumpSensor().addListener(onConnectorBackListener);
    };

    protected GasPumpButtonB03 (IGasPumpButtonPanel panel, IGasPump pump) {
        super("B03", panel, pump);
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
        panel.getB03State().b03Pressed();
    }

    public IConnectorSensor.ISensorListener getOnConnectedListenerS1 () {
        return onConnectedListenerS1;
    }

    // Needed since the listener can't reference itself
    private void removeListeners () {
        pump.getConnectorInPumpSensor().removeListener(onConnectorBackListener);
        pump.getConnector().getSensor().removeListener(onConnectedListenerS1);
    }
}
