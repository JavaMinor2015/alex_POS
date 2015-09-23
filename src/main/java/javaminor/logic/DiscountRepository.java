package javaminor.logic;

import javaminor.domain.abs.Discount;
import javaminor.domain.concrete.discounts.DiscountFixedAmount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class DiscountRepository {

    private static List<Discount> discountList;

    static{
        discountList = new ArrayList<>();

        discountList.add(new DiscountFixedAmount(10));
    }

    public static List<Discount> getDiscounts(int startIndex, int size) {
        int start = startIndex;
        int end = startIndex + size;

        if(start > discountList.size()){
            start = discountList.size() - size;
        }
        if(end > discountList.size()){
            end = discountList.size();
        }
        return discountList.subList(start,end);

    }
}
