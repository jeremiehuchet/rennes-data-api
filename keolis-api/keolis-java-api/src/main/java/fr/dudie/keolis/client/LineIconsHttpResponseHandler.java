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

import fr.dudie.keolis.model.LineIcon;

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
public final class LineIconsHttpResponseHandler implements ResponseHandler<List<LineIcon>> {

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LineIconsHttpResponseHandler.class);

    /** Default character encoding to use. */
    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * {@inheritDoc}
     * 
     * @see fr.itinerennes.http.handler.ProgressResponseHandler#handleContent(java.lang.String)
     */

    @Override
    public List<LineIcon> handleResponse(final HttpResponse response)
            throws ClientProtocolException, IOException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.start");
        }

        final String content = EntityUtils.toString(response.getEntity(), DEFAULT_ENCODING);

        JSONObject data = null;
        try {
            data = KeoUtils.getServiceResponse(content);
        } catch (final JSONException e) {
            throw new IOException("Unable to parse the json response received from Keolis:\n"
                    + content);
        }

        List<LineIcon> listIcons = null;

        if (null != data) {

            listIcons = new ArrayList<LineIcon>();
            final String baseUrl = data.optString("baseurl");
            final JSONArray jsonIcons = data.optJSONArray("line");
            for (int i = 0; !jsonIcons.isNull(i); i++) {
                listIcons.add(convertJsonObjectToLineTransportIcon(baseUrl,
                        jsonIcons.optJSONObject(i)));
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("handleResponse.end");
        }
        return listIcons;
    }

    /**
     * Converts a json object to a bean representing a line icon.
     * 
     * @param baseUrl
     *            the base url of the icon
     * @param jsonObject
     *            the json object to convert
     * @return the bean representing the given json object
     */
    private LineIcon convertJsonObjectToLineTransportIcon(final String baseUrl,
            final JSONObject jsonObject) {

        final LineIcon icon = new LineIcon();
        icon.setLine(jsonObject.optString("name"));
        icon.setIconUrl(String.format("%s/%s", baseUrl, jsonObject.optString("picto")));
        return icon;
    }
}
