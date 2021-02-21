package s60.gasstation.vehicle;

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
        int leftLimit = '0';
        int rightLimit = 'z';
        int targetLength = 8;

        id = rng.ints(leftLimit, rightLimit + 1)
                .filter(Character::isLetterOrDigit)
                .limit(targetLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println("INIT: Initializing Vehicle " + id + "(type: " + type + ";energy:" + energy + ")");
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
        System.out.println("VHCL: Vehicle \"" + getId() + "\" was fueled with " + amount + " Units.");
    }

    @Override
    public String toString () {
        return id;
    }
}
