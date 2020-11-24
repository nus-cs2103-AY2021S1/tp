package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.commons.core.Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX;
import static atas.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static atas.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static atas.logic.commands.atas.HelpCommand.MESSAGE_USAGE;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import atas.logic.commands.Command;
import atas.logic.commands.atas.ExitCommand;
import atas.logic.commands.atas.HelpCommand;
import atas.logic.commands.atas.RedoCommand;
import atas.logic.commands.atas.RngCommand;
import atas.logic.commands.atas.SwitchCommand;
import atas.logic.commands.atas.UndoCommand;
import atas.logic.commands.confirmation.ConfirmationCommand;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.commands.memo.AddNoteCommand;
import atas.logic.commands.sessionlist.AddSessionCommand;
import atas.logic.commands.sessionlist.ClearSessionsCommand;
import atas.logic.commands.sessionlist.DeleteSessionCommand;
import atas.logic.commands.sessionlist.EditSessionCommand;
import atas.logic.commands.sessionlist.EnterSessionCommand;
import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.logic.commands.sessionlist.session.PresenceCommand;
import atas.logic.commands.studentlist.AddStudentCommand;
import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.logic.commands.studentlist.FindStudentsCommand;
import atas.logic.commands.studentlist.ListStudentsCommand;
import atas.logic.parser.exceptions.ParseException;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**
 * Parses user input.
 */
public class AtasParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /** The command to be executed after confirmation prompt*/
    private Optional<ConfirmationCommand> previousCommand = Optional.empty();

    @FXML
    private Tab inClassTab;

    /**
     * Removes the previous confirmation command.
     */
    private void removePreviousCommand() {
        this.previousCommand = Optional.empty();
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        assert userInput != null;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        assert matcher != null;

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments;

        // addnote command should except whitespaces as valid arguments
        if (commandWord.equals(AddNoteCommand.COMMAND_WORD)) {
            arguments = userInput.substring(AddNoteCommand.COMMAND_WORD.length());
        } else {
            arguments = matcher.group("arguments");
        }

        // If there is an edit, delete or clear, there should be a previous command.
        if (previousCommand.isPresent()) {
            Command result = new ConfirmationParser(previousCommand.get()).parse(userInput);
            // Remove previous command if the result is able to be executed.
            removePreviousCommand();
            return result;
        } else {
            return parseNonConfirmationCommand(commandWord, arguments);
        }
    }

    private Command parseNonConfirmationCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            //Sets the previous command to a confirmation edit student command.
            this.previousCommand =
                Optional.of(new ConfirmationCommand(new EditStudentCommandParser().parse(arguments)));
            return previousCommand.orElseThrow();

        case DeleteStudentCommand.COMMAND_WORD:
            //Sets the previous command to a confirmation delete student command.
            this.previousCommand =
                Optional.of(new ConfirmationCommand(new DeleteStudentCommandParser().parse(arguments)));
            return previousCommand.orElseThrow();

        case ClearStudentListCommand.COMMAND_WORD:
            //Sets the previous command to a confirmation clear students command.
            this.previousCommand =
                Optional.of(new ConfirmationCommand(new ClearStudentListCommand()));
            return previousCommand.orElseThrow();

        case FindStudentsCommand.COMMAND_WORD:
            return new FindStudentsCommandParser().parse(arguments);

        case ListStudentsCommand.COMMAND_WORD:
            return new ListStudentsCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SwitchCommand.COMMAND_WORD:
            return new SwitchCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case AddSessionCommand.COMMAND_WORD:
            return new AddSessionCommandParser().parse(arguments);

        case DeleteSessionCommand.COMMAND_WORD:
            this.previousCommand =
                Optional.of(new ConfirmationCommand(new DeleteSessionCommandParser().parse(arguments)));
            return previousCommand.orElseThrow();

        case EditSessionCommand.COMMAND_WORD:
            this.previousCommand =
                Optional.of(new ConfirmationCommand(new EditSessionCommandParser().parse(arguments)));
            return previousCommand.orElseThrow();

        case ClearSessionsCommand.COMMAND_WORD:
            this.previousCommand =
                Optional.of(new ConfirmationCommand(new ClearSessionsCommand()));
            return previousCommand.orElseThrow();

        case ParticipateCommand.COMMAND_WORD:
            return new ParticipateCommandParser().parse(arguments);

        case PresenceCommand.COMMAND_WORD:
            return new PresenceCommandParser().parse(arguments);

        case EnterSessionCommand.COMMAND_WORD:
            return new EnterSessionCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case RngCommand.COMMAND_WORD:
            return new RngCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }
    }

    /**
     * Removes the previousCommand if CommandException thrown recently is related to displayed indexes.
     *
     * @param e The recent CommandException that is thrown in Logic.
     */
    public void refreshInternalState(CommandException e) {
        if (checkExceptionMessageIsIndexRelated(e.getMessage())) {
            removePreviousCommand();
        }
    }

    /**
     * Checks if CommandException thrown is with regards to invalid displayed indexes.
     *
     * @param message The message of CommandException.
     * @return True if message is related to invalid indexes.
     */
    private boolean checkExceptionMessageIsIndexRelated(String message) {
        return message.equals(MESSAGE_INVALID_SESSION_DISPLAYED_INDEX)
                || message.equals(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

}
