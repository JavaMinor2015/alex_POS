package javaminor.logic.factories;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.Customer;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.logic.abs.ScanItemFactory;

import java.util.Map;

/**
 * Created by alex on 9/23/15.
 */
public class FidelityCardFactory extends ScanItemFactory {
    @Override
    public ScanItem createProduct(final String name, Map<String, String> codeMap, final double price) {
        return null;
    }

    @Override
    public ScanItem createFidelityCard(String cardName, Map<String, String> codeMap, final String customerName) {
        FidelityCard card = new FidelityCard();
        card.setName(cardName);
        for (String codeType : codeMap.keySet()) {
            card.setCode(codeMap.get(codeType));
            break; // sorry pascal
        }
        Customer customer = new Customer();
        customer.setName(customerName);
        card.setCustomer(customer);
        return card;
    }
}
