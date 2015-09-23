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
public class AndCriteriaTest extends TestCase {
    @Test
    public void testAndCriteria(){
        Product p1 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;

        List<ScanItem> list = new ArrayList<>();
        list.add(i);

        Criteria eco = new ProductTypeCriteria("ECO");
        Criteria notMeal = new NotCriteria(new ProductTypeCriteria("MEAL"));

        Criteria andCriteria = new AndCriteria(eco,notMeal);
        assertEquals(1,andCriteria.meetCriteria(list).size());

        andCriteria = new AndCriteria(eco,eco);
        assertEquals(1,andCriteria.meetCriteria(list).size());

        Criteria meal = new ProductTypeCriteria("MEAL");
        andCriteria = new AndCriteria(meal,meal);
        assertEquals(0,andCriteria.meetCriteria(list).size());

    }
}