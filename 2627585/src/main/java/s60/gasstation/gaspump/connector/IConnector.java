package s60.gasstation.gaspump.connector;

import s60.gasstation.gaspump.connector.sensor.IConnectorSensor;
import s60.gasstation.vehicle.Vehicle;

public interface IConnector {
    void stickIn (Vehicle vehicle);

    Vehicle getVehicle ();

    void putBack ();

    IConnectorSensor getSensor ();
}
