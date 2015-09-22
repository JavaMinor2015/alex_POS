package javaminor.logic;

import javaminor.domain.abs.Transaction;
import javaminor.domain.concrete.paymentitems.Cash;
import javaminor.domain.concrete.paymentitems.Digital;
import javaminor.domain.concrete.paymentitems.TypeCoupon;
import javaminor.domain.concrete.transactions.Return;
import javaminor.domain.concrete.transactions.Sale;
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

    public CashRegisterImpl(){
        sales = new ArrayList<>();
        returns = new ArrayList<>();
    }



    // TODO possibly warn if previous sale hasn't finished properly yet?
    @Override
    public void startNewSale(){
        if(sale!=null){
            sales.add(sale);
        }
        if(returnz!=null){
            returns.add(returnz);
        }
        sale = new Sale();
        returnz = new Return();
    }

    /**
     * Handles any codes scanned by a cashier.
     *
     * Delegates codes to proper handlers.
     *
     * @param code the scanned code
     */
    @Override
    public void scan(final String code){
        sale.handleCode(code);
        //logger.info("Scanned code: "+ code);
    }


    // button on register
    @Override
    public void payWithTypeCoupon(final String type, final double amount){
        sale.handlePayment(new TypeCoupon(type, amount));
    }

    // button on register
    @Override
    public void payWithCash(final double amount){
        sale.handlePayment(new Cash(amount));
    }

    @Override
    public void payWithDigital(final double amount) {
        sale.handlePayment(new Digital(amount));
    }

    /**
     * Transaction done, print bill and create a new one.
     */
    @Override
    public void finishUpSale(){
        // TODO move print decision up to caller
        logger.info("");
        logger.info("");
        sale.finishTransaction(true);
    }

    @Override
    public void makeReturn(final String code) {
        returnz.handleCode(code);
    }

    @Override
    public void makeReservation(final List<String> codes){
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
