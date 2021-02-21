import org.junit.jupiter.api.Test;
import s60.gasstation.parking.Display;
import s60.gasstation.parking.IDisplay;
import s60.gasstation.parking.IParkingLot;
import s60.gasstation.parking.ParkingLot;
import s60.gasstation.vehicle.Car;
import s60.gasstation.vehicle.Truck;
import s60.gasstation.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingTest {
    @Test
    void testParkingLotInitializing () {
        IDisplay display = new Display();
        IParkingLot lot = new ParkingLot(display);

        assertNotNull(display);
        assertNotNull(lot);
    }

    @Test
    void testVehiclesNotDuplicating () {
        IParkingLot lot = new ParkingLot(new Display());

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car());
        vehicles.add(new Truck());
        vehicles.add(new Car());

        vehicles.forEach(lot::storeVehicle);

        for (int i = 0 ; i < vehicles.size() ; i++) {
            assertNotNull(lot.callNextVehicle());
        }

        // Should now be empty
        assertNull(lot.callNextVehicle());
    }

    @Test
    void testOutputOrder () {
        IParkingLot lot = new ParkingLot(new Display());

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car());
        vehicles.add(new Truck());
        vehicles.add(new Car());

        vehicles.forEach(lot::storeVehicle);

        // Trucks are served first (if they arrived at the same time as the cars
        assertEquals(vehicles.get(1), lot.callNextVehicle());

        // After that the cars are served even if another truck arrives
        assertEquals(vehicles.get(0), lot.callNextVehicle());
        Vehicle t = new Truck();
        lot.storeVehicle(t);
        assertEquals(vehicles.get(2), lot.callNextVehicle());

        // The later arriving truck is served later
        assertEquals(t, lot.callNextVehicle());
    }

    @Test
    void testMaximalCapacity() {
        IParkingLot lot = new ParkingLot(new Display());

        // Store 20 trucks (15 is maximum)
        for (int i = 0 ; i < 20 ; i++) {
            lot.storeVehicle(new Truck());
        }

        // Store 50 cars (35 is maximum)
        for (int i = 0 ; i < 35 ; i++) {
            lot.storeVehicle(new Car());
        }

        // First 15 results should be trucks
        for (int i = 0 ; i < 15 ; i++) {
            Vehicle nextVehicle = lot.callNextVehicle();
            assertNotNull(nextVehicle);
            assertTrue(nextVehicle instanceof Truck);
        }

        // Next 35 are cars
        for (int i = 0 ; i < 35 ; i++) {
            Vehicle nextVehicle = lot.callNextVehicle();
            assertNotNull(nextVehicle);
            assertTrue(nextVehicle instanceof Car);
        }

        // After that no other may come out
        assertNull(lot.callNextVehicle());
    }
}
