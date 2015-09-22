package javaminor.logic;

import javaminor.domain.concrete.scanitems.Customer;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.util.Populator;

import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
public class ProductFactory {
    private static List<Product> productList;
    private static List<Customer> customerList;
    private static Populator populator;

    static{

        // TODO replace when database has been implemented
        populator = new Populator();
        populator.populate();
        //productList = populator.getProductList();
        //customerList = populator.getCustomerList();
    }

    public static List<Product> getProductList(){
        return productList;
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static Populator getPopulator() {
        return populator;
    }
}
