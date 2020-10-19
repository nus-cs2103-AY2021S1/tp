package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SnapCommand;

public class SnapCommandParserTest {
    private SnapCommandParser parser = new SnapCommandParser();

    @Test
    public void parse_validFileName_success() {
        String validFileName1 = "zookeep_19-10-2020";
        assertParseSuccess(parser, validFileName1, new SnapCommand(validFileName1));

        String validFileName2 = "_";
        assertParseSuccess(parser, validFileName2, new SnapCommand(validFileName2));

        String validFileName3 = "-";
        assertParseSuccess(parser, validFileName3, new SnapCommand(validFileName3));

        String validFileNameLength = "x".repeat(100);
        assertParseSuccess(parser, validFileNameLength, new SnapCommand(validFileNameLength));
    }

    @Test
    public void parse_invalidFileName_failure() {
        String expectedMessageEmptyString = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SnapCommand.MESSAGE_USAGE);
        String expectedMessageConstraints = SnapCommand.MESSAGE_CONSTRAINTS;

        String fileNameIsEmpty = " ";
        assertParseFailure(parser, fileNameIsEmpty, expectedMessageEmptyString);

        String fileNameContainsWhitespace = "zookeep 19-10-2020";
        assertParseFailure(parser, fileNameContainsWhitespace, expectedMessageConstraints);

        String fileNameContainsNonAlphanumeric = "19.10.2020";
        assertParseFailure(parser, fileNameContainsNonAlphanumeric, expectedMessageConstraints);

        String fileNameLongerThan100Characters = "x".repeat(101);
        assertParseFailure(parser, fileNameLongerThan100Characters, expectedMessageConstraints);
    }
}
