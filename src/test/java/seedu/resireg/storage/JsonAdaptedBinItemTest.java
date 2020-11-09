package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.storage.JsonAdaptedBinItem.INVALID_DATE_DELETED_FORMAT;
import static seedu.resireg.storage.JsonAdaptedBinItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.resireg.storage.JsonAdaptedBinItem.MULTIPLE_BINNABLE_ITEMS_MESSAGE;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_ONE;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_TWO;
import static seedu.resireg.testutil.TypicalRooms.ROOM_A;
import static seedu.resireg.testutil.TypicalStudents.AMY;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

public class JsonAdaptedBinItemTest {
    private static final String INVALID_DATE_DELETED = "fnfnf";
    private static final JsonAdaptedStudent INVALID_ITEM = null;

    private static final String VALID_DATE_DELETED = "2020-10-26";
    private static final JsonAdaptedStudent VALID_ITEM_STUDENT = new JsonAdaptedStudent(AMY);
    private static final JsonAdaptedRoom VALID_ITEM_ROOM = new JsonAdaptedRoom(ROOM_A);

    @Test
    public void toModelType_validBinItemDetails_returnsCommandWordAlias() throws Exception {
        final JsonAdaptedBinItem binItem = new JsonAdaptedBinItem(BIN_ITEM_ONE);
        assertEquals(BIN_ITEM_ONE, binItem.toModelType());

        final JsonAdaptedBinItem binItem2 = new JsonAdaptedBinItem(BIN_ITEM_TWO);
        assertEquals(BIN_ITEM_TWO, binItem2.toModelType());

        // ensure that the multiple tests are actually meaningful and testing different types of bin items
        assertEquals(Student.class, BIN_ITEM_ONE.getBinnedItem().getClass());
        assertEquals(Room.class, BIN_ITEM_TWO.getBinnedItem().getClass());
    }

    @Test
    public void toModelType_invalidDateDeleted_throwsIllegalValueException() {
        final String expectedMessage = INVALID_DATE_DELETED_FORMAT;
        final JsonAdaptedBinItem binItem1 =
            new JsonAdaptedBinItem(INVALID_DATE_DELETED, null, VALID_ITEM_STUDENT);
        assertThrows(IllegalValueException.class, expectedMessage, binItem1::toModelType);
        final JsonAdaptedBinItem binItem2 =
            new JsonAdaptedBinItem(INVALID_DATE_DELETED, VALID_ITEM_ROOM, null);
        assertThrows(IllegalValueException.class, expectedMessage, binItem2::toModelType);
    }

    @Test
    public void toModelType_nullDateDeleted_throwsIllegalValueException() {
        final String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName());
        final JsonAdaptedBinItem binItem1 =
            new JsonAdaptedBinItem(null, null, VALID_ITEM_STUDENT);
        assertThrows(IllegalValueException.class, expectedMessage, binItem1::toModelType);
        final JsonAdaptedBinItem binItem2 =
            new JsonAdaptedBinItem(null, VALID_ITEM_ROOM, null);
        assertThrows(IllegalValueException.class, expectedMessage, binItem2::toModelType);
    }

    @Test
    public void toModelType_allNullItems_throwsIllegalValueException() {
        final JsonAdaptedBinItem binItem =
            new JsonAdaptedBinItem(VALID_DATE_DELETED, null, null);
        final String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName() + ", "
                + Room.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, binItem::toModelType);
    }

    @Test
    public void toModelType_multipleItems_throwsIllegalValueException() {
        final JsonAdaptedBinItem binItem =
            new JsonAdaptedBinItem(VALID_DATE_DELETED, VALID_ITEM_ROOM, VALID_ITEM_STUDENT);
        final String expectedMessage = MULTIPLE_BINNABLE_ITEMS_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, binItem::toModelType);
    }
}
