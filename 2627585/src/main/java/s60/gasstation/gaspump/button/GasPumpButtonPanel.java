package s60.gasstation.gaspump.button;

import s60.gasstation.gaspump.IGasPump;
import s60.gasstation.gaspump.button.state.GasPumpButtonState;

import java.util.ArrayList;
import java.util.List;

public class GasPumpButtonPanel implements IGasPumpButtonPanel {
    private final List<GasPumpButton> buttons;
    private final GasPumpButtonB03 button03;

    public GasPumpButtonPanel (IGasPump pump) {
        System.out.println("INIT: Initializing GasPumpButtonPanel");
        buttons = new ArrayList<>(3);
        buttons.add(new GasPumpButtonB01(this, pump));
        buttons.add(new GasPumpButtonB02(this, pump));
        button03 = new GasPumpButtonB03(this, pump);
        buttons.add(button03);
    }

    @Override
    public void pressB01 () {
        buttons.get(0).press();
    }

    @Override
    public void pressB02 () {
        buttons.get(1).press();
    }

    @Override
    public void pressB03 () {
        buttons.get(2).press();
    }

    @Override
    public GasPumpButtonState getB03State () {
        return button03.getState();
    }
}
