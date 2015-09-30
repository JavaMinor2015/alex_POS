package javaminor.logic;

import javaminor.data.access.DataHandler;
import javaminor.data.object.ScanItemHandler;
import javaminor.domain.abs.ScanItem;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class ScanItemRepositoryTest extends TestCase {
    @Before
    public void setUp() {
        ScanItemRepository.setScanItems(new ScanItemHandler(new DataHandler()).getAllScanItems());
    }

    @Test
    public void testGetProducts() throws Exception {
        List<ScanItem> items = ScanItemRepository.getProducts(0, 1);
        assertEquals(1, items.size());

        items = ScanItemRepository.getProducts(0, 5);
        assertEquals(5, items.size());

        items = ScanItemRepository.getProducts(1, 5);
        assertEquals(5, items.size());

        items = ScanItemRepository.getProducts(2, 2);
        assertEquals(2, items.size());
    }
}