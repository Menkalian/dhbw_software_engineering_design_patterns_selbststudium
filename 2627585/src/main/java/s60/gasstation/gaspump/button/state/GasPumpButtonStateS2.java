package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public class GasPumpButtonStateS2 extends GasPumpButtonState {
    private String selectedMethod = "Debit";

    public GasPumpButtonStateS2 (GasPumpButtonB03 button) {
        super(button);
    }

    @Override
    public void b01Pressed () {
        selectedMethod = "Debit";
    }

    @Override
    public void b02Pressed () {
        selectedMethod = "Credit";
    }

    @Override
    public void b03Pressed () {
        System.out.println("Paiment completed with method " + selectedMethod);
        System.out.println("Printing receipt:");
        double price = Math.round(Math.random() * 50 * 100) / 100.0 + 30; // Price is random between 30 and 80 €
        System.out.printf(
                """
                *********************
                *      RECEIPT      *
                *                   *
                \033[1,38,5,24m* YOU PAID: %2.2f € *\033[0m
                *                   *
                *   THANK YOU FOR   *
                *  USING DHBW-FUEL  *
                *********************
                """.stripIndent(), price);

        button.setState(new GasPumpButtonStateS0(button));
    }
}
