package seedu.resireg.testutil;

import static seedu.resireg.logic.commands.CommandTestUtil.VALID_DATE_DELETED_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_DATE_DELETED_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ITEM_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ITEM_B;
import static seedu.resireg.testutil.BinItemBuilder.DEFAULT_DATE_DELETED;
import static seedu.resireg.testutil.TypicalRooms.ROOM_A;
import static seedu.resireg.testutil.TypicalRooms.getTypicalRooms;
import static seedu.resireg.testutil.TypicalStudents.AMY;
import static seedu.resireg.testutil.TypicalStudents.CARL;
import static seedu.resireg.testutil.TypicalStudents.getTypicalStudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.resireg.model.ResiReg;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * A utility class containing a list of {@code BinItem} objects to be used in tests.
 * Assumes typical students and rooms.
 */
public class TypicalBinItems {
    /** Student */
    public static final BinItem BIN_ITEM_ONE = new BinItemBuilder()
        .withDateDeleted(DEFAULT_DATE_DELETED)
        .withItem(AMY)
        .build();

    /** Student */
    public static final BinItem BIN_ITEM_TWO = new BinItemBuilder()
        .withDateDeleted(DEFAULT_DATE_DELETED)
        .withItem(ROOM_A)
        .build();

    /** Room */
    public static final BinItem BIN_ITEM_THREE = new BinItemBuilder()
        .withDateDeleted(DEFAULT_DATE_DELETED)
        .withItem(CARL)
        .build();

    // Manually added
    /** Student */
    public static final BinItem BIN_ITEM_A = new BinItemBuilder()
        .withDateDeleted(VALID_DATE_DELETED_A)
        .withItem(VALID_ITEM_A)
        .build();

    /** Room */
    public static final BinItem BIN_ITEM_B = new BinItemBuilder()
        .withDateDeleted(VALID_DATE_DELETED_B)
        .withItem(VALID_ITEM_B)
        .build();

    private TypicalBinItems() {
    } // prevents instantiation

    /**
     * Returns an {@code ResiReg} with all the typical students, rooms and bin items.
     */
    public static ResiReg getTypicalResiReg() {
        ResiReg rr = new ResiReg();
        for (Student student : getTypicalStudents()) {
            rr.addStudent(student);
        }
        for (Room room : getTypicalRooms()) {
            rr.addRoom(room);
        }
        for (BinItem item: getTypicalBinItems()) {
            rr.addBinItem(item);
        }
        return rr;
    }

    public static List<BinItem> getTypicalBinItems() {
        return new ArrayList<>(Arrays.asList(BIN_ITEM_ONE, BIN_ITEM_TWO, BIN_ITEM_THREE));
    }
}
