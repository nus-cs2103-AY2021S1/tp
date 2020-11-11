package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOT_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.function.Predicate;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.algorithm.SuggestionAlgorithm;
import seedu.bookmark.logic.parser.Prefix;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.wordstore.Word;
import seedu.bookmark.model.wordstore.WordComparator;

/**
 * Finds and lists all books in bookmark whose specified property contains any of the argument keywords.
 * Currently supports finding in name, genre and tag properties, as well as finding completed and non completed books.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all books that contain any of the specified "
            + "keywords in the specified field (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: ONE Prefix Specifier\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENRE + "GENRE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_COMPLETED + "COMPLETED BOOKS] "
            + "[" + PREFIX_NOT_COMPLETED + "BOOKS IN PROGRESS]\n"
            + "KEYWORD [MORE_KEYWORDS]... (Not needed for c/ and nc/)\n"
            + "Example: " + COMMAND_WORD + " g/ fantasy horror";

    private final Predicate<Book> predicate;
    private final String[] keywords;
    private final Prefix prefix;

    /**
     * Creates a FindCommand Object
     */
    public FindCommand(Predicate<Book> predicate, Prefix prefix, String[] keywords) {
        this.predicate = predicate;
        this.prefix = prefix;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        PriorityQueue<Word> finalSuggestion = new PriorityQueue<>(new WordComparator());
        ArrayList<Word> wordSuggestions;
        model.updateFilteredBookList(predicate);

        if (model.getFilteredBookList().size() == 0
                && !prefix.equals(PREFIX_COMPLETED)
                && !prefix.equals(PREFIX_NOT_COMPLETED)) {
            SuggestionAlgorithm suggestionAlgorithm = new SuggestionAlgorithm(model.getWordBank());
            for (String word : keywords) {
                wordSuggestions = suggestionAlgorithm.findSuggestion(word, prefix);
                finalSuggestion.addAll(wordSuggestions);
            }
            String searchedWord = String.join(" ", keywords);
            if (finalSuggestion.isEmpty()) {
                return new CommandResult(String.format(
                        Messages.MESSAGE_WORD_NOT_UNDERSTOOD, searchedWord));
            }

            String suggestedWord = finalSuggestion.poll().getWord();
            return new CommandResult(
                    String.format(Messages.MESSAGE_WORD_SUGGESTION, searchedWord, suggestedWord));
        }

        model.save();
        storeViewType(model.getCurrentState(), ViewType.DEFAULT);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
