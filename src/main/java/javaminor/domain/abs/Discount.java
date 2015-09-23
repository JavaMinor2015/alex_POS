package javaminor.domain.abs;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alex on 9/7/15.
 */
public abstract class Discount {

    public static final String URL = "/discounts";
    protected static Logger log = LogManager.getLogger(Discount.class.getName());
    @Getter
    protected double discountValue;
    public Discount(double discount){
        this.discountValue = discount;
    }
    public abstract double getDiscountOn(final double price, int amount);

}
