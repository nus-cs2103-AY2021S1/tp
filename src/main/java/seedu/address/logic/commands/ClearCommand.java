package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModuleBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "contact clear";
    public static final String MESSAGE_SUCCESS = "All contacts have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        //@@author jerrylchong
        requireNonNull(model);
        model.setSelectedMeeting(null);
        model.setAddressBook(new AddressBook());
        model.setMeetingBook(new MeetingBook());
        model.setModuleBook(new ModuleBook());
        //@@author
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, true);
    }
}
