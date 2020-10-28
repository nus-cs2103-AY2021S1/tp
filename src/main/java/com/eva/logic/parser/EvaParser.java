package com.eva.logic.parser;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eva.commons.core.Messages;
import com.eva.logic.commands.AddApplicantCommand;
import com.eva.logic.commands.AddApplicationCommand;
import com.eva.logic.commands.AddCommand;
import com.eva.logic.commands.AddCommentCommand;
import com.eva.logic.commands.AddLeaveCommand;
import com.eva.logic.commands.AddStaffCommand;
import com.eva.logic.commands.ClearCommand;
import com.eva.logic.commands.Command;
import com.eva.logic.commands.DeleteApplicantCommand;
import com.eva.logic.commands.DeleteApplicationCommand;
import com.eva.logic.commands.DeleteCommand;
import com.eva.logic.commands.DeleteCommentCommand;
import com.eva.logic.commands.DeleteLeaveCommand;
import com.eva.logic.commands.DeleteStaffCommand;
import com.eva.logic.commands.EditApplicantCommand;
import com.eva.logic.commands.EditCommand;
import com.eva.logic.commands.EditCommentCommand;
import com.eva.logic.commands.EditStaffCommand;
import com.eva.logic.commands.ExitCommand;
import com.eva.logic.commands.FindCommand;
import com.eva.logic.commands.HelpCommand;
import com.eva.logic.commands.ListCommand;
import com.eva.logic.commands.ViewCommand;
import com.eva.logic.parser.comment.AddCommentCommandParser;
import com.eva.logic.parser.comment.DeleteCommentCommandParser;
import com.eva.logic.parser.comment.EditCommentCommandParser;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.logic.parser.leave.AddLeaveCommandParser;
import com.eva.logic.parser.leave.DeleteLeaveCommandParser;

/**
 * Parses user input.
 */
public class EvaParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException, FileNotFoundException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddStaffCommand.COMMAND_WORD:
            return new AddStaffCommandParser().parse(arguments);

        case DeleteStaffCommand.COMMAND_WORD:
            return new DeleteStaffCommandParser().parse(arguments);

        case EditStaffCommand.COMMAND_WORD:
            return new EditStaffCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case AddCommentCommand.COMMAND_WORD:
            return new AddCommentCommandParser().parse(arguments);

        case DeleteCommentCommand.COMMAND_WORD:
            return new DeleteCommentCommandParser().parse(arguments);

        case EditCommentCommand.COMMAND_WORD:
            return new EditCommentCommandParser().parse(arguments);

        case AddLeaveCommand.COMMAND_WORD:
            return new AddLeaveCommandParser().parse(arguments);

        case DeleteLeaveCommand.COMMAND_WORD:
            return new DeleteLeaveCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case AddApplicantCommand.COMMAND_WORD:
            return new AddApplicantCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case DeleteApplicantCommand.COMMAND_WORD:
            return new DeleteApplicantCommandParser().parse(arguments);

        case EditApplicantCommand.COMMAND_WORD:
            return new EditApplicantCommandParser().parse(arguments);

        case AddApplicationCommand.COMMAND_WORD:
            return new AddApplicationCommandParser().parse(arguments);

        case DeleteApplicationCommand.COMMAND_WORD:
            return new DeleteApplicationCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
