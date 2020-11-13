package seedu.jarvis.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.jarvis.model.quest.Quest;

public class JsonAdaptedQuest {
    private final String title;
    private final String deadline;

    /**
     * Constructs a Json-friendly Quest object.
     * @param title quest's title
     * @param deadline quest's deadline
     */
    public JsonAdaptedQuest(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Quest} into this class for Jackson use.
     */
    public JsonAdaptedQuest(Quest source) {
        title = source.getTitle();
        deadline = source.getDeadline();
    }
}
