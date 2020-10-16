package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.model.Model;
import seedu.resireg.model.VersionedResiReg;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ResiReg has been cleared!";

    public static final Help HELP = new Help(COMMAND_WORD, "Clears all students and rooms.");

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new VersionedResiReg());
        model.saveStateResiReg();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
