package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAssignmentDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.DeadlineContainsKeywordsPredicate;
import seedu.address.model.assignment.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.assignment.NameContainsKeywordsPredicate;
import seedu.address.model.assignment.PriorityContainsKeywordsPredicate;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.AssignmentUtil;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

public class ProductiveNusParserTest {

    private final ProductiveNusParser parser = new ProductiveNusParser();

    @Test
    public void parseCommand_add() throws Exception {
        Assignment assignment = new AssignmentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(AssignmentUtil.getAddCommand(assignment));
        assertEquals(new AddCommand(assignment), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ASSIGNMENT.getOneBased());
        List<Index> assignmentIndexToDelete = new ArrayList<>();
        assignmentIndexToDelete.add(INDEX_FIRST_ASSIGNMENT);
        assertEquals(new DeleteCommand(assignmentIndexToDelete), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Assignment assignment = new AssignmentBuilder().build();
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(assignment).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ASSIGNMENT.getOneBased() + " "
                + AssignmentUtil.getEditAssignmentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ASSIGNMENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> nameKeywords = Arrays.asList("Assignment", "homework", "lab");
        List<String> moduleCodeKeywords = Arrays.asList("CS2100", "CS2103T", "MA1101R");
        List<String> deadlineKeywords = Arrays.asList("23-10-2020", "1200", "10-11-2021");
        List<String> priorityKeywords = Arrays.asList("HIGH", "LOW");

        FindCommand findByNames = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + nameKeywords.stream()
                        .collect(Collectors.joining(" ")));

        FindCommand findByModuleCodes = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_MODULE_CODE + moduleCodeKeywords.stream()
                        .collect(Collectors.joining(" ")));

        FindCommand findByDeadlines = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_DEADLINE + deadlineKeywords.stream()
                        .collect(Collectors.joining(" ")));

        FindCommand findByPriorities = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_PRIORITY + priorityKeywords.stream()
                        .collect(Collectors.joining(" ")));

        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(nameKeywords)), findByNames);
        assertEquals(new FindCommand(new ModuleCodeContainsKeywordsPredicate(moduleCodeKeywords)), findByModuleCodes);
        assertEquals(new FindCommand(new DeadlineContainsKeywordsPredicate(deadlineKeywords)), findByDeadlines);
        assertEquals(new FindCommand(new PriorityContainsKeywordsPredicate(priorityKeywords)), findByPriorities);
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
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_HELP), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_HELP);
        assertThrows(ParseException.class,
                expectedMessage, () -> parser.parseCommand("unknownCommand"));
    }
}
