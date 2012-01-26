package fr.dudie.keolis.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dudie.keolis.model.LineAlert;

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
public class LineAlertHttpResponseHandler implements ResponseHandler<List<LineAlert>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LineAlertHttpResponseHandler.class);

    /** Default character encoding to use. */
    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * {@inheritDoc}
     * 
     * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
     */
    @Override
    public List<LineAlert> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }
        final String content = EntityUtils.toString(response.getEntity(), DEFAULT_ENCODING);

        JSONObject data = null;
        try {
            data = KeoUtils.getServiceResponse(content);
        } catch (final JSONException e) {
            LOGGER.error("Unable to parse json response from Keolis", e);
            throw new IOException("Unable to parse the json response received from Keolis:\n"
                    + content);
        }

        List<LineAlert> listAlerts = null;

        if (null != data) {
            // else handle multiple stations
            final JSONArray jsonAlerts = data.optJSONArray("alert");
            if (null != jsonAlerts) {
                listAlerts = new ArrayList<LineAlert>();
                for (int i = 0; !jsonAlerts.isNull(i); i++) {
                    listAlerts.add(convertJsonObjectToLineAlert(jsonAlerts.optJSONObject(i)));
                }
            } else {
                final JSONObject jsonAlert = data.optJSONObject("alert");
                if (null != jsonAlert) {
                    listAlerts = new ArrayList<LineAlert>();
                    listAlerts.add(convertJsonObjectToLineAlert(jsonAlert));
                } else {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("0 alert found in response");
                    }
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return listAlerts;
    }

    /**
     * Converts a json formatted line alert to an Object representation.
     * 
     * @param jsonAlert
     *            the json representation
     * @return an object representation
     */
    private LineAlert convertJsonObjectToLineAlert(final JSONObject jsonAlert) {

        final LineAlert alert = new LineAlert();
        alert.setTitle(jsonAlert.optString("title"));
        alert.setDetail(jsonAlert.optString("detail"));
        alert.setStartTime(KeoUtils.convertJsonStringToDate(jsonAlert.optString("starttime")));
        alert.setEndTime(KeoUtils.convertJsonStringToDate(jsonAlert.optString("endtime")));
        alert.setMajorDisturbance(jsonAlert.optString("majordisturbance"));

        final JSONObject lines = jsonAlert.optJSONObject("lines");
        final JSONArray lineArray = lines.optJSONArray("line");
        if (null != lineArray) {
            for (int i = 0; lineArray != null && !lineArray.isNull(i); i++) {
                alert.getLines().add(lineArray.optString(i));
            }
        } else {
            final String line = lines.optString("line");
            if (null != line) {
                alert.getLines().add(line);
            }
        }
        return alert;
    }
}
