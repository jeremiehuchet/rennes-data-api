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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a line alert.
 * 
 * <pre>
 * alert: [
 *     {
 *         title: "59 marché Corps Nuds"
 *         starttime: "2010-09-27T09:33:30+02:00"
 *         endtime: "2012-07-27T00:00:00+02:00"
 *         lines: {
 *             line: "59"
 *         }
 *         majordisturbance: "0"
 *         detail: "Le dimanche matin, jusqu'à 14h environ : Marché à Corps Nuds Ligne 59 dans les deux sens L'arrêt Place de Kildare est reporté à l'arrêt Corps Nuds Mairie. Le Star vous remercie de votre compréhension. Pour plus d'information contactez INFOSTAR au 09 70 821 800 (Appel non surtaxé)"
 *         link: ""
 *     }
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public class LineAlert {

    /** The alert title. */
    private String title;

    /** The alert start time. */
    @SerializedName("starttime")
    private Date startTime;

    /** The alert end time. */
    @SerializedName("endtime")
    private Date endTime;

    /** The lines the alert is related to. */
    @SerializedName("lines")
    private final List<String> lines = new ArrayList<String>();

    /** The major disturbance. */
    @SerializedName("majordisturbance")
    private String majorDisturbance;

    /** The alert details. */
    private String detail;

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public final String getTitle() {

        return title;
    }

    /**
     * Gets the startTime.
     * 
     * @return the startTime
     */
    public final Date getStartTime() {

        return startTime;
    }

    /**
     * Gets the endTime.
     * 
     * @return the endTime
     */
    public final Date getEndTime() {

        return endTime;
    }

    /**
     * Gets the lines.
     * 
     * @return the lines
     */
    public final List<String> getLines() {

        return lines;
    }

    /**
     * Gets the majorDisturbance.
     * 
     * @return the majorDisturbance
     */
    public final String getMajorDisturbance() {

        return majorDisturbance;
    }

    /**
     * Gets the detail.
     * 
     * @return the detail
     */
    public final String getDetail() {

        return detail;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            the title to set
     */
    public final void setTitle(final String title) {

        this.title = title;
    }

    /**
     * Sets the startTime.
     * 
     * @param startTime
     *            the startTime to set
     */
    public final void setStartTime(final Date startTime) {

        this.startTime = startTime;
    }

    /**
     * Sets the endTime.
     * 
     * @param endTime
     *            the endTime to set
     */
    public final void setEndTime(final Date endTime) {

        this.endTime = endTime;
    }

    /**
     * Sets the majorDisturbance.
     * 
     * @param majorDisturbance
     *            the majorDisturbance to set
     */
    public final void getMajorDisturbance(final String majorDisturbance) {

        this.majorDisturbance = majorDisturbance;
    }

    /**
     * Sets the detail.
     * 
     * @param detail
     *            the detail to set
     */
    public final void setDetail(final String detail) {

        this.detail = detail;
    }

    /**
     * Try to make the alert title more readable.
     * <p>
     * Keolis API returns <em>titles</em> with the following formats:
     * <code>&lt;line id&gt;* &lt;alert title&gt;</code> and a list of line ids related to the
     * alert.
     * <p>
     * Sample : <em>4 40 52 Some alert about something</em> and the following list of lines ids :
     * <em>4, 40ex, 52</em>.
     * <p>
     * This method removes lines identifiers from the begining of the title.
     * 
     * @return a title without line identifiers
     */
    public final String getBetterTitle() {

        String betterTitle = title == null ? "" : title;
        boolean lastTokenNotFound = false;
        final List<String> lowercaseLines = getLinesLowercase();
        final StringTokenizer stk = new StringTokenizer(title, " ");

        while (stk.hasMoreElements() && !lastTokenNotFound) {
            final String word = stk.nextToken().toLowerCase();

            if (lowercaseLines.contains(word)) {
                // if word is a line identifier related to this alert, then remove it from the title
                betterTitle = betterTitle.replaceFirst(String.format("\\s*%s\\s*", word), "");
            } else {
                // else stop trying to make a better title
                lastTokenNotFound = true;
            }
        }
        if (betterTitle.length() > 0) {
            return betterTitle.substring(0, 1).toUpperCase() + betterTitle.substring(1);
        } else {
            return "";
        }
    }

    /**
     * Gets lowercase line identifiers.
     * 
     * @return the lowercased line ids
     */
    public final List<String> getLinesLowercase() {

        final List<String> linesLowercase = new ArrayList<String>(lines.size());
        for (final String line : lines) {
            linesLowercase.add(line.toLowerCase());
        }
        return linesLowercase;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("LineAlert [title=");
        builder.append(title);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", lines=");
        builder.append(lines);
        builder.append(", majorDisturbance=");
        builder.append(majorDisturbance);
        builder.append(", detail=");
        builder.append(detail);
        builder.append("]");
        return builder.toString();
    }

}
