package javaminor.domain.concrete.scanitems;

import javaminor.domain.abs.ScanItem;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 9/8/15.
 */
@Getter
@Setter
public class FidelityCard extends ScanItem{
    public static final String URL = "/cards";
    private String self;
    private String code;
    private Customer customer;
    private Map<DateTime, Map<Product,Integer>> productHistory;

    public FidelityCard(){
        productHistory = new HashMap<DateTime,Map<Product,Integer>>();
    }

    public void addProductsToHistory(DateTime purchaseDate,Map<Product,Integer> productMap){
        productHistory.put(purchaseDate, productMap);
    }

    public boolean codeMatches(final String codeToCheck){
        if(code.equals(codeToCheck)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append(getName());

        if(hasDiscount()){
            b.append("\n");
            b.append("Discount: ");
            b.append(getDiscount().toString());
        }

        return b.toString();
    }
}
