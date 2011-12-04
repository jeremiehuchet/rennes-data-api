package fr.dudie.keolis.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import fr.dudie.keolis.client.KeoUtils;
import fr.dudie.keolis.model.SubwayStation;

/**
 * Deserializer for "station" element returned by getmetrostations command of keolis api.
 * 
 * @author Olivier Boudet
 */
public final class SubwayStationDeserializer implements JsonDeserializer<List<SubwayStation>> {

    @Override
    public List<SubwayStation> deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) {

        ArrayList<SubwayStation> subwayStations = new ArrayList<SubwayStation>();

        if (json instanceof JsonArray) {

            for (int i = 0; i < ((JsonArray) json).size(); i++) {
                subwayStations.add(KeoUtils.getGsonInstance().fromJson(((JsonArray) json).get(i),
                        SubwayStation.class));
            }

        } else {
            subwayStations.add((SubwayStation) context.deserialize(json, SubwayStation.class));
        }

        return subwayStations;
    }
}
