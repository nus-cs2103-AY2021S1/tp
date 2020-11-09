package seedu.address.logic.commands.schedulercommands;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.FindEventCriteria;

/**
 * Represents the FindEventCommand.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "findevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all event whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " assignment";

    private final Logger logger = LogsCenter.getLogger(FindContactCommand.class);

    private final Predicate<Event> predicate;
    private final FindEventCriteria criteria;

    /**
     * Represents the command container for the find event command.
     * @param predicate the searching criteria.
     */
    public FindEventCommand(FindEventCriteria predicate) {
        logger.info("The find event command is being executed");
        this.criteria = predicate;
        this.predicate = predicate.getFindEventPredicate();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredEventList(this.predicate);
        return new CommandResult(String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW,
                model.getFilteredEventList().size()));
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof FindEventCommand) {
            return this.criteria.equals(((FindEventCommand) other).criteria);
        } else {
            return false;
        }
    }
}
