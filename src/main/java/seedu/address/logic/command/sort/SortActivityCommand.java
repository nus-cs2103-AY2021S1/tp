package seedu.address.logic.command.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX;

import java.util.Comparator;

import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;


public class SortActivityCommand extends SortCommand {
    public static final String COMMAND_WORD = "sort activity";

    public static final String MESSAGE_USAGE =
            "sort activity: Sorts the list of activities in a travel plan/wishlist by the keyword input by the user.\n"
                    + "Parameters: KEYWORD (name/cost/importance/datetime)\n";

    public static final String MESSAGE_SORT_ACTIVITY_SUCCESS = "Sorted list of activities by: %1$s";

    public static final String MESSAGE_INVALID_KEYWORD = "INVALID KEYWORD! "
            + "Activity list can only sort by name/cost/importance/datetime.";
    private final String keyword;

    /**
     * Constructor for SortActivityCommand.
     * @param keyword identifier to sort activity list.
     */
    public SortActivityCommand(String keyword) {
        super(keyword);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();

        switch (keyword) {

        case SortCommand.KEYWORD_COST:
            Comparator<Activity> costComparator = Comparator.comparingInt(c -> -c.getCostAsInt());
            if (isTravelPlan) {
                model.sortActivityList(costComparator);
            } else {
                model.sortWishlist(costComparator);
            }
            return new CommandResult(String.format(MESSAGE_SORT_ACTIVITY_SUCCESS, SortCommand.KEYWORD_COST),
                    ACTIVITY_INDEX);

        case SortCommand.KEYWORD_IMPORTANCE:
            Comparator<Activity> importanceComparator = Comparator.comparingInt(a -> -a.getImportanceAsInt());
            if (isTravelPlan) {
                model.sortActivityList(importanceComparator);
            } else {
                model.sortWishlist(importanceComparator);
            }
            return new CommandResult(String.format(MESSAGE_SORT_ACTIVITY_SUCCESS, SortCommand.KEYWORD_IMPORTANCE),
                    ACTIVITY_INDEX);

        case SortCommand.KEYWORD_DATETIME:
            Comparator<Activity> dateComparator = Comparator.comparing(d -> d.getActivityDateTime().getValue());
            if (isTravelPlan) {
                model.sortActivityList(dateComparator);
            } else {
                model.sortWishlist(dateComparator);
            }
            return new CommandResult(String.format(MESSAGE_SORT_ACTIVITY_SUCCESS, SortCommand.KEYWORD_DATETIME),
                    ACTIVITY_INDEX);

        case SortCommand.KEYWORD_NAME:
            Comparator<Activity> nameComparator = Comparator.comparing(d -> d.getName().toString());
            if (isTravelPlan) {
                model.sortActivityList(nameComparator);
            } else {
                model.sortWishlist(nameComparator);
            }
            return new CommandResult(String.format(MESSAGE_SORT_ACTIVITY_SUCCESS, SortCommand.KEYWORD_NAME),
                    ACTIVITY_INDEX);

        default:
            throw new CommandException(MESSAGE_INVALID_KEYWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortActivityCommand // instanceof handles nulls
                && keyword.equals(((SortActivityCommand) other).keyword)); // state check
    }
}
