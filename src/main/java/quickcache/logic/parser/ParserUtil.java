package quickcache.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.commons.util.StringUtil;
import quickcache.logic.commands.AddMultipleChoiceQuestionCommand;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Option;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code question} is invalid.
     */
    public static String parseQuestion(String question) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!Question.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(OpenEndedQuestion.MESSAGE_CONSTRAINTS);
        }
        return question;
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if answer is less than choices and question is invalid.
     */
    public static Question parseOpenEndedQuestion(String question, Answer answer) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!MultipleChoiceQuestion.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);
        }
        return new OpenEndedQuestion(question, answer);
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if answer is less than choices and question is invalid.
     */
    public static Question parseMultipleChoiceQuestion(String question, Answer answer,
                                                       Choice[] choices) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!MultipleChoiceQuestion.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);
        }
        int ans;
        try {
            ans = Integer.parseInt(answer.getValue());
            if (ans > choices.length) {
                throw new ParseException("Answer must be smaller than number of choices");
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Answer must be integer");
        }

        Answer finalAnswer = new Answer(choices[ans - 1].getValue());
        return new MultipleChoiceQuestion(trimmedQuestion, finalAnswer, choices);
    }

    /**
     * Parses a {@code String answer} into a {@code Answer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code answer} is invalid.
     */
    public static Answer parseAnswer(String answer) throws ParseException {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim();
        if (!Answer.isValidAnswer(trimmedAnswer)) {
            throw new ParseException(Answer.MESSAGE_CONSTRAINTS);
        }
        return new Answer(trimmedAnswer);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Choice parseChoice(String choice) throws ParseException {
        requireNonNull(choice);
        String trimmedChoice = choice.trim();
        if (!Choice.isValidChoice(trimmedChoice)) {
            throw new ParseException("Choices cannot be empty");
        }
        return new Choice(trimmedChoice);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Choice[] parseChoices(Collection<String> choices) throws ParseException {
        requireNonNull(choices);
        List<Choice> choicesList = new ArrayList<>();
        for (String choice : choices) {
            choicesList.add(parseChoice(choice));
        }
        Choice[] result = new Choice[choicesList.size()];
        if (result.length <= 1) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMultipleChoiceQuestionCommand.MESSAGE_USAGE));
        }
        choicesList.toArray(result);
        return result;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String option} into a {@code Option}.
     * Leading and trailing white spaces will be trimmed.
     *
     * @throws ParseException if the given {@code option} is invalid.
     */
    public static Option parseOption(String option) throws ParseException {
        requireNonNull(option);
        String trimmedOption = option.trim();
        if (!Option.isValidOption(trimmedOption)) {
            throw new ParseException(Option.MESSAGE_CONSTRAINTS);
        }
        return new Option(trimmedOption);
    }
}
