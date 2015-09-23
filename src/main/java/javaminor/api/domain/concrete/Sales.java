package javaminor.api.domain.concrete;

import javaminor.api.domain.abs.RestModel;
import javaminor.domain.abs.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class Sales extends RestModel {
    @Getter
    @Setter
    private List<Transaction> saleList;
}
