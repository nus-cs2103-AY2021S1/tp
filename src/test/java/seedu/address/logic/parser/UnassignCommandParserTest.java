package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalModules.CS2103;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnassignCommand;
import seedu.address.model.module.ModuleCode;

public class UnassignCommandParserTest {

    private UnassignCommandParser parser = new UnassignCommandParser();

    @Test
    public void parse_validArgs_returnsUnassignCommand() {
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());

        assertParseSuccess(parser, "1 " + VALID_MODULE_CODE_DESC,
            new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a " + VALID_MODULE_CODE_DESC,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 " + INVALID_MODULE_CODE_DESC,
            ModuleCode.MESSAGE_CONSTRAINTS);
    }

}
