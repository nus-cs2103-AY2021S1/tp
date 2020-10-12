package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.model.Model;

/**
 * Lists all rooms in the address book to the user.
 */
public class ListRoomCommand extends Command {
    public static final String COMMAND_WORD = "rooms";
    public static final String COMMAND_VACANT_FLAG = "--vacant";

    public static final String MESSAGE_SUCCESS = "Listed all rooms";
    public static final String MESSAGE_VACANT_SUCCESS = "Listed all vacant rooms";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all rooms within the system. If the "
            + COMMAND_VACANT_FLAG
            + " parameter is specified, find only rooms that are vacant, meaning no students are assigned to them.\n"
            + "Parameters: [" + COMMAND_VACANT_FLAG + "]...\n"
            + "Example: " + COMMAND_WORD;

    private final boolean shouldDisplayOnlyVacant;

    public ListRoomCommand(boolean shouldDisplayOnlyVacant) {
        this.shouldDisplayOnlyVacant = shouldDisplayOnlyVacant;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (shouldDisplayOnlyVacant) {
            model.updateFilteredRoomList(room -> !room.hasStudent());
            return new ToggleCommandResult(MESSAGE_VACANT_SUCCESS, TabView.ROOMS);
        } else {
            model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
            return new ToggleCommandResult(MESSAGE_SUCCESS, TabView.ROOMS);
        }
    }
}
