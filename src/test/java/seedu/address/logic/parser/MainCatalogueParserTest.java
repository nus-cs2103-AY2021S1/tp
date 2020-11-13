package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.TaskCommandTestUtil.PLAN_MEETING;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_TIME_RANGE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_TASK_PROGRESS_HALF;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_A_DEADLINE;
import static seedu.address.testutil.TypicalTasks.TASK_A_NAME;
import static seedu.address.testutil.TypicalTasks.TASK_A_PROGRESS;
import static seedu.address.testutil.TypicalTasks.TASK_B_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.global.AddCommand;
import seedu.address.logic.commands.global.AddPersonCommand;
import seedu.address.logic.commands.global.ClearCommand;
import seedu.address.logic.commands.global.DeleteCommand;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.logic.commands.global.ExitCommand;
import seedu.address.logic.commands.global.FindCommand;
import seedu.address.logic.commands.global.HelpCommand;
import seedu.address.logic.commands.global.LeaveCommand;
import seedu.address.logic.commands.global.ListPersonsCommand;
import seedu.address.logic.commands.global.ListProjectsCommand;
import seedu.address.logic.commands.global.StartProjectCommand;
import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.logic.commands.project.AddTeammateParticipationCommand;
import seedu.address.logic.commands.project.AllTasksCommand;
import seedu.address.logic.commands.project.AssignCommand;
import seedu.address.logic.commands.project.DeletePersonCommand;
import seedu.address.logic.commands.project.DeleteTaskCommand;
import seedu.address.logic.commands.project.DeleteTeammateParticipationCommand;
import seedu.address.logic.commands.project.EditTaskCommand;
import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.logic.commands.project.TaskSorterCommand;
import seedu.address.logic.commands.project.ViewTaskCommand;
import seedu.address.logic.commands.project.ViewTeammateCommand;
import seedu.address.logic.parser.exceptions.InvalidScopeException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Status;
import seedu.address.model.person.Person;
import seedu.address.model.project.NameContainsKeywordsPredicate;
import seedu.address.model.project.Project;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.EditProjectDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ProjectUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class MainCatalogueParserTest {

    private final MainCatalogueParser parser = new MainCatalogueParser();

    @Test
    public void parseCommand_add() throws Exception {
        Project project = new ProjectBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ProjectUtil.getAddCommand(project), Status.PROJECT_LIST);
        assertEquals(new AddCommand(project), command);
    }

    @Test
    public void parseCommand_addTeammate() throws Exception {
        Person teammate = new PersonBuilder().build();
        AddPersonCommand command =
            (AddPersonCommand) parser.parseCommand(PersonUtil.getAddTeammateCommand(teammate), Status.PROJECT);
        assertEquals(new AddPersonCommand(teammate), command);
    }

    @Test
    public void parseCommand_addPart() throws Exception {
        GitUserIndex gitUserIndex = new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A);
        AddTeammateParticipationCommand command =
            (AddTeammateParticipationCommand) parser.parseCommand(AddTeammateParticipationCommand.COMMAND_WORD
                + " " + VALID_TEAMMATE_GIT_USERNAME_A, Status.PROJECT);
        assertEquals(new AddTeammateParticipationCommand(gitUserIndex), command);
    }

    @Test
    public void parseCommand_deleteTeammate() throws Exception {
        GitUserIndex gitUserIndex = new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A);
        DeletePersonCommand command =
            (DeletePersonCommand) parser.parseCommand(DeletePersonCommand.COMMAND_WORD + " "
                + VALID_TEAMMATE_GIT_USERNAME_A, Status.PERSON_LIST);
        assertEquals(new DeletePersonCommand(gitUserIndex), command);
    }

    @Test
    public void parseCommand_deleteTeammateParticipation() throws Exception {
        GitUserIndex gitUserIndex = new GitUserIndex(VALID_TEAMMATE_GIT_USERNAME_A);
        DeleteTeammateParticipationCommand command =
            (DeleteTeammateParticipationCommand) parser.parseCommand(DeleteTeammateParticipationCommand
                .COMMAND_WORD + " " + VALID_TEAMMATE_GIT_USERNAME_A, Status.PROJECT);
        assertEquals(new DeleteTeammateParticipationCommand(gitUserIndex), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, Status.PROJECT_LIST) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PROJECT.getOneBased(), Status.PROJECT_LIST);
        assertEquals(new DeleteCommand(INDEX_FIRST_PROJECT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Project project = new ProjectBuilder().build();
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(project).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PROJECT.getOneBased() + " " + ProjectUtil.getEditProjectDescriptorDetails(descriptor),
                Status.PROJECT_LIST);
        assertEquals(new EditCommand(INDEX_FIRST_PROJECT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, Status.PROJECT_LIST) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, Status.PROJECT) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
            Status.PROJECT_LIST);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, Status.PROJECT_LIST) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, Status.PROJECT) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listProjects() throws Exception {
        assertTrue(parser.parseCommand(ListProjectsCommand.COMMAND_WORD, Status.PROJECT_LIST)
            instanceof ListProjectsCommand);
    }

    @Test
    public void parseCommand_listPersons() throws Exception {
        assertTrue(parser.parseCommand(ListPersonsCommand.COMMAND_WORD, Status.PERSON_LIST)
            instanceof ListPersonsCommand);
    }

    @Test
    public void parseCommand_start() throws Exception {
        StartProjectCommand command = (StartProjectCommand) parser.parseCommand(
            StartProjectCommand.COMMAND_WORD + " " + INDEX_FIRST_PROJECT.getOneBased(), Status.PROJECT_LIST);
        assertEquals(new StartProjectCommand(INDEX_FIRST_PROJECT), command);
    }

    @Test
    public void parseCommand_leave() throws Exception {
        assertTrue(parser.parseCommand(LeaveCommand.COMMAND_WORD,
            Status.PROJECT) instanceof LeaveCommand);
    }

    @Test
    public void parseCommand_assign() throws Exception {
        AssignCommand command = (AssignCommand) parser.parseCommand(
            AssignCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " "
                + ALICE.getGitUserName(), Status.PROJECT);
        assertEquals(new AssignCommand(INDEX_FIRST_TASK, ALICE.getGitUserNameString()), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                AddTaskCommand.COMMAND_WORD + " " + TASK_A_NAME + TASK_A_PROGRESS
                        + TASK_A_DEADLINE, Status.PROJECT);
        assertEquals(new AddTaskCommand(new TaskBuilder(TASK_A).withTaskDescription(null).build()), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withTaskName(SampleDataUtil.getTask1().get(0)).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(
                EditTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + TASK_B_NAME, Status.PROJECT);
        assertEquals(new EditTaskCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
            DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased(), Status.PROJECT);
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_allTasks() throws Exception {
        assertTrue(parser.parseCommand(AllTasksCommand.COMMAND_WORD,
            Status.PROJECT) instanceof AllTasksCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        // TaskFilterCommand does not have equal method
        // as one cannot compare two predicates unless they are the same object.
        assertTrue(parser.parseCommand(TaskFilterCommand.COMMAND_WORD + " "
            + PREFIX_TASK_ASSIGNEE + ALICE.getGitUserName(), Status.PROJECT) instanceof TaskFilterCommand);
        assertTrue(parser.parseCommand(TaskFilterCommand.COMMAND_WORD + TASK_TIME_RANGE_DESC,
            Status.PROJECT) instanceof TaskFilterCommand);
    }

    @Test
    public void parseCommand_sorter() throws Exception {
        // TaskSorterCommand does not have equal method
        // as one cannot compare two comparators unless they are the same object.
        assertTrue(parser.parseCommand(TaskSorterCommand.COMMAND_WORD + " "
            + PREFIX_ASCENDING_SORT + " " + PREFIX_TASK_NAME, Status.PROJECT) instanceof TaskSorterCommand);
    }

    @Test
    public void parseCommand_viewTask() throws Exception {
        ViewTaskCommand command = (ViewTaskCommand) parser.parseCommand(
            ViewTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased(), Status.PROJECT
        );
        assertEquals(new ViewTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_viewTeammate() throws Exception {
        ViewTeammateCommand command = (ViewTeammateCommand) parser.parseCommand(
            ViewTeammateCommand.COMMAND_WORD + " " + VALID_TEAMMATE_GIT_USERNAME_A, Status.PROJECT);
        assertEquals(new ViewTeammateCommand(GIT_USERINDEX_FIRST_TEAMMATE), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", Status.PROJECT_LIST));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand",
            Status.PROJECT_LIST));
    }

    @Test
    public void parseCommand_invalidScope_throwsInvalidScopeException() {

        try {
            parser.parseCommand(
                AssignCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " "
                    + ALICE.getPersonName(), Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased(), Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(
                TaskFilterCommand.COMMAND_WORD + " " + PREFIX_TASK_ASSIGNEE + ALICE.getPersonName(),
                Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(
                TaskSorterCommand.COMMAND_WORD + " " + PREFIX_ASCENDING_SORT + " " + PREFIX_TASK_NAME,
                Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(AddTaskCommand.COMMAND_WORD + " "
                + TaskUtil.getTaskCommand(PLAN_MEETING), Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(EditTaskCommand.COMMAND_WORD + " " + PREFIX_TASK_PROGRESS
                + " " + VALID_TASK_PROGRESS_HALF, Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(ViewTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased(),
                Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }

        try {
            parser.parseCommand(ViewTeammateCommand.COMMAND_WORD + " " + VALID_TEAMMATE_GIT_USERNAME_A,
                Status.PROJECT_LIST);
            fail();
        } catch (Exception e) {
            assertEquals(new InvalidScopeException(Status.PROJECT, Status.PROJECT_LIST), e);
        }
    }
}
