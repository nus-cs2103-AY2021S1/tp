package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HEAPSORT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertFlashcardParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertFlashcardParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;

//author zeying99

public class FlashcardParserTest {
    private FlashcardParser parser = new FlashcardParser();

    @Test
    public void valid_clearCommand() {
        ClearCommand expectedClearCommand = new ClearCommand();
        assertFlashcardParseSuccess(parser, "clear", expectedClearCommand);
    }

    @Test
    public void invalid_capital_clearCommand() {
        assertFlashcardParseFailure(parser, "CleAr", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Quiz or "
            + "Performance interfaces, which are not allowed in Flashcard interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }

    @Test
    public void valid_editCommand() {
        EditCommand expectedEditCommand = new EditCommand(INDEX_FIRST_FLASHCARD, new EditFlashcardDescriptorBuilder()
            .withName(VALID_NAME_HEAPSORT).build());
        assertFlashcardParseSuccess(parser, "edit 1 n/" + VALID_NAME_HEAPSORT, expectedEditCommand);
    }

    @Test
    public void invalid_capital_editCommand() {
        assertFlashcardParseFailure(parser, "EdIt 1 n/bubble sort", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Quiz or "
            + "Performance interfaces, which are not allowed in Flashcard interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }

    @Test
    public void invalidCommandFromQuizInterface() {
        assertFlashcardParseFailure(parser, "leave quiz", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Quiz or "
            + "Performance interfaces, which are not allowed in Flashcard interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }

    @Test
    public void invalidCommandFromPerformanceInterface() {
        assertFlashcardParseFailure(parser, "view 1", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Quiz or "
            + "Performance interfaces, which are not allowed in Flashcard interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }




}
