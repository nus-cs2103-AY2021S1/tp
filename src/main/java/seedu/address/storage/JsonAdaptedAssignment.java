package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

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
    private final String priority;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                                 @JsonProperty("module") String moduleCode,
                                 @JsonProperty("isReminded") boolean isReminded,
                                 @JsonProperty("isScheduled") boolean isScheduled,
                                 @JsonProperty("suggestedStartTime") String suggestedStartTime,
                                 @JsonProperty("suggestedEndTime") String suggestedEndTime,
                                 @JsonProperty("priority") String priority,
                                 @JsonProperty("isDone") boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.moduleCode = moduleCode;
        this.isReminded = isReminded;
        this.isScheduled = isScheduled;
        this.suggestedStartTime = suggestedStartTime;
        this.suggestedEndTime = suggestedEndTime;
        this.priority = priority;
        this.isDone = isDone;
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
        if (isScheduled) {
            suggestedStartTime = source.getSchedule().getSuggestedStartTime().value;
            suggestedEndTime = source.getSchedule().getSuggestedEndTime().value;
        } else {
            suggestedStartTime = "";
            suggestedEndTime = "";
        }
        priority = source.getPriority().toString();
        isDone = source.getDone().isMarkedDone();
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        final Priority modelPriority;
        if (priority == null || priority.equals("")) {
            modelPriority = new Priority();
        } else {
            modelPriority = new Priority(priority);
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(deadline)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelDeadline = new Time(deadline);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        final Remind modelRemind = new Remind(isReminded);

        final Done modelDone = new Done(isDone);

        // the assignment is scheduled
        if ((suggestedStartTime.equals("") || suggestedEndTime.equals("")) && isScheduled) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Schedule.class.getSimpleName()));
        }
        if (isScheduled && !Time.isValidTime(suggestedStartTime)) {
            throw new IllegalValueException(Schedule.START_TIME_MESSAGE_CONSTRAINS);
        }
        if (isScheduled && !Time.isValidTime(suggestedEndTime)) {
            throw new IllegalValueException(Schedule.END_TIME_MESSAGE_CONSTRAINS);
        }
        // the assignment is not scheduled
        if (!isScheduled && (!suggestedStartTime.equals("") || !suggestedEndTime.equals(""))) {
            throw new IllegalValueException(Schedule.NOT_SCHEDULED_CONSTRAINS);
        }

        if (isScheduled) {
            final Schedule modelSchedule = new Schedule(new Time(suggestedStartTime),
                    new Time(suggestedEndTime));
            return new Assignment(modelName, modelDeadline, modelModuleCode, modelRemind, modelSchedule, modelPriority,
                    modelDone);
        } else {
            return new Assignment(modelName, modelDeadline, modelModuleCode, modelRemind, new Schedule(),
                    modelPriority, modelDone);
        }
    }
}
