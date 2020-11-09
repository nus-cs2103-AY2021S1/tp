package seedu.address.logic.command;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ACCOMMODATIONS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_FRIENDS_LISTED_OVERVIEW;

import seedu.address.commons.core.Messages;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.commons.NameContainsKeywordsPredicate;

/**
 * Finds and lists all travel plan object in Wanderlust whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 * Wishlist can only find activity while travelplan can find all travel plan objects
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final int COMMAND_TOKEN = 3;

    public static final String MESSAGE_USAGE = "Find all activities/accommodations/friends "
            + "in the travel plan/wishlist in the current directory "
            + "whose names contain any of the specified keywords (case-insensitive) "
            + "using the following as an example:\n"
            + "find -activity KEYWORD [MORE_KEYWORDS]";

    public static final String MISSING_KEYWORDS = "Please provide at least one keyword to search for";

    private final NameContainsKeywordsPredicate predicate;
    private final int travelPlanObjectType;

    /**
     * Constructor for FindCommand
     *
     * @param predicate contains a list of strings to filter travel plan object list
     * @param travelPlanObjectType an integer to identify the type of travel plan object to find
     */
    public FindCommand(NameContainsKeywordsPredicate predicate, int travelPlanObjectType) {
        requireNonNull(predicate);

        assert travelPlanObjectType == ParserUtil.ACTIVITY_INDEX
                || travelPlanObjectType == ParserUtil.ACCOMMODATION_INDEX
                || travelPlanObjectType == ParserUtil.FRIEND_INDEX;

        this.predicate = predicate;
        this.travelPlanObjectType = travelPlanObjectType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isDirectoryTypeTravelPlan() && this.travelPlanObjectType != 0) { //wishlist
            throw new CommandException(Messages.MESSAGE_INVALID_TRAVEL_PLAN_OBJECT_AT_WISHLIST);
        }

        String message = "";
        int size = 0;

        switch (this.travelPlanObjectType) {
        case (ParserUtil.ACTIVITY_INDEX):
            model.updateFilteredActivityList(predicate);
            message = MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
            size = model.getFilteredActivityList().size();
            break;
        case (ParserUtil.ACCOMMODATION_INDEX):
            model.updateFilteredAccommodationList(predicate);
            message = MESSAGE_ACCOMMODATIONS_LISTED_OVERVIEW;
            size = model.getFilteredAccommodationList().size();
            break;
        case (ParserUtil.FRIEND_INDEX):
            model.updateFilteredFriendList(predicate);
            message = MESSAGE_FRIENDS_LISTED_OVERVIEW;
            size = model.getFilteredFriendList().size();
            break;
        default:
            assert false;
        }

        return new CommandResult(
                String.format(message, size), this.travelPlanObjectType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && travelPlanObjectType == (((FindCommand) other).travelPlanObjectType)
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
