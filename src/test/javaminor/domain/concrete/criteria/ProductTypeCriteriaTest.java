package javaminor.domain.concrete.criteria;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.criteria.specific.ProductTypeCriteria;
import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.scanitems.Product;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class ProductTypeCriteriaTest extends TestCase {

    @Test
    public void testProductTypeCriteria() {
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

        List<ScanItem> list = new ArrayList<>();
        list.add(i);
        list.add(i2);
        list.add(i3);

        Criteria productTypeCriteria = new ProductTypeCriteria("ECO");
        assertEquals(1, productTypeCriteria.meetCriteria(list).size());

        productTypeCriteria = new ProductTypeCriteria("MEAL");
        assertEquals(1, productTypeCriteria.meetCriteria(list).size());

        productTypeCriteria = new ProductTypeCriteria("DOESNOTEXIST");
        assertEquals(0, productTypeCriteria.meetCriteria(list).size());


        Criteria productType1 = new ProductTypeCriteria("ECO");
        Criteria productType2 = new ProductTypeCriteria("MEAL");
        Criteria orCriteria = new OrCriteria(productType1,productType2);
        assertEquals(2, orCriteria.meetCriteria(list).size());

        Criteria andCriteria = new AndCriteria(new NotCriteria(productType1),new NotCriteria(productType2));
        assertEquals(1,andCriteria.meetCriteria(list).size());

    }

}