package fr.dudie.keolis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents a line alert.
 * 
 * @author Jérémie Huchet
 */
public class LineAlert {

    /** The alert title. */
    private String title;

    /** The alert start time. */
    private Date startTime;

    /** The alert end time. */
    private Date endTime;

    /** The lines the alert is related to. */
    private List<String> lines = new ArrayList<String>();

    /** The major disturbance. */
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
     * Sets the title.
     * 
     * @param title
     *            the title to set
     */
    public final void setTitle(final String title) {

        this.title = title;
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
     * Sets the startTime.
     * 
     * @param startTime
     *            the startTime to set
     */
    public final void setStartTime(final Date startTime) {

        this.startTime = startTime;
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
     * Sets the endTime.
     * 
     * @param endTime
     *            the endTime to set
     */
    public final void setEndTime(final Date endTime) {

        this.endTime = endTime;
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
     * Sets the lines.
     * 
     * @param lines
     *            the lines to set
     */
    public final void setLines(final List<String> lines) {

        this.lines = lines;
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
     * Sets the majorDisturbance.
     * 
     * @param majorDisturbance
     *            the majorDisturbance to set
     */
    public final void setMajorDisturbance(final String majorDisturbance) {

        this.majorDisturbance = majorDisturbance;
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
    public String getBetterTitle() {

        String betterTitle = title;
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
        return betterTitle.substring(0, 1).toUpperCase() + betterTitle.substring(1);
    }

    /**
     * Gets lowercase line identifiers.
     * 
     * @return the lowercased line ids
     */
    public List<String> getLinesLowercase() {

        final List<String> linesLowercase = new ArrayList<String>(lines.size());
        for (final String line : lines) {
            linesLowercase.add(line.toLowerCase());
        }
        return linesLowercase;
    }
}
