package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.gradetrackercommands.AddGradeCommand;
import seedu.address.logic.commands.modulelistcommands.AddCompletedModuleCommand;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.AddZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.ArchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.CalculateCapCommand;
import seedu.address.logic.commands.modulelistcommands.ClearModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.ListModuleCommand;
import seedu.address.logic.commands.modulelistcommands.RedoCommand;
import seedu.address.logic.commands.modulelistcommands.TargetCapCalculatorCommand;
import seedu.address.logic.commands.modulelistcommands.UnarchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.UndoCommand;
import seedu.address.logic.commands.modulelistcommands.ViewArchivedModulesCommand;
import seedu.address.logic.commands.modulelistcommands.ViewModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.gradetrackerparsers.AddGradeParser;
import seedu.address.logic.parser.modulelistparsers.AddCompletedModuleParser;
import seedu.address.logic.parser.modulelistparsers.AddModuleParser;
import seedu.address.logic.parser.modulelistparsers.AddZoomLinkParser;
import seedu.address.logic.parser.modulelistparsers.ArchiveModuleParser;
import seedu.address.logic.parser.modulelistparsers.DeleteModuleParser;
import seedu.address.logic.parser.modulelistparsers.DeleteZoomLinkParser;
import seedu.address.logic.parser.modulelistparsers.EditModuleParser;
import seedu.address.logic.parser.modulelistparsers.EditZoomLinkParser;
import seedu.address.logic.parser.modulelistparsers.TargetCapCalculatorParser;
import seedu.address.logic.parser.modulelistparsers.UnarchiveModuleParser;
import seedu.address.logic.parser.modulelistparsers.ViewModuleParser;

/**
 * Parses user input.
 */
public class ModuleListParser implements FeatureParser {

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
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleParser().parse(arguments);

        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleParser().parse(arguments);

        case FindModuleCommand.COMMAND_WORD:
            return new FindModuleParser().parse(arguments);

        case AddZoomLinkCommand.COMMAND_WORD:
            return new AddZoomLinkParser().parse(arguments);

        case EditZoomLinkCommand.COMMAND_WORD:
            return new EditZoomLinkParser().parse(arguments);

        case DeleteZoomLinkCommand.COMMAND_WORD:
            return new DeleteZoomLinkParser().parse(arguments);

        case ViewModuleCommand.COMMAND_WORD:
            return new ViewModuleParser().parse(arguments);

        case AddGradeCommand.COMMAND_WORD:
            return new AddGradeParser().parse(arguments);

        case AddCompletedModuleCommand.COMMAND_WORD:
            return new AddCompletedModuleParser().parse(arguments);

        case TargetCapCalculatorCommand.COMMAND_WORD:
            return new TargetCapCalculatorParser().parse(arguments);

        case ArchiveModuleCommand.COMMAND_WORD:
            return new ArchiveModuleParser().parse(arguments);

        case UnarchiveModuleCommand.COMMAND_WORD:
            return new UnarchiveModuleParser().parse(arguments);

        case ClearModuleCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ClearModuleCommand.COMMAND_WORD, arguments);

        case ExitCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ExitCommand.COMMAND_WORD, arguments);

        case HelpCommand.COMMAND_WORD:
            return singleWordCommandsChecker(HelpCommand.COMMAND_WORD, arguments);

        case UndoCommand.COMMAND_WORD:
            return singleWordCommandsChecker(UndoCommand.COMMAND_WORD, arguments);

        case RedoCommand.COMMAND_WORD:
            return singleWordCommandsChecker(RedoCommand.COMMAND_WORD, arguments);

        case CalculateCapCommand.COMMAND_WORD:
            return singleWordCommandsChecker(CalculateCapCommand.COMMAND_WORD, arguments);

        case ListModuleCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ListModuleCommand.COMMAND_WORD, arguments);

        case ViewArchivedModulesCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ViewArchivedModulesCommand.COMMAND_WORD, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Returns the respective Command for the singleWord Commands after checking the arguments.
     * @param commandWord the command word for respective commands.
     * @param argument the argument after the command word.
     * @return the repective command.
     * @throws ParseException if there is an argument after the command word.
     */
    public Command singleWordCommandsChecker(String commandWord, String argument) throws ParseException {
        if (!argument.equals("")) {
            throw new ParseException("Invalid input format, extra string after the command word.");
        }
        switch (commandWord) {
        case ClearModuleCommand.COMMAND_WORD:
            return new ClearModuleCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case CalculateCapCommand.COMMAND_WORD:
            return new CalculateCapCommand();

        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        case ViewArchivedModulesCommand.COMMAND_WORD:
            return new ViewArchivedModulesCommand();

        default:
            throw new ParseException("Invalid command");
        }
    }
}
