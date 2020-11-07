package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS1010S;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS50;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DELMOD_MISSING_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1010S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS50;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DelModCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.builders.ModuleCodeBuilder;

class DelModCommandParserTest {
    private DelModCommandParser parser = new DelModCommandParser();

    @Test
    void parse_invalidModuleCode_failure() {
        //" m/CS*#1"
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_missingModuleCode_failure() {
        //" m/"
        assertParseFailure(parser, INVALID_DELMOD_MISSING_CODE,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_missingPrefix_failure() {
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelModCommand.MESSAGE_USAGE);
        //"CS2103"
        assertParseFailure(parser, VALID_MODULE_CODE_CS1010S, expectedErrorMessage);
    }

    @Test
    void parse_duplicatePrefix_failure() {
        String expectedErrorMessage = String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_MODULE_CODE);
        //"m/CS50 m/CS1101S"
        assertParseFailure(parser, CODE_DESC_CS50 + CODE_DESC_CS1010S, expectedErrorMessage);
    }

    @Test
    void parse_validArgument_success() {
        ModuleCode moduleCode = new ModuleCodeBuilder().withCode(VALID_MODULE_CODE_CS50).build();
        DelModCommand expectedDelmodCommand = new DelModCommand(moduleCode);
        //" m/CS50"
        assertParseSuccess(parser, CODE_DESC_CS50, expectedDelmodCommand);
    }
}
