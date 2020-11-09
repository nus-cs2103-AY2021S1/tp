package seedu.address.testutil;

import seedu.address.model.task.MeetingLink;

/**
 * A utility class to help with building MeetingLink objects.
 */
public class MeetingLinkBuilder {

    public static final String DEFAULT_DESCRIPTION = "Project Meeting";
    public static final String DEFAULT_DATETIME = "03-03-2020 1411";
    public static final String DEFAULT_URL =
            "https://nus-sg.zoom.us/j/85350904475?pwd=T0JwTEIwNjRuMnNKUEt4L2RBMFJWZz09";

    private String description;
    private String datetime;
    private String url;

    /**
     * Initializes the MeetingLinkBuilder with default data.
     */
    public MeetingLinkBuilder() {
        description = DEFAULT_DESCRIPTION;
        datetime = DEFAULT_DATETIME;
        url = DEFAULT_URL;
    }

    /**
     * Initializes the MeetingLinkBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingLinkBuilder(MeetingLink meetingToCopy) {
        description = meetingToCopy.getRawDescription();
        datetime = meetingToCopy.getDateTime();
        url = meetingToCopy.getUrl();
    }

    /**
     * Sets the {@code Description} of the {@code MeetingLink} that we are building.
     */
    public MeetingLinkBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code MeetingLink} that we are building.
     */
    public MeetingLinkBuilder withDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    /**
     * Sets the {@code URL} of the {@code MeetingLink} that we are building.
     */
    public MeetingLinkBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public MeetingLink build() {
        return new MeetingLink(description, url, datetime);
    }

}
