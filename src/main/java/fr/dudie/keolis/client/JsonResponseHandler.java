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

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dudie.keolis.model.ApiResponse;

/**
 * Handles a JSON http response.
 * 
 * @param <V>
 */
public abstract class JsonResponseHandler<V> implements ResponseHandler<ApiResponse<V>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonResponseHandler.class);

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public final ApiResponse<V> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");

        }

        final InputStream inputStream = response.getEntity().getContent();

        final ApiResponse<V> apiResponse = handleJsonResponse(inputStream);

        inputStream.close();

        try {
            KeoUtils.checkResponse(apiResponse);
        } catch (final JSONException e) {
            throw new IOException("Unable to parse the json response received from Keolis:\n" + e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return apiResponse;
    }

    /**
     * Handles the JSON reponse and deserialize it.
     * 
     * @param responseStream
     *            http response input stream
     * @return the api response
     */
    abstract ApiResponse<V> handleJsonResponse(final InputStream responseStream);
}