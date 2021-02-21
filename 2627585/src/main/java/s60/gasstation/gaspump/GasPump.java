package s60.gasstation.gaspump;

import s60.gasstation.gaspump.button.IGasPumpButtonPanel;
import s60.gasstation.gaspump.connector.IConnector;
import s60.gasstation.gaspump.sensor.IConnectorInPumpSensor;
import s60.gasstation.vehicle.EnergyType;
import s60.gasstation.vehicle.Vehicle;

public class GasPump implements IGasPump {
    private final EnergyType type;
    private final IGasPump successor;
    private final IConnector connector;
    private final IConnectorInPumpSensor connectorInPumpSensor;
    private IGasPumpButtonPanel buttonPanel;
    private int currentAmount = 10;

    public GasPump (EnergyType type, IGasPump successor, IConnector connector, IConnectorInPumpSensor connectorInPumpSensor) {
        System.out.println("INIT: Initializing GasPump (" + type + ")");
        this.type = type;
        this.successor = successor;
        this.connector = connector;
        this.connectorInPumpSensor = connectorInPumpSensor;
    }

    public void setButtonPanel (IGasPumpButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    @Override
    public IConnectorInPumpSensor getConnectorInPumpSensor () {
        return connectorInPumpSensor;
    }

    @Override
    public IConnector getConnector () {
        return connector;
    }

    @Override
    public void serve (Vehicle vehicle) {
        if (canServe(vehicle)) {
            System.out.println("GPMP: " + type + "-pump serving vehicle \"" + vehicle.getId() + "\"");

            // Setting amount
            int targetAmount = ((int) (Math.random() * 11)) * 5;
            System.out.println("GPMP: Targeted amount: " + targetAmount);

            if (targetAmount > currentAmount) {
                while (targetAmount > currentAmount) {
                    buttonPanel.pressB01();
                }
            } else {
                while (targetAmount < currentAmount) {
                    buttonPanel.pressB02();
                }
            }

            // Start fueling
            System.out.println("GPMP: Initializing fueling");
            buttonPanel.pressB03();
            getConnector().stickIn(vehicle);

            // Fueling is in finished when method returns
            System.out.println("GPMP: Finished Fueling");
            getConnector().putBack();

            // Choose payment
            System.out.println("GPMP: Choosing payment method");
            if (Math.random() <= 0.5) {
                buttonPanel.pressB01();
            } else {
                buttonPanel.pressB02();
            }

            buttonPanel.pressB03();

            // Done. Fueled and paid. continue with next vehicle
            System.out.println("GPMP: Done serving vehicle \"" + vehicle.getId() + "\"! Next vehicle may be served.");
        } else {
            if (successor != null) {
                successor.serve(vehicle);
            }
        }
    }

    @Override
    public void increaseAmount () {
        currentAmount += 5;
        currentAmount = Math.min(currentAmount, 50);
        System.out.println("GPMP: Increased amount to " + currentAmount);
    }

    @Override
    public void decreaseAmount () {
        currentAmount -= 5;
        currentAmount = Math.max(currentAmount, 0);
        System.out.println("GPMP: Decreased amount to " + currentAmount);
    }

    @Override
    public void doFueling () {
        try {
            System.out.println("GPMP: Starting fueling");

            long duration = (long) ((Math.random() * 2001) + 1000); // allow any number of milliseconds between 1000 and 3000
            System.out.println("GPMP: Fueling takes " + duration + " ms");
            Thread.sleep(duration);

            connector.getVehicle().fuel(currentAmount);
            System.out.println("GPMP: Fueling complete");
        } catch (InterruptedException ex) {
            System.out.println("\033[38;5;9mGPMP: Fueling was interrupted\033[0m");
        }
    }

    private boolean canServe (Vehicle vehicle) {
        return vehicle.getEnergy() == this.type;
    }
}
