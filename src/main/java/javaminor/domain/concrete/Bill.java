package javaminor.domain.concrete;

import javaminor.domain.abs.Discount;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.Customer;
import javaminor.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 9/7/15.
 */
@Getter
@Setter
public class Bill {
    private Logger logger = LogManager.getLogger(Bill.class.getName());


    private Map<ScanItem, Integer> scanItemsMap;
    private Customer customer;
    private Discount discount;
    private Double totalPrice;
    private Map<String,Double> totalCategoryPrices;
    private String description;
    private double totalPaid;
    private Map<String, Double> totalCategoryPaid;

    public Bill(){
        scanItemsMap = new HashMap<ScanItem, Integer>();
    }

    /**
     * Print the bill to the logger.
     */
    public void print(){
        logger.info("");
        logger.info(description);
        for (ScanItem k : scanItemsMap.keySet()) {
            logger.info(k.toString() + " X " +scanItemsMap.get(k));
        }
        logger.info("=================================");
        logger.info("Total Price: " + StrUtil.twoDecimal(totalPrice));
        if(discount!=null) {
            logger.info(discount.toString() + " Discount: " + StrUtil.twoDecimal(discount.getDiscountOn(totalPrice, 1)));
        }
        if(totalCategoryPrices!=null){
            logger.info("Categories: ");
            for(String type : totalCategoryPrices.keySet()){
                logger.info("\t"+type + ": " + StrUtil.twoDecimal(totalCategoryPrices.get(type)));
            }
        }
        logger.info("=================================");
        logger.info("Total Paid: " + StrUtil.twoDecimal(totalPaid));
        if(totalCategoryPrices!=null){
            logger.info("Categories: ");
            for (String type : totalCategoryPaid.keySet()) {
                logger.info("\t"+type + ": " + StrUtil.twoDecimal(totalCategoryPaid.get(type)));
            }
        }

    }
}
