package javaminor.domain.concrete.criteria.specific;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.criteria.NotCriteria;
import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.domain.concrete.scanitems.Product;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class FidelityCardCriteriaTest extends TestCase {

    @Test
    public void testFidelityCardCriteria(){
        Product p1 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;

        Product p2 = new Product();
        p2.setName("Product2");
        p2.addProperties("type", "MEAL");
        p2.addCode("barcode", "12345");
        p2.setDiscount(new DiscountNone());
        p2.setPrice(15.0);
        ScanItem i2 = p2;

        Product p3 = new Product();
        p3.setName("Product3");
        p3.addCode("barcode", "123456");
        p3.setDiscount(new DiscountNone());
        p3.setPrice(15.0);
        ScanItem i3 = p3;

        FidelityCard f1 = new FidelityCard();
        f1.setName("FidelityCard_" + i);

        List<ScanItem> list = new ArrayList<>();
        list.add(i);
        list.add(i2);
        list.add(i3);
        list.add(f1);

        Criteria fidelityCard = new FidelityCardCriteria();
        assertEquals(1,fidelityCard.meetCriteria(list).size());
        assertEquals(3,new NotCriteria(fidelityCard).meetCriteria(list).size());
    }
}