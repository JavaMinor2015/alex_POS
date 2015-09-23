package javaminor.logic;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.util.Populator;
import javaminor.util.PreferenceUtil;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alex on 9/9/15.
 */
public class ScanItemRepository {
    private static Logger logger = LogManager.getLogger(ScanItemRepository.class.getName());

    @Getter
    private static List<ScanItem> scanItems;
    private Object itemTypes;

    @Getter
    private static Populator populator;

    static{

        // TODO replace when database has been implemented
        populator = new Populator();
        populator.populate();
        scanItems = populator.getAllScanItems();
    }

    public ScanItemRepository(){

    }

    public static ScanItem getItemByCode(final String code){
//        List<ScanItem> found = new ArrayList<ScanItem>();
//
//        for (ScanItem scanItem : scanItems) {
//            if(scanItem.hasCodeValue(code)){
//                found.add(scanItem);
//            }
//        }
        List<ScanItem> found = scanItems.stream()
                .filter(scanItem -> scanItem.hasCodeValue(code))
                .collect(Collectors.toList());

        // more than one product found
        if(found.size() > 1){
            logger.error("More than one product matches an id for " + code);
            logger.error("Product was not added.");
            found.forEach(logger::error);

            // nothing can be done, return false to inform caller nothing happened
            return null;
        }

        // less than one product found
        if (found.size() < 1){
            logger.error("Not one product matches an id for " + code);
            // no error messages, no product found, but code might not be meant for this method
            // return false to inform caller nothing happened
            return null;
        }


        return found.get(0);
    }

    public static List<ScanItem> getItemsByType(List<ScanItem> scanItems, final String type) {
        final String typeName = PreferenceUtil.getCATEGORY_KEY_NAME();

        List<ScanItem> results = new ArrayList<>();

//        for (ScanItem scanItem : scanItems) {
//            if(scanItem.hasProperty(typeName)){
//                if(scanItem.getProperty(typeName).equals(type)){
//                    results.add(scanItem);
//                }
//            }
//        }
        results.addAll(scanItems.stream()
                .filter(scanItem -> scanItem.hasProperty(typeName))
                .filter(scanItem -> scanItem.getProperty(typeName).equals(type))
                .collect(Collectors.toList()));

        return results;
    }

    public static String[] getItemTypes() {
        return PreferenceUtil.getPRICING_CATEGORIES();
    }

    public static boolean addProduct(final Product product) {
        if (product==null || !(product instanceof Product) | scanItemExists(product)){
            return false;
        }
        scanItems.add(product);
        return true;
    }

    /**
     * Returns true if a scan item already exists, false otherwise.
     * @param item the item to check
     * @return true if exists, false otherwise.
     */
    public static boolean scanItemExists(final ScanItem item){
        for (ScanItem scanItem : scanItems) {
            if(scanItem.equals(item)){
                return true; // TODO change
            }
        }
        return false;
    }
}
