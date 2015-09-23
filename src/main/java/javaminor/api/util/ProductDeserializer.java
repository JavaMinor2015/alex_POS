package javaminor.api.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javaminor.domain.abs.Discount;
import javaminor.domain.abs.DiscountType;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.logic.factories.ProductFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by alex on 9/23/15.
 */
public class ProductDeserializer implements JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ProductFactory factory = new ProductFactory();
        JsonObject json = jsonElement.getAsJsonObject();
        String name = json.get("name").getAsString();

        JsonObject codes = json.getAsJsonObject("scanCodes");
        Type codeMapType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> codeMap = new Gson().fromJson(codes, codeMapType);

        JsonObject properties = json.getAsJsonObject("properties");
        Type propertyMapType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> propertyMap = new Gson().fromJson(properties,
                propertyMapType);

        double price = json.get("price").getAsDouble();

        // TODO perhaps it can be smaller? product as type, discount as type in its own serializer
        JsonObject discountJson = json.getAsJsonObject("discount");

        String t = discountJson.get("type").getAsString();
        Class clazz = DiscountType.valueOf(t).getClazz();
        Discount discount = (Discount)new Gson().fromJson(discountJson,clazz);

        Product product = (Product)factory.createProduct(name,null,null,price);
        product.setScanCodes(codeMap);
        product.setProperties(propertyMap);
        product.setDiscount(discount);


        return product;
    }
}
