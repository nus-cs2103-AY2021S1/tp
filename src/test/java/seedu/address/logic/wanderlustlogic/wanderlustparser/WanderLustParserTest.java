package seedu.address.logic.wanderlustlogic.wanderlustparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST_TRAVELPLAN;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.wanderlustlogic.wanderlustcommands.ClearCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.ExitCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.FindCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.HelpCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.ListCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.add.AddActivityCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.delete.DeleteActivityCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.delete.DeleteCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditActivityCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditDescriptor;
import seedu.address.logic.wanderlustlogic.wanderlustparser.exceptions.ParseException;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.NameContainsKeywordsPredicate;
import seedu.address.testutil.ActivityUtil;
import seedu.address.testutil.builders.ActivityBuilder;


public class WanderLustParserTest {

    private final WanderlustParser parser = new WanderlustParser();

    @Test
    public void parseCommand_add() throws Exception {
        Activity activity = new ActivityBuilder().build();
        AddActivityCommand command = (AddActivityCommand) parser.parseCommand(ActivityUtil.getAddCommand(activity));
        Assertions.assertTrue(new AddActivityCommand(activity).equals(command));
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteActivityCommand command = (DeleteActivityCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " -activity " + INDEX_FIRST_TRAVELPLAN.getOneBased());
        assertEquals(new DeleteActivityCommand(INDEX_FIRST_TRAVELPLAN), command);
    }

    //checks if commands are equal
    @Test
    public void parseCommand_edit() throws Exception {
        EditDescriptor descriptor = new EditDescriptor();
        descriptor.setName(new Name("Change name"));
        EditActivityCommand command = (EditActivityCommand) parser.parseCommand(EditCommand.COMMAND_WORD
                + " -activity " + INDEX_FIRST_TRAVELPLAN.getOneBased() + " "
                + ActivityUtil.getNewEditActivityDescriptorDetails(descriptor));
        assertEquals(new EditActivityCommand(INDEX_FIRST_TRAVELPLAN, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz"); //find activity
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " -" + Activity.TPO_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords), 0), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
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
