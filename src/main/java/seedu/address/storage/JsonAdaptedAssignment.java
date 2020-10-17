package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";

    private final String name;
    private final String deadline;
    private final String moduleCode;
    private final boolean isReminded;
    private final boolean isScheduled;
    private final String suggestedStartTime;
    private final String suggestedEndTime;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                                 @JsonProperty("module") String moduleCode,
                                 @JsonProperty("isRemind") boolean isReminded,
                                 @JsonProperty("isSchedule") boolean isScheduled,
                                 @JsonProperty("suggestedStartTime") String suggestedStartTime,
                                 @JsonProperty("suggestedEndTime") String suggestedEndTime) {
        this.name = name;
        this.deadline = deadline;
        this.moduleCode = moduleCode;
        this.isReminded = isReminded;
        this.isScheduled = isScheduled;
        this.suggestedStartTime = suggestedStartTime;
        this.suggestedEndTime = suggestedEndTime;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        name = source.getName().fullName;
        deadline = source.getDeadline().value;
        moduleCode = source.getModuleCode().moduleCode;
        isReminded = source.getRemind().isReminded();
        isScheduled = source.getSchedule().isScheduled();
        suggestedStartTime = source.getSchedule().getSuggestedStartTime().value;
        suggestedEndTime = source.getSchedule().getSuggestedEndTime().value;
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        final Remind modelRemind = new Remind(isReminded);

        if ((suggestedStartTime == null || suggestedEndTime == null) && isScheduled) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        if (suggestedStartTime != null && !Deadline.isValidDeadline(suggestedStartTime)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        if (suggestedEndTime != null && !Deadline.isValidDeadline(suggestedEndTime)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Schedule modelSchedule = new Schedule(new Deadline(suggestedStartTime),
                new Deadline(suggestedEndTime));

        return new Assignment(modelName, modelDeadline, modelModuleCode, modelRemind, modelSchedule);
    }

}
