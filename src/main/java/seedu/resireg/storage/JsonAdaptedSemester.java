package seedu.resireg.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.semester.SemesterNumber;

/**
 * Jackson-friendly version of {@link Semester}.
 */
public class JsonAdaptedSemester {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Semester's %s field is missing!";

    private final int academicYear;
    private final int semesterNumber;
    private final List<JsonAdaptedAllocation> allocations = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSemester} with the given student details.
     */
    @JsonCreator

    public JsonAdaptedSemester(@JsonProperty("academicYear") int academicYear,
                               @JsonProperty("semesterNumber") int semesterNumber,
                               @JsonProperty("allocations") List<JsonAdaptedAllocation> allocations) {
        this.academicYear = academicYear;
        this.semesterNumber = semesterNumber;
        if (allocations != null) {
            this.allocations.addAll(allocations);
        }
    }

    /**
     * Converts a given {@code Semester} into this class for Jackson use.
     */
    public JsonAdaptedSemester(Semester source) {
        academicYear = source.getAcademicYear().value;
        semesterNumber = source.getSemesterNumber().value;
        allocations.addAll(source.getAllocations().stream()
                .map(JsonAdaptedAllocation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Semester} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted semester.
     */
    public Semester toModelType() throws IllegalValueException {
        int modelAcademicYear;
        SemesterNumber modelSemesterNumber;
        List<Allocation> modelAllocations;
        Map<RoomType, Integer> modelRoomFees;
        return null;
//        return new Semester(modelAcademicYear, modelSemesterNumber, modelAllocations, modelRoomFees);
    }
}
