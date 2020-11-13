package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteVisitCommand;

class DeleteVisitCommandParserTest {

    private DeleteVisitCommandParser parser = new DeleteVisitCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteVisitCommand() {
        // Valid visit index
        String userinput1 = INDEX_FIRST_PATIENT.getOneBased() + " " + PREFIX_VISIT_INDEX + "1";
        assertParseSuccess(parser, userinput1 , new DeleteVisitCommand(INDEX_FIRST_PATIENT, 1));

        // No visit index given
        String userinput2 = INDEX_FIRST_PATIENT.getOneBased() + " " + PREFIX_VISIT_INDEX;
        assertParseSuccess(parser, userinput2 , new DeleteVisitCommand(INDEX_FIRST_PATIENT, -1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userinput3 = INDEX_FIRST_PATIENT.getOneBased() + " " + PREFIX_VISIT_INDEX + "aa";
        assertParseFailure(parser, userinput3 , String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteVisitCommand.MESSAGE_USAGE));
    }
}
