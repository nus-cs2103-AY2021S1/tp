package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonPointer;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.*;
import seedu.address.model.project.*;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Participation}.
 */
class JsonParticipation {

    private final JsonAdaptedPerson person;
    private final String project;
    private final Role role;
    private List<JsonAdaptedTask> tasks = new ArrayList<>();
//    private Set<Meeting> meetings;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonParticipation(@JsonProperty("person") JsonAdaptedPerson person,
                             @JsonProperty("project") String project,
                             @JsonProperty("role")Role role,
                             @JsonProperty("tasks")List<JsonAdaptedTask> tasks
//                             @JsonProperty("meetings")Set<Meeting> meetings
    ) {
        this.person = person;
        this.project = project;
        this.role = role;
        this.tasks = tasks;
//        this.meetings = meetings;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonParticipation(Participation source) {
        person = new JsonAdaptedPerson(source.getPerson());
        project = source.getProject().getProjectName().toString();
        role = source.getRole();
        tasks.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
//        meetings = source.getMeetings();
    }

    /**
     * Converts this Jackson-friendly adapted Participation object into the model's {@code Participation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participation.
     */
    public Participation toModelType() throws IllegalValueException {

        Person p = person.toModelType();

        Participation part = new Participation(p.getGitUserNameString(), project);

        for (JsonAdaptedTask jsonTask : tasks) {
            Task task = jsonTask.toModelType();
            part.addTask(task);
        }

        return part;
    }

}
