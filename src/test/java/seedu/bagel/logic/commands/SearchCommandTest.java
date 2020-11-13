package seedu.bagel.logic.commands;

import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

public class SearchCommandTest {

    private Model model = new ModelManager(getTypicalBagel(), new UserPrefs());

    @Test
    public void execute_matchingTitle_success() {
        List<Flashcard> flashcards = new ArrayList<Flashcard>(Arrays.asList(
                makeFlashcard("apple", "0", "", "1", "1"),
                makeFlashcard("apple pie", "1", "", "1", "1"),
                makeFlashcard("banana", "2", "", "1", "1")
            )
        );

        ModelManager expectedModel = new ModelManager();
        model = new ModelManager();
        for (Flashcard f: flashcards) {
            expectedModel.addFlashcard(f);
            model.addFlashcard(f);
        }
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0
                || flashcards.indexOf(flashcard) == 1);

        String keyword = "apple";
        SearchCommand searchCommand = new SearchCommand(keyword);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SEARCH_FLASHCARD_SUCCESS, keyword);
        assertCommandSuccess(searchCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_matchingDescription_success() {
        List<Flashcard> flashcards = new ArrayList<Flashcard>(Arrays.asList(
                makeFlashcard("0", "apple", "", "1", "1"),
                makeFlashcard("1", "apple pie", "", "1", "1"),
                makeFlashcard("2", "banana", "", "1", "1")
        )
        );

        ModelManager expectedModel = new ModelManager();
        model = new ModelManager();
        for (Flashcard f: flashcards) {
            expectedModel.addFlashcard(f);
            model.addFlashcard(f);
        }
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0
                || flashcards.indexOf(flashcard) == 1);

        String keyword = "apple";
        SearchCommand searchCommand = new SearchCommand(keyword);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SEARCH_FLASHCARD_SUCCESS, keyword);
        assertCommandSuccess(searchCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_matchingTag_success() {
        List<Flashcard> flashcards = new ArrayList<Flashcard>(Arrays.asList(
                makeFlashcard("0", "0", "", "1", "apple"),
                makeFlashcard("1", "1", "", "1", "applepie"),
                makeFlashcard("2", "2", "", "1", "banana")
        )
        );

        ModelManager expectedModel = new ModelManager();
        model = new ModelManager();
        for (Flashcard f: flashcards) {
            expectedModel.addFlashcard(f);
            model.addFlashcard(f);
        }
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0
                || flashcards.indexOf(flashcard) == 1);

        String keyword = "apple";
        SearchCommand searchCommand = new SearchCommand(keyword);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SEARCH_FLASHCARD_SUCCESS, keyword);
        assertCommandSuccess(searchCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_caseInsensitive_success() {
        List<Flashcard> flashcards = new ArrayList<Flashcard>(Arrays.asList(
                makeFlashcard("ApPlE", "0", "", "1", "1"),
                makeFlashcard("apple pie", "1", "", "1", "1"),
                makeFlashcard("banana", "2", "", "1", "1")
        )
        );

        ModelManager expectedModel = new ModelManager();
        model = new ModelManager();
        for (Flashcard f: flashcards) {
            expectedModel.addFlashcard(f);
            model.addFlashcard(f);
        }
        expectedModel.viewFlashcard(flashcard -> flashcards.indexOf(flashcard) == 0
                || flashcards.indexOf(flashcard) == 1);

        String keyword = "apple";
        SearchCommand searchCommand = new SearchCommand(keyword);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SEARCH_FLASHCARD_SUCCESS, keyword);
        assertCommandSuccess(searchCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatching_success() {
        List<Flashcard> flashcards = new ArrayList<Flashcard>(Arrays.asList(
                makeFlashcard("apple", "0", "", "1", "1"),
                makeFlashcard("apple pie", "1", "", "1", "1"),
                makeFlashcard("banana", "2", "", "1", "1")
        )
        );

        ModelManager expectedModel = new ModelManager();
        model = new ModelManager();
        for (Flashcard f : flashcards) {
            expectedModel.addFlashcard(f);
            model.addFlashcard(f);
        }
        expectedModel.viewFlashcard(flashcard -> false);

        String keyword = "elephant";
        SearchCommand searchCommand = new SearchCommand(keyword);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SEARCH_FLASHCARD_SUCCESS, keyword);
        assertCommandSuccess(searchCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void emptyBagel_success() {
        ModelManager expectedModel = new ModelManager();
        model = new ModelManager();

        String keyword = "apple";
        SearchCommand searchCommand = new SearchCommand(keyword);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SEARCH_FLASHCARD_SUCCESS, keyword);
        assertCommandSuccess(searchCommand, model, expectedMessage, expectedModel);
    }

    private Flashcard makeFlashcard(String title, String description, String link, String setNumber, String tagName) {
        Flashcard f = new Flashcard(
                new Title(title), new Description(description), new Link(link), new FlashcardSet(setNumber),
                new HashSet<Tag>(Arrays.asList(new Tag(tagName))));
        return f;
    }
}
