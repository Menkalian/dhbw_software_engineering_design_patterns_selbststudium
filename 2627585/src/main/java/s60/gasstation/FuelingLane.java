package s60.gasstation;

import s60.gasstation.gaspump.GasPumpFactory;
import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.vehicle.EnergyType;
import s60.gasstation.vehicle.Vehicle;
import s60.gasstation.vehicle.VehicleType;

public class FuelingLane implements IFuelingLane {
    private final IGasPump[] gasPumps = new IGasPump[6];
    private final VehicleType type;
    private final IFuelingLane successor;

    public FuelingLane (VehicleType type, IFuelingLane successor) {
        this.type = type;
        this.successor = successor;

        // Init gasPumps
        gasPumps[5] = GasPumpFactory.build(EnergyType.DIESEL, null);
        gasPumps[4] = GasPumpFactory.build(EnergyType.BENZOL, gasPumps[5]);
        gasPumps[3] = GasPumpFactory.build(EnergyType.GAS, gasPumps[4]);
        for (int i = 2 ; i >= 0 ; i--) {
            gasPumps[i] = GasPumpFactory.build(EnergyType.ELECTRIC, gasPumps[i + 1]);
        }
    }

    @Override
    public void serveVehicle (Vehicle vehicle) {
        if (canServe(vehicle)) {
            gasPumps[0].serve(vehicle);
        } else {
            if (successor != null) {
                successor.serveVehicle(vehicle);
            }
        }
    }

    private boolean canServe (Vehicle vehicle) {
        return this.type == vehicle.getType();
    }
}
