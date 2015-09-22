package javaminor.domain.concrete.transactions;

import javaminor.domain.abs.PaymentItem;
import javaminor.domain.abs.Transaction;

/**
 * Created by alex on 9/10/15.
 */
public class Reservation extends Transaction {

    @Override
    public boolean handleCode(String code) {
        return false;
    }

    @Override
    public boolean handlePayment(PaymentItem item) {
        return false;
    }

    @Override
    public void calculate() {

    }

    @Override
    public void closeTransaction() {

    }

    @Override
    public void finishTransaction(boolean print) {

    }
}
