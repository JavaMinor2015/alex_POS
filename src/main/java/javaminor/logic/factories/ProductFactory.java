package javaminor.logic.factories;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.logic.abs.ScanItemFactory;

import java.util.Map;

/**
 * Created by alex on 9/7/15.
 */
public class ProductFactory extends ScanItemFactory {


    public ProductFactory() {

    }

    @Override
    public ScanItem createProduct(final String name, Map<String, String> codeMap, final double price) {
        Product product = new Product();
        product.setName(name);
        product.setScanCodes(codeMap);
        product.setPrice(price);
        return product;
    }

    @Override
    public ScanItem createFidelityCard(String cardName, Map<String, String> codeMap, final String customerName) {
        return null;
    }

}
