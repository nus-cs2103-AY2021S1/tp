package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseFailure;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.StatsCommand;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.Tag;
import quickcache.testutil.TypicalTags;

public class StatsCommandParserTest {
    private final StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validIndex_returnsOpenCommand() {
        assertParseSuccess(parser, "1", new StatsCommand(INDEX_FIRST_FLASHCARD));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTag_returnsStatsCommand() {
        Set<Tag> tagsToMatch = new HashSet<>();
        tagsToMatch.add(TypicalTags.TEST_TAG);
        FlashcardPredicate flashcardPredicate = prepareFlashcardPredicate(tagsToMatch);
        assertParseSuccess(parser, " t/test", new StatsCommand(flashcardPredicate, tagsToMatch));
    }

    @Test
    public void parse_emptyTag_throwsParseException() {
        assertParseFailure(parser, " t/ t/test", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser,
            "1 q/test",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validIndexAndTag_throwsParseException() {
        assertParseFailure(parser,
            "1 t/test",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    private FlashcardPredicate prepareFlashcardPredicate(Set<Tag> tagsToMatch) {
        ArrayList<Predicate<Flashcard>> predicates = new ArrayList<>();
        predicates.add(new FlashcardContainsTagPredicate(tagsToMatch));
        return new FlashcardPredicate(predicates);
    }
}
