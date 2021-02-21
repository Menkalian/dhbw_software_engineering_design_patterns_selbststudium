package s60.gasstation.gaspump;

import s60.gasstation.gaspump.connector.IConnector;
import s60.gasstation.gaspump.sensor.IConnectorInPumpSensor;
import s60.gasstation.vehicle.Vehicle;

public interface IGasPump {
    void serve (Vehicle vehicle);

    IConnectorInPumpSensor getConnectorInPumpSensor ();

    IConnector getConnector ();

    void increaseAmount ();

    void decreaseAmount ();

    void doFueling ();
}
