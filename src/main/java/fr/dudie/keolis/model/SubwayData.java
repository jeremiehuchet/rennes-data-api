package fr.dudie.keolis.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olivier Boudet
 */
public class SubwayData {

    /** The list of stations. */
    @SerializedName("station")
    private List<SubwayStation> stations;

    /**
     * Gets the stations.
     * 
     * @return the stations
     */
    public final List<SubwayStation> getStations() {

        return stations;
    }

    /**
     * Sets the stations.
     * 
     * @param stations
     *            the stations to set
     */
    public final void setStations(final List<SubwayStation> stations) {

        this.stations = stations;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("SubwayData [stations=");
        builder.append(stations);
        builder.append("]");
        return builder.toString();
    }

}
