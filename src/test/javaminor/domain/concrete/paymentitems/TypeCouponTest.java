package javaminor.domain.concrete.paymentitems;

import javaminor.domain.abs.PaymentItem;
import junit.framework.TestCase;

/**
 * Created by alex on 9/9/15.
 */
public class TypeCouponTest extends TestCase {

    public void testRemainder() throws Exception {
        PaymentItem coupon = new TypeCoupon("ECO",50);

        assertEquals(0,coupon.remainder(50),0.01);
        assertEquals(50,coupon.remainder(100),0.01);
    }
}