package fr.dudie.keolis.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.ApiResponse;
import fr.dudie.keolis.model.RelayParkData;

/**
 * Handles http responses containing relay parks in json format.
 * 
 * <pre>
 * "relaypark": [
 *     {
 *         "name": "La Poterie"
 *         "latitude": "48.0868139293"
 *         "longitude": "-1.6434973209"
 *         "carparkavailable": "249"
 *         "carparkcapacity": "388"
 *         "lastupdate": "2011-09-03T18:26:11+02:00"
 *         "state": "0"
 *         "distance": "5184.2893316804"
 *     }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class RelayParkHttpResponseHandler implements
        ResponseHandler<ApiResponse<RelayParkData>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RelayParkHttpResponseHandler.class);

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public ApiResponse<RelayParkData> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }

        final InputStream inputStream = response.getEntity().getContent();

        Type apiResponseType = new TypeToken<ApiResponse<RelayParkData>>() {
        }.getType();

        final ApiResponse<RelayParkData> apiResponse = KeoUtils.getGsonInstance().fromJson(
                new InputStreamReader(inputStream), apiResponseType);

        inputStream.close();

        try {
            KeoUtils.checkResponse(apiResponse);
        } catch (JSONException e) {
            throw new IOException("Unable to parse the json response received from Keolis:\n" + e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return apiResponse;
    }

}
