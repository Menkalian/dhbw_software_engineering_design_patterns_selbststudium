package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;

public class Display implements IDisplay {
    @Override
    public void displayNextVehicle (Vehicle vehicle) {
        System.out.println("\033[1;38;5;75m***NEXT UP: SERVING " + vehicle.getId() + "***\033[0m");
    }
}
