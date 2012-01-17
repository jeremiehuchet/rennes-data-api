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
import fr.dudie.keolis.model.LineAlertData;

/**
 * Handles http responses containing lines alerts in json format.
 * 
 * <pre>
 * alert: [
 *     {
 *         title: "59 marché Corps Nuds"
 *         starttime: "2010-09-27T09:33:30+02:00"
 *         endtime: "2012-07-27T00:00:00+02:00"
 *         lines: {
 *             line: "59"
 *         }
 *         majordisturbance: "0"
 *         detail: "Le dimanche matin, jusqu'à 14h environ : Marché à Corps Nuds Ligne 59 dans les deux sens L'arrêt Place de Kildare est reporté à l'arrêt Corps Nuds Mairie. Le Star vous remercie de votre compréhension. Pour plus d'information contactez INFOSTAR au 09 70 821 800 (Appel non surtaxé)"
 *         link: ""
 *     }
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class LineAlertHttpResponseHandler implements
        ResponseHandler<ApiResponse<LineAlertData>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LineAlertHttpResponseHandler.class);

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public ApiResponse<LineAlertData> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }

        final InputStream inputStream = response.getEntity().getContent();

        final Type apiResponseType = new TypeToken<ApiResponse<LineAlertData>>() {
        }.getType();

        final ApiResponse<LineAlertData> apiResponse = KeoUtils.getGsonInstance().fromJson(
                new InputStreamReader(inputStream), apiResponseType);

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

}
