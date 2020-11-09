package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RoomBook;
import seedu.address.model.room.Double;
import seedu.address.model.room.Room;
import seedu.address.model.room.Single;
import seedu.address.model.room.Suite;

/**
 * Initiates a Hotel that has 5 Singles, 5 Doubles and 5 Suites.
 */
public class TypicalRooms {

    public static final Room DEFAULT_ROOM = new Single(2103);
    public static final int DEFAULT_ROOMID = 2103;

    public static final Room SINGLE_1 = new Single(2104);
    public static final Room SINGLE_2 = new Single(2105);
    public static final Room SINGLE_3 = new Single(2106);
    public static final Room SINGLE_4 = new Single(2107);
    public static final Room SINGLE_5 = new Single(2108);
    public static final Room SINGLE_MIDDLE = new Single(2110);
    public static final Room SINGLE_END = new Single(2112);

    public static final Room DOUBLE_1 = new Double(2113);
    public static final Room DOUBLE_2 = new Double(2114);
    public static final Room DOUBLE_3 = new Double(2115);
    public static final Room DOUBLE_4 = new Double(2116);
    public static final Room DOUBLE_5 = new Double(2117);
    public static final Room DOUBLE_MIDDLE = new Double(2120);
    public static final Room DOUBLE_END = new Double(2122);

    public static final Room SUITE_1 = new Suite(2123);
    public static final Room SUITE_2 = new Suite(2124);
    public static final Room SUITE_3 = new Suite(2125);
    public static final Room SUITE_4 = new Suite(2126);
    public static final Room SUITE_5 = new Suite(2127);
    public static final Room SUITE_MIDDLE = new Suite(2129);
    public static final Room SUITE_END = new Suite(2132);


    /**
     * Returns an {@code PersonBook} with all the typical persons.
     */
    public static RoomBook getTypicalRoomBook() {
        RoomBook ab = new RoomBook();
        for (Room r : getTypicalRooms()) {
            ab.addRoom(r);
        }
        return ab;
    }

    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(DEFAULT_ROOM, SINGLE_1, SINGLE_2, SINGLE_3, SINGLE_4,
                SINGLE_5, SINGLE_MIDDLE, SINGLE_END,
                DOUBLE_1, DOUBLE_2, DOUBLE_3, DOUBLE_4, DOUBLE_5, DOUBLE_MIDDLE, DOUBLE_END,
                SUITE_1, SUITE_2, SUITE_3, SUITE_4, SUITE_5, SUITE_MIDDLE, SUITE_END));
    }
}
