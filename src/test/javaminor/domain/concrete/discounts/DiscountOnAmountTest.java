package javaminor.domain.concrete.discounts;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 9/9/15.
 */
public class DiscountOnAmountTest {

    @Test
    public void testGetDiscountOn() throws Exception {
        DiscountOnAmount testDiscount = new DiscountOnAmount(5,1);

        // we bought 5 products at 2.00 each, action is buy 5 get 1 free, so we pay for 5 (10)
        assertEquals(10.0,testDiscount.getDiscountOn(2,5),0.01);

        // we bought 6 products at 2.00 each, action is buy 5 get 1 free, so we pay for 5 (10)
        assertEquals(10.0,testDiscount.getDiscountOn(2,6),0.01);

        // we bought 10 products at 2.00 each, action is buy 5 get 1 free, so we pay for 9 (18)
        assertEquals(18.0,testDiscount.getDiscountOn(2,10),0.01);

        // we bought 12 products at 2.00 each, action is buy 5 get 1 free, so we pay for 10 (20)
        assertEquals(20.0,testDiscount.getDiscountOn(2,12),0.01);
    }
}