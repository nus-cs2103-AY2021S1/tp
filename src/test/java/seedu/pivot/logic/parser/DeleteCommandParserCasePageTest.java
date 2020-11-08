package seedu.pivot.logic.parser;

import static seedu.pivot.logic.commands.Command.TYPE_DESC;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.descriptioncommands.DeleteDescriptionCommand;
import seedu.pivot.logic.commands.documentcommands.DeleteDocumentCommand;
import seedu.pivot.logic.commands.suspectcommands.DeleteSuspectCommand;
import seedu.pivot.logic.commands.victimcommands.DeleteVictimCommand;
import seedu.pivot.logic.commands.witnesscommands.DeleteWitnessCommand;
import seedu.pivot.logic.state.StateManager;

public class DeleteCommandParserCasePageTest {

    public static final String VALID_INDEX = " " + "1";
    public static final String TYPE_DOC = "doc";
    public static final String TYPE_SUSPECT = "suspect";
    public static final String TYPE_WITNESS = "witness";
    public static final String TYPE_VICTIM = "victim";

    private static final Index caseIndex = Index.fromZeroBased(FIRST_INDEX.getZeroBased());
    private static final Index miscTypeIndex = Index.fromZeroBased(FIRST_INDEX.getZeroBased());

    private DeleteCommandParser parser = new DeleteCommandParser();

    @BeforeEach
    public void setStateZero() {
        StateManager.setState(caseIndex);
    }

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    @Test
    public void parse_validArgs_returnsDeleteDocumentCommand() {
        assertParseSuccess(parser, TYPE_DOC + VALID_INDEX, new DeleteDocumentCommand(caseIndex, miscTypeIndex));
    }

    @Test
    public void parse_validArgs_returnsDeleteSuspectCommand() {
        assertParseSuccess(parser, TYPE_SUSPECT + VALID_INDEX, new DeleteSuspectCommand(caseIndex, miscTypeIndex));
    }

    @Test
    public void parse_validArgs_returnsDeleteVictimCommand() {
        assertParseSuccess(parser, TYPE_VICTIM + VALID_INDEX, new DeleteVictimCommand(caseIndex, miscTypeIndex));
    }

    @Test
    public void parse_validArgs_returnsDeleteWitnessCommand() {
        assertParseSuccess(parser, TYPE_WITNESS + VALID_INDEX, new DeleteWitnessCommand(caseIndex, miscTypeIndex));
    }

    @Test
    public void parse_validArgs_returnsDeleteDescriptionCommand() {
        assertParseSuccess(parser, TYPE_DESC, new DeleteDescriptionCommand(caseIndex));
    }


}
