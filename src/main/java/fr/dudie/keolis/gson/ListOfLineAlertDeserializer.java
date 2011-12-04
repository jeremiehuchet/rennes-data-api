package fr.dudie.keolis.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import fr.dudie.keolis.client.KeoUtils;
import fr.dudie.keolis.model.LineAlert;

/**
 * Deserializer for "line" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class ListOfLineAlertDeserializer implements JsonDeserializer<List<LineAlert>> {

    @Override
    public List<LineAlert> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        ArrayList<LineAlert> lineAlerts = new ArrayList<LineAlert>();

        if (json instanceof JsonArray) {
            JsonArray jsonArray = (JsonArray) json;

            for (int i = 0; i < jsonArray.size(); i++) {
                lineAlerts.add(KeoUtils.getGsonInstance().fromJson(jsonArray.get(i),
                        LineAlert.class));
            }
        } else {
            lineAlerts.add((LineAlert) context.deserialize(json, LineAlert.class));
        }

        return lineAlerts;
    }
}
