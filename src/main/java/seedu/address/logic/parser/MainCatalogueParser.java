package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.global.AddCommand;
import seedu.address.logic.commands.global.ClearCommand;
import seedu.address.logic.commands.global.DeleteCommand;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.global.ExitCommand;
import seedu.address.logic.commands.global.FindCommand;
import seedu.address.logic.commands.global.HelpCommand;
import seedu.address.logic.commands.global.LeaveCommand;
import seedu.address.logic.commands.global.ListPersonsCommand;
import seedu.address.logic.commands.global.ListProjectsCommand;
import seedu.address.logic.commands.global.StartPersonCommand;
import seedu.address.logic.commands.global.StartProjectCommand;
import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.logic.commands.project.AddTeammateCommand;
import seedu.address.logic.commands.project.AddTeammateParticipationCommand;
import seedu.address.logic.commands.project.AllTasksCommand;
import seedu.address.logic.commands.project.AssignCommand;
import seedu.address.logic.commands.project.DeleteTaskCommand;
import seedu.address.logic.commands.project.DeleteTeammateCommand;
import seedu.address.logic.commands.project.DeleteTeammateParticipationCommand;
import seedu.address.logic.commands.project.EditTaskCommand;
import seedu.address.logic.commands.project.EditTeammateCommand;
import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.logic.commands.project.TaskSorterCommand;
import seedu.address.logic.commands.project.ViewTaskCommand;
import seedu.address.logic.commands.project.ViewTeammateCommand;
import seedu.address.logic.parser.exceptions.InvalidScopeException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Status;

/**
 * Parses user input.
 */
public class MainCatalogueParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param status    the status of the current scoping
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Status status) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            switch (status) {
            case PERSON_LIST:
            case PERSON:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            default:
                return new AddCommandParser().parse(arguments);
            }

        case EditCommand.COMMAND_WORD:
            switch (status) {
            case PERSON_LIST:
            case PERSON:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            default:
                return new EditCommandParser().parse(arguments);
            }

        case DeleteCommand.COMMAND_WORD:
            switch (status) {
            case PROJECT_LIST:
                return new DeleteCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            }

        case ClearCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(ClearCommand.MESSAGE_EXTRA_ARGS);
            }
            switch (status) {
            case PROJECT_LIST:
                return new ClearCommand();
            default:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            }

        case FindCommand.COMMAND_WORD:
            switch (status) {
            case PERSON_LIST:
            case PERSON:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            default:
                return new FindCommandParser().parse(arguments);
            }

        case ListProjectsCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(ClearCommand.MESSAGE_EXTRA_ARGS);
            }
            switch (status) {
            case PERSON:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            default:
                return new ListProjectsCommand();
            }

        case ListPersonsCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(ClearCommand.MESSAGE_EXTRA_ARGS);
            }
            switch (status) {
            case PROJECT:
            case TASK:
            case TEAMMATE:
                throw new InvalidScopeException(Status.PERSON_LIST, status);
            default:
                return new ListPersonsCommand();
            }

        case ExitCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(ExitCommand.MESSAGE_EXTRA_ARGS);
            } else {
                return new ExitCommand();
            }

        case HelpCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(HelpCommand.MESSAGE_EXTRA_ARGS);
            } else {
                return new HelpCommand();
            }

        case StartProjectCommand.COMMAND_WORD:
            switch (status) {
            case PROJECT_LIST:
            case PROJECT:
                return new StartProjectCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PROJECT_LIST, status);
            }

        case StartPersonCommand.COMMAND_WORD:
            switch (status) {
            case PERSON_LIST:
            case PERSON:
                return new StartPersonCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PERSON_LIST, status);
            }

        case LeaveCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(LeaveCommand.MESSAGE_EXTRA_ARGS);
            } else {
                return new LeaveCommand();
            }

        case AssignCommand.COMMAND_WORD:
            switch (status) {
            case PROJECT:
            case TEAMMATE:
            case TASK:
                return new AssignCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case AllTasksCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(ClearCommand.MESSAGE_EXTRA_ARGS);
            }
            switch (status) {
            case PROJECT:
            case TASK:
            case TEAMMATE:
                return new AllTasksCommand();
            default:
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case TaskFilterCommand.COMMAND_WORD:
            switch (status) {
            case PROJECT:
            case TASK:
            case TEAMMATE:
                return new TaskFilterCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case TaskSorterCommand.COMMAND_WORD:
            switch (status) {
                case PROJECT:
                case TASK:
                case TEAMMATE:
                    return new TaskSorterCommandParser().parse(arguments);
                default:
                    throw new InvalidScopeException(Status.PROJECT, status);
            }

        case AddTeammateCommand.COMMAND_WORD:
            switch (status) {
            case PROJECT:
            case TASK:
            case TEAMMATE:
                return new AddTeammateCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case AddTeammateParticipationCommand.COMMAND_WORD:
            switch (status) {
            case PROJECT:
            case TASK:
            case TEAMMATE:
                return new AddTeammateParticipationCommandParser().parse(arguments);
            default:
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case DeleteTeammateParticipationCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new DeleteTeammateParticipationCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case AddTaskCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new AddTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }
        case DeleteTaskCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new DeleteTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }
        case DeleteTeammateCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new DeleteTeammateCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case EditTaskCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new EditTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case EditTeammateCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new EditTeammateCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case ViewTaskCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new ViewTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case ViewTeammateCommand.COMMAND_WORD:
            if (status != Status.PROJECT_LIST) {
                return new ViewTeammateCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
