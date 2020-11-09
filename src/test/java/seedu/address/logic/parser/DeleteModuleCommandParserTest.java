package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleBuilder;

public class DeleteModuleCommandParserTest {
    private DeleteModuleCommandParser parser = new DeleteModuleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMeetingCommand() {
        Set<Person> expectedMembers = new HashSet<>();
        expectedMembers.add(BOB);
        expectedMembers.add(ALICE);
        Module expectedModule = new ModuleBuilder().withName("CS2103").withMembers(expectedMembers).build();

        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(expectedModule.getModuleName());

        // module
        assertParseSuccess(parser, " m/CS2103", deleteModuleCommand);

        // multiple white spaces
        assertParseSuccess(parser, "     m/CS2103", deleteModuleCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no space in front
        assertParseFailure(parser, "/m CS2103",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // no space after prefix
        assertParseFailure(parser, " /mCS2103",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // no input
        assertParseFailure(parser, " /m",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // wrong prefixes
        assertParseFailure(parser, " /mo CS2103",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
    }
}
