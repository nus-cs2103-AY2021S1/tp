package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ANIMALS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAnimals.COCO;
import static seedu.address.testutil.TypicalAnimals.GRECIA;
import static seedu.address.testutil.TypicalAnimals.NEMO;
import static seedu.address.testutil.TypicalAnimals.getTypicalZooKeepBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.animal.AnimalContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());

    @Test
    public void equals() {
        AnimalContainsKeywordsPredicate firstPredicate =
                new AnimalContainsKeywordsPredicate(Collections.singletonList("first"));
        AnimalContainsKeywordsPredicate secondPredicate =
                new AnimalContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different animal -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAnimalFound() {
        String expectedMessage = String.format(MESSAGE_ANIMALS_LISTED_OVERVIEW, 0);
        AnimalContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredAnimalList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAnimalList());
    }

    @Test
    public void execute_multipleKeywords_multipleAnimalsFound() {
        String expectedMessage = String.format(MESSAGE_ANIMALS_LISTED_OVERVIEW, 3);
        AnimalContainsKeywordsPredicate predicate = preparePredicate("Coco Grecia Nemo");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredAnimalList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(COCO, GRECIA, NEMO), model.getFilteredAnimalList());
    }

    /**
     * Parses {@code userInput} into a {@code AnimalContainsKeywordsPredicate}.
     */
    private AnimalContainsKeywordsPredicate preparePredicate(String userInput) {
        return new AnimalContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
