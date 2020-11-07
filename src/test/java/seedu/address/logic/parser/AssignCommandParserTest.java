package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MODULE_CODE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS1010S;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS50;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS50;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalModuleCodes.CS1010S_CODE;
import static seedu.address.testutil.TypicalModuleCodes.CS2103_CODE;
import static seedu.address.testutil.TypicalModuleCodes.CS50_CODE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.model.module.ModuleCode;

class AssignCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE);

    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CODE_DESC_CS50, MESSAGE_INVALID_FORMAT);

        // no module code specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no module code specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CODE_DESC_CS50, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CODE_DESC_CS50, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidModuleCode_failure() {
        // single invalid module code
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid module followed by valid module code
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC
                + CODE_DESC_CS50, ModuleCode.MESSAGE_CONSTRAINTS);

        // valid module followed by invalid module code
        assertParseFailure(parser, "1" + CODE_DESC_CS50
                + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid module code followed by valid module code
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC
                + CODE_DESC_CS50, ModuleCode.MESSAGE_CONSTRAINTS);

        // multiple invalid module codes
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC
                + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicateModuleCode_failure() {
        // similar module codes
        assertParseFailure(parser, "1" + CODE_DESC_CS50 + CODE_DESC_CS50,
                String.format(MESSAGE_DUPLICATE_MODULE_CODE, VALID_MODULE_CODE_CS50));

        // similar module codes followed by unique module code
        assertParseFailure(parser, "1" + CODE_DESC_CS50 + CODE_DESC_CS50 + CODE_DESC_CS1010S,
                String.format(MESSAGE_DUPLICATE_MODULE_CODE, VALID_MODULE_CODE_CS50));

        // unique module code followed by similar module codes
        assertParseFailure(parser, "1" + CODE_DESC_CS1010S + CODE_DESC_CS50 + CODE_DESC_CS50,
                String.format(MESSAGE_DUPLICATE_MODULE_CODE, VALID_MODULE_CODE_CS50));

        // two pairs of similar module codes -> detect first duplicate module code
        assertParseFailure(parser, "1" + CODE_DESC_CS50 + CODE_DESC_CS1010S
                + CODE_DESC_CS50 + CODE_DESC_CS1010S, String.format(MESSAGE_DUPLICATE_MODULE_CODE,
                VALID_MODULE_CODE_CS50));
    }

    @Test
    public void parse_allPartsPresent_success() {
        // single module code
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CODE_DESC_CS50;
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS50_CODE);
        AssignCommand expectedCommand = new AssignCommand(targetIndex, moduleCodes);
        assertParseSuccess(parser, userInput, expectedCommand);

        // two module codes
        userInput = targetIndex.getOneBased() + CODE_DESC_CS50 + CODE_DESC_CS1010S;
        moduleCodes.add(CS1010S_CODE);
        expectedCommand = new AssignCommand(targetIndex, moduleCodes);
        assertParseSuccess(parser, userInput, expectedCommand);

        // three module codes
        userInput = targetIndex.getOneBased() + CODE_DESC_CS50 + CODE_DESC_CS1010S + CODE_DESC_CS2103;
        moduleCodes.add(CS2103_CODE);
        expectedCommand = new AssignCommand(targetIndex, moduleCodes);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
