package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TypicalPolicies;

public class ClientListParserTest {

    private final ClientListParser parser = new ClientListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().withoutPolicy().build();
        //Note: the policy field is not added until model verifies that the policy is in the policy list
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person, new PolicyName(LIFE_TIME_NAME)), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " "
                + CliSyntax.PREFIX_ARCHIVE) instanceof ListCommand);

        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " "
                + CliSyntax.PREFIX_ARCHIVE + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_archive() throws Exception {
        ArchiveCommand command = (ArchiveCommand) parser.parseCommand(
                ArchiveCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ArchiveCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_unarchive() throws Exception {
        UnarchiveCommand command = (UnarchiveCommand) parser.parseCommand(
                UnarchiveCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new UnarchiveCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_addPolicy() throws Exception {
        AddPolicyCommand command = (AddPolicyCommand) parser.parseCommand(
                AddPolicyCommand.COMMAND_WORD + " "
                        + PREFIX_POLICY_NAME + " " + LIFE_TIME_NAME + " "
                        + PREFIX_POLICY_DESCRIPTION + " " + LIFE_TIME_DESCRIPTION
        );
        assertEquals(new AddPolicyCommand(
                new Policy(
                        new PolicyName(LIFE_TIME_NAME),
                        new PolicyDescription(LIFE_TIME_DESCRIPTION)))
                , command);
    }

    @Test
    public void parseCommand_clearPolicy() throws Exception {
        assertTrue(parser.parseCommand(ClearPolicyCommand.COMMAND_WORD) instanceof ClearPolicyCommand);
        assertEquals(parser.parseCommand(ClearPolicyCommand.COMMAND_WORD), new ClearPolicyCommand());
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
