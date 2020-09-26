package seedu.resireg.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.AddressBook;
import seedu.resireg.model.ReadOnlyAddressBook;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_ROOM = "Rooms list contains duplicate room(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
        this.rooms.addAll(rooms);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        rooms.addAll(source.getRoomList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addStudent(student);
        }
        for (JsonAdaptedRoom jsonAdaptedRoom : rooms) {
            Room room = jsonAdaptedRoom.toModelType();
            if (addressBook.hasRoom(room)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROOM);
            }
            addressBook.addRoom(room);
        }
        return addressBook;
    }

}
