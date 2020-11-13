package seedu.internhunter.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_FIND_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.company.CompanyNameContainsKeyWordsPredicate;
import seedu.internhunter.model.item.ItemList;

/**
 * Test for find company command.
 */
public class FindCompanyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(model.getUnfilteredCompanyList(), new ItemList<>(),
                new ItemList<>(), new UserPrefs());
    }

    @Test
    public void equals() {
        CompanyNameContainsKeyWordsPredicate firstPredicate =
                new CompanyNameContainsKeyWordsPredicate(Collections.singletonList("first"));
        CompanyNameContainsKeyWordsPredicate secondPredicate =
                new CompanyNameContainsKeyWordsPredicate(Collections.singletonList("second"));

        FindCompanyCommand findCompanyFirstCommand = new FindCompanyCommand(firstPredicate);
        FindCompanyCommand findCompanySecondCommand = new FindCompanyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findCompanyFirstCommand.equals(findCompanyFirstCommand));
        assertTrue(findCompanySecondCommand.equals(findCompanySecondCommand));

        // same values -> returns true
        FindCompanyCommand findCompanyFirstCommandCopy = new FindCompanyCommand(firstPredicate);
        assertTrue(findCompanyFirstCommand.equals(findCompanyFirstCommandCopy));
        FindCompanyCommand findCompanySecondCommandCopy = new FindCompanyCommand(secondPredicate);
        assertTrue(findCompanySecondCommand.equals(findCompanySecondCommandCopy));

        // different types -> returns false
        assertFalse(findCompanyFirstCommand.equals(1));
        assertFalse(findCompanySecondCommand.equals(1));

        // null -> returns false
        assertFalse(findCompanyFirstCommand.equals(null));
        assertFalse(findCompanySecondCommand.equals(null));

        // different company -> returns false
        assertFalse(findCompanyFirstCommand.equals(findCompanySecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_FIND_SUCCESS, 0, COMPANY_NAME);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);
        CompanyNameContainsKeyWordsPredicate predicate = preparePredicate(" ");
        FindCompanyCommand command = new FindCompanyCommand(predicate);

        // update for expected model
        expectedModel.updateFilteredCompanyList(predicate);
        expectedModel.setCompanyViewIndex(Index.fromOneBased(1));

        // test
        assertCommandSuccess(command, model, commandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCompanyList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicationFound() {
        String expectedMessage = String.format(MESSAGE_FIND_SUCCESS, 1, COMPANY_NAME);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);
        CompanyNameContainsKeyWordsPredicate predicate = preparePredicate("Facebook");
        FindCompanyCommand command = new FindCompanyCommand(predicate);

        // update for expected model
        expectedModel.updateFilteredCompanyList(predicate);
        expectedModel.setCompanyViewIndex(Index.fromOneBased(1));

        // test
        assertCommandSuccess(command, model, commandResult, expectedModel);
        assertEquals(Arrays.asList(FACEBOOK), model.getFilteredCompanyList());
    }

    /**
     * Parses {@code userInput} into a {@code CompanyNameContainsKeyWordsPredicate}.
     */
    private CompanyNameContainsKeyWordsPredicate preparePredicate(String userInput) {
        return new CompanyNameContainsKeyWordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
