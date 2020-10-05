package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Email;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final String deadline;
    private final String email;
    private final String projectDescription;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTask> occupied = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName,
                                @JsonProperty("deadline") String deadline,
                                @JsonProperty("email") String email,
                                @JsonProperty("projectDescription") String projectDescription,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                @JsonProperty("occupied") List<JsonAdaptedTask> occupied) {
        this.projectName = projectName;
        this.deadline = deadline;
        this.email = email;
        this.projectDescription = projectDescription;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (occupied != null) {
            this.occupied.addAll(occupied);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().fullProjectName;
        deadline = source.getDeadline().value;
        email = source.getEmail().value;
        projectDescription = source.getProjectDescription().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        occupied.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Tag> projectTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            projectTags.add(tag.toModelType());
        }
        final List<Task> projectTasks = new ArrayList<>();
        for (JsonAdaptedTask task : occupied) {
            projectTasks.add(task.toModelType());
        }

        if (projectName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidProjectName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modelProjectName = new ProjectName(projectName);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (projectDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ProjectDescription.class.getSimpleName()));
        }
        if (!ProjectDescription.isValidProjectDescription(projectDescription)) {
            throw new IllegalValueException(ProjectDescription.MESSAGE_CONSTRAINTS);
        }
        final ProjectDescription modelProjectDescription = new ProjectDescription(projectDescription);

        final Set<Tag> modelTags = new HashSet<>(projectTags);
        final Set<Task> modelTasks = new HashSet<>(projectTasks);
        return new Project(modelProjectName, modelDeadline, modelEmail, modelProjectDescription,
                modelTags, new HashMap<>(), modelTasks);
    }

}
