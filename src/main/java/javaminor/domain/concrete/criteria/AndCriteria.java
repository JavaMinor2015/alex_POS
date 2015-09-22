package javaminor.domain.concrete.criteria;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;

import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class AndCriteria extends Criteria {
    private Criteria[] criteria;

    public AndCriteria(Criteria... criteria){
        this.criteria = criteria;
    }

    @Override
    public List<ScanItem> meetCriteria(List<ScanItem> items) {
        List<ScanItem> meetCriteria = items;
        for (Criteria crit : criteria) {
            meetCriteria = crit.meetCriteria(meetCriteria);
        }
        return meetCriteria;
    }
}
