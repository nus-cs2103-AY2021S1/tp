package seedu.resireg.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.semester.AcademicYear;
import seedu.resireg.model.semester.RoomRate;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.semester.SemesterNumber;

/**
 * Jackson-friendly version of {@link Semester}.
 */
public class JsonAdaptedSemester {

    private final int academicYear;
    private final int semesterNumber;
    private final List<JsonAdaptedAllocation> allocations = new ArrayList<>();
    private final HashMap<RoomType, RoomRate> roomFees = new HashMap<>();

    /**
     * Constructs a {@code JsonAdaptedSemester} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedSemester(@JsonProperty("academicYear") int academicYear,
                               @JsonProperty("semesterNumber") int semesterNumber,
                               @JsonProperty("allocations") List<JsonAdaptedAllocation> allocations,
                               @JsonProperty("roomFees") Map<String, Integer> roomFees) {
        this.academicYear = academicYear;
        this.semesterNumber = semesterNumber;
        if (allocations != null) {
            this.allocations.addAll(allocations);
        }
        if (roomFees != null) {
            for (Map.Entry<String, Integer> entry : roomFees.entrySet()) {
                RoomType roomType = new RoomType(entry.getKey());
                RoomRate roomRate = new RoomRate(entry.getValue());
                this.roomFees.put(roomType, roomRate);
            }
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
        final List<Allocation> modelSemesterAllocations = new ArrayList<>();
        for (JsonAdaptedAllocation allocation : allocations) {
            modelSemesterAllocations.add(allocation.toModelType());
        }

        if (!AcademicYear.isValidAcademicYear(academicYear)) {
            throw new IllegalValueException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        final AcademicYear modelAcademicYear = new AcademicYear(academicYear);

        if (!SemesterNumber.isValidSemesterNumber(semesterNumber)) {
            throw new IllegalValueException(SemesterNumber.MESSAGE_CONSTRAINTS);
        }
        final SemesterNumber modelSemesterNumber = new SemesterNumber(semesterNumber);

        final Map<RoomType, RoomRate> modelRoomFees = new HashMap<>(roomFees);

        return new Semester(modelAcademicYear, modelSemesterNumber, modelSemesterAllocations, modelRoomFees);
    }
}
