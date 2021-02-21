package s60.gasstation.vehicle;

import java.util.PrimitiveIterator;
import java.util.Random;

public abstract class Vehicle {
    protected final VehicleType type;
    protected final EnergyType energy;
    protected final String id;

    public Vehicle (VehicleType type) {
        this.type = type;

        Random rng = new Random();
        energy = EnergyType.values()[rng.nextInt(EnergyType.values().length)];

        // Generate random ID
        PrimitiveIterator.OfInt iterator = rng.ints().iterator();
        StringBuilder tempId = new StringBuilder();
        while (iterator.hasNext() && tempId.length() < 8) {
            char c = ((char) iterator.nextInt());
            if (Character.isLetterOrDigit(c)) {
                tempId.append(c);
            }
        }
        id = tempId.toString();
    }

    public VehicleType getType () {
        return type;
    }

    public EnergyType getEnergy () {
        return energy;
    }

    public String getId () {
        return id;
    }

    public void fuel (int amount) {
        System.out.println("Vehicle \"" + getId() + "\" was fueled with " + amount + " Units.");
    }
}
