package s60;

import s60.gasstation.GasStation;
import s60.gasstation.IGasStation;

public abstract class Main {
    public static void main (String[] args) {
        IGasStation station = new GasStation.Builder().amountCarLanes(1).amountTruckLanes(1).build();

        // Fill ParkingLot to 80 % [40 vehicles]
        // 30% probability for new vehicles per second
        station.startSimulation(40, 0.3);
    }
}
