package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.grade.Grade;
import seedu.address.model.module.grade.GradeTracker;

/**
 * Jackson-friendly version of {@link GradeTracker}.
 */

public class JsonAdaptedGradeTracker {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "GradeTracker's %s field is missing!";

    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();
    private final double grade;

    /**
     * Constructs a {@code JsonAdaptedGradeTracker} with the given Grade Tracker details.
     */
    @JsonCreator
    public JsonAdaptedGradeTracker(@JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
                             @JsonProperty("grade") double grade) {
        this.grade = grade;
        if (assignments != null) {
            this.assignments.addAll(assignments);
        }
    }

    /**
     * Converts a given {@code GradeTracker} into this class for Jackson use.
     */
    public JsonAdaptedGradeTracker(GradeTracker source) {
        assignments.addAll(source.getAssignments().stream()
                .map(JsonAdaptedAssignment::new)
                .collect(Collectors.toList()));
        this.grade = source.getGrade().gradeResult;
    }

    /**
     * Converts this Jackson-friendly adapted Grade Tracker object into the model's {@code GradeTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted GradeTracker.
     */
    public GradeTracker toModelType() throws IllegalValueException {
        GradeTracker modelGradeTracker = new GradeTracker();
        for (JsonAdaptedAssignment assignment : assignments) {
            if (!modelGradeTracker.isDuplicateAssignment(assignment.toModelType())) {
                modelGradeTracker.addAssignment(assignment.toModelType());
            } else {
                throw new IllegalValueException(GradeTracker.MESSAGE_DUPLICATE_ASSIGNMENT);
            }
        }
        if (Double.isNaN(grade)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(GradeTracker.MESSAGE_INVALID_GRADE);
        }
        final Grade modelGrade = new Grade(grade);
        modelGradeTracker.setGrade(modelGrade);
        return modelGradeTracker;
    }
}
