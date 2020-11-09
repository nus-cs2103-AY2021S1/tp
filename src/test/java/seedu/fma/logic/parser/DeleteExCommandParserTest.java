package seedu.fma.logic.parser;

import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_LOG;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.logic.commands.DeleteExCommand;
import seedu.fma.model.LogBook;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteExCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteExCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteExCommandParserTest {
    private DeleteExCommandParser parser = new DeleteExCommandParser();
    private final LogBook logBook = new LogBook();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteExCommand(INDEX_FIRST_LOG), logBook);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX, logBook);
    }
}
