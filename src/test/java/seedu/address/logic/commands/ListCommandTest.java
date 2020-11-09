package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARCHIVE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.JASON;
import static seedu.address.testutil.TypicalPersons.KING;
import static seedu.address.testutil.TypicalPersons.LINDA;
import static seedu.address.testutil.TypicalPersons.MONK;
import static seedu.address.testutil.TypicalPersons.getTypicalClientList;
import static seedu.address.testutil.TypicalPersons.getTypicalClientListOnlyArchive;
import static seedu.address.testutil.TypicalPersons.getTypicalClientListWithArchive;
import static seedu.address.testutil.TypicalPolicies.getTypicalPolicyList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 * Note that integration tests with FindCommand are not done here.
 */
public class ListCommandTest {

    @Test
    public void equals() {
        ListCommand listFirstCommand = new ListCommand();
        ListCommand listSecondCommand = new ListCommand(false);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listSecondCommand));
    }

    @Test
    public void execute_listIsNotFilteredAndStaysInActiveMode_showsSameList() {
        Model model = new ModelManager(getTypicalClientList(), new UserPrefs(), getTypicalPolicyList());
        Model expectedModel = new ModelManager(getTypicalClientList(), new UserPrefs(), getTypicalPolicyList());

        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS_ACTIVE, expectedModel);
    }

    @Test
    public void execute_listIsNotFilteredSwitchFromArchiveToActive_showsActiveOnly() {
        Model model = new ModelManager(getTypicalClientListWithArchive(), new UserPrefs(), getTypicalPolicyList());
        model.setIsArchiveMode(true);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);

        Model expectedModel = new ModelManager(
                getTypicalClientListWithArchive(),
                new UserPrefs(),
                getTypicalPolicyList());

        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS_ACTIVE, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE,
                FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_listIsNotFilteredAndIsInArchiveMode_showsSameList() {
        Model model = new ModelManager(getTypicalClientListOnlyArchive(), new UserPrefs(), getTypicalPolicyList());
        model.setIsArchiveMode(true);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);

        Model expectedModel = new ModelManager(
                getTypicalClientListOnlyArchive(),
                new UserPrefs(),
                getTypicalPolicyList());
        expectedModel.setIsArchiveMode(true);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);

        assertCommandSuccess(new ListCommand(true), model,
                ListCommand.MESSAGE_SUCCESS_ARCHIVE, expectedModel);
    }

    @Test
    public void execute_listIsNotFilteredSwitchFromActiveToArchive_showsArchiveOnly() {
        Model model = new ModelManager(
                getTypicalClientListWithArchive(),
                new UserPrefs(),
                getTypicalPolicyList());

        Model expectedModel = new ModelManager(
                getTypicalClientListWithArchive(),
                new UserPrefs(),
                getTypicalPolicyList());
        expectedModel.setIsArchiveMode(true);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);

        assertCommandSuccess(new ListCommand(true), model,
                ListCommand.MESSAGE_SUCCESS_ARCHIVE, expectedModel);
        assertEquals(Arrays.asList(JASON, KING, LINDA, MONK), model.getFilteredPersonList());
    }

}

