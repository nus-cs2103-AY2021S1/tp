package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Link {

    public static final String MESSAGE_CONSTRAINTS = "Link must be in URL Format: "
            + "\"^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]\"\n"
            + "Example: " + "https://nus-sg.zoom.us/j/12350904475?pwd=T0Jw";

    public static final String VALIDATION_REGEX =
            "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    /** A brief description of the link. */
    private final String description;

    /** The url for the link */
    private final String url;

    /**
     * Constructs a link with description and url.
     *
     * @param description a brief description of the link
     * @param url the url of the link
     */
    public Link(String description, String url) {
        requireAllNonNull(description, url);
        this.description = description;
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        this.url = url;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidUrl(String url) {
        return url.matches(VALIDATION_REGEX) || url.equals(null);
    }

    /**
     * Returns the description of the link as a string.
     *
     * @return the description of the link.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the url of the link as a string.
     *
     * @return the url of the link.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a boolean value indicating if the link is equal to
     * another object by determining if descriptions and url
     * are equal.
     *
     * @param o an object that is compared to the link to determine if both are equal
     * @return true or false if the object is equal or not equal to the link respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Link) {
            Link link = (Link) o;
            boolean isEqualLink = this.description.equals(link.description) && this.url == link.url;
            return isEqualLink;
        } else {
            return false;
        }
    }
}
