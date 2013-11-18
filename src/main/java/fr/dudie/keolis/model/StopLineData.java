package fr.dudie.keolis.model;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jeremie Huchet
 */
public class StopLineData {

    /**
     * Helper class for Gson deserialization. The localdatetime property value is held into a json object.
     */
    private static class Attributes {

        /** The date of the server generating the response. */
        private Date localdatetime;
    }

    /** See {@link Attributes}. */
    @SerializedName("@attributes")
    private Attributes attributes;

    /** The stop lines informations. */
    @SerializedName("stopline")
    private List<StopLine> stopLines;

    /**
     * Gets the time of the system generating the bus next departures.
     * 
     * @return the time of the system generating the bus next departures
     */
    public Date getServerDateTime() {
        return attributes.localdatetime;
    }

    /**
     * Gets the stop lines informations.
     * 
     * The dates are modified to be coherent with the current system. All departure date are moved forward/backard from
     * the difference between the server date and the local date.
     * 
     * @return the stop lines informations
     */
    public List<StopLine> getStopLines() {
        return normalize(stopLines);
    }

    private List<StopLine> normalize(final List<StopLine> stopLines) {
        // TODO
        return stopLines;
    }

    /**
     * Gets the raw stop lines informations from the server.
     * 
     * @return the raw stop lines informations
     */
    public List<StopLine> getRawStopLines() {
        return stopLines;
    }
}
