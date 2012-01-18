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
