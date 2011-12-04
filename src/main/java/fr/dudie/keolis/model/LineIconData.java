package fr.dudie.keolis.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Olivier Boudet
 */
public class LineIconData {

    /** The base URL to fetch icon from. */
    private String baseUrl;

    /** The list of lines and icon files names. */
    @SerializedName("line")
    private List<LineIcon> lines;

    /**
     * Gets the baseUrl.
     * 
     * @return the baseUrl
     */
    public final String getBaseUrl() {

        return baseUrl;
    }

    /**
     * Gets the lines.
     * 
     * @return the lines
     */
    public final List<LineIcon> getLines() {

        return lines;
    }

    /**
     * Sets the baseUrl.
     * 
     * @param baseUrl
     *            the baseUrl to set
     */
    public final void setBaseUrl(final String baseUrl) {

        this.baseUrl = baseUrl;
    }

    /**
     * Sets the lines.
     * 
     * @param lines
     *            the lines to set
     */
    public final void setLines(final List<LineIcon> lines) {

        this.lines = lines;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("LineIconData [baseUrl=");
        builder.append(baseUrl);
        builder.append(", line=");
        builder.append(lines);
        builder.append("]");
        return builder.toString();
    }

}
