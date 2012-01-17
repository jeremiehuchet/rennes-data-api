package fr.dudie.keolis.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olivier Boudet
 */
public class BikeData {

    /** The station field. */
    @SerializedName("station")
    private List<BikeStation> stations;

    /**
     * Gets the bike stations.
     * 
     * @return the list of bike stations
     */
    public final List<BikeStation> getStations() {

        return stations;
    }

    /**
     * Sets the stations.
     * 
     * @param stations
     *            the stations to set
     */
    public final void setStations(final List<BikeStation> stations) {

        this.stations = stations;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("BikeData [stations=");
        builder.append(stations);
        builder.append("]");
        return builder.toString();
    }

}
