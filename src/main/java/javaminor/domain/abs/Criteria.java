package javaminor.domain.abs;

import java.util.List;

/**
 * Created by alex on 9/16/15.
 */
public abstract class Criteria {
    public abstract List<ScanItem> meetCriteria(final List<ScanItem> items);
}
