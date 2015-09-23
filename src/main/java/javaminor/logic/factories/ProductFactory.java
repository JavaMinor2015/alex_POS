package javaminor.logic.factories;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.logic.abs.ScanItemFactory;

/**
 * Created by alex on 9/7/15.
 */
public class ProductFactory extends ScanItemFactory {


    public ProductFactory() {

    }

    @Override
    public ScanItem createProduct(final String name, final String codeType, final String code, final double price) {
        Product product = new Product();
        product.setName(name);
        product.addCode(codeType, code);
        product.setPrice(price);
        return product;
    }

    @Override
    public ScanItem createFidelityCard(final String codeName, final String code, final String customerName) {
        return null;
    }

}
