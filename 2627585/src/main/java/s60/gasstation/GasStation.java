package s60.gasstation;

import s60.gasstation.parking.Display;
import s60.gasstation.parking.IDisplay;
import s60.gasstation.parking.IParkingLot;
import s60.gasstation.parking.ParkingLot;
import s60.gasstation.vehicle.Car;
import s60.gasstation.vehicle.Truck;
import s60.gasstation.vehicle.Vehicle;
import s60.gasstation.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class GasStation implements IGasStation {
    private final List<IFuelingLane> lanes;
    private final IParkingLot parkingLot;

    private GasStation (List<IFuelingLane> lanes, IParkingLot parkingLot) {
        this.lanes = lanes;
        this.parkingLot = parkingLot;
    }

    @Override
    public List<IFuelingLane> getLanes () {
        return lanes;
    }

    @Override
    public IParkingLot getParkingLot () {
        return parkingLot;
    }

    @Override
    public void startSimulation (int initialVehicles, double probabilityNewVehicles) {
        System.out.println("GSTN: Starting Simulation");

        Consumer<Integer> generateCars = (amount) -> {
            for (int i = 0 ; i < amount ; i++) {
                Vehicle arriving;

                // 30% trucks according to percentage of parking spots
                double cartypeRandom = Math.random();
                if (cartypeRandom < 0.30) {
                    arriving = new Truck();
                } else {
                    arriving = new Car();
                }

                getParkingLot().storeVehicle(arriving);
            }
        };

        // Store initial vehicles
        generateCars.accept(initialVehicles);

        Runnable carsArrivingRunnable = () -> {
            double rand = Math.random();

            // 30% probability
            if (rand < probabilityNewVehicles) {
                int amount = (int) ((Math.random() * 3) + 1);
                System.out.println("GSTN: " + amount + " vehicles arriving.");
                generateCars.accept(amount);
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(carsArrivingRunnable, 1000, 1000, TimeUnit.MILLISECONDS);

        Vehicle nextVehicle;
        while ((nextVehicle = getParkingLot().callNextVehicle()) != null) {
            getLanes().get(0).serveVehicle(nextVehicle);
            System.out.println("GSTN: Done serving " + nextVehicle);
            System.out.println();
        }
        System.out.println("\nSYSM: All vehicles done. Shutting down!\n");
        executorService.shutdown();
    }

    public static class Builder {
        private int amountCarLanes;
        private int amountTruckLanes;

        public Builder amountCarLanes (int amount) {
            this.amountCarLanes = amount;
            return this;
        }

        public Builder amountTruckLanes (int amount) {
            this.amountTruckLanes = amount;
            return this;
        }

        public GasStation build () {
            List<IFuelingLane> lanes = new ArrayList<>(amountCarLanes + amountTruckLanes);

            // Fill up from the end
            IFuelingLane successor = null;
            for (int i = 0 ; i < amountTruckLanes ; i++) {
                IFuelingLane lane = new FuelingLane(VehicleType.TRUCK, successor);
                lanes.add(0, lane);
                successor = lane;
            }
            for (int i = 0 ; i < amountCarLanes ; i++) {
                IFuelingLane lane = new FuelingLane(VehicleType.CAR, successor);
                lanes.add(0, lane);
                successor = lane;
            }

            IDisplay display = new Display();
            IParkingLot lot = new ParkingLot(display);

            return new GasStation(lanes, lot);
        }
    }
}
