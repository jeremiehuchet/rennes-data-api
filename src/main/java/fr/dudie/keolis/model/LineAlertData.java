package fr.dudie.keolis.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olivier Boudet
 */
public class LineAlertData {

    /** The list of line alerts. */
    @SerializedName("alert")
    private List<LineAlert> alerts;

    /**
     * Gets the alerts.
     * 
     * @return the alerts
     */
    public final List<LineAlert> getAlerts() {

        return alerts;
    }

    /**
     * Sets the alerts.
     * 
     * @param alerts
     *            the alerts to set
     */
    public final void setAlerts(final List<LineAlert> alerts) {

        this.alerts = alerts;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("LineAlertData [alerts=");
        builder.append(alerts);
        builder.append("]");
        return builder.toString();
    }
}
