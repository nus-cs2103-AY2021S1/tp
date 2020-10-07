package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.history.History;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command implements Undoable {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_REVERSE_SUCCESS = "Address book has been restored!";
    public static final String MESSAGE_ADDRESSBOOK_NOT_CLEARED = "Address book was not cleared!";

    private ReadOnlyAddressBook addressBook;

    @Override
    public CommandResult execute(Model model, History history) {
        requireNonNull(model);
        addressBook = new AddressBook(model.getAddressBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getAddressBook().getPersonList().size() != 0 || addressBook == null) {
            throw new CommandException(MESSAGE_ADDRESSBOOK_NOT_CLEARED);
        }

        model.setAddressBook(addressBook);
        return new CommandResult(MESSAGE_REVERSE_SUCCESS);
    }

    @Override
    public CommandResult redo(Model model, History history) {
        return execute(model, history);
    }
}
