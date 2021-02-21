package s60.gasstation.gaspump.button.state;

import s60.gasstation.gaspump.button.GasPumpButtonB03;

public class GasPumpButtonStateS2 extends GasPumpButtonState {
    private String selectedMethod = "Debit";

    public GasPumpButtonStateS2 (GasPumpButtonB03 button) {
        super(button);
    }

    @Override
    public void b01Pressed () {
        System.out.println("GPBS: B01 pressed in State S2");
        selectedMethod = "Debit ";
    }

    @Override
    public void b02Pressed () {
        System.out.println("GPBS: B02 pressed in State S2");
        selectedMethod = "Credit";
    }

    @Override
    public void b03Pressed () {
        System.out.println("GPBS: B01 pressed in State S0");
        System.out.println("INFO: Payment completed with method " + selectedMethod);
        System.out.println("INFO: Printing receipt:");
        double price = Math.round(Math.random() * 50 * 100) / 100.0 + 30; // Price is random between 30 and 80 €
        System.out.printf(
                """
                RCPT: *********************
                RCPT: *      RECEIPT      *
                RCPT: *                   *
                RCPT: \033[1;38;5;26m* YOU PAID: %2.2f € *\033[0m
                RCPT: *  using "%s"  *
                RCPT: *                   *
                RCPT: *   THANK YOU FOR   *
                RCPT: *  USING DHBW-FUEL  *
                RCPT: *********************
                """.stripIndent(), price, selectedMethod);

        button.setState(new GasPumpButtonStateS0(button));
    }
}
