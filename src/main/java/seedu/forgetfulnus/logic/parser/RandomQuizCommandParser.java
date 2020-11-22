package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.RandomQuizCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseZeroException;

/**
 * Parses input arguments and creates a new RandomQuizCommand object.
 */
public class RandomQuizCommandParser implements Parser<RandomQuizCommand> {

    private static Logger logger = Logger.getLogger("RandomQuizCommandParserLogger");

    /**
     * Parses the given {@code String} of arguments in the context of the RandomQuizCommand
     * and returns a RandomQuizCommand object for execution.
     * @throws ParseZeroException if the user input is '0'.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public RandomQuizCommand parse(String args) throws ParseException, ParseZeroException {
        logger.log(Level.INFO, "Start processing arguments");
        try {
            Index index = ParserUtil.parseIndex(args);
            assert index.getOneBased() > 0 : "Parameter of RandomQuizCommand must be more than 0";
            logger.log(Level.INFO, "End of processing");
            return new RandomQuizCommand(index);
        } catch (ParseZeroException pze) {
            logger.log(Level.WARNING, "Processing error");
            throw new ParseZeroException(
                    String.format(MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX,
                            RandomQuizCommand.MESSAGE_USAGE), pze);
        } catch (ParseException pe) {
            logger.log(Level.WARNING, "Processing error");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RandomQuizCommand.MESSAGE_USAGE), pe);
        }
    }
}
