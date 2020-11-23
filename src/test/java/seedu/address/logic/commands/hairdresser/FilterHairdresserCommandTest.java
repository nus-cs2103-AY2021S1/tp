package seedu.address.logic.commands.hairdresser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_HAIRDRESSER_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHairdressers.AMELIA;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;
import static seedu.address.testutil.TypicalHairdressers.CALEB;
import static seedu.address.testutil.TypicalHairdressers.getTypicalHairStyleX;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.hairdresser.HairdresserNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalHairdressers;


public class FilterHairdresserCommandTest {

    private Model model = new ModelManager(getTypicalHairStyleX(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());

    @Test
    public void equals() {
        HairdresserNameContainsKeywordsPredicate firstPredicate =
                new HairdresserNameContainsKeywordsPredicate(Collections.singletonList("first"));
        HairdresserNameContainsKeywordsPredicate secondPredicate =
                new HairdresserNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FilterHairdresserCommand filterFirstCommand = new FilterHairdresserCommand(firstPredicate);
        FilterHairdresserCommand filterSecondCommand = new FilterHairdresserCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterHairdresserCommand filterFirstCommandCopy = new FilterHairdresserCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noHairdresserFound() {
        String expectedMessage = String.format(MESSAGE_HAIRDRESSER_LISTED_OVERVIEW, 0);
        HairdresserNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterHairdresserCommand command = new FilterHairdresserCommand(predicate);
        expectedModel.updateFilteredHairdresserList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredHairdresserList());
    }

    @Test
    public void execute_multipleKeywords_multipleHairdressersFound() {
        String expectedMessage = String.format(MESSAGE_HAIRDRESSER_LISTED_OVERVIEW, 3);
        HairdresserNameContainsKeywordsPredicate predicate = preparePredicate("tan Benedict kurz");
        FilterHairdresserCommand command = new FilterHairdresserCommand(predicate);
        expectedModel.updateFilteredHairdresserList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMELIA, BENEDICT, CALEB), model.getFilteredHairdresserList());
        model.getFilteredHairdresserList().stream().forEach(x -> System.out.println(x.getName()));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private HairdresserNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new HairdresserNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
