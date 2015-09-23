package javaminor.domain.concrete.criteria;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.criteria.specific.ProductTypeCriteria;
import javaminor.domain.concrete.discounts.DiscountNone;
import javaminor.domain.concrete.scanitems.Product;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class OrCriteriaTest extends TestCase {

    public void testOrCriteria(){

        Product p1 = new Product();
        p1.setName("Product1");
        p1.addProperties("type", "ECO");
        p1.addCode("barcode", "1234");
        p1.setDiscount(new DiscountNone());
        p1.setPrice(15.0);
        ScanItem i = p1;

        List<ScanItem> list = new ArrayList<>();
        list.add(i);

        Criteria ecoProduct = new ProductTypeCriteria("ECO");
        Criteria notEco = new NotCriteria(ecoProduct);

        Criteria orCriteria = new OrCriteria(ecoProduct,notEco);
        assertEquals(1,orCriteria.meetCriteria(list).size());

        orCriteria = new OrCriteria(notEco,notEco);
        assertEquals(0,orCriteria.meetCriteria(list).size());
    }


}