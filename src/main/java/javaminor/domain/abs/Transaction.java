package javaminor.domain.abs;

import javaminor.domain.concrete.Basket;
import javaminor.domain.concrete.Bill;
import javaminor.domain.concrete.transactions.TransactionState;
import javaminor.logic.ScanItemRepository;
import javaminor.util.PreferenceUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 9/10/15.
 */
@Getter
@Setter
public abstract class Transaction {
    private static Logger logger = LogManager.getLogger(Transaction.class.getName());

    private int id;

    private Bill bill;
    private TransactionState state;
    private ScanItemRepository repository;
    private Basket basket;

    private double totalPrice;
    private double totalPriceRemaining;

    private Map<String, Double> pricePerCategory;
    private Map<String, Double> priceRemainingPerCategory;

    public Transaction() {
        state = TransactionState.OPENED;

        bill = new Bill();
        repository = new ScanItemRepository();
        basket = new Basket();

        pricePerCategory = new HashMap<>();
        priceRemainingPerCategory = new HashMap<>();

        // populate
        for (String category : PreferenceUtil.getPRICING_CATEGORIES()) {
            pricePerCategory.put(category, new Double(0));
            priceRemainingPerCategory.put(category, new Double(0));
        }

        state = TransactionState.MUTATING;
    }

    /**
     * Handles a provided code, i.e. from a cash register.
     *
     * @param code the provided code.
     * @return true if handled as expected, false otherwise.
     */
    public abstract boolean handleCode(final String code);

    /**
     * Handles a provided payment, i.e. from a cash register.
     *
     * @param item the provided payment item.
     * @return true if handled as expected, false otherwise.
     */
    public abstract boolean handlePayment(final PaymentItem item);

    /**
     * Calculates totals and subtotals (up to now) incorporated in this transaction.
     * <p>
     * Can safely be called multiple times.
     */
    public abstract void calculate();

    /**
     * Closes the transaction, preparation for finishing.
     * <p>
     * Can safely be called once.
     */
    public abstract void closeTransaction();

    /**
     * Finishes and optionally prints the result of the transaction.
     *
     * @param print if true the result will be printed.
     */
    public abstract void finishTransaction(final boolean print);

    /**
     * Returns a ScanItem given a valid identity code.
     *
     * @param code the code to search for.
     * @return the corresponding ScanItem or null if not found
     */
    protected final ScanItem getItemByCode(final String code) {
        return ScanItemRepository.getItemByCode(code);
    }

    /**
     * Adds an item to the basket.
     *
     * @param code an identifying code of the item to add.
     * @return true if added, false otherwise
     */
    protected final boolean addItemToBasket(final String code) {
        return addItemToBasket(getItemByCode(code));
    }

    /**
     * Adds an item to the basket.
     *
     * @param item the item to add.
     * @return true if added, false otherwise
     */
    protected final boolean addItemToBasket(final ScanItem item) {
        if (state == TransactionState.MUTATING) {
            return basket.addToBasket(item);
        }
        logger.error("Can not add items to this transaction, it is no longer in the correct state.");
        return false;
    }

    // TODO replace calculate
    protected boolean calculate2() {
        totalPrice = basket.calculateTotalPrice();
        totalPriceRemaining = totalPrice;
        for (String type : pricePerCategory.keySet()) {
            double priceForType = basket.calculatePriceForPropertyType(type);
            pricePerCategory.put(type, priceForType);
            priceRemainingPerCategory.put(type, priceForType);
        }
        return true;
    }

    //TODO replace finishTransaction
    protected boolean finishTransaction2(final boolean print) {

        bill.setDescription(PreferenceUtil.BILL_SALE_DESCRIPTION);
        bill.setDiscount(basket.getEndDiscount());
        bill.setScanItemsMap(basket.getScannedItems());
        bill.setTotalPrice(totalPrice);
        bill.setTotalCategoryPrices(pricePerCategory);
        bill.setTotalPaid(totalPrice - totalPriceRemaining);

        Map<String, Double> paidPerCategory = new HashMap<>();

        for (String type : pricePerCategory.keySet()) {
            paidPerCategory.put(type, pricePerCategory.get(type) - priceRemainingPerCategory.get(type));
        }

        bill.setTotalCategoryPaid(paidPerCategory);
        if (print) {
            bill.print();
        }
        return true;
    }

    // TODO fix price calculation/payment
    protected final boolean payWithItem(final PaymentItem item) {
        boolean success = false;
        String type = item.getType();
        //if (state != TransactionState.CLOSED & state != TransactionState.PAID) {
            state = TransactionState.PAYING;
            if (item.hasType()) {
                // means it's a category specific item
                success = payPerCategory(item);
            } else {
                // means it's a general item
                success = payNormal(item);
            }
      //  }else{
       //     logger.error("Transaction not in the correct state, cannot pay.");
       // }

        // if everything was paid, state changes to PAID
        if (totalPriceRemaining <= 0 & success) {
            state = TransactionState.PAID;
        }
        return success;
    }

    private boolean payNormal(final PaymentItem item) {
        if (item.getAmount() < -1) {
            logger.error("Payment value is incorrect: " + item.getAmount());
            return false;
        }
        if (item.getAmount() == -1) {
            // shortcut for paying the entire remaining sum
            totalPriceRemaining = 0;

            // put category remaining on 0 as well
            for (String type : priceRemainingPerCategory.keySet()) {
                priceRemainingPerCategory.put(type, 0.0);
            }
            return true;
        } else {
            // might pay with more than is needed, so no capping
            totalPriceRemaining = totalPriceRemaining - item.getAmount();

            double remainder = item.getAmount();

            // TODO infinite loop, when payment is larger than amount?

//            while (remainder > 0) {
//                // bring category remaining down as well
//                for (String type : priceRemainingPerCategory.keySet()) {
//                    remainder = Math.max(0, priceRemainingPerCategory.get(type) - remainder);
//                    priceRemainingPerCategory.put(type, remainder);
//                }
//            }

        }
        return true;
    }

    private boolean payPerCategory(final PaymentItem item) {
        if (!item.hasType()) {
            // if item has no type, can't do anything
            return false;
        }

        String type = item.getType();

        if (!priceRemainingPerCategory.containsKey(type)) {
            // if item type is not found in current list of types, can't do anything
            return false;
        }

        Double priceRemaining = priceRemainingPerCategory.get(type);

        // cannot overpay with a specialty payment item, so capped at 0
        priceRemaining = new Double(priceRemaining - item.getAmount());

        totalPriceRemaining = priceRemaining;
        priceRemainingPerCategory.put(type, priceRemaining);

        return true;
    }
}
