package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.participation.Participation;
import seedu.address.model.project.Role;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Participation}.
 */
class JsonAdaptedParticipation {

    private final String person;
    private final String project;
    private final Role role;
    private List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedParticipation(@JsonProperty("person") String person,
                                    @JsonProperty("project") String project,
                                    @JsonProperty("role")Role role,
                                    @JsonProperty("tasks")List<JsonAdaptedTask> tasks
    ) {
        this.person = person;
        this.project = project;
        this.role = role;
        this.tasks = tasks;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedParticipation(Participation source) {
        person = source.getPerson().getGitUserNameString();
        project = source.getProject().getProjectName().toString();
        role = source.getRole();
        tasks.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Participation object into the model's {@code Participation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participation.
     */
    public Participation toModelType() throws IllegalValueException {

        Participation part = new Participation(person, project);

        for (JsonAdaptedTask jsonTask : tasks) {
            Task task = jsonTask.toModelType();
            part.addTask(task);
        }

        return part;
    }

}
