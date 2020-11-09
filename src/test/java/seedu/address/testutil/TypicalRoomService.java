package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RoomServiceBook;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;



public class TypicalRoomService {

    public static final RoomService ROOM_SERVICE_DAN_MASSAGE = new RoomService(
            VALID_BOOKING_ID_DAN, RoomServiceType.MASSAGE);
    public static final RoomService ROOM_SERVICE_DAN_WIFI = new RoomService(
            VALID_BOOKING_ID_DAN, RoomServiceType.WIFI);
    public static final RoomService ROOM_SERVICE_DAN_DINING = new RoomService(
            VALID_BOOKING_ID_DAN, RoomServiceType.DINING);

    private TypicalRoomService() {} // prevents instantiation

    /**
     * Returns an {@code RoomServiceBook} with all the typical room services.
     */
    public static RoomServiceBook getTypicalRoomServiceBook() {
        RoomServiceBook rsb = new RoomServiceBook();
        for (RoomService rs : getTypicalRoomServices()) {
            rsb.addRoomService(rs);
        }
        return rsb;
    }

    public static List<RoomService> getTypicalRoomServices() {
        return new ArrayList<>(Arrays.asList(
                ROOM_SERVICE_DAN_MASSAGE, ROOM_SERVICE_DAN_WIFI, ROOM_SERVICE_DAN_DINING));
    }
}
