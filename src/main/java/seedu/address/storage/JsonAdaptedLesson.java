package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

public class JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String name;
    private final String startTime;
    private final String moduleCode;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("name") String name, @JsonProperty("startTime") String startTime,
                             @JsonProperty("endTime") String endTime, @JsonProperty("module") String moduleCode) {
        this.name = name;
        this.startTime = startTime;
        this.moduleCode = moduleCode;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        name = source.getName().fullName;
        startTime = source.getTime().value;
        moduleCode = source.getModuleCode().moduleCode;
        endTime = source.getEndTime().value;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (startTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(startTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelStartTime = new Time(startTime);

        if (!Time.isValidTime(endTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelEndTime = new Time(endTime);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        return new Lesson(modelName, modelStartTime, modelEndTime, modelModuleCode);
    }

}
