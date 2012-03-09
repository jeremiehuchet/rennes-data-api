package fr.dudie.keolis.model;

/**
 * Class representing attributes field in Keolis Api response.
 * 
 * @author Olivier Boudet
 */
public class StatusAttributes {

    /** The code. */
    private int code;

    /** The message. */
    private String message;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public final int getCode() {

        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code
     *            the code to set
     */
    public final void setCode(final int code) {

        this.code = code;
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public final String getMessage() {

        return message;
    }

    /**
     * Sets the message.
     * 
     * @param message
     *            the message to set
     */
    public final void setMessage(final String message) {

        this.message = message;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("StatusAttributes [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }

}
