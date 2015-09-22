package javaminor.domain.concrete;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.Discount;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.criteria.specific.ProductTypeCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 9/9/15.
 */
@Getter
@Setter
public class Basket {
    private static Logger logger = LogManager.getLogger(Basket.class.getName());
    private Map<ScanItem, Integer> scannedItems;
    private Discount endDiscount;

    public Basket() {
        scannedItems = new HashMap<ScanItem, Integer>();
    }

    public double calculateTotalPrice() {
        double total = 0;

        for (ScanItem scanItem : scannedItems.keySet()) {

            total += scanItem.calculatePrice(scannedItems.get(scanItem));

            // if the scan item offers a discount
            if (scanItem.isEndDiscountItem()) {
                endDiscount = scanItem.getDiscount();
            }
        }

        if (endDiscount != null & total > 0) {
            return endDiscount.getDiscountOn(total, 1);
        }
        return total;
    }

    public boolean addToBasket(final ScanItem item) {
        if (scannedItems.containsKey(item)) {
            scannedItems.put(item, scannedItems.get(item) + 1);
        } else {
            scannedItems.put(item, 1);
        }
        return true;
    }

    /**
     * Use {@link #calculatePriceForPropertyType(String)} instead
     *
     * @param property
     * @param value
     * @return
     */
    @Deprecated
    public double calculatePriceForItemsWithProperty(final String property, final String value) {
        double total = 0;
        for (ScanItem scanItem : scannedItems.keySet()) {
            if (scanItem.hasProperty(property) && scanItem.getProperty(property).equals(value)) {
                int amount = scannedItems.get(scanItem);
                double v = scanItem.calculatePrice(amount);
                total += v;
            }
        }
        if (endDiscount == null) {
            return total;
        }
        return endDiscount.getDiscountOn(total, 1);
    }

    public double calculatePriceForPropertyType(final String value) {
        ScanItem[] items = new ScanItem[scannedItems.size()];
        scannedItems.keySet().toArray(items);

        Criteria productType = new ProductTypeCriteria(value);
        List<ScanItem> matchingItems = productType.meetCriteria(Arrays.asList(items));

        double total = 0;
        for (ScanItem matchingItem : matchingItems) {
            final int amount = scannedItems.get(matchingItem);
            total += matchingItem.calculatePrice(amount);
        }

        return total;
    }
}
