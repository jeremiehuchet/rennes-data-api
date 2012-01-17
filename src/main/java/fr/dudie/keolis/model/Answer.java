package fr.dudie.keolis.model;

/**
 * Class representing the "answer" field of keolis response.
 * 
 * @author Olivier Boudet
 * @param <T>
 *            type of data
 */
public class Answer<T> {

    /** Status field. */
    private Status status;

    /** Data field. */
    private T data;

    /**
     * Gets the data field.
     * 
     * @return the data field.
     */
    public final T getData() {

        return data;
    }

    /**
     * Gets the status field.
     * 
     * @return the status field.
     */
    public final Status getStatus() {

        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            the status to set
     */
    public void setStatus(final Status status) {

        this.status = status;
    }

    /**
     * Sets the data.
     * 
     * @param data
     *            the data to set
     */
    public void setData(final T data) {

        this.data = data;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("Answer [status=");
        builder.append(status);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }

}
