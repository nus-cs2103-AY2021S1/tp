package seedu.forgetfulnus.logic.commands;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for QuizCommand.
 */
public class QuizCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_hideAllEnglishWords() {
        for (FlashCard flashCard: expectedModel.getFilteredFlashCardList()) {
            FlashCard changeTo = flashCard.copy();
            changeTo.updateShowingEnglish(false);
            expectedModel.setFlashCard(flashCard, changeTo);
        }
        assertCommandSuccess(new QuizCommand(), model, QuizCommand.MESSAGE_SUCCESS
                        + QuizCommand.FIRST_CARD
                        + model.getFilteredFlashCardList().get(0).getGermanPhrase().toString()
                        + "\n" + QuizCommand.TRY_COMMAND_REMINDER, expectedModel);
    }
}
