package javaminor.api.util;

import com.google.gson.*;
import javaminor.domain.abs.Discount;
import javaminor.domain.abs.DiscountType;

import java.lang.reflect.Type;

/**
 * Created by alex on 9/23/15.
 */
public class DiscountDeserializer extends AbstractDeserializer<Discount>{
    @Override
    public Discount deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        String t = json.get("type").getAsString();
        Class clazz = DiscountType.valueOf(t).getClazz();
        return (Discount)new Gson().fromJson(json,clazz);
    }

    @Override
    Class getClazz() {
        return Discount.class;
    }
}
