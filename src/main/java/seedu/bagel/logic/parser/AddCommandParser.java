package seedu.bagel.logic.parser;

import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.bagel.logic.commands.AddCommand;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESC, PREFIX_LINK, PREFIX_SET, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DESC, PREFIX_SET)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());
        Link link = ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).orElse(""));
        FlashcardSet flashcardSet = ParserUtil.parseSet(argMultimap.getValue(PREFIX_SET).get());
        // FlashcardSet flashcardSet = ParserUtil.parseSet(argMultimap.getValue(PREFIX_SET).orElse("1"));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Flashcard flashcard = new Flashcard(title, description, link, flashcardSet, tagList);
        return new AddCommand(flashcard);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
