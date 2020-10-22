package seedu.taskmaster.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.Taskmaster;
import seedu.taskmaster.model.student.Student;

/**
 * An Immutable Taskmaster that is serializable to JSON format.
 */
@JsonRootName(value = "taskmaster")
class JsonSerializableTaskmaster {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskmaster} with the given students.
     */
    @JsonCreator
    public JsonSerializableTaskmaster(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        assert students != null;
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyTaskmaster} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskmaster}.
     */
    public JsonSerializableTaskmaster(ReadOnlyTaskmaster source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student list into the model's {@code Taskmaster} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Taskmaster toModelType() throws IllegalValueException {
        Taskmaster taskmaster = new Taskmaster();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (taskmaster.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            taskmaster.addStudent(student);
        }
        return taskmaster;
    }

}
