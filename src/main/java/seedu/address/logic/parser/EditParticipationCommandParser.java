package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditParticipationCommand object.
 */
public class EditParticipationCommandParser implements Parser<EditParticipationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditParticipationCommand
     * and returns an EditParticipationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditParticipationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PARTICIPATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditParticipationCommand.MESSAGE_USAGE),
                    e
            );
        }

        if (!argumentMultimap.getValue(PREFIX_PARTICIPATION).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditParticipationCommand.MESSAGE_USAGE)
            );
        }

        String score = argumentMultimap.getValue(PREFIX_PARTICIPATION).get();
        return new EditParticipationCommand(index, score);
    }
}
