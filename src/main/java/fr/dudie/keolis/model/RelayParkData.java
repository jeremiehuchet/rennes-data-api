package fr.dudie.keolis.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olivier Boudet
 */
public class RelayParkData {

    /** The list of relay parks. */
    @SerializedName("relaypark")
    private List<RelayPark> relayparks;

    /**
     * Gets the list of relay parks.
     * 
     * @return the list of relay parks.
     */
    public final List<RelayPark> getRelayParks() {

        return relayparks;
    }

    /**
     * Gets the relayparks.
     * 
     * @return the relayparks
     */
    public final List<RelayPark> getRelayparks() {

        return relayparks;
    }

    /**
     * Sets the relayparks.
     * 
     * @param relayparks
     *            the relayparks to set
     */
    public final void setRelayparks(final List<RelayPark> relayparks) {

        this.relayparks = relayparks;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("RelayParkData [relayparks=");
        builder.append(relayparks);
        builder.append("]");
        return builder.toString();
    }
}
