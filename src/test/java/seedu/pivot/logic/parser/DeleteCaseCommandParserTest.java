package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.casecommands.DeleteCaseCommand;
import seedu.pivot.logic.commands.documentcommands.DeleteDocumentCommand;
import seedu.pivot.logic.state.StateManager;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCaseCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCaseCommand() {
        assertParseSuccess(parser, "case 1", new DeleteCaseCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgsMainPage_throwsParseException() {
        assertParseFailure(parser, "case a",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));
    }

}
