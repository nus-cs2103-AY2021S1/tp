package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_BORROWING_TIMES_HISTORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.ui.Mode;

/**
 * Gets all borrowed times summed up.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    /**
     * Executes history command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult object
     * @throws CommandException if size is not legal
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Book> books = model.getFilteredBookList();
        int history = 0;
        for (Book book: books) {
            history += book.getTimes().getValue();
        }

        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);

        return new CommandResult(String.format(MESSAGE_BORROWING_TIMES_HISTORY,
                history));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof HistoryCommand);
    }
}
