package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_MULTIPLE_PREFIX;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GENDER_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.PredefinedTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    public static final String MESSAGE_INVALID_CLASS_CAST = "Invalid casting of class";
    private static Logger logger = Logger.getLogger("AddCommandParserLogger");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws ClassCastException if wrong class is cast
     */
    public AddCommand parse(String args) throws ParseException, ClassCastException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE,
                                PREFIX_DIFFICULTY_TAG, PREFIX_GENDER_TAG, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        GermanPhrase germanPhrase = handleGermanPhrase(argMultimap);
        EnglishPhrase englishPhrase = handleEnglishPhrase(argMultimap);
        DifficultyTag difficultyTag = handleDifficultyTag(argMultimap);
        GenderTag genderTag = handleGenderTag(argMultimap);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        FlashCard flashCard = new FlashCard(germanPhrase, englishPhrase, difficultyTag, genderTag, tagList);

        return new AddCommand(flashCard);
    }

    /**
     * Returns a GermanPhrase for the AddCommand.
     * {@code ArgumentMultimap}.
     * @throws ParseException if the user input does not conform to the expected format
     */
    private static GermanPhrase handleGermanPhrase(ArgumentMultimap argMultimap)
            throws ParseException {
        if (!argMultimap.isSingleValue(PREFIX_GERMAN_PHRASE)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        }

        return ParserUtil.parseGermanPhrase(argMultimap.getValue(PREFIX_GERMAN_PHRASE).get());
    }

    /**
     * Returns a EnglishPhrase for the AddCommand.
     * {@code ArgumentMultimap}.
     * @throws ParseException if the user input does not conform to the expected format
     */
    private static EnglishPhrase handleEnglishPhrase(ArgumentMultimap argMultimap)
            throws ParseException {
        if (!argMultimap.isSingleValue(PREFIX_ENGLISH_PHRASE)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        }

        return ParserUtil.parseEnglishPhrase(argMultimap.getValue(PREFIX_ENGLISH_PHRASE).get());
    }


    /**
     * Returns a DifficultyTag for the AddCommand.
     * {@code ArgumentMultimap}.
     * @throws ParseException if the user input does not conform to the expected format
     */
    private static DifficultyTag handleDifficultyTag(ArgumentMultimap argMultimap)
            throws ParseException {

        if (!arePrefixesPresent(argMultimap, PREFIX_DIFFICULTY_TAG)) {
            logger.log(Level.INFO, "Set default difficulty tag, MEDIUM Difficulty.");
            return new DifficultyTag(DifficultyTag.MEDIUM_TAG);
        } else if (!argMultimap.isSingleValue(PREFIX_DIFFICULTY_TAG)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        } else {

            PredefinedTag newTag = ParserUtil.parsePredefinedTag(PREFIX_DIFFICULTY_TAG,
                    argMultimap.getValue(PREFIX_DIFFICULTY_TAG).get());

            if (newTag instanceof DifficultyTag) {
                return (DifficultyTag) newTag;
            } else {
                throw new ClassCastException(MESSAGE_INVALID_CLASS_CAST);
            }
        }
    }

    /**
     * Returns a GenderTag for the AddCommand.
     * {@code ArgumentMultimap}.
     * @throws ParseException if the user input does not conform to the expected format
     */
    private static GenderTag handleGenderTag(ArgumentMultimap argMultimap)
            throws ParseException {

        if (!arePrefixesPresent(argMultimap, PREFIX_GENDER_TAG)) {
            logger.log(Level.INFO, "Set default gender tag, NONE Gender.");
            return new GenderTag(GenderTag.NONE_GENDER_TAG);
        } else if (!argMultimap.isSingleValue(PREFIX_GENDER_TAG)) {
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        } else {

            PredefinedTag newTag = ParserUtil.parsePredefinedTag(PREFIX_GENDER_TAG,
                    argMultimap.getValue(PREFIX_GENDER_TAG).get());

            if (newTag instanceof GenderTag) {
                return (GenderTag) newTag;
            } else {
                throw new ClassCastException(MESSAGE_INVALID_CLASS_CAST);
            }
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
