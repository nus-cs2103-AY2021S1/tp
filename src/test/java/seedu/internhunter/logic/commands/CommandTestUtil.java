package seedu.internhunter.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.ApplicationNameContainsKeyWordsPredicate;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyNameContainsKeyWordsPredicate;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemContainsKeywordPredicate;

/**
 * Contains helper methods and strings for testing commands.
 */
public class CommandTestUtil {

    // Preamble
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY_RANDOM = "NonEmptyPreamble";
    public static final String PREAMBLE_EMPTY = "";

    // Valid indexes
    public static final String VALID_INDEX_ONE = " " + PREFIX_INDEX + INDEX_FIRST;
    public static final String VALID_INDEX_TWO = " " + PREFIX_INDEX + INDEX_SECOND;

    // Invalid indexes
    public static final String INVALID_INDEX_RANDOM_STRING = " " + PREFIX_INDEX + "random";

    public static final String METHOD_SHOULD_NOT_FAIL_MESSAGE = "Execution of method should not fail.";
    public static final String EXECUTION_SHOULD_NOT_FAIL_MESSAGE = "Execution of command should not fail.";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError(EXECUTION_SHOULD_NOT_FAIL_MESSAGE, ce);
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
     * - the itemList, filtered item list and selected item in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ItemList<CompanyItem> expectedCompanyList = new ItemList<>(actualModel.getUnfilteredCompanyList());
        List<CompanyItem> expectedFilteredCompanyList = new ArrayList<>(actualModel.getFilteredCompanyList());
        ItemList<ApplicationItem> expectedApplicationList = new ItemList<>(actualModel.getUnfilteredApplicationList());
        List<ApplicationItem> expectedFilteredApplicationList =
                new ArrayList<>(actualModel.getFilteredApplicationList());
        ItemList<ProfileItem> expectedProfileList = new ItemList<>(actualModel.getUnfilteredProfileList());
        List<ProfileItem> expectedFilteredProfileList = new ArrayList<>(actualModel.getFilteredProfileList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCompanyList, actualModel.getUnfilteredCompanyList());
        assertEquals(expectedFilteredCompanyList, actualModel.getFilteredCompanyList());
        assertEquals(expectedApplicationList, actualModel.getUnfilteredApplicationList());
        assertEquals(expectedFilteredApplicationList, actualModel.getFilteredApplicationList());
        assertEquals(expectedProfileList, actualModel.getUnfilteredProfileList());
        assertEquals(expectedFilteredProfileList, actualModel.getFilteredProfileList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the profileItem at the given {@code targetIndex} in the
     * {@code model}'s profile list.
     */
    public static void showProfileItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProfileList().size());

        ProfileItem profileItem = model.getProfileItemFromFilteredList(targetIndex.getZeroBased());
        final String[] splitTitle = profileItem.getTitle().toString().split("\\s+");
        model.updateFilteredProfileList(new ProfileItemContainsKeywordPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredProfileListSize());
    }

    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s application list.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        ApplicationItem applicationItem = model.getApplicationItemFromFilteredList(targetIndex.getZeroBased());
        final String[] splitJobTitle = applicationItem.getInternshipJobTitleValue().split("\\s+");
        model.updateFilteredApplicationList(
                new ApplicationNameContainsKeyWordsPredicate(Collections.singletonList(splitJobTitle[0])));

        assertEquals(1, model.getFilteredApplicationListSize());
    }

    /**
     * Updates {@code model}'s filtered list to show only the company at the given {@code targetIndex} in the
     * {@code model}'s company list.
     */
    public static void showCompanyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCompanyListSize());

        CompanyItem companyItem = model.getCompanyItemFromFilteredList(targetIndex.getZeroBased());
        final String[] splitJobTitle = companyItem.getCompanyNameValue().split("\\s+");
        model.updateFilteredCompanyList(
                new CompanyNameContainsKeyWordsPredicate(Collections.singletonList(splitJobTitle[0])));

        assertEquals(1, model.getFilteredCompanyListSize());
    }

    /**
     * Updates {@code model}'s filtered list to show no profile items.
     */
    public static void showNoProfile(Model model) {
        model.updateFilteredProfileList(p -> false);
        assertEquals(model.getFilteredProfileListSize(), 0);
    }

    /**
     * Updates {@code model}'s filtered list to show no companies.
     */
    public static void showNoCompany(Model model) {
        model.updateFilteredProfileList(p -> false);
        assertEquals(model.getFilteredProfileListSize(), 0);
    }

}
