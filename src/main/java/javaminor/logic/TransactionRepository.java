package javaminor.logic;

import javaminor.domain.abs.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class TransactionRepository {
    private static List<Transaction> saleList;

    static{
        saleList = new ArrayList<>();
    }

    public static List<Transaction> getSales(int startIndex, int size) {
        int start = startIndex;
        int end = startIndex + size;

        if(start > saleList.size()){
            start = saleList.size() - size;
        }
        if(end > saleList.size()){
            end = saleList.size();
        }
        //TODO move to other repo's
        try{
            return saleList.subList(start,end);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return new ArrayList<>();


    }
}
