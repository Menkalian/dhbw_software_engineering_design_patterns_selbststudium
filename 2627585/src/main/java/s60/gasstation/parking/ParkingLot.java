package s60.gasstation.parking;

import s60.gasstation.vehicle.Vehicle;
import s60.gasstation.vehicle.VehicleType;

import java.util.Arrays;
import java.util.Comparator;

public class ParkingLot implements IParkingLot {
    private final IParkingSpot[][] spots = new IParkingSpot[5][10];
    private final IDisplay display;

    public ParkingLot (IDisplay display) {
        this.display = display;

        // Init parking spots (in reversed order to initialize successors
        int index = 49;
        IParkingSpot successor = null;

        // Last 35 are car spots
        for (int i = 0 ; i < 35 ; i++) {
            ParkingSpot spot = new ParkingSpot(String.format("P%02d", index), VehicleType.CAR, successor);
            spots[index / 10][index % 10] = spot;
            successor = spot;
            index--;
        }
        // First 15 are truck spots
        for (int i = 0 ; i < 15 ; i++) {
            ParkingSpot spot = new ParkingSpot(String.format("P%02d", index), VehicleType.TRUCK, successor);
            spots[index / 10][index % 10] = spot;
            successor = spot;
            index--;
        }
    }

    @Override
    public Vehicle callNextVehicle () {
        Comparator<IParkingSpot> spotComparator = (spot1, spot2) -> {
            // More waited -> Earlier serve
            int waitedForCompare = Integer.compare(spot1.getWaitedFor(), spot2.getWaitedFor());

            if (waitedForCompare == 0) {
                // Lower ID -> Earlier serve ; Empty spots always have waited for -1 so they are always below spots with cars
                return -1 * spot1.getId().compareTo(spot2.getId());
            } else {
                return waitedForCompare;
            }
        };

        IParkingSpot nextSpot = Arrays.stream(spots).map(row -> Arrays.stream(row).max(spotComparator).orElse(null)).max(spotComparator).orElse(null);
        Vehicle nextVehicle = null;

        if (nextSpot != null) {
            nextVehicle = nextSpot.getVehicle();
        }

        display.displayNextVehicle(nextVehicle);
        return nextVehicle;
    }

    @Override
    public void storeVehicle (Vehicle toStore) {
        // COR
        spots[0][0].storeVehicle(toStore);
    }
}
