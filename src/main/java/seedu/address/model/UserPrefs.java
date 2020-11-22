package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Block;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    public static final int MAX_ALLOWED_ROOMS = 99;
    public static final int MIN_ALLOWED_ROOMS = 1;
    public static final int MAX_ALLOWED_FLOORS = 9;
    public static final int MIN_ALLOWED_FLOORS = 1;
    public static final char MIN_ALLOWED_BLOCK_LETTER = 'A';
    public static final char MAX_ALLOWED_BLOCK_LETTER = 'Z';

    public static final int DEFAULT_MIN_ROOM_SETTINGS = 1;
    public static final int DEFAULT_MAX_ROOM_SETTINGS = 20;
    public static final int DEFAULT_MIN_FLOOR_SETTINGS = 1;
    public static final int DEFAULT_MAX_FLOOR_SETTINGS = 4;
    public static final String[] DEFAULT_BLOCK_SETTINGS = {"A", "B", "C", "D"};

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private String[] blockSettings = DEFAULT_BLOCK_SETTINGS;
    private int minRoomSettings = DEFAULT_MIN_ROOM_SETTINGS;
    private int maxRoomSettings = DEFAULT_MAX_ROOM_SETTINGS;
    private int minFloorSettings = DEFAULT_MIN_FLOOR_SETTINGS;
    private int maxFloorSettings = DEFAULT_MAX_FLOOR_SETTINGS;

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setBlockSettings(newUserPrefs.getBlockSettings());
        setMinRoomSettings(newUserPrefs.getMinRoomSettings());
        setMaxRoomSettings(newUserPrefs.getMaxRoomSettings());
        setMinFloorSettings(newUserPrefs.getMinFloorSettings());
        setMaxFloorSettings(newUserPrefs.getMaxFloorSettings());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public String[] getBlockSettings() {
        return blockSettings;
    }

    public void setBlockSettings(String[] blockSettings) {
        requireNonNull(blockSettings);
        if (blockSettings.length < 2) {
            this.blockSettings = DEFAULT_BLOCK_SETTINGS;
            return;
        }
        for (int i = 0; i < blockSettings.length; i++) {
            if (!blockSettings[i].matches(Block.VALIDATION_REGEX)) {
                this.blockSettings = DEFAULT_BLOCK_SETTINGS;
                return;
            }
            blockSettings[i] = blockSettings[i].toUpperCase();
        }
        this.blockSettings = blockSettings;
    }

    public int getMinRoomSettings() {
        return minRoomSettings;
    }

    public void setMinRoomSettings(int minRoomSettings) {
        requireNonNull(minRoomSettings);
        if (minRoomSettings <= MAX_ALLOWED_ROOMS && minRoomSettings >= MIN_ALLOWED_ROOMS) {
            this.minRoomSettings = minRoomSettings;
        } else {
            this.minRoomSettings = DEFAULT_MIN_ROOM_SETTINGS;
            this.maxRoomSettings = DEFAULT_MAX_ROOM_SETTINGS;
        }
        assert minRoomSettings >= MIN_ALLOWED_ROOMS;
        assert maxRoomSettings <= MAX_ALLOWED_ROOMS;
    }

    public int getMaxRoomSettings() {
        return maxRoomSettings;
    }

    public void setMaxRoomSettings(int maxRoomSettings) {
        requireNonNull(maxRoomSettings);
        if (maxRoomSettings <= MAX_ALLOWED_ROOMS && maxRoomSettings >= MIN_ALLOWED_ROOMS) {
            this.maxRoomSettings = maxRoomSettings;
        } else {
            this.minRoomSettings = DEFAULT_MIN_ROOM_SETTINGS;
            this.maxRoomSettings = DEFAULT_MAX_ROOM_SETTINGS;
        }
    }

    public int getMinFloorSettings() {
        return minFloorSettings;
    }

    public void setMinFloorSettings(int minFloorSettings) {
        requireNonNull(minFloorSettings);
        if (minFloorSettings <= MAX_ALLOWED_FLOORS && minFloorSettings >= MIN_ALLOWED_FLOORS) {
            this.minFloorSettings = minFloorSettings;
        } else {
            this.minFloorSettings = DEFAULT_MIN_FLOOR_SETTINGS;
            this.maxFloorSettings = DEFAULT_MAX_FLOOR_SETTINGS;
        }
    }

    public int getMaxFloorSettings() {
        return maxFloorSettings;
    }

    public void setMaxFloorSettings(int maxFloorSettings) {
        requireNonNull(maxFloorSettings);
        if (maxFloorSettings <= MAX_ALLOWED_FLOORS && maxFloorSettings >= MIN_ALLOWED_FLOORS) {
            this.maxFloorSettings = maxFloorSettings;
        } else {
            this.minFloorSettings = DEFAULT_MIN_FLOOR_SETTINGS;
            this.maxFloorSettings = DEFAULT_MAX_FLOOR_SETTINGS;
        }
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    /**
     * Reset block and room values if they are invalid
     */
    public void validationCheck() {
        if (minFloorSettings > maxFloorSettings) {
            minFloorSettings = DEFAULT_MIN_FLOOR_SETTINGS;
            maxFloorSettings = DEFAULT_MAX_FLOOR_SETTINGS;
        }
        if (minRoomSettings > maxRoomSettings) {
            minRoomSettings = DEFAULT_MIN_ROOM_SETTINGS;
            maxRoomSettings = DEFAULT_MAX_ROOM_SETTINGS;
        }
        this.setMaxFloorSettings(maxFloorSettings);
        this.setMinFloorSettings(minFloorSettings);
        this.setMaxRoomSettings(maxRoomSettings);
        this.setMinRoomSettings(minRoomSettings);
        this.setBlockSettings(blockSettings);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nBlock Settings : " + blockSettings);
        sb.append("\nMin room Settings : " + minRoomSettings);
        sb.append("\nMax room Settings : " + maxRoomSettings);
        sb.append("\nMin floor Settings : " + minFloorSettings);
        sb.append("\nMax floor Settings : " + maxFloorSettings);
        return sb.toString();
    }

}
