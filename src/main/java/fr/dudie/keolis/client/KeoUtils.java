package fr.dudie.keolis.client;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.gson.BikeStationDeserializer;
import fr.dudie.keolis.gson.LineAlertDataDeserializer;
import fr.dudie.keolis.gson.LinesOfLineAlertDeserializer;
import fr.dudie.keolis.gson.ListOfLineAlertDeserializer;
import fr.dudie.keolis.gson.SubwayStationDeserializer;
import fr.dudie.keolis.model.ApiResponse;
import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineAlert;
import fr.dudie.keolis.model.LineAlertData;
import fr.dudie.keolis.model.StatusAttributes;
import fr.dudie.keolis.model.SubwayStation;

/**
 * Utilities for Keolis service.
 * 
 * @author Olivier Boudet
 */
public final class KeoUtils {

    /** Gson instance for Keolis api calls. */
    private static Gson gsonInstance;

    /**
     * Private constructor to avoid instantiation.
     */
    private KeoUtils() {

    }

    /**
     * Checks the response code of the keolis API and return the JSON Object named "data".
     * 
     * @param apiResponse
     *            the response of the keolis API
     * @throws JSONException
     *             the response does not contains valid data
     */
    public static <T> void checkResponse(final ApiResponse<T> apiResponse) throws JSONException {

        StatusAttributes attributes = apiResponse.getOpendata().getAnswer().getStatus()
                .getAttributes();
        if (attributes.getCode() != 0) {
            final String message = String.format("Keolis API error : code=%S, %s ",
                    attributes.getCode(), attributes.getMessage());
            throw new JSONException(message);
        }
    }

    /**
     * Gets a gson instance dedicated to Keolis API.
     * 
     * @return the gson instance
     */
    public static Gson getGsonInstance() {

        if (gsonInstance == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            // type adapter because list of only one element returned by keolis are not a JsonArray
            // but
            // a JsonObject.
            Type listOfLinesType = new TypeToken<List<String>>() {
            }.getType();
            gsonBuilder.registerTypeAdapter(listOfLinesType, new LinesOfLineAlertDeserializer());

            // type adapter needed just because lists in the response can be an array or a json
            // object.
            Type listOfLineAlertsType = new TypeToken<List<LineAlert>>() {
            }.getType();
            gsonBuilder
                    .registerTypeAdapter(listOfLineAlertsType, new ListOfLineAlertDeserializer());

            // type adapter needed just because "data" object in the response can be an array or an
            // empty string.
            Type lineAlertDataType = new TypeToken<LineAlertData>() {
            }.getType();
            gsonBuilder.registerTypeAdapter(lineAlertDataType, new LineAlertDataDeserializer());

            Type listOfSubwayStation = new TypeToken<List<SubwayStation>>() {
            }.getType();

            Type listOfBikeStation = new TypeToken<List<BikeStation>>() {
            }.getType();
            gsonBuilder.registerTypeAdapter(listOfBikeStation, new BikeStationDeserializer());

            gsonBuilder.registerTypeAdapter(listOfSubwayStation, new SubwayStationDeserializer());

            gsonInstance = gsonBuilder.create();
        }

        return gsonInstance;
    }

}
