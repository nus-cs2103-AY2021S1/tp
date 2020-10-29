package seedu.resireg.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.bin.Binnable;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * Jackson-friendly version of {@link BinItem}.
 */
class JsonAdaptedBinItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "BinItem's %s field is missing!";
    public static final String INVALID_DATE_DELETED_FORMAT = "The date of deletion in the file is corrupted!";

    private final JsonAdaptedStudent studentItem;
    private final JsonAdaptedRoom roomItem;
    private final String dateDeleted;

    /**
     * Constructs a {@code JsonAdaptedBinItem} with the given BinItem details.
     */
    @JsonCreator
    public JsonAdaptedBinItem(@JsonProperty("dateDeleted") String dateDeleted,
                              @JsonProperty("roomItem") JsonAdaptedRoom roomItem,
                              @JsonProperty("studentItem") JsonAdaptedStudent studentItem) {
        this.studentItem = studentItem;
        this.roomItem = roomItem;
        this.dateDeleted = dateDeleted;
    }

    /**
     * Converts a given {@code BinItem} into this class for Jackson use.
     */
    public JsonAdaptedBinItem(BinItem source) {
        Binnable item = source.getBinnedItem();
        if (item instanceof Student) {
            Student p = (Student) item;
            this.studentItem = new JsonAdaptedStudent(p);
            this.roomItem = null;
        } else if (item instanceof Room) {
            Room room = (Room) item;
            this.studentItem = null;
            this.roomItem = new JsonAdaptedRoom(room);
        } else {
            assert false : "Binnable instance not known.";
            this.studentItem = null;
            this.roomItem = null;
        }
        this.dateDeleted = source.getDateDeleted().toString();
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were
     * any data constraints violated in the adapted policy.
     */
    BinItem toModelType() throws IllegalValueException {
        Binnable modelItem = null;
        if (studentItem != null) {
            modelItem = studentItem.toModelType();
        }
        if (roomItem != null) {
            modelItem = roomItem.toModelType();
        }

        if (studentItem != null && roomItem != null) {
            // TODO extract out message
            throw new IllegalValueException("Binnable instance contains both student and room field!");
        }

        if (studentItem == null && roomItem == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName()
                    + ", " + Room.class.getSimpleName()));
        }

        LocalDate modelDateDeleted;
        if (dateDeleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDate.class.getSimpleName()));
        }
        try {
            modelDateDeleted = LocalDate.parse(dateDeleted);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(INVALID_DATE_DELETED_FORMAT));
        }

        return new BinItem(modelItem, modelDateDeleted);
    }

}
