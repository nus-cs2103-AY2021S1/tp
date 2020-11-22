package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * Contains integration test (interaction with the Model) and unit test for
 * {@code RandomQuizCommand}.
 */
public class RandomQuizCommandTest {

    private Model model;

    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void execute_randomNonEmptyList_success() throws CommandException {
        setUp();
        String expectedMessage = RandomQuizCommand.MESSAGE_SUCCESS;
        String[] actualMessageArr = new RandomQuizCommand(
                Index.fromOneBased(getTypicalGlossary().getFlashCardList().size()))
                .execute(model)
                .getFeedbackToUser()
                .split(" ");
        String actualMessage = actualMessageArr[0]
                + " " + actualMessageArr[1]
                + " " + actualMessageArr[2];
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void execute_randomEmptyList_throwsCommandException() {
        setUp();
        model.setGlossary(new Glossary());
        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX, () -> new RandomQuizCommand(
                        Index.fromOneBased(1)).execute(model));
    }
}
