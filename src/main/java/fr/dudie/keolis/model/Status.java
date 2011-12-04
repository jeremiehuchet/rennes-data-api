package fr.dudie.keolis.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class representing the status field in Keolis Api response.
 * 
 * @author Olivier Boudet
 */
public class Status {

    /** THe attributes field. */
    @SerializedName("@attributes")
    private StatusAttributes attributes;

    /**
     * Gets the attributes field.
     * 
     * @return the attributes field
     */
    public final StatusAttributes getAttributes() {

        return attributes;
    }

    /**
     * Sets the attributes.
     * 
     * @param attributes
     *            the attributes to set
     */
    public final void setAttributes(final StatusAttributes attributes) {

        this.attributes = attributes;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Status [attributes=");
        builder.append(attributes);
        builder.append("]");
        return builder.toString();
    }

}
