package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Block;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.RoomInBlockPredicate;
import seedu.address.model.person.StudentGroupPredicate;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<Person>> firstPredicates =
                List.of(new NameContainsKeywordsPredicate(Collections.singletonList("first")));
        List<Predicate<Person>> secondPredicates =
                Collections.singletonList(new NameContainsKeywordsPredicate(Collections.singletonList("second")));
        List<Predicate<Person>> thirdPredicates =
                List.of(new NameContainsKeywordsPredicate(Collections.singletonList("second")),
                        new RoomInBlockPredicate(new Block("B")));
        Set<StudentGroup> studentGroupSet = new HashSet<>();
        studentGroupSet.add(new StudentGroup("soccer"));
        List<Predicate<Person>> fourthPredicates =
            List.of(new NameContainsKeywordsPredicate(Collections.singletonList("second")),
                new RoomInBlockPredicate(new Block("C")),
                new StudentGroupPredicate(studentGroupSet));

        FindCommand findFirstCommand = new FindCommand(firstPredicates);
        FindCommand findSecondCommand = new FindCommand(secondPredicates);
        FindCommand findThirdCommand = new FindCommand(thirdPredicates);
        FindCommand findFourthCommand = new FindCommand(fourthPredicates);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicates);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // extra block field -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));

        // extra block and student group field -> returns false
        assertFalse(findFirstCommand.equals(findFourthCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNameKeywordsPredicate(" ");
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNameKeywordsPredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_block_personInBlockFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        RoomInBlockPredicate predicate = prepareBlockPredicate("B");
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_blockAndNameKeyword_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        RoomInBlockPredicate blockPredicate = prepareBlockPredicate("C");
        NameContainsKeywordsPredicate namePredicate = prepareNameKeywordsPredicate("meier");
        FindCommand command = new FindCommand(List.of(blockPredicate, namePredicate));
        expectedModel.updateFilteredPersonList(blockPredicate.and(namePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_studentGroup_personWithOneStudentGroupFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Set<StudentGroup> studentGroupSet = new HashSet<>();
        studentGroupSet.add(new StudentGroup("badminton"));
        StudentGroupPredicate predicate = prepareStudentGroupPredicate(studentGroupSet);
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_studentGroup_personWithMultipleStudentGroupsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Set<StudentGroup> studentGroupSet = new HashSet<>();
        studentGroupSet.add(new StudentGroup("hackers"));
        studentGroupSet.add(new StudentGroup("soccer"));
        StudentGroupPredicate predicate = prepareStudentGroupPredicate(studentGroupSet);
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_studentGroup_personNotFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Set<StudentGroup> studentGroupSet = new HashSet<>();
        studentGroupSet.add(new StudentGroup("invalidStudentGroup"));
        StudentGroupPredicate predicate = prepareStudentGroupPredicate(studentGroupSet);
        FindCommand command = new FindCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameBlockStudentGroup_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNameKeywordsPredicate("george");
        RoomInBlockPredicate blockPredicate = prepareBlockPredicate("B");
        Set<StudentGroup> studentGroupSet = new HashSet<>();
        studentGroupSet.add(new StudentGroup("basketball"));
        StudentGroupPredicate studentGroupPredicate = prepareStudentGroupPredicate(studentGroupSet);
        List<Predicate<Person>> predicateList = List.of(namePredicate, blockPredicate, studentGroupPredicate);
        FindCommand command = new FindCommand(predicateList);
        expectedModel.updateFilteredPersonList(namePredicate.and(blockPredicate).and(studentGroupPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code keywords} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNameKeywordsPredicate(String keywords) {
        return new NameContainsKeywordsPredicate(Arrays.asList(keywords.split("\\s+")));
    }

    /**
     * Parses {@code block} into a {@code RoomInBlockPredicate}.
     */
    private RoomInBlockPredicate prepareBlockPredicate(String block) {
        return new RoomInBlockPredicate(new Block(block));
    }

    /**
     * Parses {@code Set<StudentGroup>} into a {@code StudentGroupPredicate}.
     */
    private StudentGroupPredicate prepareStudentGroupPredicate(Set<StudentGroup> studentGroupSet) {
        return new StudentGroupPredicate(studentGroupSet);
    }
}
