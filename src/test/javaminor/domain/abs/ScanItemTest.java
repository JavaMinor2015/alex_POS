package javaminor.domain.abs;

import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.discounts.DiscountOnAmount;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.domain.concrete.scanitems.Product;
import junit.framework.TestCase;

/**
 * Created by alex on 9/9/15.
 */
public class ScanItemTest extends TestCase {

    public void testCalculatePrice() throws Exception {

        Product p1 = new Product();
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;
        assertEquals(15.0, i.calculatePrice(1), 0.01);


        p1 = new Product();
        p1.setDiscount(new DiscountOnAmount(5,1));
        p1.setPrice(2.0);
        i = p1;
        assertEquals(10.0, i.calculatePrice(6), 0.01);

        i = new FidelityCard();
        assertEquals(0,i.calculatePrice(1),0.01);
    }
}