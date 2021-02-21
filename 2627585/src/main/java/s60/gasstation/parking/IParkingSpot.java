package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;

public interface IParkingSpot {
    String getId ();

    Vehicle getVehicle ();
    boolean storeVehicle (Vehicle toStore);

    int getWaitedFor ();
    void incrementWaitedFor ();
}
