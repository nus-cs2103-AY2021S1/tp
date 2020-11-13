package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ParticipationBelowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.ParticipationBelowSpecifiedScorePredicate;

/**
 * Parses input arguments and creates a new ParticipationBelowCommand object.
 */
public class ParticipationBelowCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ParticipationBelowCommand
     * and returns a ParticipationBelowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ParticipationBelowCommand parse(String args) throws ParseException {
        final int upperBound;

        try {
            upperBound = ParserUtil.parseUpperBound(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ParticipationBelowCommand.MESSAGE_USAGE), pe);
        }

        return new ParticipationBelowCommand(new ParticipationBelowSpecifiedScorePredicate(upperBound), upperBound);
    }
}
