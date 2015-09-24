package javaminor.api.util;

import com.google.gson.*;
import javaminor.domain.concrete.scanitems.Customer;

import java.lang.reflect.Type;

/**
 * Created by alex on 9/24/15.
 */
public class CustomerDeserializer extends AbstractDeserializer<Customer>{

    @Override
    public Customer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();

        long id = json.get("id").getAsLong();
        String name = json.get("name").getAsString();
        return new Customer(id,name);
    }

    @Override
    public Class getClazz() {
        return Customer.class;
    }
}
