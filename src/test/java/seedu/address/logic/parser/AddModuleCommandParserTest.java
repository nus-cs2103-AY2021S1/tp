package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADD_MODULE_DESC_CS1000;
import static seedu.address.logic.commands.CommandTestUtil.ADD_MODULE_DESC_CS1001;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS1000;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandParserTest {
    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(CS1000).build();

        // multiple participants - all accepted
        Module expectedModuleMultipleParticipants = new ModuleBuilder(CS1000)
                .withMembers(new HashSet<>(Arrays.asList(AMY, BOB)))
                .build();
        assertParseSuccess(parser, ADD_MODULE_DESC_CS1000
                + PARTICIPANT_DESC_AMY
                + PARTICIPANT_DESC_BOB, new AddModuleCommand(expectedModuleMultipleParticipants));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PARTICIPANT_DESC_AMY,
                expectedMessage);

        // missing participant prefix
        assertParseFailure(parser, ADD_MODULE_DESC_CS1000,
                expectedMessage);

        // multiple modules
        assertParseFailure(parser, ADD_MODULE_DESC_CS1000
                + ADD_MODULE_DESC_CS1001
                + PARTICIPANT_DESC_AMY, String.format(MESSAGE_DUPLICATE_PREFIX, "n/"));
    }
}
