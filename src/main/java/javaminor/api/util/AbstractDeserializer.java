package javaminor.api.util;

import com.google.gson.JsonDeserializer;

/**
 * Created by alex on 9/24/15.
 */
public abstract class AbstractDeserializer<T> implements JsonDeserializer<T>{
    abstract Class getClazz();
}
