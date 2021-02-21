package mocks;

import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.gaspump.connector.IConnector;
import s60.gasstation.gaspump.sensor.IConnectorInPumpSensor;
import s60.gasstation.vehicle.Vehicle;

public class MockGasPump implements IGasPump {

    @Override
    public IConnectorInPumpSensor getConnectorInPumpSensor () {
        throw new RuntimeException("MockPump should not be reached");
    }

    @Override
    public IConnector getConnector () {
        throw new RuntimeException("MockPump should not be reached");
    }

    @Override
    public void serve (Vehicle vehicle) {
        throw new RuntimeException("MockPump should not be reached");
    }

    @Override
    public void increaseAmount () {
        throw new RuntimeException("MockPump should not be reached");
    }

    @Override
    public void decreaseAmount () {
        throw new RuntimeException("MockPump should not be reached");
    }

    @Override
    public void doFueling () {
        throw new RuntimeException("MockPump should not be reached");
    }
}

