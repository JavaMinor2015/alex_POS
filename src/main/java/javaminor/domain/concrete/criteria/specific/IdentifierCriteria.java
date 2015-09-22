package javaminor.domain.concrete.criteria.specific;

import javaminor.domain.abs.Criteria;
import javaminor.domain.abs.ScanItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public class IdentifierCriteria extends Criteria {

    private final String identifierName;

    public IdentifierCriteria(final String identifierName){
        this.identifierName = identifierName;
    }

    @Override
    public List<ScanItem> meetCriteria(List<ScanItem> items) {
        List<ScanItem> meetCriteria = new ArrayList<>();

        for (ScanItem item : items) {
            if(item.hasCodeType(identifierName)){
                meetCriteria.add(item);
            }
        }

        return meetCriteria;
    }
}
