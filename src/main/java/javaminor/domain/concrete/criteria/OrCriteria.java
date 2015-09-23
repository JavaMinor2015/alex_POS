package javaminor.domain.concrete.criteria;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class OrCriteria extends Criteria{

    private Criteria[] criteria;

    public OrCriteria(Criteria... criteria){
        this.criteria = criteria;
    }

    @Override
    public List<ScanItem> meetCriteria(List<ScanItem> items) {
        List<ScanItem> meetCriteria = new ArrayList<>();

        for (Criteria crit : criteria) {
            if(!meetCriteria.contains(crit)){
                meetCriteria.addAll(crit.meetCriteria(items));
            }
        }

        return meetCriteria;
    }
}
