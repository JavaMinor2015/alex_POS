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
public class NotCriteriaTest extends TestCase {

    @Test
    public void testNotCriteria(){
        Product p1 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;

        List<ScanItem> list = new ArrayList<>();
        list.add(i);

        Criteria productTypeCriteria = new ProductTypeCriteria("ECO");
        assertEquals(0,new NotCriteria(productTypeCriteria).meetCriteria(list).size());

        list = new ArrayList<>();
        list.add(i);

        productTypeCriteria = new ProductTypeCriteria("MEAL");
        assertEquals(1,new NotCriteria(productTypeCriteria).meetCriteria(list).size());
    }


}