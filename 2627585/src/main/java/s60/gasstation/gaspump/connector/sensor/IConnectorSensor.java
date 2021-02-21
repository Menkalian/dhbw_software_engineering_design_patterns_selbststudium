package s60.gasstation.gaspump.connector.sensor;

public interface IConnectorSensor {
    void addListener (ISensorListener listener);

    void removeListener (ISensorListener listener);

    void connected ();

    interface ISensorListener {
        void onConnectorInVehicle ();
    }
}
