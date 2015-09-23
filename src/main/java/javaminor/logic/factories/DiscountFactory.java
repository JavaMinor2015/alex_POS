package javaminor.logic.factories;

import javaminor.domain.abs.Discount;
import javaminor.domain.abs.DiscountType;
import javaminor.domain.concrete.discounts.DiscountFixedAmount;
import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.discounts.DiscountOnAmount;
import javaminor.domain.concrete.discounts.DiscountPercentage;

/**
 * Created by alex on 9/23/15.
 */
public class DiscountFactory {

    /**
     * Can be used to create a discount.
     * See {@see DiscountType} to see expected values;
     *
     * @param type the type of discount
     * @param value the value(s) for the discount
     * @return the created discount or null
     */
    public Discount createDiscount(final DiscountType type, final double... value){
        try{
            switch (type) {
                case NONE: return new DiscountNone();
                case FIXED_AMOUNT: return new DiscountFixedAmount(value[0]);
                case PERCENTAGE: return new DiscountPercentage((long)value[0]);
                case ON_AMOUNT_BOUGHT: return new DiscountOnAmount((int)value[0],(int)value[1]);
            }
        }catch(ArrayIndexOutOfBoundsException e){
            // die
        }
        return null;
    }
}
