package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_DETAIL_COMMAND;
import static seedu.address.logic.commands.HelpCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.ReeveParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAdditionalDetailCommand;
import seedu.address.logic.commands.AdditionalDetailCommand;
import seedu.address.logic.commands.DeleteAdditionalDetailCommand;
import seedu.address.logic.commands.EditAdditionalDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AdditionalDetailCommandParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public AdditionalDetailCommand parseAdditionalDetailCommand(String userInput)
            throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddAdditionalDetailCommand.COMMAND_WORD:
            return new AddAdditionalDetailCommandParser().parse(arguments);

        case DeleteAdditionalDetailCommand.COMMAND_WORD:
            return new DeleteAdditionalDetailCommandParser().parse(arguments);

        case EditAdditionalDetailCommand.COMMAND_WORD:
            return new EditAdditionalDetailCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_DETAIL_COMMAND);
        }
    }
}
