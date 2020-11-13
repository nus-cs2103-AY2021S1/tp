package seedu.bagel.logic.parser;

import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;

import java.util.stream.Stream;

import seedu.bagel.logic.commands.ListCommand;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.flashcard.FlashcardSet;

public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SET);
        if (!arePrefixesPresent(argMultimap, PREFIX_SET)) {
            if (args.isEmpty()) {
                return new ListCommand();
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }
        }

        try {
            FlashcardSet flashcardSet = ParserUtil.parseSet(argMultimap.getValue(PREFIX_SET).get());
            return new ListCommand(flashcardSet);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
