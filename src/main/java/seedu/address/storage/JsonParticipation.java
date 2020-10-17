package seedu.address.storage;

import java.util.ArrayList;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonPointer;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.PersonName;
import seedu.address.model.project.*;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Participation}.
 */
class JsonParticipation {

    private final String person;
    private final String project;
    private final Role role;
    private final Set<Task> tasks;
//    private Set<Meeting> meetings;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonParticipation(@JsonProperty("person") String person,
                             @JsonProperty("project") String project,
                             @JsonProperty("role")Role role,
                             @JsonProperty("tasks hashcode")Set<Task> tasks
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
        person = source.getPerson().getPersonName().toString();
        project = source.getProject().getProjectName().toString();
        role = source.getRole();
        tasks = source.getTasks();
//        meetings = source.getMeetings();
    }

    /**
     * Converts this Jackson-friendly adapted Participation object into the model's {@code Participation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participation.
     */
    public Participation toModelType() throws IllegalValueException {
        return new Participation(person,project);
    }

}
