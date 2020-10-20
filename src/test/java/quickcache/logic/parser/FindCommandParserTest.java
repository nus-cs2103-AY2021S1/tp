package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseFailure;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.FindCommand;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.QuestionContainsKeywordsPredicate;
import quickcache.model.flashcard.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTag_throwsParseException() {
        assertParseFailure(parser, " t/ t/TagOne", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyQuestion_throwsParseException() {
        assertParseFailure(parser, " q/ q/KeywordOne", "Keyword should not be blank");
    }

    @Test
    public void parse_argsContainOnlyQuestionKeywords_returnsFindCommand() {
        // no leading and trailing whitespaces
        Predicate<Flashcard> keywordPredicate =
                new QuestionContainsKeywordsPredicate(List.of("KeywordOne", "KeywordTwo"));
        FlashcardPredicate predicate = new FlashcardPredicate(List.of(keywordPredicate));
        FindCommand expectedFindCommand = new FindCommand(predicate);

        assertParseSuccess(parser, " q/KeywordOne q/KeywordTwo", expectedFindCommand);
    }

    @Test
    public void parse_argsContainOnlyTags_returnsFindCommand() {
        // no leading and trailing whitespaces
        Predicate<Flashcard> tagPredicate = new FlashcardContainsTagPredicate(new HashSet<>(
                Arrays.asList(new Tag("TagOne"), new Tag("TagTwo"))));
        FlashcardPredicate predicate = new FlashcardPredicate(List.of(tagPredicate));
        FindCommand expectedFindCommand = new FindCommand(predicate);

        assertParseSuccess(parser, " t/TagOne t/TagTwo", expectedFindCommand);
    }

    @Test
    public void parse_argsContainBothQuestionKeywordsAndTags_returnsFindCommand() {
        // no leading and trailing whitespaces
        Predicate<Flashcard> tagPredicate = new FlashcardContainsTagPredicate(new HashSet<>(
                Arrays.asList(new Tag("TagOne"), new Tag("TagTwo"))));
        Predicate<Flashcard> keywordPredicate =
                new QuestionContainsKeywordsPredicate(List.of("KeywordOne", "KeywordTwo"));
        FlashcardPredicate predicate = new FlashcardPredicate(List.of(tagPredicate, keywordPredicate));
        FindCommand expectedFindCommand = new FindCommand(predicate);

        assertParseSuccess(parser, " q/KeywordOne q/KeywordTwo t/TagOne t/TagTwo", expectedFindCommand);
    }

}
