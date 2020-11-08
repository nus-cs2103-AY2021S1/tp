package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_LIMIT;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;



/**
 * Sets user preferences for maximum number of cards that can be reviewed per session.
 */
public class SetReviewLimitCommand extends Command {
    public static final String COMMAND_WORD = "setReviewLimit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets user preferences for maximum number of cards that can "
            + "be reviewed per session.\n"
            + "Parameters: NUMBER or STRING\n"
            + "Example: " + COMMAND_WORD + " 20"
            + " or " + COMMAND_WORD + " all";
    public static final String MESSAGE_SUCCESS = "Review card limit successfully updated! "
            + "Review limit is now %d.";
    public static final String MESSAGE_SUCCESS_NO_LIMIT = "Review card limit successfully updated! "
            + "There is now no review limit.";
    public static final String MESSAGE_INVALID_LIMIT = "Review card limit must be an integer greater "
            + "than 0 and smaller than 2147483648.";


    private final long reviewCardLimit;

    /**
     * Constructor to set review card limit
     * @param reviewCardLimit
     */
    public SetReviewLimitCommand(long reviewCardLimit) {
        requireNonNull(reviewCardLimit);
        this.reviewCardLimit = reviewCardLimit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isReviewLimitInvalid = reviewCardLimit < 1 || reviewCardLimit > Integer.MAX_VALUE;
        if (isReviewLimitInvalid) {
            throw new CommandException(MESSAGE_INVALID_LIMIT);
        }

        model.setReviewCardLimit(reviewCardLimit);
        long newReviewLimit = model.getReviewCardLimit();

        if (newReviewLimit == Integer.MAX_VALUE) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_NO_LIMIT));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, newReviewLimit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetReviewLimitCommand // instanceof handles nulls
                && reviewCardLimit == (((SetReviewLimitCommand) other).reviewCardLimit));
    }
}
