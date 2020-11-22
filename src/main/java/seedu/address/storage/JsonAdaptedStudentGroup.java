package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Jackson-friendly version of {@link StudentGroup}.
 */
class JsonAdaptedStudentGroup {

    private final String studentGroupName;

    /**
     * Constructs a {@code JsonAdaptedStudentGroup} with the given {@code studentGroupName}.
     */
    @JsonCreator
    public JsonAdaptedStudentGroup(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }

    /**
     * Converts a given {@code StudentGroup} into this class for Jackson use.
     */
    public JsonAdaptedStudentGroup(StudentGroup source) {
        studentGroupName = source.studentGroupName;
    }

    @JsonValue
    public String getStudentGroupName() {
        return studentGroupName;
    }

    /**
     * Converts this Jackson-friendly adapted student group object into the model's {@code StudentGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student group.
     */
    public StudentGroup toModelType() throws IllegalValueException {
        if (!StudentGroup.isValidStudentGroupName(studentGroupName)) {
            throw new IllegalValueException(StudentGroup.MESSAGE_CONSTRAINTS);
        }
        return new StudentGroup(studentGroupName);
    }

}
