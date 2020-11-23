package seedu.tr4cker.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String deadline;
    private final int completionStatus;
    private final String description;
    private final List<JsonAdaptedModuleCode> moduleCode = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                           @JsonProperty("completionStatus") int completionStatus,
                           @JsonProperty("description") String description,
                           @JsonProperty("moduleCode") List<JsonAdaptedModuleCode> moduleCode,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.deadline = deadline;
        this.completionStatus = completionStatus;
        this.description = description;
        if (moduleCode != null) {
            this.moduleCode.addAll(moduleCode);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().taskName;
        deadline = source.getDeadline().toString();
        completionStatus = source.getCompletionStatus().value;
        description = source.getTaskDescription().value;
        moduleCode.addAll(source.getModuleCode().stream()
                .map(JsonAdaptedModuleCode::new).collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }
        final List<ModuleCode> taskModuleCode = new ArrayList<>();
        for (JsonAdaptedModuleCode code : moduleCode) {
            taskModuleCode.add(code.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline, false);

        /*
        no null check for CompletionStatus
         */
        if (!CompletionStatus.isValidCompletionStatus(completionStatus)) {
            throw new IllegalValueException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }
        final CompletionStatus modelCompletionStatus = new CompletionStatus(completionStatus);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidTaskDescription(description)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelTaskDescription = new TaskDescription(description);

        final Set<ModuleCode> modelModuleCode = new HashSet<>(taskModuleCode);
        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Task(
                modelName,
                modelDeadline,
                modelCompletionStatus,
                modelTaskDescription,
                modelModuleCode,
                modelTags);
    }

}
