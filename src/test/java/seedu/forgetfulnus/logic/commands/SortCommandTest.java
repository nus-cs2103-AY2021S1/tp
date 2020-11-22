package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.SortCommand.MESSAGE_EMPTY_GLOSSARY;
import static seedu.forgetfulnus.logic.commands.SortCommand.MESSAGE_SORT_SUCCESS;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TestUtil.checkSortedOrder;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.core.Comparators;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.flashcard.FlashCard;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void constructor_invalidString_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new SortCommand("invalid string"));
        assertThrows(AssertionError.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_stringGerman_success() {
        SortCommand command = new SortCommand("german");
        CommandResult result = command.execute(model);
        List<FlashCard> expectedList = new ArrayList<>(expectedModel.getGlossary().getFlashCardList());
        expectedList.sort(Comparators.GERMAN_COMP);
        assertTrue(checkSortedOrder(model.getGlossary().getFlashCardList(), expectedList));
        assertEquals(result, new CommandResult(MESSAGE_SORT_SUCCESS));
    }

    @Test
    public void execute_stringEnglish_success() {
        SortCommand command = new SortCommand("english");
        CommandResult result = command.execute(model);
        List<FlashCard> expectedList = new ArrayList<>(expectedModel.getGlossary().getFlashCardList());
        expectedList.sort(Comparators.ENGLISH_COMP);
        assertTrue(checkSortedOrder(model.getGlossary().getFlashCardList(), expectedList));
        assertEquals(result, new CommandResult(MESSAGE_SORT_SUCCESS));
    }

    @Test
    public void execute_stringEasyToHard_success() {
        SortCommand command = new SortCommand("easytohard");
        CommandResult result = command.execute(model);
        List<FlashCard> expectedList = new ArrayList<>(expectedModel.getGlossary().getFlashCardList());
        expectedList.sort(Comparators.DIFFICULTY_EASY_COMP);
        assertTrue(checkSortedOrder(model.getGlossary().getFlashCardList(), expectedList));
        assertEquals(result, new CommandResult(MESSAGE_SORT_SUCCESS));
    }

    @Test
    public void execute_stringEarliest_success() {
        SortCommand command = new SortCommand("earliest");
        CommandResult result = command.execute(model);
        List<FlashCard> expectedList = new ArrayList<>(expectedModel.getGlossary().getFlashCardList());
        expectedList.sort(Comparators.CHRONOLOGICAL_EARLIEST_COMP);
        assertTrue(checkSortedOrder(model.getGlossary().getFlashCardList(), expectedList));
        assertEquals(result, new CommandResult(MESSAGE_SORT_SUCCESS));
    }

    @Test
    public void execute_emptyGlossary_sendsEmptyMessage() {
        SortCommand command = new SortCommand("german");
        model.setGlossary(new Glossary());
        CommandResult result = command.execute(model);
        assertEquals(result, new CommandResult(MESSAGE_EMPTY_GLOSSARY));
    }
}
