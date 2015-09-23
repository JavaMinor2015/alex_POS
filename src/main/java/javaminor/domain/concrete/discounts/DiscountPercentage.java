package javaminor.domain.concrete.discounts;

import javaminor.domain.abs.Discount;
import javaminor.domain.abs.DiscountType;
import javaminor.util.StrUtil;

/**
 * Created by alex on 9/7/15.
 */
public class DiscountPercentage extends Discount {
    private final DiscountType type = DiscountType.PERCENTAGE;

    public DiscountPercentage(final long discount) {
        super(discount);
        if(discount > 100){
            log.info("Discount is more than 100%!");
        }
    }

    @Override
    public double getDiscountOn(final double price, int amount) {
        return (price * amount) * ((100-discountValue)/100);
    }

    @Override
    public String toString(){
        return StrUtil.twoDecimal(super.discountValue) + "% ";
    }
}
