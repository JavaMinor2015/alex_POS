package javaminor.api.domain.concrete;

import javaminor.api.domain.abs.RestModel;
import javaminor.domain.abs.Discount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class Discounts extends RestModel {
    @Getter
    @Setter
    private List<Discount> discountList;
}
