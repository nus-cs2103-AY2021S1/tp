package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a Tag's meeting link in the Projact.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeetingLink(String)}
 */
public class MeetingLink {

    public static final String MESSAGE_CONSTRAINTS = "The link must start with either http:// or https://,"
            + " followed by a subdomain, and end with top level domains like"
            + " .com or .org";

    public static final String VALIDATION_REGEX =
            "^https?:\\/\\/([-a-zA-Z0-9]+\\.)+[a-zA-Z]{2,3}(:\\d{1,5})?([?\\/].*)?$";

    public final URL link;

    /**
     * Constructs a {@code MeetingLink}.
     *
     * @param link A valid URL.
     */
    public MeetingLink(String link) throws MalformedURLException {
        requireNonNull(link);
        checkArgument(isValidMeetingLink(link), MESSAGE_CONSTRAINTS);
        this.link = new URL(link);
    }

    /**
     * Returns true if a given URL is a valid meeting link.
     */
    public static boolean isValidMeetingLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both MeetingLink objects have the same meeting link field.
     * This defines a weaker notion of equality between two MeetingLink objects.
     */
    public boolean isSameMeetingLink(MeetingLink otherMeetingLink) {
        if (otherMeetingLink == this) {
            return true;
        }

        return otherMeetingLink != null
                && otherMeetingLink.link.equals(this.link);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return link.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingLink // instanceof handles nulls
                && link.equals(((MeetingLink) other).link)); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}
