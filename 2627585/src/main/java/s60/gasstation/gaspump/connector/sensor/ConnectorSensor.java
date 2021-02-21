package s60.gasstation.gaspump.connector.sensor;

import java.util.LinkedList;
import java.util.List;

public class ConnectorSensor implements IConnectorSensor {
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
    public void connected () {
        listeners.forEach(ISensorListener::onConnectorInVehicle);
    }
}
