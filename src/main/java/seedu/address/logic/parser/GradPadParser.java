package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.ADD_COMMAND_WORD;
import static seedu.address.commons.core.Messages.CHECKMC_COMMAND_WORD;
import static seedu.address.commons.core.Messages.CLEAR_COMMAND_WORD;
import static seedu.address.commons.core.Messages.DELETE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.EDIT_COMMAND_WORD;
import static seedu.address.commons.core.Messages.EXIT_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FIND_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FORCE_CLEAR_COMMAND_WORD;
import static seedu.address.commons.core.Messages.FORCE_DELETE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.GEM_COMMAND_WORD;
import static seedu.address.commons.core.Messages.HELP_COMMAND_WORD;
import static seedu.address.commons.core.Messages.LIST_COMMAND_WORD;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.REQUIRED_COMMAND_WORD;
import static seedu.address.commons.core.Messages.SCIENCE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.SEARCH_COMMAND_WORD;
import static seedu.address.commons.core.Messages.TAGS_COMMAND_WORD;
import static seedu.address.commons.core.Messages.YES_COMMAND_WORD;
import static seedu.address.commons.core.Messages.YE_COMMAND_WORD;
import static seedu.address.commons.core.Messages.Y_COMMAND_WORD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CheckMcCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ForceClearCommand;
import seedu.address.logic.commands.GemCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RequiredCommand;
import seedu.address.logic.commands.ScienceCommand;
import seedu.address.logic.commands.TagsCommand;
import seedu.address.logic.commands.YesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class GradPadParser {

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
            throw new ParseException(Messages.MESSAGE_EMPTY_FIELD);
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ADD_COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EDIT_COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DELETE_COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FORCE_DELETE_COMMAND_WORD:
            return new ForceDeleteCommandParser().parse(arguments);

        case CLEAR_COMMAND_WORD:
            return new ClearCommand();

        case FORCE_CLEAR_COMMAND_WORD:
            return new ForceClearCommand();

        case FIND_COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case LIST_COMMAND_WORD:
            return new ListCommand();

        case EXIT_COMMAND_WORD:
            return new ExitCommand();

        case HELP_COMMAND_WORD:
            return new HelpCommand();

        case CHECKMC_COMMAND_WORD:
            return new CheckMcCommand();

        case SEARCH_COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case REQUIRED_COMMAND_WORD:
            return new RequiredCommand();

        case SCIENCE_COMMAND_WORD:
            return new ScienceCommand();

        case YES_COMMAND_WORD:
        case YE_COMMAND_WORD:
        case Y_COMMAND_WORD:
            return new YesCommand();

        case TAGS_COMMAND_WORD:
            return new TagsCommand();

        case GEM_COMMAND_WORD:
            return new GemCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
