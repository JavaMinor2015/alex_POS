package javaminor.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
public class RestUtil {

    /**
     * Builds a response around the object and parameters.
     *
     * @param o the object to jsonify
     * @param params optional parameters from the user
     * @return a Response object
     */
    public static Response buildReponse(Object o, Object... params){
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        //Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        if(o==null){
            return Response.ok(gson.toJson("Product(s) not found for parameters: " + params)).build();
        }
        return Response.ok(gson.toJson(o)).build();
    }

    public static GsonBuilder getBuilder(List<AbstractDeserializer> deserializers){
        GsonBuilder builder = new GsonBuilder();
        for (AbstractDeserializer deserializer : deserializers) {
            builder.registerTypeAdapter(deserializer.getClazz(),deserializer);
        }
        return builder;
    }
}
