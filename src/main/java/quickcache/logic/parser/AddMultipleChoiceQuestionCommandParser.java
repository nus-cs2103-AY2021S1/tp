package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.commons.core.Messages.MESSAGE_TOO_MANY_ANSWERS;
import static quickcache.commons.core.Messages.MESSAGE_TOO_MANY_DIFFICULTIES;
import static quickcache.commons.core.Messages.MESSAGE_TOO_MANY_QUESTIONS;
import static quickcache.logic.parser.CliSyntax.PREFIX_ANSWER;
import static quickcache.logic.parser.CliSyntax.PREFIX_CHOICE;
import static quickcache.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import quickcache.logic.commands.AddMultipleChoiceQuestionCommand;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Difficulty;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Tag;

/**
 * Parses input arguments and creates a new AddMultipleChoiceQuestionCommand object
 */
public class AddMultipleChoiceQuestionCommandParser implements Parser<AddMultipleChoiceQuestionCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddMultipleChoiceQuestionCommand
     * and returns an AddMultipleChoiceQuestionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMultipleChoiceQuestionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_CHOICE,
                        PREFIX_TAG, PREFIX_DIFFICULTY);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMultipleChoiceQuestionCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getAllValues(PREFIX_QUESTION).size() > 1) {
            throw new ParseException(MESSAGE_TOO_MANY_QUESTIONS);
        }
        if (argMultimap.getAllValues(PREFIX_ANSWER).size() > 1) {
            throw new ParseException(MESSAGE_TOO_MANY_ANSWERS);
        }

        Choice[] choicesList = ParserUtil.parseChoices(argMultimap.getAllValues(PREFIX_CHOICE));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        String questionInString = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        Question question = ParserUtil.parseMultipleChoiceQuestion(
                questionInString, argMultimap.getValue(PREFIX_ANSWER).get(), choicesList);

        if (arePrefixesPresent(argMultimap, PREFIX_DIFFICULTY)) {

            if (argMultimap.getAllValues(PREFIX_DIFFICULTY).size() > 1) {
                throw new ParseException(MESSAGE_TOO_MANY_DIFFICULTIES);
            }
            Difficulty difficulty = ParserUtil.parseDifficulty(argMultimap.getValue(PREFIX_DIFFICULTY).get());
            Flashcard flashcard = new Flashcard(question, tagList, difficulty);
            return new AddMultipleChoiceQuestionCommand(flashcard);
        } else {
            Flashcard flashcard = new Flashcard(question, tagList);
            return new AddMultipleChoiceQuestionCommand(flashcard);
        }
    }

}
