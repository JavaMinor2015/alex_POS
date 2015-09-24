package javaminor.domain.concrete.discounts;

import javaminor.domain.abs.Discount;
import org.joda.time.DateTime;

/**
 * Created by alex on 9/24/15.
 */
public class DiscountOnDate extends Discount {

    private final DateTime dateTime;

    public DiscountOnDate(DateTime dateTime, double value){
        super(value);
        this.dateTime = dateTime;
    }

    @Override
    public double getDiscountOn(double price, int amount) {
//
//        if(DateTime.now().isBefore()){
//
//        }

        return 0;
    }
}
