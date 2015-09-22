package javaminor.domain.abs;

import lombok.Getter;

/**
 * Created by alex on 9/9/15.
 */
public abstract class PaymentItem {
    @Getter
    private double amount;

    @Getter
    private String type;

    /**
     * Constructor.
     *
     * @param type   the type of products this payment counts for
     * @param amount the amount covered by this item, -1 for infinite.
     */
    public PaymentItem(final String type, final double amount) {
        this.amount = amount;
        this.type = type;
    }

    public abstract double remainder(final double price);

    public boolean hasType() {
        if (type == null || "".equals(type)) {
            return false;
        }
        return true;
    }
}
