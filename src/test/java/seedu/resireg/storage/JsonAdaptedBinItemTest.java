package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.storage.JsonAdaptedBinItem.INVALID_DATE_DELETED_FORMAT;
import static seedu.resireg.storage.JsonAdaptedBinItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_ONE;
import static seedu.resireg.testutil.TypicalStudents.AMY;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;
// TODO add tests for rooms
public class JsonAdaptedBinItemTest {
    private static final String INVALID_DATE_DELETED = "fnfnf";
    private static final JsonAdaptedStudent INVALID_ITEM = null;

    private static final String VALID_DATE_DELETED = "2020-10-26";
    private static final JsonAdaptedStudent VALID_ITEM = new JsonAdaptedStudent(AMY);

    @Test
    public void toModelType_validBinItemDetails_returnsCommandWordAlias() throws Exception {
        JsonAdaptedBinItem binItem = new JsonAdaptedBinItem(BIN_ITEM_ONE);
        assertEquals(BIN_ITEM_ONE, binItem.toModelType());
    }

    @Test
    public void toModelType_invalidDateDeleted_throwsIllegalValueException() {
        JsonAdaptedBinItem binItem =
            new JsonAdaptedBinItem(INVALID_DATE_DELETED, null, VALID_ITEM);
        String expectedMessage = INVALID_DATE_DELETED_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, binItem::toModelType);
    }

    @Test
    public void toModelType_nullDateDeleted_throwsIllegalValueException() {
        JsonAdaptedBinItem binItem =
            new JsonAdaptedBinItem(null, null, VALID_ITEM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, binItem::toModelType);
    }

    @Test
    public void toModelType_nullItem_throwsIllegalValueException() {
        JsonAdaptedBinItem binItem =
            new JsonAdaptedBinItem(VALID_DATE_DELETED, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName() + ", "
                + Room.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, binItem::toModelType);
    }
}
