package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NON_EMPTY_ARGUMENT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.CclearCommand;
import seedu.address.logic.commands.ClistCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MclearCommand;
import seedu.address.logic.commands.MlistCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.UnassignallCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class OneWordCommandParser implements Parser<Command> {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case ResetCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, ResetCommand.COMMAND_WORD)
                    + ResetCommand.MESSAGE_USAGE);
            }

            return new ResetCommand();

        case SwitchCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, SwitchCommand.COMMAND_WORD)
                        + SwitchCommand.MESSAGE_USAGE);
            }

            return new SwitchCommand();

        case ListCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.COMMAND_WORD)
                        + ListCommand.MESSAGE_USAGE);
            }

            return new ListCommand();

        case CclearCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, CclearCommand.COMMAND_WORD)
                        + CclearCommand.MESSAGE_USAGE);
            }

            return new CclearCommand();

        case MclearCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, MclearCommand.COMMAND_WORD)
                        + MclearCommand.MESSAGE_USAGE);
            }

            return new MclearCommand();

        case ClistCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, ClistCommand.COMMAND_WORD)
                        + ClistCommand.MESSAGE_USAGE);
            }

            return new ClistCommand();

        case MlistCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, MlistCommand.COMMAND_WORD)
                        + MlistCommand.MESSAGE_USAGE);
            }

            return new MlistCommand();

        case UnassignallCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, UnassignallCommand.COMMAND_WORD)
                        + UnassignallCommand.MESSAGE_USAGE);
            }

            return new UnassignallCommand();

        case HelpCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, HelpCommand.COMMAND_WORD)
                        + HelpCommand.MESSAGE_USAGE);
            }

            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_NON_EMPTY_ARGUMENT, ExitCommand.COMMAND_WORD)
                        + ExitCommand.MESSAGE_USAGE);
            }

            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }


    }
}
