/*
 * Copyright (C) 2010 Dudie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
