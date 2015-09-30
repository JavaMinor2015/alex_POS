package javaminor.data.object;

import javaminor.data.access.DataHandler;
import javaminor.domain.abs.ScanItem;
import junit.framework.TestCase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by alex on 9/29/15.
 */
public class ScanItemHandlerTest extends TestCase {

    public void testGetAllScanItems() throws Exception {
        ScanItemHandler handler = new ScanItemHandler(new DataHandler());
        List<ScanItem> items = handler.getAllScanItems();
        assertThat(items.size(),not(0));
    }
}