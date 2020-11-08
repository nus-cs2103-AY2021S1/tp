package seedu.address.logic.parser.calorie;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CalorieMinusCommand;
import seedu.address.logic.parser.CalorieMinusCommandParser;
import seedu.address.model.calorie.Calorie;

public class CalorieMinusCommandParserTest {
    private final CalorieMinusCommandParser parser = new CalorieMinusCommandParser();
    private final CalorieMinusCommand command = new CalorieMinusCommand(new Calorie(100));
    private final String formatErrorMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            CalorieMinusCommand.MESSAGE_USAGE);


    @Test
    public void parseValidInput_success() {
        assertParseSuccess(parser, " c/100", command);
    }

    @Test
    public void parseInvalidInputMissingPrefix_failure() {
        assertParseFailure(parser, " 100", formatErrorMessage);
    }

    @Test
    public void parseInvalidInputNegativeNumber_failure() {
        assertParseFailure(parser, " -1", formatErrorMessage);
    }

    @Test
    public void parseInvalidInputTooLarge_failure() {
        assertParseFailure(parser, " 2147483648", formatErrorMessage);
    }

    @Test
    public void parseInvalidInputDoublePrefix_failure() {
        assertParseFailure(parser, " c/1000 c/1", formatErrorMessage);
    }
}
