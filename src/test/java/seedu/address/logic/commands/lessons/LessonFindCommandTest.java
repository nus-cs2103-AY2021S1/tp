package seedu.address.logic.commands.lessons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_LESSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLessons.GEQ1000;
import static seedu.address.testutil.TypicalLessons.GET1011;
import static seedu.address.testutil.TypicalLessons.IS1103;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code LessonFindCommand}.
 */
public class LessonFindCommandTest {
    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void equals() {
        LessonNameContainsKeywordsPredicate firstPredicate =
                new LessonNameContainsKeywordsPredicate(Collections.singletonList("first"));
        LessonNameContainsKeywordsPredicate secondPredicate =
                new LessonNameContainsKeywordsPredicate(Collections.singletonList("second"));

        LessonFindCommand lessonFindFirstCommand = new LessonFindCommand(firstPredicate);
        LessonFindCommand lessonFindSecondCommand = new LessonFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(lessonFindFirstCommand.equals(lessonFindFirstCommand));

        // same values -> returns true
        LessonFindCommand lessonFindFirstCommandCopy = new LessonFindCommand(firstPredicate);
        assertTrue(lessonFindFirstCommand.equals(lessonFindFirstCommandCopy));

        // different types -> returns false
        assertFalse(lessonFindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(lessonFindFirstCommand.equals(null));

        // different lesson -> returns false
        assertFalse(lessonFindFirstCommand.equals(lessonFindSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noLessonFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        LessonNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        LessonFindCommand command = new LessonFindCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLessonList());
    }

    @Test
    public void execute_multipleKeywords_multipleLessonsFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 3);
        LessonNameContainsKeywordsPredicate predicate = preparePredicate("1011 geq 1103");
        LessonFindCommand command = new LessonFindCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GET1011, GEQ1000, IS1103), model.getFilteredLessonList());
    }

    /**
     * Parses {@code userInput} into a {@code LessonNameContainsKeywordsPredicate}.
     */
    private LessonNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new LessonNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
