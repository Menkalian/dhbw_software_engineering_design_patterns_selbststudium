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
    public void startSimulation () {
        // Fill ParkingLot to 80 % [40 vehicles]
        for (int i = 0 ; i < 40 ; i++) {
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

        Runnable carsArrivingRunnable = () -> {
            double rand = Math.random();

            // 30% probability
            if (rand < 0.30) {
                int amount = (int) ((Math.random() * 3) + 1);
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

            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(carsArrivingRunnable, 1000, 1000, TimeUnit.MILLISECONDS);

        Vehicle nextVehicle;
        while ((nextVehicle = getParkingLot().callNextVehicle()) != null) {
            getLanes().get(0).serveVehicle(nextVehicle);
        }
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
