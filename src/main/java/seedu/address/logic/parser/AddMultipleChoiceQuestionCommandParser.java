package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHOICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Choice;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.Question;
import seedu.address.flashcard.Tag;
import seedu.address.logic.commands.AddMultipleChoiceQuestionCommand;
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMultipleChoiceQuestionCommand.MESSAGE_USAGE));
        }

        Choice[] choicesList = ParserUtil.parseChoices(argMultimap.getAllValues(PREFIX_CHOICE));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Question question = ParserUtil.parseMultipleChoiceQuestion(
                argMultimap.getValue(PREFIX_QUESTION).get(), choicesList);
        Answer parsedAnswer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());

        int ans;
        try {
            ans = Integer.parseInt(parsedAnswer.getAnswer());
            if (ans > choicesList.length) {
                throw new ParseException("Answer must be smaller than number of choices");
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Answer must be integer");
        }

        Answer answer = new Answer(choicesList[ans - 1].getContent());
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
