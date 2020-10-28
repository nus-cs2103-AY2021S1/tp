package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Adds a student to the address book.
 */
public class SetBinExpiryCommand extends Command {

    public static final String COMMAND_WORD = "set-bin-expiry";

    public static final String MESSAGE_SUCCESS = "Bin items will now be stored for %1$s days";

    public static final Help HELP = new Help(COMMAND_WORD,
        "Sets the number of days a bin item is stored.\n",
        "Parameters: "
             + "NO_OF_DAYS (must be a positive integer)");

    private final int daysStoredInBin;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public SetBinExpiryCommand(int daysStoredInBin) {
        requireNonNull(daysStoredInBin);
        this.daysStoredInBin = daysStoredInBin;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        model.setDaysStoredInBin(daysStoredInBin);
        return new CommandResult(String.format(MESSAGE_SUCCESS, daysStoredInBin));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SetBinExpiryCommand // instanceof handles nulls
            && daysStoredInBin == ((SetBinExpiryCommand) other).daysStoredInBin);
    }
}
