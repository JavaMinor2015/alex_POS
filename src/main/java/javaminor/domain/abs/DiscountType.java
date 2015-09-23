package javaminor.domain.abs;

/**
 * Created by alex on 9/23/15.
 */
public enum DiscountType {
    /**
     * Discount as a fixed amount.
     *
     * Expected value is the amount of discount.
     */
    FIXED_AMOUNT,

    /**
     * Discount per certain amount bought.
     *
     * i.e. Buy X get Y free.
     *
     * Expected values are X and Y.
     */
    ON_AMOUNT_BOUGHT,

    /**
     * Discount as a Percentage.
     *
     * Expected value is the percentage of discount.
     */
    PERCENTAGE,

    /**
     * Discount without value.
     *
     * No expected value.
     */
    NONE;
}
