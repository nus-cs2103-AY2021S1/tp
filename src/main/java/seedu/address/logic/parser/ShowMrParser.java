package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ShowMrCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;

public class ShowMrParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowMr Command
     * and returns a ShowMr Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowMrCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (trimmedArgs.isEmpty() || nameKeywords.length != 1 || !Nric.isValidNric(nameKeywords[0])) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowMrCommand.MESSAGE_USAGE));
        }
        return new ShowMrCommand(new NricPredicate(Arrays.asList(nameKeywords)));
    }

}
