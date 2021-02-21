package s60.gasstation.gaspump.connector;

import s60.gasstation.gaspump.connector.sensor.IConnectorSensor;
import s60.gasstation.vehicle.Vehicle;

public interface IConnector {
    IConnectorSensor getSensor ();

    Vehicle getVehicle ();

    void stickIn (Vehicle vehicle);

    void putBack ();
}
