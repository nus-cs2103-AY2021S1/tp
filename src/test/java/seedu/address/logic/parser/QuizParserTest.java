package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertQuizParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertQuizParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StartAttemptCommand;

//author zeying99

public class QuizParserTest {
    private QuizParser parser = new QuizParser();

    @Test
    public void invalid_clearCommand_fromFlashcardInterface() {
        assertQuizParseFailure(parser, "clear", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Flashcard or "
            + "Performance interfaces, which are not allowed in Quiz interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }

    @Test
    public void valid_startAttemptCommand() {
        StartAttemptCommand expectedStartAttemptCommand = new StartAttemptCommand();
        assertQuizParseSuccess(parser, "start attempt", expectedStartAttemptCommand);
    }


    @Test
    public void invalidCommandFromPerformanceInterface() {
        assertQuizParseFailure(parser, "view 1", MESSAGE_UNKNOWN_COMMAND
            + "\nMaybe you have used commands from Flashcard or "
            + "Performance interfaces, which are not allowed in Quiz interface.\n"
            + "Type 'help' to see the list of supported command lines from user guide.");
    }




}
