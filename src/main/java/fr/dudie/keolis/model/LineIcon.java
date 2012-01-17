package fr.dudie.keolis.model;

import com.google.gson.annotations.SerializedName;

/**
 * Contains informations to retrieve the icon of a transport line.
 * 
 * @author Jérémie Huchet
 */
public class LineIcon {

    /** The line identifier. */
    @SerializedName("name")
    private String line;

    /** The icon's name. */
    @SerializedName("picto")
    private String iconName;

    /**
     * Gets the line identifier.
     * 
     * @return the line identifier
     */
    public final String getLine() {

        return line;
    }

    /**
     * Gets the icon's name.
     * 
     * @return the name of the icon
     */
    public final String getIconName() {

        return iconName;
    }

    /**
     * Sets the line.
     * 
     * @param line
     *            the line to set
     */
    public final void setLine(final String line) {

        this.line = line;
    }

    /**
     * Sets the iconName.
     * 
     * @param iconName
     *            the iconName to set
     */
    public final void setIconName(final String iconName) {

        this.iconName = iconName;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("LineIcon [line=");
        builder.append(line);
        builder.append(", iconName=");
        builder.append(iconName);
        builder.append("]");
        return builder.toString();
    }

}
