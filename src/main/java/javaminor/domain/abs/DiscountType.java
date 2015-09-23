package javaminor.domain.abs;

import javaminor.domain.concrete.discounts.DiscountFixedAmount;
import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.discounts.DiscountOnAmount;
import javaminor.domain.concrete.discounts.DiscountPercentage;
import lombok.Getter;

/**
 * Created by alex on 9/23/15.
 */
public enum DiscountType {
    /**
     * Discount as a fixed amount.
     *
     * Expected value is the amount of discount.
     */
    FIXED_AMOUNT(DiscountFixedAmount.class),

    /**
     * Discount per certain amount bought.
     *
     * i.e. Buy X get Y free.
     *
     * Expected values are X and Y.
     */
    ON_AMOUNT_BOUGHT(DiscountOnAmount.class),

    /**
     * Discount as a Percentage.
     *
     * Expected value is the percentage of discount.
     */
    PERCENTAGE(DiscountPercentage.class),

    /**
     * Discount without value.
     *
     * No expected value.
     */
    NONE(DiscountNone.class);

    @Getter
    private Class clazz;
    DiscountType(Class name){
        this.clazz =name;
    }
}
