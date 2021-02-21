import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import s60.gasstation.GasStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GasStationTest {
    static Stream<Arguments> laneArguments () {
        List<Arguments> argumentsList = new ArrayList<>(10);
        Random random = new Random();

        for (int i = 0 ; i < 10 ; i++) {
            argumentsList.add(
                    Arguments.of(random.nextInt(100), random.nextInt(100))
            );
        }

        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("laneArguments")
    void testLaneBuilder (int amountCar, int amountTruck) {
        GasStation station = new GasStation.Builder()
                .amountCarLanes(amountCar)
                .amountTruckLanes(amountTruck)
                .build();
        assertEquals(amountCar + amountTruck, station.getLanes().size());
    }

    @Test
    void initializationSuccessful () {
        GasStation station = new GasStation.Builder()
                .amountCarLanes(1)
                .amountTruckLanes(1)
                .build();

        assertEquals(2, station.getLanes().size());
        station.getLanes().forEach(Assertions::assertNotNull);
        assertNotNull(station.getParkingLot());
    }

    @Test
    void simulationWithFewerVehicles () {
        GasStation station = new GasStation.Builder()
                .amountCarLanes(1)
                .amountTruckLanes(1)
                .build();

        // Test the simulation is terminating with fewer vehicles
        station.startSimulation(5, 0.05);
    }
}
