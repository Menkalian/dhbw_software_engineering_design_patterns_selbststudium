package s60.gasstation.gaspump.connector;

import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.gaspump.connector.sensor.IConnectorSensor;
import s60.gasstation.vehicle.Vehicle;

public class Connector implements IConnector {
    private final IConnectorSensor sensor;
    private IGasPump pump;
    private Vehicle currentVehicle;

    public Connector (IConnectorSensor sensor) {
        this.sensor = sensor;
    }

    public void setPump (IGasPump pump) {
        this.pump = pump;
    }

    @Override
    public void stickIn (Vehicle vehicle) {
        currentVehicle = vehicle;
        sensor.connected();
    }

    @Override
    public Vehicle getVehicle () {
        return currentVehicle;
    }

    @Override
    public void putBack () {
        currentVehicle = null;
        pump.getConnectorInPumpSensor().connectorIn();
    }

    @Override
    public IConnectorSensor getSensor () {
        return sensor;
    }
}
