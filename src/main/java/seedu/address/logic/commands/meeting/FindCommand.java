package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.meeting.TitleDescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all meeting in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose titles and/or "
            + "descriptions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " discuss user guide";

    private final TitleDescriptionContainsKeywordsPredicate predicate;

    public FindCommand(TitleDescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(ModelMeeting modelMeeting) {
        requireNonNull(modelMeeting);
        modelMeeting.updateFilteredMeetingList(predicate);
        return new CommandResult(
                String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, modelMeeting.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
