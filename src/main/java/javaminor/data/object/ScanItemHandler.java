package javaminor.data.object;

import com.google.gson.Gson;
import javaminor.api.util.DiscountDeserializer;
import javaminor.api.util.RestUtil;
import javaminor.data.access.DataHandler;
import javaminor.domain.abs.Discount;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.logic.abs.ScanItemFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
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
        try {
            ResultSet results = handler.getAllScanItems();
            while (results.next()) {

                // Type of scanitem
                int id = results.getInt("ID");
                String itemType = results.getString("TYPE");
                String name = results.getString("NAME");
                double price = results.getDouble("PRICE");
                int points = results.getInt("POINTS");
                Clob properties = results.getClob("PROPERTIES");


                ResultSet codes = handler.getScanCodesForItem(id);
                Map<String, String> codeMap = new HashMap<>();
                while (codes.next()) {
                    String code = codes.getString("CODE");
                    String type = codes.getString("CODETYPE");
                    codeMap.put(type, code);
                }

                String propertyJson = clobToString(properties);
                Map<String, String> propertiesMap = new Gson().fromJson(propertyJson, Map.class);

                String discountJson = clobToString(results.getClob("DISCOUNT"));

                ScanItemFactory factory = ScanItemFactory.getFactory(itemType);
                ScanItem item = null;

                // eww
                if (itemType.equals("PRODUCT")) {
                    item = factory.createProduct(name, codeMap, price);
                    item.setProperties(propertiesMap);
                }
                if (itemType.equals("FIDELITY_CARD")) {
                    item = factory.createFidelityCard(name, codeMap, "");
                    ((FidelityCard) item).setPoints(points);
                }
                item.setId(id);


                item.setDiscount(RestUtil.getBuilder(new DiscountDeserializer()).create().fromJson(discountJson,Discount.class));

                if (item != null) {
                    items.add(item);
                }

            }
        } catch (SQLException e) {
            // TODO exception
            e.printStackTrace();
        } finally {
            handler.closeAll();
        }
        return items;
    }

    // http://stackoverflow.com/questions/2169732/most-efficient-solution-for-reading-clob-to-string-and-string-to-clob-in-java
    private final String clobToString(final Clob data) {
        if (data == null) {
            return new String("{}");
        }
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = data.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);

            String line;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
            br.close();
        } catch (SQLException e) {
            // handle this exception
        } catch (IOException e) {
            // handle this exception
        }
        return sb.toString();
    }
}
