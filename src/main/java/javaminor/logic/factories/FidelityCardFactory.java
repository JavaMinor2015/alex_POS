package javaminor.logic.factories;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.Customer;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.logic.abs.ScanItemFactory;

/**
 * Created by alex on 9/23/15.
 */
public class FidelityCardFactory extends ScanItemFactory {
    @Override
    public ScanItem createProduct(final String name, final String codeType, final String code, final double price) {
        return null;
    }

    @Override
    public ScanItem createFidelityCard(final String codeName, final String code, final String customerName) {
        FidelityCard card = new FidelityCard();
        card.addCode(codeName, code);
        Customer customer = new Customer();
        customer.setName(customerName);
        card.setCustomer(customer);
        return card;
    }
}
