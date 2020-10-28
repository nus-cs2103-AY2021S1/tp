package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.commons.core.Messages.MESSAGE_STOCKS_LISTED_OVERVIEW;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SourceContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindExactCommand}.
 */
public class FindExactCommandTest {
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), getTypicalSerialNumberSetsBook());
    private Model expectedModel = new ModelManager(getTypicalStockBook(),
            new UserPrefs(), getTypicalSerialNumberSetsBook());

    @Test
    public void constructor_nullPredicateList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindExactCommand(null));
    }

    @Test
    public void execute_multiplePredicates_oneStockFound() {
        String expectedMessage = String.format(MESSAGE_STOCKS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("app");
        SourceContainsKeywordsPredicate sourcePredicate = prepareSourcePredicate("ntuc");

        FindExactCommand command = new FindExactCommand(Arrays.asList(namePredicate, sourcePredicate));

        // expected status message to show what user has searched for
        expectedMessage = "Searching for:\n" + namePredicate.toString() + ", "
                + sourcePredicate.toString() + "\n" + expectedMessage;
        List<Predicate<Stock>> predicateList = Arrays.asList(namePredicate, sourcePredicate);
        expectedModel.updateFilteredStockList(
                predicateList.stream().reduce(x -> true, Predicate::and));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(APPLE), model.getFilteredStockList());
    }

    @Test
    public void execute_zeroKeywords_noStockFound() {
        String expectedMessage = String.format(MESSAGE_STOCKS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("");
        FindExactCommand command = new FindExactCommand(Collections.singletonList(predicate));
        // status message to show what user has searched for
        String statusMessage = "Searching for:\n" + predicate.toString();
        expectedModel.updateFilteredStockList(predicate);
        assertCommandSuccess(command, model, statusMessage + "\n" + expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStockList());
    }

    @Test
    public void execute_multipleKeywords_oneStockFound() {
        String expectedMessage = String.format(MESSAGE_STOCKS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("A ple");
        FindExactCommand command = new FindExactCommand(Collections.singletonList(predicate));
        // status message to show what user has searched for
        String statusMessage = "Searching for:\n" + predicate.toString();
        expectedModel.updateFilteredStockList(predicate);
        assertCommandSuccess(command, model, statusMessage + "\n" + expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(APPLE), model.getFilteredStockList());
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        NameContainsKeywordsPredicate thirdPredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("second", "second"));

        FindExactCommand firstFindExactCommand = new FindExactCommand(Collections.singletonList(firstPredicate));
        FindExactCommand secondFindExactCommand = new FindExactCommand(Collections.singletonList(secondPredicate));
        FindExactCommand thirdFindExactCommand = new FindExactCommand(Collections.singletonList(thirdPredicate));

        // same object -> returns true
        assertTrue(firstFindExactCommand.equals(firstFindExactCommand));

        // same values -> returns true
        FindExactCommand findFirstCommandCopy = new FindExactCommand(Collections.singletonList(firstPredicate));
        assertTrue(firstFindExactCommand.equals(findFirstCommandCopy));

        FindExactCommand secondFindExactCommandCopy = new FindExactCommand(Collections
                .singletonList(new NameContainsKeywordsPredicate(Collections.singletonList("second"))));
        assertTrue(secondFindExactCommand.equals(secondFindExactCommandCopy));

        // different types -> returns false
        assertFalse(firstFindExactCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindExactCommand.equals(null));

        // different stock -> returns false
        assertFalse(firstFindExactCommand.equals(secondFindExactCommand));

        // one same value, other copy of value -> returns false
        assertFalse(secondFindExactCommand.equals(thirdFindExactCommand));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String trimmedUserInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(trimmedUserInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code SourceContainsKeywordsPredicate}.
     */
    private SourceContainsKeywordsPredicate prepareSourcePredicate(String trimmedUserInput) {
        return new SourceContainsKeywordsPredicate(Arrays.asList(trimmedUserInput.split("\\s+")));
    }
}
