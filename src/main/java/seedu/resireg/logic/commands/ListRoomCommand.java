package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import seedu.resireg.model.Model;

/**
 * Lists all rooms in the address book to the user.
 */
public class ListRoomCommand extends Command {
    public static final String COMMAND_WORD = "rooms";

    public static final String MESSAGE_SUCCESS = "Listed all rooms";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
