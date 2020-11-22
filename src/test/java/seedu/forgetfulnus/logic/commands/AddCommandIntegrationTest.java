package seedu.forgetfulnus.logic.commands;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.testutil.FlashCardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void execute_newFlashcard_success() {
        FlashCard validFlashCard = new FlashCardBuilder().build();

        Model expectedModel = new ModelManager(model.getGlossary(), new ScoreList(), new UserPrefs());
        expectedModel.addFlashCard(validFlashCard);

        assertCommandSuccess(new AddCommand(validFlashCard), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFlashCard), expectedModel);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {

        FlashCard flashCardInList = model.getGlossary().getFlashCardList().get(0);
        assertCommandFailure(new AddCommand(flashCardInList), model, AddCommand.MESSAGE_DUPLICATE_PHRASE);
    }

}
