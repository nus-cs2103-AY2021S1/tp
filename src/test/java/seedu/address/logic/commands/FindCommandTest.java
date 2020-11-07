package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.CS1231S_HW;
import static seedu.address.testutil.TypicalAssignments.CS2103T_TUT;
import static seedu.address.testutil.TypicalAssignments.CS2106_LAB;
import static seedu.address.testutil.TypicalAssignments.IS1103_MISSION;
import static seedu.address.testutil.TypicalAssignments.MA1101R_TUTORIAL_ONE;
import static seedu.address.testutil.TypicalAssignments.MA1101R_TUTORIAL_TWO;
import static seedu.address.testutil.TypicalAssignments.PEER_REVIEW;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.DeadlineContainsKeywordsPredicate;
import seedu.address.model.assignment.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.assignment.NameContainsKeywordsPredicate;
import seedu.address.model.assignment.PriorityContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
    private Model expectedModel = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Assignment 1"));

        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Lab"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);

        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroNameKeywords_noAssignmentFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = preparePredicateName(" ");
        FindCommand findName = new FindCommand(namePredicate);
        expectedModel.updateFilteredAssignmentList(namePredicate);
        assertCommandSuccess(findName, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_zeroModuleCodeKeywords_noAssignmentFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 0);
        ModuleCodeContainsKeywordsPredicate moduleCodePredicate = preparePredicateModuleCode(" ");
        FindCommand findModuleCode = new FindCommand(moduleCodePredicate);
        expectedModel.updateFilteredAssignmentList(moduleCodePredicate);
        assertCommandSuccess(findModuleCode, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_zeroDeadlineKeywords_noAssignmentFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 0);
        DeadlineContainsKeywordsPredicate deadlinePredicate = preparePredicateDeadline(" ");
        FindCommand findDeadline = new FindCommand(deadlinePredicate);
        expectedModel.updateFilteredAssignmentList(deadlinePredicate);
        assertCommandSuccess(findDeadline, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_zeroPriorityKeywords_noAssignmentFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 0);
        PriorityContainsKeywordsPredicate priorityPredicate = preparePredicatePriority(" ");
        FindCommand findPriority = new FindCommand(priorityPredicate);
        expectedModel.updateFilteredAssignmentList(priorityPredicate);
        assertCommandSuccess(findPriority, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }



    @Test
    public void execute_multipleNameKeywords_multipleAssignmentsFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicateName("Lab Mission Peer");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredAssignmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2106_LAB, IS1103_MISSION, PEER_REVIEW), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_multipleModuleCodeKeywords_multipleAssignmentsFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 2);
        ModuleCodeContainsKeywordsPredicate moduleCodePredicate = preparePredicateModuleCode("CS2103T CS1231S");
        FindCommand findModuleCode = new FindCommand(moduleCodePredicate);
        expectedModel.updateFilteredAssignmentList(moduleCodePredicate);
        assertCommandSuccess(findModuleCode, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS1231S_HW, CS2103T_TUT), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_multipleDeadlineKeywords_multipleAssignmentsFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 2);
        DeadlineContainsKeywordsPredicate deadlinePredicate = preparePredicateDeadline("01-01-2020 1800 1200");
        FindCommand findDeadline = new FindCommand(deadlinePredicate);
        expectedModel.updateFilteredAssignmentList(deadlinePredicate);
        assertCommandSuccess(findDeadline, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS1231S_HW, CS2106_LAB), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_multiplePriorityKeywords_multipleAssignmentsFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 2);
        PriorityContainsKeywordsPredicate priorityPredicate = preparePredicatePriority("high LOW");
        FindCommand findPriority = new FindCommand(priorityPredicate);
        expectedModel.updateFilteredAssignmentList(priorityPredicate);
        assertCommandSuccess(findPriority, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MA1101R_TUTORIAL_ONE, MA1101R_TUTORIAL_TWO), model.getFilteredAssignmentList());
    }



    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicateName(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ModuleCodeContainsKeywordsPredicate}.
     */
    private ModuleCodeContainsKeywordsPredicate preparePredicateModuleCode(String userInput) {
        return new ModuleCodeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code DeadlineContainsKeywordsPredicate}.
     */
    private DeadlineContainsKeywordsPredicate preparePredicateDeadline(String userInput) {
        return new DeadlineContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PriorityContainsKeywordsPredicate}.
     */
    private PriorityContainsKeywordsPredicate preparePredicatePriority(String userInput) {
        return new PriorityContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
