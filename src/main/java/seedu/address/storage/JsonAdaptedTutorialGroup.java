package seedu.address.storage;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Module;
import seedu.address.model.TutorialGroup;

public class JsonAdaptedTutorialGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    private final String id;
    private LocalTime startTime;
    private LocalTime endTime;
    private int durationInHours;

    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("id") String id) {
        this.id = id;
        startTime = LocalTime.parse("15:00");
        endTime = LocalTime.parse("17:00");
        durationInHours = 2;
    }

    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        id = source.getId();
        startTime = source.getStartTime();
        endTime = source.getEndTime();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public TutorialGroup toModelType() throws IllegalValueException {

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        return new TutorialGroup(id);
    }


}
