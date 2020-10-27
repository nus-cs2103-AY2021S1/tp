package seedu.address.logic.commands.meetingcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.TimeContainsKeywordsPredicate;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;

/**
 * Finds and lists all meetings in meeting book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "find-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose venues contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "\n\nParameters: </v /b /p /t> [MORE_KEYWORDS]...\n"
            + "\nExample: " + COMMAND_WORD + "/v bedok\n"
            + "\nExample: " + COMMAND_WORD + "/t 2300hrs\n"
            + "\nExample: " + COMMAND_WORD + "/b b10\n"
            + "\nExample: " + COMMAND_WORD + "/p p10\n";

    private final VenueContainsKeywordsPredicate venuePredicate;
    private final TimeContainsKeywordsPredicate timePredicate;
    private final BidderIdContainsKeywordsPredicate bidderIdPredicate;
    private final PropertyIdContainsKeywordsPredicate propertyIdPredicate;

    /**
     * Finds and lists all meetings in meeting book whose name contains any of the argument keywords.
     * Keyword matching is case insensitive.
     */
    public FindMeetingCommand(VenueContainsKeywordsPredicate venuePredicate,
                              TimeContainsKeywordsPredicate timePredicate,
                              BidderIdContainsKeywordsPredicate bidderIdPredicate,
                              PropertyIdContainsKeywordsPredicate propertyIdPredicate) {
        this.venuePredicate = venuePredicate;
        this.timePredicate = timePredicate;
        this.bidderIdPredicate = bidderIdPredicate;
        this.propertyIdPredicate = propertyIdPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (venuePredicate != null) {
            model.updateFilteredMeetingList(venuePredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                            model.getFilteredMeetingList().size())).setEntity(EntityType.MEETING);
        } else if (timePredicate != null) {
            model.updateFilteredMeetingList(timePredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                            model.getFilteredMeetingList().size())).setEntity(EntityType.MEETING);
        } else if (bidderIdPredicate != null) {
            model.updateFilteredMeetingList(bidderIdPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                            model.getFilteredMeetingList().size())).setEntity(EntityType.MEETING);
        } else if (propertyIdPredicate != null) {
            model.updateFilteredMeetingList(propertyIdPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                            model.getFilteredMeetingList().size())).setEntity(EntityType.MEETING);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && venuePredicate.equals(((FindMeetingCommand) other).venuePredicate)
                && timePredicate.equals(((FindMeetingCommand) other).timePredicate)
                && bidderIdPredicate.equals(((FindMeetingCommand) other).bidderIdPredicate)
                && propertyIdPredicate.equals(((FindMeetingCommand) other).propertyIdPredicate)); // state check
    }
}
