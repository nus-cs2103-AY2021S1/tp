package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.DeleteAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.gradetracker.AssignmentBuilder;
import seedu.address.testutil.gradetracker.AssignmentUtil;
import seedu.address.testutil.gradetracker.EditAssignmentDescriptorBuilder;

public class GradeTrackerParserTest {

    private final GradeTrackerParser parser = new GradeTrackerParser();


    @Test
    public void parseCommand_add() throws Exception {
        Assignment assignment = new AssignmentBuilder().build();
        Command command = parser.parseCommand(AssignmentUtil.getAddAssignmentCommand(
                new ModuleName(VALID_MODULENAME_CS2103T), assignment));
        assertEquals(new AddAssignmentCommand(new ModuleName(VALID_MODULENAME_CS2103T), assignment), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Command command = parser.parseCommand(AssignmentUtil.getDeleteAssignmentCommand(Index.fromOneBased(1),
                        new ModuleName(VALID_MODULENAME_CS2103T)));
        assertEquals(new DeleteAssignmentCommand(Index.fromOneBased(1), new ModuleName(VALID_MODULENAME_CS2103T))
                , command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Assignment assignment = new AssignmentBuilder().build();
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(assignment).build();
        Command command = parser.parseCommand(AssignmentUtil.getEditAssignmentCommand(Index.fromOneBased(1),
                new ModuleName(VALID_MODULENAME_CS2103T), assignment));
        assertEquals(new EditAssignmentCommand(Index.fromOneBased(1), new ModuleName(VALID_MODULENAME_CS2103T),
                descriptor), command);
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
