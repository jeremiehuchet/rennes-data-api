package fr.dudie.keolis.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for Keolis service.
 * 
 * @author Jérémie Huchet
 */
public final class KeoUtils {

    /** The date format the Keolis service use to send. */
    private static final SimpleDateFormat KEOLIS_DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssZ");

    /** The event logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeoUtils.class);

    /**
     * Private constructor to avoid instantiation.
     */
    private KeoUtils() {

    }

    /**
     * Checks the response code of the keolis API and return the JSON Object named "data".
     * 
     * @param reponse
     *            the reponse of the keolis API
     * @return the JSON Object named "data"
     * @throws JSONException
     *             the response does not contains valid data
     */
    public static JSONObject getServiceResponse(final String reponse) throws JSONException {

        final JSONObject jsonResult = new JSONObject(reponse);

        final JSONObject opendata = jsonResult.getJSONObject("opendata");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("checking response for request : {}", opendata.getString("request"));
        }
        final JSONObject answer = opendata.getJSONObject("answer");
        final JSONObject status = answer.getJSONObject("status");
        final JSONObject attributes = status.getJSONObject("@attributes");
        final int code = attributes.getInt("code");
        if (code != 0) {
            final String message = String.format("Keolis API error : code=%S, %s ", code,
                    attributes.getString("message"));
            throw new JSONException(message);
        }
        return answer.getJSONObject("data");
    }

    /**
     * Converts Keolis date string to a {@link Date}.
     * 
     * @param stringDate
     *            the date as a string ("2010-11-11T20:47:06+01:00")
     * @return the date
     */
    public static Date convertJsonStringToDate(final String stringDate) {

        // transforms the following string : 2011-02-20T00:30:06+01:00
        // into : 2011-02-20T00:30:06+0100
        final String d = stringDate.replaceAll("\\:..$",
                stringDate.substring(stringDate.length() - 2));

        try {
            return KEOLIS_DATE_FORMAT.parse(d);
        } catch (final ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * Converts Keolis int status to a {@link Boolean}.
     * 
     * @param status
     *            the status to convert
     * @return false if the given integer is equals to 0, else true
     */
    public static boolean convertJsonIntToBoolean(final int status) {

        return 0 != status;
    }
}
