package fr.dudie.keolis.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import fr.dudie.keolis.client.KeoUtils;

/**
 * Deserializer for "line" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class LinesOfLineAlertDeserializer implements JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        JsonElement lines = json.getAsJsonObject().get("line");
        if (lines instanceof JsonArray) {
            return KeoUtils.getGsonInstance().fromJson(lines, typeOfT);
        }

        String line = context.deserialize(lines, String.class);
        ArrayList<String> list = new ArrayList<String>();
        list.add(line);
        return list;
    }
}
