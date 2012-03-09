package fr.dudie.keolis.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class representing the opendata field in Keolis Api Response.
 * 
 * @author Olivier Boudet
 * @param <T>
 *            the type of data returned by the api.
 */
public class OpenData<T> {

    /** The request URL. */
    @SerializedName("request")
    private String request;

    /** The answer. */
    @SerializedName("answer")
    private Answer<T> answer;

    /**
     * Gets the answer field.
     * 
     * @return the answer field.
     */
    public final Answer<T> getAnswer() {

        return answer;
    }

    /**
     * Gets the request field.
     * 
     * @return the request field.
     */
    public final String getRequest() {

        return request;
    }

    /**
     * Sets the request.
     * 
     * @param request
     *            the request to set
     */
    public final void setRequest(final String request) {

        this.request = request;
    }

    /**
     * Sets the answer.
     * 
     * @param answer
     *            the answer to set
     */
    public final void setAnswer(final Answer<T> answer) {

        this.answer = answer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append("OpenData [request=");
        builder.append(request);
        builder.append(", answer=");
        builder.append(answer);
        builder.append("]");
        return builder.toString();
    }

}
