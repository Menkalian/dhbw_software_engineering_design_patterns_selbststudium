package s60.gasstation.gaspump.sensor;

import java.util.LinkedList;
import java.util.List;

public class ConnectorInPumpSensor implements IConnectorInPumpSensor {
    private final List<ISensorListener> listeners = new LinkedList<>();

    @Override
    public void addListener (ISensorListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener (ISensorListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void connectorIn () {
        listeners.forEach(ISensorListener::onConnectorStuckIn);
    }
}
