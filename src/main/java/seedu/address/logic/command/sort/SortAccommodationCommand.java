package seedu.address.logic.command.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.ACCOMMODATION_INDEX;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accommodation.Accommodation;



public class SortAccommodationCommand extends SortCommand {
    public static final String COMMAND_WORD = "sort accommodation";
    // TODO: Add example
    public static final String MESSAGE_USAGE =
            "sort accommodation: Sorts the list of accommodation in a travel plan by the keyword input by the user.\n"
                    + "Parameters: KEYWORD (cost/date)\n";

    public static final String MESSAGE_SORT_ACCOMMODATION_SUCCESS = "Sorted list of accommodation by: %1$s";

    public static final String MESSAGE_INVALID_KEYWORD = "INVALID KEYWORD! "
            + "Accommodation list can only sort by name/cost/date.";

    private final String keyword;

    /**
     * Constructor for SortAccommodationCommand.
     * @param keyword identifier to sort accommodation list.
     */
    public SortAccommodationCommand(String keyword) {
        super(keyword);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        if (!isTravelPlan) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAVEL_PLAN_OBJECT_AT_WISHLIST);
        }

        switch (keyword) {

        case SortCommand.KEYWORD_COST:
            Comparator<Accommodation> costComparator = Comparator.comparingInt(c -> -c.getCostAsInt());
            model.sortAccommodationList(costComparator);
            return new CommandResult(String.format(MESSAGE_SORT_ACCOMMODATION_SUCCESS, SortCommand.KEYWORD_COST),
                    ACCOMMODATION_INDEX);

        case SortCommand.KEYWORD_DATE:
            Comparator<Accommodation> dateComparator = Comparator.comparing(d -> d.getStartDate().getValue());
            model.sortAccommodationList(dateComparator);
            return new CommandResult(String.format(MESSAGE_SORT_ACCOMMODATION_SUCCESS, SortCommand.KEYWORD_DATE),
                    ACCOMMODATION_INDEX);

        case SortCommand.KEYWORD_NAME:
            Comparator<Accommodation> nameComparator = Comparator.comparing(d -> d.getName().toString());
            model.sortAccommodationList(nameComparator);
            return new CommandResult(String.format(MESSAGE_SORT_ACCOMMODATION_SUCCESS, SortCommand.KEYWORD_NAME),
                    ACCOMMODATION_INDEX);

        default:
            throw new CommandException(MESSAGE_INVALID_KEYWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortAccommodationCommand // instanceof handles nulls
                && keyword.equals(((SortAccommodationCommand) other).keyword)); // state check
    }
}
