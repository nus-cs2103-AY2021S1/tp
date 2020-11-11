package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_READING_PROGRESS;

import java.util.Comparator;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.parser.Prefix;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;

/**
 * Sorts books in bookmark based on the input prefix.
 * Currently supports sorting in lexicographical order for Name and Genre properties
 * and ascending order of pages read.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all books in bookmark based on the input "
            + "prefix and displays them as a list with index numbers.\n"
            + "Parameters: ONE Prefix Specifier\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENRE + "GENRE] "
            + "[" + PREFIX_BOOKMARK + "PAGES READ (BOOKMARK)]... "
            + "[" + PREFIX_READING_PROGRESS + "READING PROGRESS]... "
            + "Example: " + COMMAND_WORD + " g/ ";

    private final Comparator<Book> comparator;
    private final Prefix inputPrefix;

    /**
     * @param comparator The comparator to sort the book list by
     * @param inputPrefix The new sorting preference prefix
     */
    public SortCommand(Comparator<Book> comparator, Prefix inputPrefix) {
        requireNonNull(comparator);
        requireNonNull(inputPrefix);
        this.comparator = comparator;
        this.inputPrefix = inputPrefix;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSortingPreference(inputPrefix.toString());
        model.sortFilteredBookList(comparator);
        model.save();
        storeViewType(model.getCurrentState(), ViewType.DEFAULT);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_SORTED + comparator.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)) // state check
                && inputPrefix.equals(((SortCommand) other).inputPrefix); // state check
    }
}
