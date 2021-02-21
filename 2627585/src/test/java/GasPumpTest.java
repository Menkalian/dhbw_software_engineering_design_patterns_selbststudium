import mocks.MockGasPump;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import s60.gasstation.gaspump.GasPumpFactory;
import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.gaspump.button.GasPumpButtonPanel;
import s60.gasstation.gaspump.button.IGasPumpButtonPanel;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS0;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS1;
import s60.gasstation.gaspump.button.state.GasPumpButtonStateS2;
import s60.gasstation.vehicle.Car;
import s60.gasstation.vehicle.EnergyType;
import s60.gasstation.vehicle.Vehicle;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GasPumpTest {
    @TestFactory
    List<DynamicTest> factoryTests () {
        List<DynamicTest> toReturn = new LinkedList<>();

        // Without sucessor
        for (EnergyType type : EnergyType.values()) {
            toReturn.add(DynamicTest.dynamicTest("EnergyType " + type, () -> {
                IGasPump pump = GasPumpFactory.build(type, null);
                Assertions.assertNotNull(pump);
            }));
        }

        // With mocked sucessor
        for (EnergyType type : EnergyType.values()) {
            toReturn.add(DynamicTest.dynamicTest("EnergyType " + type + " handled", () -> {
                IGasPump pump = GasPumpFactory.build(type, new MockGasPump());

                Vehicle tempTestCar;
                do {
                    tempTestCar = new Car();
                } while (tempTestCar.getEnergy() != type);
                final Vehicle testCar = tempTestCar;

                assertDoesNotThrow(() -> pump.serve(testCar));
            }));
        }

        return toReturn;
    }

    @Test
    void testBuildCorrectStructure () {
        IGasPump pump = GasPumpFactory.build(EnergyType.ELECTRIC, null);
        assertNotNull(pump);

        assertNotNull(pump.getConnector());
        assertNotNull(pump.getConnector().getSensor());
        assertNull(pump.getConnector().getVehicle());

        assertNotNull(pump.getConnectorInPumpSensor());
    }

    @Test
    void testButtonPanel () {
        IGasPump pump = GasPumpFactory.build(EnergyType.ELECTRIC, null);
        IGasPumpButtonPanel panel = new GasPumpButtonPanel(pump);
        assertNotNull(panel);
        assertTrue(panel.getB03State() instanceof GasPumpButtonStateS0);

        panel.pressB03();
        assertTrue(panel.getB03State() instanceof GasPumpButtonStateS1);
        pump.getConnector().stickIn(new Car());
        assertTrue(panel.getB03State() instanceof GasPumpButtonStateS1);
        pump.getConnector().putBack();
        assertTrue(panel.getB03State() instanceof GasPumpButtonStateS2);
        panel.pressB03();
        assertTrue(panel.getB03State() instanceof GasPumpButtonStateS0);
    }

}
