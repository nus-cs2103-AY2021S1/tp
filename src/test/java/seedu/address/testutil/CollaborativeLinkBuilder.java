package seedu.address.testutil;

import seedu.address.model.task.CollaborativeLink;

/**
 * A utility class to help with building MeetingLink objects.
 */
public class CollaborativeLinkBuilder {

    public static final String DEFAULT_DESCRIPTION = "Project Document";
    public static final String DEFAULT_URL =
            "https://docs.google.com/document/d/1oAObtne793B1nDX123hrbAdEy1aoeua5cTuabc326L4c/edit";

    private String description;
    private String url;

    /**
     * Creates a {@code MeetingLink} with the default details.
     */
    public CollaborativeLinkBuilder() {
        description = DEFAULT_DESCRIPTION;
        url = DEFAULT_URL;
    }

    /**
     * Initializes the MeetingLinkBuilder with the data of {@code meetingToCopy}.
     */
    public CollaborativeLinkBuilder(CollaborativeLink meetingToCopy) {
        description = meetingToCopy.getDescription();
        url = meetingToCopy.getUrl();
    }

    /**
     * Sets the {@code Description} of the {@code MeetingLink} that we are building.
     */
    public CollaborativeLinkBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code URL} of the {@code MeetingLink} that we are building.
     */
    public CollaborativeLinkBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public CollaborativeLink build() {
        return new CollaborativeLink(description, url);
    }

}
