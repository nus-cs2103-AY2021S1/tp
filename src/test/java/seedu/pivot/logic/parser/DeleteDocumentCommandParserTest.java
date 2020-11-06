package seedu.pivot.logic.parser;

import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.documentcommands.DeleteDocumentCommand;
import seedu.pivot.logic.state.StateManager;

public class DeleteDocumentCommandParserTest {

    private static Index caseIndex = Index.fromZeroBased(FIRST_INDEX.getZeroBased());
    private static Index miscTypeIndex = Index.fromZeroBased(FIRST_INDEX.getZeroBased());

    private DeleteCommandParser parser = new DeleteCommandParser();

    @BeforeAll
    public static void setStateZero() {
        StateManager.setState(caseIndex);
    }

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    @Test
    public void parse_validArgs_returnsDeleteDocCommand() {
        assertParseSuccess(parser, "doc 1", new DeleteDocumentCommand(caseIndex, miscTypeIndex));
    }
}
