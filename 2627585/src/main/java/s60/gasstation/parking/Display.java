package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;

public class Display implements IDisplay {
    @Override
    public void displayNextVehicle (Vehicle vehicle) {
        if (vehicle != null) {
            System.out.println("DSPY: \033[1;38;5;75m***NEXT UP: SERVING " + vehicle + "***\033[0m");
        }
    }
}
