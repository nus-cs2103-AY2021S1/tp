package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ProductiveNus;
import seedu.address.model.ReadOnlyProductiveNus;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.lesson.Lesson;

/**
 * An Immutable ProductiveNus that is serializable to JSON format.
 */
@JsonRootName(value = "productivenus")
class JsonSerializableProductiveNus {

    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "Assignments list contains duplicate assignment(s).";

    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableProductiveNus} with the given assignments.
     */
    @JsonCreator
    public JsonSerializableProductiveNus(
            @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
            @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.assignments.addAll(assignments);
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyProductiveNus} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableProductiveNus}.
     */
    public JsonSerializableProductiveNus(ReadOnlyProductiveNus source) {
        assignments.addAll(source.getAssignmentList().stream().map(JsonAdaptedAssignment::new)
                                                     .collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this ProductiveNus into the model's {@code ProductiveNus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ProductiveNus toModelType() throws IllegalValueException {
        ProductiveNus productiveNus = new ProductiveNus();
        for (JsonAdaptedAssignment jsonAdaptedAssignment : assignments) {
            Assignment assignment = jsonAdaptedAssignment.toModelType();
            if (productiveNus.hasAssignment(assignment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ASSIGNMENT);
            }
            productiveNus.addAssignment(assignment);
        }
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            productiveNus.addLesson(lesson);
        }
        return productiveNus;
    }
}
