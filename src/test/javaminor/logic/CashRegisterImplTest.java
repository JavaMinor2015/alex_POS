package javaminor.logic;

import javaminor.domain.abs.ScanItem;
import javaminor.logic.abs.CashRegister;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class CashRegisterImplTest extends TestCase {

    @Test
    public void testRegister() {

        // for test data only, get 10 random products
        List<ScanItem> randomProducts = ScanItemRepository.getPopulator().getRandomSelectionFromProductList(10);
        ScanItem randomFidelityCard = ScanItemRepository.getPopulator().getRandomFidelityCard();


        CashRegister register = new CashRegisterImpl();
        register.startNewSale();

        // 4 time the first random product
        register.scan(randomProducts.get(1).getCodeByType("barcode"));
        register.scan(randomProducts.get(1).getCodeByType("barcode"));
        register.scan(randomProducts.get(1).getCodeByType("barcode"));
        register.scan(randomProducts.get(1).getCodeByType("barcode"));

        // product with a custom code
        register.scan(randomProducts.get(2).getCodeByType("customcode"));

        // product with a digit code
        register.scan(randomProducts.get(3).getCodeByType("digitcode"));

        // another product 1 popped up
        register.scan(randomProducts.get(1).getCodeByType("barcode"));

        // scanned a fidelity card (10% discount)
        register.scan(randomFidelityCard.getCodeByType("cardcode"));

        // one last product
        register.scan(randomProducts.get(4).getCodeByType("barcode"));


        // TODO check somehow

    }


}