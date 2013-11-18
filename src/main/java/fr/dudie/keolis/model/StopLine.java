package fr.dudie.keolis.model;

import java.util.List;

/**
 * @author Jeremie Huchet
 */
public class StopLine {
    

    /**
     * Helper class for Gson deserialization. The date and attributes properties values are held into a json object.
     */
    private static class Departures {

        /** The departure date. */
        private List<StopLineDeparture> departure;
    }

    /** The stop id. */
    private String stop;

    /** The route id. */
    private String route;

    /** The route direction. */
    private Integer direction;

    /** The bus next departures. */
    private Departures departures;

    /**
     * Gets the stop id.
     * 
     * @return the stop id
     */
    public String getStop() {
        return stop;
    }

    /**
     * Gets the route id.
     * 
     * @return the route id
     */
    public String getRoute() {
        return route;
    }

    /**
     * Gets the route direction.
     * 
     * @return the route direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Gets the bus next departures.
     * 
     * @return the bus next departures
     */
    public List<StopLineDeparture> getDepartures() {
        return departures.departure;
    }

}
