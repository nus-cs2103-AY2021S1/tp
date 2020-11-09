package seedu.address.logic.command.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.FRIEND_INDEX;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;


public class SortFriendCommand extends SortCommand {
    public static final String COMMAND_WORD = "sort friend";

    public static final String MESSAGE_USAGE =
            "sort friend: Sorts the list of friend in a travel plan by the keyword input by the user.\n"
                    + "Parameters: KEYWORD (name)\n";

    public static final String MESSAGE_SORT_FRIEND_SUCCESS = "Sorted list of friends by: %1$s";

    public static final String MESSAGE_INVALID_KEYWORD = "INVALID KEYWORD! "
            + "Friend list can only sort by name/mobile/passport.";

    private final String keyword;

    /**
     * Constructor for SortFriendCommand.
     * @param keyword identifier to sort friend list.
     */
    public SortFriendCommand(String keyword) {
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

        case SortCommand.KEYWORD_NAME:
            Comparator<Friend> nameComparator = Comparator.comparing(Friend::getNameAsString);
            model.sortFriendList(nameComparator);
            return new CommandResult(String.format(MESSAGE_SORT_FRIEND_SUCCESS, SortCommand.KEYWORD_NAME),
                    FRIEND_INDEX);

        case SortCommand.KEYWORD_MOBILE:
            Comparator<Friend> mobileComparator = Comparator.comparing(f -> f.getMobile().getValue());
            model.sortFriendList(mobileComparator);
            return new CommandResult(String.format(MESSAGE_SORT_FRIEND_SUCCESS, SortCommand.KEYWORD_MOBILE),
                    FRIEND_INDEX);

        case SortCommand.KEYWORD_PASSPORT:
            Comparator<Friend> passportComparator = Comparator.comparing(f -> f.getPassport().getValue());
            model.sortFriendList(passportComparator);
            return new CommandResult(String.format(MESSAGE_SORT_FRIEND_SUCCESS, SortCommand.KEYWORD_PASSPORT),
                    FRIEND_INDEX);

        default:
            throw new CommandException(MESSAGE_INVALID_KEYWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortFriendCommand // instanceof handles nulls
                && keyword.equals(((SortFriendCommand) other).keyword)); // state check
    }
}
