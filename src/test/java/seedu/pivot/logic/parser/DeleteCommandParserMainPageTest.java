package seedu.pivot.logic.parser;

import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.DeleteCaseCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserMainPageTest {

    public static final String VALID_INDEX = " " + "1";
    public static final String TYPE_CASE = "case";

    private static Index caseIndex = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCaseCommand() {
        assertParseSuccess(parser, TYPE_CASE + VALID_INDEX, new DeleteCaseCommand(caseIndex));
    }

}
