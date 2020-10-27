package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.grade.Assignment;

public class JsonAdaptedAssignment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";

    private final String assignmentName;
    private final double assignmentPercentage;
    private final double assignmentResult;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("assignmentName") String assignmentName,
                             @JsonProperty("assignmentPercentage") double assignmentPercentage,
                                 @JsonProperty("assignmentResult") double assignmentResult) {
        this.assignmentName = assignmentName;
        this.assignmentPercentage = assignmentPercentage;
        this.assignmentResult = assignmentResult;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        assignmentName = source.getAssignmentName();
        assignmentPercentage = source.getPercentageOfGrade();
        assignmentResult = source.getResult();
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (assignmentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "assignment name"));
        }
        if (Double.isNaN(assignmentPercentage)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "assignment percentage"));
        }
        if (Double.isNaN(assignmentResult)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "assignment result"));
        }
        if (!Assignment.isValidAssignmentName(assignmentName)) {
            throw new IllegalValueException(Assignment.MESSAGE_ASSIGNMENT_NAME_CONSTRAINTS);
        }
        if (!Assignment.isValidAssignmentPercentage(assignmentPercentage)) {
            throw new IllegalValueException(Assignment.MESSAGE_ASSIGNMENT_PERCENTAGE_CONSTRAINTS);
        }
        if (!Assignment.isValidAssignmentResult(assignmentResult)) {
            throw new IllegalValueException(Assignment.MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS);
        }
        return new Assignment(assignmentName, assignmentPercentage, assignmentResult);
    }
}
