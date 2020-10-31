package seedu.address.logic.commands.schedulercommands;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

import java.util.function.Predicate;
import java.util.logging.Logger;

public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "findevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all eventd whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " assignment";

    private final Logger logger = LogsCenter.getLogger(FindContactCommand.class);

    private final Predicate<Event> predicate;

    public FindEventCommand(Predicate<Event> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
