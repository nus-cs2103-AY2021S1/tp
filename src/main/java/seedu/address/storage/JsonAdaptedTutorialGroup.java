package seedu.address.storage;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.TutorialGroup;

public class JsonAdaptedTutorialGroup {

    private final String id;
    private LocalTime startTime;
    private LocalTime endTime;
    private int durationInHours;

    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("id") String id) {
        this.id = id;
    }

    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        id = source.getId();
    }


}
