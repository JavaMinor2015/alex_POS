package javaminor.api.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javaminor.domain.abs.ScanItem;
import javaminor.domain.concrete.Basket;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by alex on 9/24/15.
 */
public class BasketDeserializer extends AbstractDeserializer<Basket> {
    @Override
    Class getClazz() {
        return Basket.class;
    }

    @Override
    public Basket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        // scannedItems
        JsonObject json = jsonElement.getAsJsonObject();
        JsonObject scannedItems = json.getAsJsonObject("scannedItems");
        Type propertyMapType = new TypeToken<Map<String, String>>(){}.getType();
        Map<ScanItem, Integer> scannedItemsMap = new Gson().fromJson(scannedItems,
                propertyMapType);

        Basket basket = new Basket();

        // TODO will it break? find out when database comes
        basket.setScannedItems(scannedItemsMap);



        // endDiscount > DiscountDeserializer?
        return null;
    }
}
