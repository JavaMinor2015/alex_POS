package javaminor.domain.concrete.transactions;

/**
 * Created by alex on 9/15/15.
 */
public enum TransactionState {
    /**
     * The Transaction has been opened, nothing has been done yet.
     */
    OPENED,

    /**
     * The transaction is mutating, items are being added etc,
     */
    MUTATING,

    /**
     * Payments are being made, possibly in installments.
     */
    PAYING,

    /**
     * The transaction has been paid.
     */
    PAID,

    /**
     * The transaction has been closed and can no longer be modified.
     */
    CLOSED;
}
