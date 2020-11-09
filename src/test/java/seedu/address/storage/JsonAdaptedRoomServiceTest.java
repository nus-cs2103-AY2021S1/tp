package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;
import static seedu.address.storage.JsonAdaptedRoomService.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.roomservice.RoomServiceType;
import seedu.address.testutil.TypicalRoomService;

public class JsonAdaptedRoomServiceTest {

    public static final String ROOM_SERVICE_TYPE = "type";
    public static final String BOOKING_ID = "bookingId";

    @Test
    public void toModelType_validRoomServiceDining_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_DINING, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceMassage_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceWifi_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(TypicalRoomService.ROOM_SERVICE_DAN_WIFI);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_WIFI, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceTypeDining_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, RoomServiceType.DINING);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_DINING, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceTypeMassage_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, RoomServiceType.MASSAGE);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceTypeWifi_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, RoomServiceType.WIFI);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_WIFI, roomService.toModelType());
    }

    @Test
    public void toModelType_nullRoomService_throwsIllegalValueException() {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ROOM_SERVICE_TYPE);
        assertThrows(IllegalValueException.class, expectedMessage, roomService::toModelType);
    }

    @Test
    public void toModelType_nullBookingId_throwsIllegalValueException() {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(null, RoomServiceType.DINING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BOOKING_ID);
        assertThrows(IllegalValueException.class, expectedMessage, roomService::toModelType);
    }
}

