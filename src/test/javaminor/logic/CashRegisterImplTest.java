package javaminor.logic;

import javaminor.data.access.DataHandler;
import javaminor.data.object.ScanItemHandler;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.logic.abs.CashRegister;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class CashRegisterImplTest extends TestCase {
    private List<ScanItem> randomProducts;
    private ScanItem randomFidelityCard;

    @Before
    public void setUp() {
        List<ScanItem> items = new ScanItemHandler(new DataHandler()).getAllScanItems();
        ScanItemRepository.setScanItems(items);

        randomProducts = ScanItemRepository.getProducts();

        randomFidelityCard = ScanItemRepository.getCards().get(0);
    }

    @Test
    public void testRegister() {

        CashRegister register = new CashRegisterImpl();
        register.openRegister();
        register.startNewSale();

        // 4 times the first random product
        register.scan(getCode(randomProducts.get(1)));
        register.scan(getCode(randomProducts.get(1)));
        register.scan(getCode(randomProducts.get(1)));
        register.scan(getCode(randomProducts.get(1)));

        // product 2
        register.scan(getCode(randomProducts.get(2)));

        // product 3
        register.scan(getCode(randomProducts.get(3)));

        // another product 1 popped up
        register.scan(getCode(randomProducts.get(1)));

        // scanned a fidelity card (10% discount)
        register.scan(getCode(randomFidelityCard));

        // one last product
        register.scan(getCode(randomProducts.get(3)));
        register.finishAdding();

        register.payWithCash(-1);

        register.finishUpSale(true);

        register.startNewSale();

        register.makeReturn(getCode(randomProducts.get(2)));

        register.makeReturn(getCode(randomProducts.get(2)));

        register.finishUpReturn();


        // TODO check somehow

    }

    // cusotm code, barcode etc.
    private final String getCode(final ScanItem item) {
        if (item.hasCodeType("barcode")) {
            return item.getCodeByType("barcode");
        }
        if (item.hasCodeType("customcode")) {
            return item.getCodeByType("customcode");
        }
        if (item.hasCodeType("digitcode")) {
            return item.getCodeByType("digitcode");
        }
        try {
            return ((FidelityCard)item).getCode();
        } catch (ClassCastException e) {
            return null;
        }
    }


}