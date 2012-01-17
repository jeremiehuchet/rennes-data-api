package fr.dudie.keolis.model;

import java.util.Date;

/**
 * Represents a relay park.
 * 
 * @author Jérémie Huchet
 */
public class RelayPark {

    /** The relay park name. */
    private String name;

    /** The relay park latitude. */
    private double latitude;

    /** The relay park longitude. */
    private double longitude;

    /** The amount of parks available. */
    private int carParkAvailable;

    /** The relay park capacity. */
    private int carParkCapacity;

    /** The last update date. */
    private Date lastUpdate;

    /** The park state (0: OPEN, 1: CLOSED, 2: FULL, 3: UNAVAILABLE). */
    private int state;

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public final String getName() {

        return name;
    }

    /**
     * Gets the latitude.
     * 
     * @return the latitude
     */
    public final int getLatitude() {

        return (int) (latitude * 1E6);
    }

    /**
     * Gets the longitude.
     * 
     * @return the longitude
     */
    public final int getLongitude() {

        return (int) (longitude * 1E6);
    }

    /**
     * Gets the carParkAvailable.
     * 
     * @return the carParkAvailable
     */
    public final int getCarParkAvailable() {

        return carParkAvailable;
    }

    /**
     * Gets the carParkCapacity.
     * 
     * @return the carParkCapacity
     */
    public final int getCarParkCapacity() {

        return carParkCapacity;
    }

    /**
     * Gets the lastUpdate.
     * 
     * @return the lastUpdate
     */
    public final Date getLastUpdate() {

        return lastUpdate;
    }

    /**
     * Gets the state.
     * 
     * @return the state
     */
    public final RelayParkState getState() {

        return RelayParkState.values()[state];
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the name to set
     */
    public final void setName(final String name) {

        this.name = name;
    }

    /**
     * Sets the latitude.
     * 
     * @param latitude
     *            the latitude to set
     */
    public final void setLatitude(final double latitude) {

        this.latitude = latitude;
    }

    /**
     * Sets the longitude.
     * 
     * @param longitude
     *            the longitude to set
     */
    public final void setLongitude(final double longitude) {

        this.longitude = longitude;
    }

    /**
     * Sets the carParkAvailable.
     * 
     * @param carParkAvailable
     *            the carParkAvailable to set
     */
    public final void setCarParkAvailable(final int carParkAvailable) {

        this.carParkAvailable = carParkAvailable;
    }

    /**
     * Sets the carParkCapacity.
     * 
     * @param carParkCapacity
     *            the carParkCapacity to set
     */
    public final void setCarParkCapacity(final int carParkCapacity) {

        this.carParkCapacity = carParkCapacity;
    }

    /**
     * Sets the lastUpdate.
     * 
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public final void setLastUpdate(final Date lastUpdate) {

        this.lastUpdate = lastUpdate;
    }

    /**
     * Sets the state.
     * 
     * @param state
     *            the state to set
     */
    public final void setState(final int state) {

        this.state = state;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("RelayPark [name=");
        builder.append(name);
        builder.append(", latitude=");
        builder.append(latitude);
        builder.append(", longitude=");
        builder.append(longitude);
        builder.append(", carParkAvailable=");
        builder.append(carParkAvailable);
        builder.append(", carParkCapacity=");
        builder.append(carParkCapacity);
        builder.append(", lastUpdate=");
        builder.append(lastUpdate);
        builder.append(", state=");
        builder.append(state);
        builder.append("]");
        return builder.toString();
    }

}
