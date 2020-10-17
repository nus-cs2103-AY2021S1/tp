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

/**
 * Jackson-friendly version of {@link Participation}.
 */
class JsonParticipation {

    private PersonName person;
    private ProjectName project;
    private Role role;
    private ArrayList<Integer> tasks;
    private Set<Meeting> meetings;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonParticipation(@JsonProperty("person") PersonName person,
                             @JsonProperty("project") ProjectName project,
                             @JsonProperty("role")Role role,
                             @JsonProperty("tasks hashcode")ArrayList<Integer> tasks,
                             @JsonProperty("meetings")Set<Meeting> meetings
    ) {
        this.person = person;
        this.project = project;
        this.role = role;
        this.tasks = tasks;
        this.meetings = meetings;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonParticipation(Participation source) {
        person = source.getPerson().getPersonName();
        project = source.getProject().getProjectName();
        role = source.getRole();
        tasks = source.getTasksHash();
        meetings = source.getMeetings();
    }

    /**
     * Converts this Jackson-friendly adapted Participation object into the model's {@code Participation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participation.
     */
    public Participation toModelType() throws IllegalValueException {
        JsonPointer pointer = JsonPointer.compile("/projects");
        pointer.
                JsonValue project = Json.createPointer()
//        Participation task = new Participation();
        task.setPublishDate(publishDate);
        return task;
    }

}
