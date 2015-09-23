package javaminor.logic;

import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.util.PreferenceUtil;
import lombok.Getter;
import lombok.Setter;
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
    @Setter
    private static List<ScanItem> scanItems;

    public ScanItemRepository(){

    }

    public static List<ScanItem> getProducts(){
        return getProducts(0,10);
    }

    public static List<ScanItem> getProducts(final int startIndex){
        return getProducts(startIndex, 10);
    }

    public static List<ScanItem> getProducts(final int startIndex, final int size){
        List<ScanItem> products = filterProducts(scanItems);

        int start = startIndex;
        int end = startIndex + size;

        if(start > products.size()){
            start = products.size() - size;
        }
        if(end > products.size()){
            end = products.size();
        }
        return products.subList(start, end);
    }

    public static List<ScanItem> getCards(){
        return getCards(0, 10);
    }

    public static List<ScanItem> getCards(final int startIndex){
        return getCards(startIndex, 10);
    }

    public static List<ScanItem> getCards(final int startIndex, final int size){
        List<ScanItem> cards = filterCards(scanItems);

        int start = startIndex;
        int end = startIndex + size;

        if(start > cards.size()){
            start = cards.size() - size;
        }
        if(end > cards.size()){
            end = cards.size();
        }
        return cards.subList(start, end);
    }

    private static List<ScanItem> filterProducts(final List<ScanItem> scanItems) {
        List<ScanItem> results = new ArrayList<>();
        for (ScanItem scanItem : scanItems) {
            if(scanItem instanceof Product & !scanItem.isDisabled()){
                results.add(scanItem);
            }
        }
        return results;
    }
    private static List<ScanItem> filterCards(final List<ScanItem> scanItems) {
        List<ScanItem> results = new ArrayList<>();
        for (ScanItem scanItem : scanItems) {
            if(scanItem instanceof FidelityCard & !scanItem.isDisabled()){
                results.add(scanItem);
            }
        }
        return results;
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

    public static boolean updateProduct(final Product product) {
        for (int i = 0; i < scanItems.size(); i++) {
            if(scanItems.get(i).getId().equals(product.getId())){
                scanItems.add(i,product);
                return true;
            }
        }
        return false;
    }

    public static boolean addCard(final FidelityCard card) {
        if (card==null || !(card instanceof FidelityCard) | scanItemExists(card)){
            return false;
        }
        scanItems.add(card);
        return true;
    }

    /**
     * Returns true if a scan item already exists, false otherwise.
     * @param item the item to check
     * @return true if exists, false otherwise.
     */
    public static boolean scanItemExists(final ScanItem item){
        for (ScanItem scanItem : scanItems) {
            if(scanItem.getId().equals(item.getId())){
                return true;
            }
        }
        return false;
    }

    public static ScanItem getItemById(final int id) {
        for (ScanItem scanItem : scanItems) {
            if(scanItem.getId().equals(id)){
                return scanItem;
            }
        }
        return null;
    }


    public static boolean setItemDisabled(final int id) {
        for (int i = 0; i < scanItems.size(); i++) {
            if(scanItems.get(i).getId().equals(id)){
                scanItems.get(i).setDisabled(true);
                return true;
            }
        }
        return false;
    }
}
