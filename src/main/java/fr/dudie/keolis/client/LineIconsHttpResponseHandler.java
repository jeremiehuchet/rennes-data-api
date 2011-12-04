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
import fr.dudie.keolis.model.LineIconData;

/**
 * Handles http responses containing bike stations in json format.
 * 
 * <pre>
 * "baseurl":"http:\/\/data.keolis-rennes.com\/uploads\/tx_icsinfotrafic\/",
 * "line":[
 *   {
 *     "name":"1",
 *     "picto":"LM1.png"
 *   }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class LineIconsHttpResponseHandler implements
        ResponseHandler<ApiResponse<LineIconData>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LineIconsHttpResponseHandler.class);

    /**
     * {@inheritDoc}
     * 
     * @see fr.itinerennes.http.handler.ProgressResponseHandler#handleContent(java.lang.String)
     */

    @Override
    public ApiResponse<LineIconData> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }

        final InputStream inputStream = response.getEntity().getContent();

        Type apiResponseType = new TypeToken<ApiResponse<LineIconData>>() {
        }.getType();

        final ApiResponse<LineIconData> apiResponse = KeoUtils.getGsonInstance().fromJson(
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
