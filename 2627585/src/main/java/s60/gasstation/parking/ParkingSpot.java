package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;
import s60.gasstation.vehicle.VehicleType;

public class ParkingSpot implements IParkingSpot {
    private final String id;
    private final VehicleType type;
    private final IParkingSpot successor;
    private int waitedFor;
    private Vehicle currentVehicle;

    public ParkingSpot (String id, VehicleType type, IParkingSpot successor) {
        System.out.println("INIT: Initializing ParkingSpot " + id);

        this.id = id;
        this.type = type;
        this.successor = successor;
    }

    @Override
    public String getId () {
        return id;
    }

    @Override
    public Vehicle getVehicle () {
        System.out.println("PSPT: Vehicle leaving " + getId());
        Vehicle toReturn = currentVehicle;

        waitedFor = -1; // reset waiting time
        currentVehicle = null;

        return toReturn;
    }

    @Override
    public boolean storeVehicle (Vehicle toStore) {
        if (canStore(toStore)) {
            System.out.println("PSPT: Storing " + toStore + " at " + getId());
            waitedFor = 0;
            currentVehicle = toStore;
            return true;
        } else {
            if (successor != null) {
                return successor.storeVehicle(toStore);
            }
        }
        return false;
    }

    @Override
    public int getWaitedFor () {
        return waitedFor;
    }

    @Override
    public void incrementWaitedFor () {
        if (currentVehicle != null) {
            waitedFor++;
        }

        if (successor != null) {
            successor.incrementWaitedFor();
        }
    }

    private boolean canStore (Vehicle vehicle) {
        return this.type == vehicle.getType() && currentVehicle == null;
    }
}
