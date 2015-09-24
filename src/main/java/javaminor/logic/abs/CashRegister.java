package javaminor.logic.abs;

import java.util.List;

/**
 * Created by alex on 9/10/15.
 */
public interface CashRegister {
    // TODO rework as template pattern with state pattern?
    // TODO create flowchart

    /**
     * future:
     *
     * openregister()
     *
     * one of following:
     *
     *  newSale
     *      scan()
     *      payWith...()
     *      finishSale()
     *  newReturn
     *      scan()
     *      finishReturn()
     *  newReservation
     *      payWith...()
     *      finishReservation()
     *
     * closeRegister()
     *
     * - warn if starting action Y while still in process of doing action X (state pattern?)
     * - warn when using methods not required
     */



    void openRegister();

    void scan(final String code);
    void payWithTypeCoupon(final String type, final double amount);
    void payWithCash(final double amount);
    void payWithDigital(String identifier, final double amount);

    void startNewSale();
    void finishUpSale(boolean print);

    void makeReturn(final String code);
    void finishUpReturn();

    void makeReservation(final List<String> codes);
    void finishUpReservation();


    void closeRegister();

    void finishAdding();
}
