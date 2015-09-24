package javaminor.logic;

import javaminor.SOAPclient.PaymentClient;
import javaminor.domain.abs.Transaction;
import javaminor.domain.concrete.paymentitems.Cash;
import javaminor.domain.concrete.paymentitems.Digital;
import javaminor.domain.concrete.paymentitems.TypeCoupon;
import javaminor.domain.concrete.transactions.Return;
import javaminor.domain.concrete.transactions.Sale;
import javaminor.domain.concrete.transactions.TransactionState;
import javaminor.logic.abs.CashRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
public class CashRegisterImpl implements CashRegister {
    protected static Logger logger = LogManager.getLogger(CashRegisterImpl.class.getName());
    private Transaction sale;
    private Transaction returnz;
    private List<Transaction> sales;
    private List<Transaction> returns;

    // TODO clean up mess, make all actions similar (Reservation/Return/Sale)

    public CashRegisterImpl() {
        sales = new ArrayList<>();
        returns = new ArrayList<>();
    }


    // TODO possibly warn if previous sale hasn't finished properly yet?
    @Override
    public void startNewSale() {
        if (sale != null) {
            sales.add(sale);
        }
        if (returnz != null) {
            returns.add(returnz);
        }
        sale = new Sale();
        sale.setState(TransactionState.OPENED);
        returnz = new Return();
    }

    /**
     * Handles any codes scanned by a cashier.
     * <p>
     * Delegates codes to proper handlers.
     *
     * @param code the scanned code
     */
    @Override
    public void scan(final String code) {
        sale.setState(TransactionState.MUTATING);
        sale.handleCode(code);
//        logger.info("Scanned code: "+ code);
    }

    // button on register
    @Override
    public void payWithTypeCoupon(final String type, final double amount) {
        sale.setState(TransactionState.PAYING);
        sale.handlePayment(new TypeCoupon(type, amount));
    }

    // button on register
    @Override
    public void payWithCash(final double amount) {
        sale.setState(TransactionState.PAYING);
        sale.handlePayment(new Cash(amount));
    }

    @Override
    public void payWithDigital(String identifier, final double amount) {
        sale.setState(TransactionState.PAYING);
        boolean success = false;
        if (new PaymentClient().checkValidity(identifier, amount)) {
            success = sale.handlePayment(new Digital(amount));
        }
        if (success) {
            PaymentClient paymentClient = new PaymentClient();
            paymentClient.pay(identifier,amount);
            logger.info("Payment card is valid and the budget is sufficient. Paid " + amount);
        } else {
            sale.setTotalPriceRemaining(sale.getTotalPriceRemaining() + amount);
            logger.info("Payment card is not valid or the budget is insufficient: " + identifier);
        }

    }

    /**
     * Transaction done, print bill and create a new one.
     */
    @Override
    public void finishUpSale(final boolean print) {
        sale.finishTransaction(print);
        if(sale.getState().equals(TransactionState.PAID)){
            sale.setState(TransactionState.CLOSED);
        }else{
            logger.info("State not paid, can't close.");
        }

    }

    @Override
    public void makeReturn(final String code) {
        returnz.handleCode(code);
    }

    @Override
    public void makeReservation(final List<String> codes) {
        // TODO implement
    }

    @Override
    public void finishUpReservation() {
        // TODO implement
    }

    @Override
    public void finishUpReturn() {
        returnz.closeTransaction();
        returnz.finishTransaction(true);
    }

    @Override
    public void openRegister() {

    }

    @Override
    public void closeRegister() {

    }

    @Override
    public void finishAdding() {
        sale.closeTransaction();
    }
}
