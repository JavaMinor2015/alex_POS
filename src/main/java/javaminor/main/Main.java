package javaminor.main;

import javaminor.domain.abs.ScanItem;
import javaminor.logic.CashRegisterImpl;
import javaminor.logic.abs.CashRegister;
import javaminor.util.NumUtil;
import javaminor.util.PreferenceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // for test data only, get 10 random products
        List<ScanItem> randomProducts = ServletMain.getPopulator().getRandomSelectionFromProductList(10);
        ScanItem randomFidelityCard = ServletMain.getPopulator().getRandomFidelityCard();

        CashRegister register1 = new CashRegisterImpl();

        register1.startNewSale();

        // eight product 1's
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));

        // product with a custom code
        register1.scan(randomProducts.get(2).getCodeByType("customcode"));

        // product with a digit code
        register1.scan(randomProducts.get(3).getCodeByType("digitcode"));

        // another product 1 popped up
        register1.scan(randomProducts.get(1).getCodeByType("barcode"));

        // scanned a fidelity card (10% discount)
        register1.scan(randomFidelityCard.getCodeByType("cardcode"));

        // one last product, 4
        register1.scan(randomProducts.get(4).getCodeByType("barcode"));

        register1.finishAdding();

        // TODO multiple coupon of same type not supported yet
        register1.payWithTypeCoupon(PreferenceUtil.getPRICING_CATEGORIES()[NumUtil.getRandomInt(PreferenceUtil.getPRICING_CATEGORIES().length)], 100);
        register1.payWithDigital(-1);

        // do the finishing up
        register1.finishUpSale();

        // prove items were saved
        //logger.info(randomFidelityCard.getProductHistory().size() + " purchase(s) made by this customer!");

        register1.startNewSale();
        register1.makeReturn(randomProducts.get(4).getCodeByType("barcode"));
        register1.makeReturn(randomProducts.get(4).getCodeByType("barcode"));
        register1.finishUpReturn();

    }
}
