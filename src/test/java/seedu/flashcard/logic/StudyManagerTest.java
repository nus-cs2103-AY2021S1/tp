package seedu.flashcard.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.logic.commands.Command;
import seedu.flashcard.logic.commands.CommandResult;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;


public class StudyManagerTest {

    private Model model;
    private StudyManager studyManager;

    class LogicStub implements Logic {
        @Override
        public CommandResult execute(String commandText) throws CommandException, ParseException {
            return null;
        }

        @Override
        public CommandResult executeCommand(Command command) throws CommandException {
            return null;
        }

        @Override
        public ReadOnlyFlashcardDeck getFlashcardDeck() {
            return null;
        }

        @Override
        public ObservableList<Flashcard> getFilteredFlashcardList() {
            return null;
        }

        @Override
        public Path getFlashcardDeckFilePath() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
        studyManager = new StudyManager(model.getFilteredFlashcardList(), new LogicStub());
    }

    @Test
    public void execute_getFirstFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_1;
        assertEquals(expectedFlashcard, studyManager.getCurrentFlashcard());
    }

    @Test
    public void execute_reviewNextFlashcardFromFirstFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_2;
        assertEquals(expectedFlashcard, studyManager.getNextFlashcard());
    }

    @Test
    public void execute_reviewPreviousFlashcardFromFirstFlashcard_success() {
        assertEquals(null, studyManager.getPrevFlashcard());
    }

    @Test
    public void execute_reviewPreviousFlashcardFromSecondFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_1;
        // Set currentIndex of reviewManager to 1.
        studyManager.getNextFlashcard();
        // Return flashcard at index 0.
        assertEquals(expectedFlashcard, studyManager.getPrevFlashcard());
    }

}
