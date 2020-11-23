package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.MODULE_CODE_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.MODULE_DELETE_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.MODULE_NAME_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_CODE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_NAME_1;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.ModuleCommand;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;

class ModuleCommandParserTest {
    private static final String MESSAGE_INVALID_SWITCH_TAB_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ModuleCommand.MESSAGE_SWITCH_TAB_USAGE);
    private static final String MESSAGE_INVALID_MODULE_COMMAND_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_INDEX = MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
    private final ModuleCommandParser moduleCommandParser = new ModuleCommandParser();
    private final ModuleCommand moduleCommand = new ModuleCommand();

    @Test
    void parse_switchModuleTab_success() {
        assertParseSuccess(moduleCommandParser, "", moduleCommand);
    }

    @Test
    void parse_switchModuleTab_failure() {
        assertParseFailure(moduleCommandParser, "aaa", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
        assertParseFailure(moduleCommandParser, "aaa bbb", MESSAGE_INVALID_SWITCH_TAB_FORMAT);
    }

    @Test
    void parse_addModuleFieldsMissing_failure() {
        String userInput1 = MODULE_NAME_DESC_1;
        String userInput2 = MODULE_CODE_DESC_1;
        String userInput3 = MODULE_NAME_DESC_1 + INVALID_MODULE_CODE_DESC;
        String userInput4 = INVALID_MODULE_NAME_DESC + MODULE_CODE_DESC_1;


        // name given but no code
        assertParseFailure(moduleCommandParser, userInput1, MESSAGE_INVALID_MODULE_COMMAND_FORMAT);

        // code given but no name
        assertParseFailure(moduleCommandParser, userInput2, MESSAGE_INVALID_MODULE_COMMAND_FORMAT);

        // invalid code field
        assertParseFailure(moduleCommandParser, userInput3, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid name field
        assertParseFailure(moduleCommandParser, userInput4, Module.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_tooManyFields_failure() {
        // preamble + add module
        assertParseFailure(moduleCommandParser,
                "preamble" + MODULE_NAME_DESC_1 + MODULE_CODE_DESC_1,
                MESSAGE_INVALID_MODULE_COMMAND_FORMAT);

        // preamble + delete
        assertParseFailure(moduleCommandParser,
                "preamble" + MODULE_DELETE_DESC,
                MESSAGE_INVALID_MODULE_COMMAND_FORMAT);

        // name, code and delete at the same time
        assertParseFailure(moduleCommandParser, MODULE_NAME_DESC_1 + MODULE_CODE_DESC_1
                + MODULE_DELETE_DESC, MESSAGE_INVALID_MODULE_COMMAND_FORMAT);
    }

    @Test
    void parse_allFieldsPresent_success() { //  n/NAME m/CODE
        String moduleName = VALID_MODULE_NAME_1;
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_1);
        Module module = new Module(moduleName, moduleCode);
        assertParseSuccess(moduleCommandParser,
                MODULE_NAME_DESC_1 + MODULE_CODE_DESC_1,
               new ModuleCommand(module));
    }

    @Test
    void parse_deleteModule_success() { // del/INDEX
        assertParseSuccess(moduleCommandParser, MODULE_DELETE_DESC, new ModuleCommand(Index.fromOneBased(1)));
    }

    @Test
    void parse_deleteModule_failure() {
        assertParseFailure(moduleCommandParser, " del/0", MESSAGE_INVALID_INDEX);
        assertParseFailure(moduleCommandParser, " del/-1", MESSAGE_INVALID_INDEX);
        assertParseFailure(moduleCommandParser, " del/invalid index", MESSAGE_INVALID_INDEX);
    }
}
