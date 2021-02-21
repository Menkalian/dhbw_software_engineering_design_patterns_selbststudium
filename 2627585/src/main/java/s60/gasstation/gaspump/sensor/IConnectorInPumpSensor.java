package s60.gasstation.gaspump.sensor;

public interface IConnectorInPumpSensor {
    void addListener (ISensorListener listener);

    void removeListener (ISensorListener listener);

    void connectorIn ();

    interface ISensorListener {
        void onConnectorStuckIn ();
    }
}
