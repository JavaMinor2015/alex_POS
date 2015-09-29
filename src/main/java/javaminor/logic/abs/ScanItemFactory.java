package javaminor.logic.abs;

import javaminor.domain.abs.Discount;
import javaminor.domain.abs.ScanItem;
import javaminor.logic.factories.FidelityCardFactory;
import javaminor.logic.factories.ProductFactory;

import java.util.Map;

/**
 * Created by alex on 9/23/15.
 */
public abstract class ScanItemFactory {


    public ScanItemFactory() {

    }

    public static ScanItemFactory getFactory(final String type) {
        FactoryType factoryType = FactoryType.valueOf(type);
        try {
            return factoryType.getFactory();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract ScanItem createProduct(final String name, Map<String, String> codeMap, final double price);

    public abstract ScanItem createFidelityCard(String cardName, Map<String, String> codeMap, final String customerName);

    /**
     * Separate method for adding discount.
     *
     * @param discount the discount to add to item
     * @param item the item to add the discount to
     * @return the item with the added discount
     */
    public final ScanItem setDiscount(final Discount discount, final ScanItem item){
        item.setDiscount(discount);
        return item;
    }

    public enum FactoryType {
        PRODUCT(new ProductFactory()),
        FIDELITY_CARD(new FidelityCardFactory());

        private ScanItemFactory factory;

        FactoryType(ScanItemFactory factory) {
            this.factory = factory;
        }

        private ScanItemFactory getFactory() throws IllegalAccessException, InstantiationException {
            return factory.getClass().newInstance();
        }
    }
}
