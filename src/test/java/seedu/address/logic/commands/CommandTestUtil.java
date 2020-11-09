package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOCKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameContainsKeywordsPredicate;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;
import seedu.address.model.problem.Problem;
import seedu.address.testutil.EditBookDescriptorBuilder;
import seedu.address.testutil.EditProblemDescriptorBuilder;
import seedu.address.ui.Mode;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_BOOK1 = "Amy";
    public static final String VALID_NAME_BOOK2 = "Bob";
    public static final String VALID_ISBN_BOOK1 = "11111111";
    public static final String VALID_ISBN_BOOK2 = "22222222";
    public static final String VALID_EMAIL_BOOK1 = "book1@example.com";
    public static final String VALID_EMAIL_BOOK2 = "book2@example.com";
    public static final String VALID_LANGUAGE_BOOK1 = "English";
    public static final String VALID_LANGUAGE_BOOK2 = "Chinese";
    public static final String VALID_TIMES_BOOK1 = "23";
    public static final String VALID_TIMES_BOOK2 = "2";
    public static final String VALID_CATEGORY_MATH = "Math";
    public static final String VALID_CATEGORY_SCIENCE = "Science";
    public static final String VALID_STOCKING_BOOK1 = "centralLb 10 scienceLb 10";
    public static final String VALID_STOCKING_BOOK2 = "centralLb 30 scienceLb 15";
    public static final String VALID_AUTHOR_BOOK1 = "a";
    public static final String VALID_AUTHOR_BOOK2 = "a";
    public static final String VALID_PUBLISHER_BOOK1 = "pub";
    public static final String VALID_PUBLISHER_BOOK2 = "pub";
    public static final String VALID_SEVERITY_P1 = "low";
    public static final String VALID_SEVERITY_P2 = "high";
    public static final String VALID_DESCRIPTION_P1 = "good morning";
    public static final String VALID_DESCRIPTION_P2 = "bad morning";

    public static final String NAME_DESC_BOOK1 = " " + PREFIX_NAME + VALID_NAME_BOOK1;
    public static final String NAME_DESC_BOOK2 = " " + PREFIX_NAME + VALID_NAME_BOOK2;
    public static final String ISBN_DESC_BOOK1 = " " + PREFIX_ISBN + VALID_ISBN_BOOK1;
    public static final String ISBN_DESC_BOOK2 = " " + PREFIX_ISBN + VALID_ISBN_BOOK2;
    public static final String EMAIL_DESC_BOOK1 = " " + PREFIX_EMAIL + VALID_EMAIL_BOOK1;
    public static final String EMAIL_DESC_BOOK2 = " " + PREFIX_EMAIL + VALID_EMAIL_BOOK2;
    public static final String LANGUAGE_DESC_BOOK1 = " " + PREFIX_LANGUAGE + VALID_LANGUAGE_BOOK1;
    public static final String LANGUAGE_DESC_BOOK2 = " " + PREFIX_LANGUAGE + VALID_LANGUAGE_BOOK2;
    public static final String TIMES_DESC_BOOK1 = " " + PREFIX_TIMES + VALID_TIMES_BOOK1;
    public static final String TIMES_DESC_BOOK2 = " " + PREFIX_TIMES + VALID_TIMES_BOOK2;
    public static final String CATEGORY_DESC_FRIEND = " " + PREFIX_CATEGORY + VALID_CATEGORY_SCIENCE;
    public static final String CATEGORY_DESC_HUSBAND = " " + PREFIX_CATEGORY + VALID_CATEGORY_MATH;
    public static final String STOCKING_DESC_BOOK1 = " " + PREFIX_STOCKING + VALID_STOCKING_BOOK1;
    public static final String STOCKING_DESC_BOOK2 = " " + PREFIX_STOCKING + VALID_STOCKING_BOOK2;
    public static final String AUTHOR_DESC_BOOK1 = " " + PREFIX_AUTHOR + VALID_AUTHOR_BOOK1;
    public static final String AUTHOR_DESC_BOOK2 = " " + PREFIX_AUTHOR + VALID_AUTHOR_BOOK2;
    public static final String PUBLISHER_DESC_BOOK1 = " " + PREFIX_PUBLISHER + VALID_PUBLISHER_BOOK1;
    public static final String PUBLISHER_DESC_BOOK2 = " " + PREFIX_PUBLISHER + VALID_PUBLISHER_BOOK2;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_ISBN_DESC = " " + PREFIX_ISBN + "911a"; // 'a' not allowed in isbn
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_LANGUAGE_DESC = " " + PREFIX_LANGUAGE; // empty string not allowed for languages
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "novels*"; // '*' not allowed in
    // categories
    public static final String INVALID_STOCKING_DESC = " " + PREFIX_STOCKING + "central library: 20 science library:10";
    public static final String INVALID_TIMES_DESC = " " + PREFIX_TIMES + "03282";
    public static final String INVALID_AUTHOR_DESC = " " + PREFIX_AUTHOR + "james&&";
    public static final String INVALID_PUBLISHER_DESC = " " + PREFIX_PUBLISHER + "david&&";
    public static final String VALID_SEVERITY_P1_DESC = " " + "s/low";
    public static final String VALID_SEVERITY_P2_DESC = " " + "s/high";
    public static final String VALID_DESCRIPTION_P1_DESC = " " + "d/good morning";
    public static final String VALID_DESCRIPTION_P2_DESC = " " + "d/bad morning";



    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditBookDescriptor DESC_BOOK1;
    public static final EditCommand.EditBookDescriptor DESC_BOOK2;
    public static final EditProblemCommand.EditProblemDescriptor DESC_REPORT1;
    public static final EditProblemCommand.EditProblemDescriptor DESC_REPORT2;

    static {
        DESC_BOOK1 = new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK1).withIsbn(VALID_ISBN_BOOK1)
                .withEmail(VALID_EMAIL_BOOK1).withLanguage(VALID_LANGUAGE_BOOK1).withCategories(VALID_CATEGORY_SCIENCE)
                .withTimes(VALID_TIMES_BOOK1).withStockings(VALID_STOCKING_BOOK1).withAuthor(VALID_AUTHOR_BOOK1)
                .withPublisher(VALID_PUBLISHER_BOOK1).build();
        DESC_BOOK2 = new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK2).withIsbn(VALID_ISBN_BOOK2)
                .withEmail(VALID_EMAIL_BOOK2).withLanguage(VALID_LANGUAGE_BOOK2).withStockings(VALID_STOCKING_BOOK2)
                .withTimes(VALID_TIMES_BOOK2).withPublisher(VALID_PUBLISHER_BOOK2)
                .withCategories(VALID_CATEGORY_MATH, VALID_CATEGORY_SCIENCE).withAuthor(VALID_AUTHOR_BOOK2).build();
        DESC_REPORT1 = new EditProblemDescriptorBuilder().withSeverity(VALID_SEVERITY_P1)
                .withDescription(VALID_DESCRIPTION_P1).build();
        DESC_REPORT2 = new EditProblemDescriptorBuilder().withSeverity(VALID_SEVERITY_P2)
                .withDescription(VALID_DESCRIPTION_P2).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | IOException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the library, filtered book list and selected book in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Library expectedLibrary = new Library(actualModel.getLibrary());
        List<Book> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBookList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLibrary, actualModel.getLibrary());
        assertEquals(expectedFilteredList, actualModel.getFilteredBookList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the book at the given {@code targetIndex} in the
     * {@code model}'s library.
     */
    public static void showBookAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookList().size());

        Book book = model.getFilteredBookList().get(targetIndex.getZeroBased());
        final String[] splitName = book.getName().fullName.split("\\s+");
        model.updateFilteredBookList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])), Mode.NORMAL);

        assertEquals(1, model.getFilteredBookList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the reports at the given {@code targetIndex} in the
     * {@code model}'s library.
     */
    public static void showReportAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProblemList().size());

        Problem problem = model.getFilteredProblemList().get(targetIndex.getZeroBased());
        final String[] splitDescriptions = problem.getDescription().description.split("\\s+");
        model.updateFilteredProblemList(
                new DescriptionContainsKeywordsPredicate(Arrays.asList(splitDescriptions[0])), Mode.NORMAL);

        assertEquals(1, model.getFilteredProblemList().size());
    }

}
