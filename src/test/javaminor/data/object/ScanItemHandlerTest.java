package javaminor.data.object;

import javaminor.data.access.DataHandler;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.FidelityCard;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by alex on 9/29/15.
 */
public class ScanItemHandlerTest extends TestCase {

    public void testGetAllScanItems() throws Exception {
        ScanItemHandler handler = new ScanItemHandler(new DataHandler());
        List<ScanItem> items = handler.getAllScanItems();
        System.err.println(items.size());
        System.err.println(((FidelityCard) items.get(7)).getName());
    }
}