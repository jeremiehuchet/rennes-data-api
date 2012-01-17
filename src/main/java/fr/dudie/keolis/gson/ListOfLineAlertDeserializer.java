package fr.dudie.keolis.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.LineAlert;

/**
 * Deserializer for "line" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class ListOfLineAlertDeserializer implements JsonDeserializer<List<LineAlert>> {

    private static Gson gsonInstance;

    static {
        // TOBO is there a way to avoid creating a new Gson ?
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        final Type linesType = new TypeToken<List<String>>() {
        }.getType();
        gsonBuilder.registerTypeAdapter(linesType, new LinesOfLineAlertDeserializer());
        gsonInstance = gsonBuilder.create();
    }

    @Override
    public List<LineAlert> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        final ArrayList<LineAlert> lineAlerts = new ArrayList<LineAlert>();

        if (json instanceof JsonArray) {
            final JsonArray jsonArray = (JsonArray) json;

            for (int i = 0; i < jsonArray.size(); i++) {
                lineAlerts.add(gsonInstance.fromJson(jsonArray.get(i), LineAlert.class));
            }
        } else {
            lineAlerts.add(gsonInstance.fromJson(json, LineAlert.class));
        }

        return lineAlerts;
    }
}
