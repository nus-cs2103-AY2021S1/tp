package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    public static final int VALID_MIN_FLOOR_SETTINGS = 1;
    public static final int VALID_MAX_FLOOR_SETTINGS = 9;
    public static final int VALID_MIN_ROOM_SETTINGS = 1;
    public static final int VALID_MAX_ROOM_SETTINGS = 20;

    public static final int INVALID_MIN_FLOOR_SETTINGS = -1;
    public static final int INVALID_MAX_FLOOR_SETTINGS = 1000;
    public static final int INVALID_MIN_ROOM_SETTINGS = 1;
    public static final int INVALID_MAX_ROOM_SETTINGS = 2000;


    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void setValidFloorSettings() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMinFloorSettings(VALID_MIN_FLOOR_SETTINGS);
        userPrefs.setMaxFloorSettings(VALID_MAX_FLOOR_SETTINGS);

        // valid
        assertEquals(VALID_MIN_FLOOR_SETTINGS, userPrefs.getMinFloorSettings());
        assertEquals(VALID_MAX_FLOOR_SETTINGS, userPrefs.getMaxFloorSettings());
    }

    @Test
    public void setInvalidFloorSettings() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMinFloorSettings(INVALID_MIN_FLOOR_SETTINGS);
        userPrefs.setMaxFloorSettings(INVALID_MAX_FLOOR_SETTINGS);

        // resets to default values
        assertEquals(userPrefs.DEFAULT_MIN_FLOOR_SETTINGS, userPrefs.getMinFloorSettings());
        assertEquals(userPrefs.DEFAULT_MAX_FLOOR_SETTINGS, userPrefs.getMaxFloorSettings());
    }

    @Test
    public void setValidRoomSettings() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMinRoomSettings(VALID_MIN_ROOM_SETTINGS);
        userPrefs.setMaxRoomSettings(VALID_MAX_ROOM_SETTINGS);

        // valid
        assertEquals(VALID_MIN_ROOM_SETTINGS, userPrefs.getMinRoomSettings());
        assertEquals(VALID_MAX_ROOM_SETTINGS, userPrefs.getMaxRoomSettings());
    }

    @Test
    public void setInvalidRoomSettings() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMinRoomSettings(INVALID_MIN_ROOM_SETTINGS);
        userPrefs.setMaxRoomSettings(INVALID_MAX_ROOM_SETTINGS);

        // resets to default values
        assertEquals(userPrefs.DEFAULT_MIN_ROOM_SETTINGS, userPrefs.getMinRoomSettings());
        assertEquals(userPrefs.DEFAULT_MAX_ROOM_SETTINGS, userPrefs.getMaxRoomSettings());
    }

}
