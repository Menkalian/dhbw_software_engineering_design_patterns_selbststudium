package s60.gasstation.gaspump;

import s60.gasstation.gaspump.button.GasPumpButtonPanel;
import s60.gasstation.gaspump.button.IGasPumpButtonPanel;
import s60.gasstation.gaspump.connector.Connector;
import s60.gasstation.gaspump.connector.sensor.ConnectorSensor;
import s60.gasstation.gaspump.connector.sensor.IConnectorSensor;
import s60.gasstation.gaspump.sensor.ConnectorInPumpSensor;
import s60.gasstation.gaspump.sensor.IConnectorInPumpSensor;
import s60.gasstation.vehicle.EnergyType;

public abstract class GasPumpFactory {
    public static IGasPump build (EnergyType type, IGasPump successor) {

        IConnectorSensor connectorSensor = new ConnectorSensor();
        Connector connector = new Connector(connectorSensor);

        IConnectorInPumpSensor connectorInSensor = new ConnectorInPumpSensor();

        GasPump toReturn = new GasPump(type, successor, connector, connectorInSensor);

        IGasPumpButtonPanel buttonPanel = new GasPumpButtonPanel(toReturn);
        toReturn.setButtonPanel(buttonPanel);

        connector.setPump(toReturn);

        return toReturn;
    }
}
