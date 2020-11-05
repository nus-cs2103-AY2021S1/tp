package seedu.resireg.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * An Immutable ResiReg that is serializable to JSON format.
 */
@JsonRootName(value = "resireg")
class JsonSerializableResiReg {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_ROOM = "Rooms list contains duplicate room(s).";
    public static final String MESSAGE_DUPLICATE_ALLOCATION = "Allocation list contains duplicate allocation(s).";
    public static final String MESSAGE_DUPLICATE_BIN_ITEM = "Bin items list contains duplicate bin item(s).";


    private final JsonAdaptedSemester semester;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();
    private final List<JsonAdaptedAllocation> allocations = new ArrayList<>();
    private final List<JsonAdaptedBinItem> binItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResiReg} with the given students.
     */
    @JsonCreator
    public JsonSerializableResiReg(@JsonProperty("semester") JsonAdaptedSemester semester,
                                   @JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("rooms") List<JsonAdaptedRoom> rooms,
                                   @JsonProperty("allocations") List<JsonAdaptedAllocation> allocations,
                                   @JsonProperty("binItems") List<JsonAdaptedBinItem> binItems) {
        this.semester = semester;
        this.students.addAll(students);
        this.rooms.addAll(rooms);
        this.allocations.addAll(allocations);
        this.binItems.addAll(binItems);
    }

    /**
     * Converts a given {@code ReadOnlyResiReg} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResiReg}.
     */
    public JsonSerializableResiReg(ReadOnlyResiReg source) {
        semester = new JsonAdaptedSemester(source.getSemester());
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        rooms.addAll(source.getRoomList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
        allocations.addAll(
            source.getAllocationList().stream().map(JsonAdaptedAllocation::new).collect(Collectors.toList()));
        binItems.addAll(source.getBinItemList().stream().map(JsonAdaptedBinItem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this JsonSerializableResiReg instance into the model's {@code ResiReg} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResiReg toModelType() throws IllegalValueException {
        ResiReg resiReg = new ResiReg();
        assert semester != null;
        resiReg.setSemester(semester.toModelType());
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (resiReg.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            resiReg.addStudent(student);
        }
        for (JsonAdaptedRoom jsonAdaptedRoom : rooms) {
            Room room = jsonAdaptedRoom.toModelType();
            if (resiReg.hasRoom(room)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROOM);
            }
            resiReg.addRoom(room);
        }
        for (JsonAdaptedAllocation jsonAdaptedAllocation : allocations) {
            Allocation allocation = jsonAdaptedAllocation.toModelType();
            if (resiReg.hasAllocation(allocation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ALLOCATION);
            }
            resiReg.addAllocation(allocation);
        }
        for (JsonAdaptedBinItem jsonAdaptedBinItem : binItems) {
            BinItem binItem = jsonAdaptedBinItem.toModelType();
            if (resiReg.hasBinItem(binItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BIN_ITEM);
            }
            resiReg.addBinItem(binItem);
        }
        return resiReg;
    }

}
