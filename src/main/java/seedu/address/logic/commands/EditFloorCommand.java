package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

/**
 * Edits the range of floor number.
 */
public class EditFloorCommand extends Command {

    public static final String COMMAND_WORD = "edit-floor-range";

    public static final String COMMAND_NOTES = "Notes: Please restart the application for the floor "
        + "settings changes to take effect. This command will erase the existing data "
        + "if the resident's floor number is no longer valid after updating the floor settings.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the range of floor number "
        + "by specifying the minimum and maximum floor number.\n"
        + COMMAND_NOTES + "\n"
        + "Parameters: MIN_FLOOR_NUMBER MAX_FLOOR_NUMBER (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 4";

    public static final String MESSAGE_EDIT_FLOOR_SUCCESS = "Edited Floor Range: %s to %s\n" + COMMAND_NOTES;
    public static final String MESSAGE_ALLOWED_FLOOR_RANGE = "Allowed Floor Range: %s to %s";
    public static final String MESSAGE_INVALID_FLOOR_RANGE = "The minimum floor number cannot be larger than "
        + "the maximum floor number";
    public static final String MESSAGE_INVALID_MIN_FLOOR = "The provided minimum floor number is invalid."
        + "\n" + MESSAGE_ALLOWED_FLOOR_RANGE;
    public static final String MESSAGE_INVALID_MAX_FLOOR = "The provided maximum floor number is invalid."
        + "\n" + MESSAGE_ALLOWED_FLOOR_RANGE;

    private final int minFloorSettings;
    private final int maxFloorSettings;

    /**
     * @param minFloorSettings minimum floor number
     * @param maxFloorSettings maximum floor number
     */
    public EditFloorCommand(int minFloorSettings, int maxFloorSettings) {
        requireAllNonNull(minFloorSettings, maxFloorSettings);
        this.minFloorSettings = minFloorSettings;
        this.maxFloorSettings = maxFloorSettings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // do validation checks for minFloorSettings and maxFloorSettings
        if (minFloorSettings < UserPrefs.MIN_ALLOWED_FLOORS || minFloorSettings > UserPrefs.MAX_ALLOWED_FLOORS) {
            throw new CommandException(String.format(MESSAGE_INVALID_MIN_FLOOR,
                UserPrefs.MIN_ALLOWED_FLOORS, UserPrefs.MAX_ALLOWED_FLOORS));
        }
        if (maxFloorSettings < UserPrefs.MIN_ALLOWED_FLOORS || maxFloorSettings > UserPrefs.MAX_ALLOWED_FLOORS) {
            throw new CommandException(String.format(MESSAGE_INVALID_MAX_FLOOR,
                UserPrefs.MIN_ALLOWED_FLOORS, UserPrefs.MAX_ALLOWED_FLOORS));
        }
        if (minFloorSettings > maxFloorSettings) {
            throw new CommandException(MESSAGE_INVALID_FLOOR_RANGE);
        }

        UserPrefs userPrefs = model.getModifiableUserPrefs();
        userPrefs.setMinFloorSettings(minFloorSettings);
        userPrefs.setMaxFloorSettings(maxFloorSettings);

        return new CommandResult(String.format(MESSAGE_EDIT_FLOOR_SUCCESS, minFloorSettings, maxFloorSettings));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
            || (obj instanceof EditFloorCommand)
            && minFloorSettings == (((EditFloorCommand) obj).minFloorSettings)
            && maxFloorSettings == (((EditFloorCommand) obj).maxFloorSettings);
    }
}
