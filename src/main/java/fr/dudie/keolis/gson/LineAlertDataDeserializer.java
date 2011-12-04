package fr.dudie.keolis.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import fr.dudie.keolis.client.KeoUtils;
import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineAlertData;

/**
 * Deserializer for "alert" element returned by getlinesalerts command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class LineAlertDataDeserializer implements JsonDeserializer<LineAlertData> {

    @Override
    public LineAlertData deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        if (json instanceof JsonPrimitive) {
            return new LineAlertData();
        } else if (json instanceof JsonObject) {
            LineAlertData data = new LineAlertData();
            ArrayList<LineAlert> list = new ArrayList<LineAlert>();
            list.add((LineAlert) context.deserialize(json, LineAlert.class));
            data.setAlerts(list);
            return data;
        } else {
            return KeoUtils.getGsonInstance().fromJson(json, typeOfT);
        }
    }
}
