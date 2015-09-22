package javaminor.domain.concrete.criteria.specific;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class PropertyTypeCriteria extends Criteria {

    private final String propertyType;

    public PropertyTypeCriteria(final String propertyType){
        this.propertyType = propertyType;
    }

    @Override
    public List<ScanItem> meetCriteria(final List<ScanItem> items) {
        List<ScanItem> meetCriteria = new ArrayList<>();

        for (ScanItem item : items) {
            if(item.hasProperty(propertyType)){
                meetCriteria.add(item);
            }
        }

        return meetCriteria;
    }
}
