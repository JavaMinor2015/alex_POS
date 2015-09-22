package javaminor.domain.concrete.criteria.specific;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.FidelityCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class FidelityCardCriteria extends Criteria {

    @Override
    public List<ScanItem> meetCriteria(final List<ScanItem> items) {
        List<ScanItem> meetCriteria = new ArrayList<>();

        for (ScanItem item : items) {
            if(item instanceof FidelityCard){
                meetCriteria.add(item);
            }
        }

        return meetCriteria;
    }
}
