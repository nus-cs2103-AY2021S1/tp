package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertPerformanceParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertPerformanceParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewAttemptCommand;

//author zeying99

public class PerformanceParserTest {
    private PerformanceParser parser = new PerformanceParser();

    @Test
    public void invalid_clearCommand_fromFlashcardInterface() {
        assertPerformanceParseFailure(parser, "clear", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Flashcard or "
            + "Quiz interfaces, which are not allowed in Performance interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }

    @Test
    public void invalid_startAttemptCommand_fromQuizInterface() {
        assertPerformanceParseFailure(parser, "start attempt", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Flashcard or "
            + "Quiz interfaces, which are not allowed in Performance interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }


    @Test
    public void valid_viewAttemptCommand_fromPerformanceInterface() {
        ViewAttemptCommand expectedViewAttemptCommand = new ViewAttemptCommand(Index.fromOneBased(1));
        assertPerformanceParseSuccess(parser, "view 1", expectedViewAttemptCommand);
    }


}
