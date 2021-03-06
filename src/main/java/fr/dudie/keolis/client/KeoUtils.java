/*
 * Copyright (C) 2010 Dudie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.dudie.keolis.client;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.gson.BikeStationDeserializer;
import fr.dudie.keolis.gson.LineAlertDataDeserializer;
import fr.dudie.keolis.gson.SubwayStationDeserializer;
import fr.dudie.keolis.model.ApiResponse;
import fr.dudie.keolis.model.BikeStation;
import fr.dudie.keolis.model.LineAlertData;
import fr.dudie.keolis.model.StatusAttributes;
import fr.dudie.keolis.model.SubwayStation;

/**
 * Utilities for Keolis service.
 * 
 * @author Olivier Boudet
 */
public final class KeoUtils {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeoUtils.class);

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
     * @param <T>
     *            type of data returned by the API
     * @throws JSONException
     *             the response does not contains valid data
     */
    public static <T> void checkResponse(final ApiResponse<T> apiResponse) throws JsonParseException {

        final StatusAttributes attributes = apiResponse.getOpendata().getAnswer().getStatus()
                .getAttributes();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("checking keolis response - code = {}, message = {}",
                    attributes.getCode(), attributes.getMessage());
        }

        if (attributes.getCode() != 0) {
            final String message = String.format("Keolis API error : code=%S, %s ",
                    attributes.getCode(), attributes.getMessage());
            throw new JsonParseException(message);
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

            // type adapter needed just because "data" object in the response can be an array or an
            // empty string.
            final Type lineAlertDataType = new TypeToken<LineAlertData>() {
            }.getType();
            gsonBuilder.registerTypeAdapter(lineAlertDataType, new LineAlertDataDeserializer());

            final Type listOfSubwayStation = new TypeToken<List<SubwayStation>>() {
            }.getType();
            gsonBuilder.registerTypeAdapter(listOfSubwayStation, new SubwayStationDeserializer());

            final Type listOfBikeStation = new TypeToken<List<BikeStation>>() {
            }.getType();

            gsonBuilder.registerTypeAdapter(listOfBikeStation, new BikeStationDeserializer());

            gsonInstance = gsonBuilder.create();
        }

        return gsonInstance;
    }

}
