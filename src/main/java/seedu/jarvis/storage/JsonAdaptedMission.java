package seedu.jarvis.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAdaptedMission {
    private final String title;
    private final String deadline;

    /**
     * Constructs a Json-friendly Mission object.
     * @param title mission's title
     * @param deadline mission's deadline
     */
    public JsonAdaptedMission(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline) {
        this.title = title;
        this.deadline = deadline;
    }
}
