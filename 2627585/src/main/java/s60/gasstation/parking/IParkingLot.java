package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;

public interface IParkingLot {
    Vehicle callNextVehicle ();

    void storeVehicle (Vehicle toStore);
}
