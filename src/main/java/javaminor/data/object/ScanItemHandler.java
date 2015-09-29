package javaminor.data.object;

import javaminor.data.access.DataHandler;
import javaminor.domain.abs.ScanItem;
import javaminor.logic.abs.ScanItemFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 9/29/15.
 */
public class ScanItemHandler {
    private DataHandler handler;

    public ScanItemHandler(DataHandler handler) {
        this.handler = handler;
    }

    public List<ScanItem> getAllScanItems() {
        List<ScanItem> items = new ArrayList<>();
        try{
            ResultSet results = handler.getAllScanItems();
            while(results.next()){
                // Type of scanitem
                int id = results.getInt("ID");
                String itemType = results.getString("TYPE");
                String name = results.getString("NAME");
                double price = results.getDouble("PRICE");

                ResultSet codes = handler.getScanCodesForItem(id);
                Map<String, String> codeMap = new HashMap<>();
                while(codes.next()){
                    String code = codes.getString("CODE");
                    String type = codes.getString("CODETYPE");
                    codeMap.put(code,type);
                }

                ScanItemFactory factory = ScanItemFactory.getFactory(itemType);
                ScanItem item = null;


                // eww
                if(itemType.equals("PRODUCT")){
                    item = factory.createProduct(name,codeMap,price);
                    item.setId(id);
                }
                if(itemType.equals("FIDELITY_CARD")){
                    item = factory.createFidelityCard(name, codeMap, "");
                    item.setId(id);
                }

                if(item!=null){
                    items.add(item);
                }

            }
        }catch(SQLException e){
            // TODO exception
            e.printStackTrace();
        } finally {
            handler.closeAll();
        }
        return items;
    }
}
