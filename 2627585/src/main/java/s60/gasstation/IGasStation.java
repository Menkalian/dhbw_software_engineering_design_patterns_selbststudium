package s60.gasstation;

import s60.gasstation.parking.IParkingLot;

import java.util.List;

public interface IGasStation {
    List<IFuelingLane> getLanes ();

    IParkingLot getParkingLot ();

    void startSimulation (int initialVehicles, double probabilityNewVehicles);
}
