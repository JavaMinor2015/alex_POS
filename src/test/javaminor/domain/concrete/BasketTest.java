package javaminor.domain.concrete;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.discounts.DiscountFixedAmount;
import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.domain.concrete.scanitems.Product;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by alex on 9/9/15.
 */
public class BasketTest extends TestCase {

    @Test
    public void testCalculateTotalPrice() throws Exception {

        Basket basket = new Basket();
        Product p1 = new Product();
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;
        basket.addToBasket(i);
        assertEquals(15.0, basket.calculateTotalPrice());

        p1 = new Product();
        p1.setDiscount(new DiscountFixedAmount(10));
        p1.setPrice(15.0);
        i = p1;
        basket.addToBasket(i);
        assertEquals(20.0, basket.calculateTotalPrice());

        FidelityCard f1 = new FidelityCard();
        f1.setName("FidelityCard_" + i);
        f1.setDiscount(new DiscountFixedAmount(5));
        basket.addToBasket(f1);
        assertEquals(15.0, basket.calculateTotalPrice());
    }

    @Test
    public void testAddToBasket() throws Exception {
        Basket basket = new Basket();
        ScanItem i = new FidelityCard();
        basket.addToBasket(i);
        assertEquals(1, basket.getScannedItems().size());
    }

    @Test
    public void testCalculatePriceForItemsWithProperty() throws Exception {
        Basket basket = new Basket();
        Product p1 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;
        basket.addToBasket(i);
        assertEquals(15.0, basket.calculatePriceForItemsWithProperty("type", "ECO"));

        p1 = new Product();
        p1.setDiscount(new DiscountFixedAmount(1000000));
        p1.setPrice(1500000.0);
        i = p1;
        basket.addToBasket(i);
        assertEquals(15.0, basket.calculatePriceForItemsWithProperty("type", "ECO"));

        Product p2 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i2 = p2;

        basket.addToBasket(i2);
        assertEquals(30.0, basket.calculatePriceForItemsWithProperty("type", "ECO"));
    }

    @Test
    public void testCalculatePriceForPropertyType() throws Exception {
        Basket basket = new Basket();
        Product p1 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;
        basket.addToBasket(i);
        assertEquals(15.0, basket.calculatePriceForPropertyType("ECO"));

        p1 = new Product();
        p1.setDiscount(new DiscountFixedAmount(1000000));
        p1.setPrice(1500000.0);
        i = p1;
        basket.addToBasket(i);
        assertEquals(15.0, basket.calculatePriceForPropertyType("ECO"));

        Product p2 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i2 = p2;

        basket.addToBasket(i2);
        assertEquals(30.0, basket.calculatePriceForPropertyType("ECO"));
    }
}