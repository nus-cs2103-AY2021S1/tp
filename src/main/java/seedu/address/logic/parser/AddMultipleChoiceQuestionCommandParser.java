package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.Question;
import seedu.address.flashcard.Tag;
import seedu.address.logic.commands.AddMultipleChoiceQuestionCommand;
import seedu.address.logic.commands.AddOpenEndedQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddMultipleChoiceQuestionCommandParser implements Parser<AddMultipleChoiceQuestionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMultipleChoiceQuestionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_CHOICE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOpenEndedQuestionCommand.MESSAGE_USAGE));
        }

        String[] choicesList = ParserUtil.parseChoices(argMultimap.getAllValues(PREFIX_CHOICE));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());
        Question question = ParserUtil.parseMultipleChoiceQuestion(argMultimap.getValue(PREFIX_QUESTION).get(), choicesList, answer);


        Flashcard flashcard = new Flashcard(question, answer, tagList);

        return new AddMultipleChoiceQuestionCommand(flashcard);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
