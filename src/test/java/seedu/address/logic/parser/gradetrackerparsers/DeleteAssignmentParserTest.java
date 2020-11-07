package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradetrackercommands.DeleteAssignmentCommand;
import seedu.address.model.module.ModuleName;

import java.util.NoSuchElementException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

public class DeleteAssignmentParserTest {
    private static final Index INDEX = Index.fromOneBased(1);
    private static final String VALID_INPUT = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;

    private static final String MULTIPLE_MODULE_NAMES = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2030;

    private static final String INVALID_MODULE_NAME = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + INVALID_MODULENAME_CSA200;

    private static final String INVALID_INDEX = " a "
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;

    private static final String MISSING_MODULE_NAME = " " + INDEX.getOneBased();

    private static final String MISSING_INDEX = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;

    private DeleteAssignmentParser parser = new DeleteAssignmentParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        assertParseSuccess(parser, VALID_INPUT, new DeleteAssignmentCommand(INDEX, expectedModuleName));
    }

    @Test
    public void parse_preambleWhiteSpace_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_INPUT,
                new DeleteAssignmentCommand(INDEX, expectedModuleName));
    }

    @Test
    public void parse_multipleModuleNames_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2030);
        assertParseSuccess(parser, MULTIPLE_MODULE_NAMES, new DeleteAssignmentCommand(INDEX, expectedModuleName));
    }

    @Test
    public void parse_invalidModuleNames_failure() {
        assertParseFailure(parser, INVALID_MODULE_NAME, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, INVALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryModuleNameFieldMissing_throwsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> parser.parse(MISSING_MODULE_NAME));
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, MISSING_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAssignmentCommand.MESSAGE_USAGE));
    }
}
