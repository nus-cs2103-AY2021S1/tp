package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

/**
 * Edits the range of room number.
 */
public class EditRoomCommand extends Command {

    public static final String COMMAND_WORD = "edit-room-range";

    public static final String COMMAND_NOTES = "Notes: Please restart the application for the room "
        + "settings changes to take effect. This command will erase the existing data "
        + "if the resident's room number is no longer valid after updating the room settings.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the range of room number "
        + "by specifying the minimum and maximum room number.\n"
        + COMMAND_NOTES + "\n"
        + "Parameters: MIN_ROOM_NUMBER MAX_ROOM_NUMBER (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 20";

    public static final String MESSAGE_EDIT_ROOM_SUCCESS = "Edited Room Range: %s to %s\n" + COMMAND_NOTES;
    public static final String MESSAGE_ALLOWED_ROOM_RANGE = "Allowed Room Range: %s to %s";
    public static final String MESSAGE_INVALID_ROOM_RANGE = "The minimum room number cannot be larger than "
        + "the maximum room number";
    public static final String MESSAGE_INVALID_MIN_ROOM = "The provided minimum room number is invalid."
        + "\n" + MESSAGE_ALLOWED_ROOM_RANGE;
    public static final String MESSAGE_INVALID_MAX_ROOM = "The provided maximum room number is invalid."
        + "\n" + MESSAGE_ALLOWED_ROOM_RANGE;

    private final int minRoomSettings;
    private final int maxRoomSettings;

    /**
     * @param minRoomSettings minimum room number
     * @param maxRoomSettings maximum room number
     */
    public EditRoomCommand(int minRoomSettings, int maxRoomSettings) {
        requireAllNonNull(minRoomSettings, maxRoomSettings);
        this.minRoomSettings = minRoomSettings;
        this.maxRoomSettings = maxRoomSettings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // do validation checks for minRoomSettings and maxRoomSettings
        if (minRoomSettings < UserPrefs.MIN_ALLOWED_ROOMS || minRoomSettings > UserPrefs.MAX_ALLOWED_ROOMS) {
            throw new CommandException(String.format(MESSAGE_INVALID_MIN_ROOM,
                UserPrefs.MIN_ALLOWED_ROOMS, UserPrefs.MAX_ALLOWED_ROOMS));
        }
        if (maxRoomSettings < UserPrefs.MIN_ALLOWED_ROOMS || maxRoomSettings > UserPrefs.MAX_ALLOWED_ROOMS) {
            throw new CommandException(String.format(MESSAGE_INVALID_MAX_ROOM,
                UserPrefs.MIN_ALLOWED_ROOMS, UserPrefs.MAX_ALLOWED_ROOMS));
        }
        if (minRoomSettings > maxRoomSettings) {
            throw new CommandException(MESSAGE_INVALID_ROOM_RANGE);
        }

        UserPrefs userPrefs = model.getModifiableUserPrefs();
        userPrefs.setMinRoomSettings(minRoomSettings);
        userPrefs.setMaxRoomSettings(maxRoomSettings);

        return new CommandResult(String.format(MESSAGE_EDIT_ROOM_SUCCESS, minRoomSettings, maxRoomSettings));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
            || (obj instanceof EditRoomCommand)
            && minRoomSettings == (((EditRoomCommand) obj).minRoomSettings)
            && maxRoomSettings == (((EditRoomCommand) obj).maxRoomSettings);
    }
}
