package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;

public interface IParkingSpot {
    Vehicle getVehicle ();

    boolean storeVehicle (Vehicle toStore);

    String getId();

    int getWaitedFor ();

    void incrementWaitedFor ();
}
