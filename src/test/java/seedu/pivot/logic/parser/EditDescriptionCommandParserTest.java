package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.descriptioncommands.EditDescriptionCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Description;

public class EditDescriptionCommandParserTest {

    public static final String PREFIX_DESC = " " + CliSyntax.PREFIX_DESC.getPrefix();
    public static final String VALID_DESC = "I am a valid description!";
    public static final String EMPTY_DESC = "";
    public static final String BLANK_DESC = " ";

    private static Index index = Index.fromZeroBased(FIRST_INDEX.getZeroBased());

    private EditDescriptionCommandParser parser = new EditDescriptionCommandParser();

    @BeforeAll
    public static void setStateZero() {
        StateManager.setState(index);
    }

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    @Test
    public void parse_prefixPresent_success() {
        // normal input
        assertParseSuccess(parser, PREFIX_DESC + VALID_DESC,
                new EditDescriptionCommand(index, new Description(VALID_DESC)));
    }

    @Test
    public void parse_prefixAbsent_throwsParseException() {
        // missing prefix
        assertThrows(ParseException.class, () -> parser.parse(VALID_DESC));
        assertParseFailure(parser, VALID_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditDescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDescription_throwsParseException() {
        // Empty description
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_DESC + EMPTY_DESC));
        assertParseFailure(parser, PREFIX_DESC + EMPTY_DESC, Description.MESSAGE_CONSTRAINTS);

        // Blank description
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_DESC + BLANK_DESC));
        assertParseFailure(parser, PREFIX_DESC + BLANK_DESC, Description.MESSAGE_CONSTRAINTS);
    }
}
