package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_CLASS_PARTICIPATION;

import java.util.NoSuchElementException;

import seedu.taskmaster.commons.core.index.Index;
import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.logic.commands.ParticipationAllCommand;
import seedu.taskmaster.logic.commands.ParticipationCommand;
import seedu.taskmaster.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ParticipationCommand object
 */
public class ParticipationCommandParser implements Parser<ParticipationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ParticipationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLASS_PARTICIPATION);

        Index index;
        double score;

        try {
            score = ParserUtil.parseScore(argMultimap.getValue(PREFIX_CLASS_PARTICIPATION).get());
            String preamble = argMultimap.getPreamble();

            if (score < 0) {
                throw new IllegalValueException(
                        "Invalid input: Negative score. Score needs to be between 0 to 10 inclusive.");
            } else if (score > 10) {
                throw new IllegalValueException(
                        "Invalid input: Score is greater than 10. Score needs to be between 0 to 10 inclusive.");
            }

            if (preamble.equals("all")) {
                return new ParticipationAllCommand(score);
            } else {
                index = ParserUtil.parseIndex(preamble);
                return new ParticipationCommand(index, score);
            }
        } catch (ParseException | NoSuchElementException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParticipationCommand.MESSAGE_USAGE), e);
        } catch (IllegalValueException e) {
            throw new ParseException(
                    e.getMessage()
            );
        }
    }
}
