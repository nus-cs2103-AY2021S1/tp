package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ACTIVE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARCHIVE;

import java.util.List;

import seedu.address.model.Model;

/**
 * Lists all persons in the client list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_ACTIVE = "Listed all active persons";
    public static final String MESSAGE_SUCCESS_ARCHIVE = "Listed all persons in archive";

    private final boolean isArchiveMode;

    /**
     * Constructs list command, with default set to viewing active list.
     */
    public ListCommand() {
        this.isArchiveMode = false;
    }

    /**
     * @param isArchiveMode indicates if the user wants to view the archive.
     */
    public ListCommand(boolean isArchiveMode) {
        this.isArchiveMode = isArchiveMode;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setIsArchiveMode(isArchiveMode);

        if (isArchiveMode) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);
            return new CommandResult(MESSAGE_SUCCESS_ARCHIVE);
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ACTIVE);
            return new CommandResult(MESSAGE_SUCCESS_ACTIVE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && (isArchiveMode == (((ListCommand) other).isArchiveMode)));
    }
}
