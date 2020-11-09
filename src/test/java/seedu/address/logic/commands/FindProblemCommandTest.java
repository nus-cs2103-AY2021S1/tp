package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_REPORT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;
import seedu.address.ui.Mode;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProblemCommand}.
 */
public class FindProblemCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void equals() {
        String[] firstKeywords = new String[] {"table"};
        String[] secondKeywords = new String[] {"chair"};

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Arrays.asList(firstKeywords));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Arrays.asList(secondKeywords));

        FindProblemCommand firstTestCommand = new FindProblemCommand(firstPredicate);
        FindProblemCommand secondTestCommand = new FindProblemCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstTestCommand.equals(firstTestCommand));

        // same values -> returns true
        FindProblemCommand findFirstCommandCopy = new FindProblemCommand(firstPredicate);
        assertTrue(firstTestCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstTestCommand.equals(1));

        // different book -> returns false
        assertFalse(firstTestCommand.equals(secondTestCommand));
    }

    @Test
    public void execute_zeroKeywords_noProblemFound() {
        String expectedMessage = String.format(MESSAGE_REPORT_LISTED_OVERVIEW, 0);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("");
        FindProblemCommand testCommand = new FindProblemCommand(predicate);
        expectedModel.updateFilteredProblemList(predicate, Mode.NORMAL);
        assertCommandSuccess(testCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProblemList());
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
