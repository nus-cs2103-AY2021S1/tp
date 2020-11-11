package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Goal;


public class GoalDelCommand extends Command {
    public static final String COMMAND_WORD = "goaldel";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete goal previously set for the book specified by its index in book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_NO_GOAL = "%s currently has no goal to delete";
    public static final String MESSAGE_SUCCESS = "Goal successfully removed for %s";

    public final Index index;

    public GoalDelCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookWithGoal = lastShownList.get(index.getZeroBased());
        if (!bookWithGoal.hasGoal()) {
            throw new CommandException(String.format(MESSAGE_NO_GOAL, bookWithGoal.getName()));
        }
        Book bookWithoutGoal = Book.setGoal(bookWithGoal, Goal.defaultGoal());

        model.setBook(bookWithGoal, bookWithoutGoal);
        model.save();
        storeViewType(model.getCurrentState(), ViewType.MOST_RECENTLY_USED);
        return new CommandResult(String.format(MESSAGE_SUCCESS, bookWithGoal.getName()), false, false,
                ViewType.MOST_RECENTLY_USED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof GoalDelCommand) {
            return index.equals(((GoalDelCommand) other).index);
        } else {
            return false;
        }
    }
}
