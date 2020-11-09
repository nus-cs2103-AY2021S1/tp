package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.PropertiesMatchPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalExerciseBook(), null, new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalExerciseBook(), null, new UserPrefs());

    @Test
    public void equals() {
        try {
            Name name = new Name("Push Up");
            Description description = new Description("test");
            Date date = new Date("10-10-2020");
            Calories calories = new Calories("224");
            PropertiesMatchPredicate firstPredicate =
                    new PropertiesMatchPredicate(name, description, date,
                            calories, Collections.singletonList("first"));
            PropertiesMatchPredicate secondPredicate =
                    new PropertiesMatchPredicate(name, description, date,
                            calories, Collections.singletonList("second"));

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

            // different person -> returns false
            assertFalse(findFirstCommand.equals(findSecondCommand));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    public void execute_zeroKeywords_noExerciseFound() {
        String expectedMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW, 0);
        String input = " ";
        assert input != null;
        List<String> keywords = Arrays.asList(input.split("\\s+"));
        PropertiesMatchPredicate predicate =
                new PropertiesMatchPredicate(null, null, null, null, keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExerciseList());
    }
}
