package fr.dudie.keolis.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jeremie Huchet
 */
public class StopLineDeparture {

    /**
     * Helper class for Gson deserialization. The accurate and headsign properties values are held into a json object.
     */
    private static class Attributes {

        /** Whether or not the departure time is accurate. */
        private Boolean accurate;

        /** The trip headsign. */
        private String headsign;
    }

    /** The departure date. */
    @SerializedName("content")
    private Date date;

    /** See {@link Attributes} */
    @SerializedName("@attributes")
    private Attributes attributes;

    /**
     * Gets the departure date.
     * 
     * @return the departure date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets whether or not the departure date is accurate.
     * 
     * @return true if the departure date is accurate
     */
    public boolean isAccurate() {
        return attributes.accurate;
    }

    /**
     * Gets the trip headsign.
     * 
     * @return the trip headsign
     */
    public String getHeadsign() {
        return attributes.headsign;
    }
}
