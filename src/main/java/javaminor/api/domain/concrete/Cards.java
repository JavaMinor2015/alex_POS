package javaminor.api.domain.concrete;

import javaminor.api.domain.abs.RestModel;
import javaminor.domain.abs.ScanItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class Cards extends RestModel {
    @Getter
    @Setter
    private List<ScanItem> cards;
}
