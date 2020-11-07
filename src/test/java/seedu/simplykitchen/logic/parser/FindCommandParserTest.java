package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.commands.FindCommand.MESSAGE_USAGE;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.logic.commands.FindCommand;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.ExpiryDateSearchPredicate;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.model.tag.TagSearchPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDescriptionArgs_returnsFindCommand() {
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate =
                Optional.of(new DescriptionContainsKeywordsPredicate(Arrays.asList("Anchovies", "Bread")));
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(descriptionPredicate, priorityPredicate, expiryDatePredicate, tagPredicate);
        assertParseSuccess(parser, " d/Anchovies Bread", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " d/\t\t\n Anchovies \t\t\n Bread \n\t ", expectedFindCommand);
    }

    @Test
    public void parse_validPriorityArgs_returnsFindCommand() {
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate = Optional.empty();
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate =
                Optional.of(new PrioritySearchPredicate(Priority.Level.HIGH));
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(descriptionPredicate, priorityPredicate, expiryDatePredicate, tagPredicate);
        assertParseSuccess(parser, " p/high", expectedFindCommand);

        // multiple whitespaces between prefix and priority
        assertParseSuccess(parser, " p/\t\nhigh \t \n  ", expectedFindCommand);
    }

    @Test
    public void parse_validExpiryDateArgs_returnsFindCommand() {
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate = Optional.empty();
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate =
                Optional.of(new ExpiryDateSearchPredicate("31-01-2022"));
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(descriptionPredicate, priorityPredicate, expiryDatePredicate, tagPredicate);
        assertParseSuccess(parser, " e/31-1-2022", expectedFindCommand);

        // multiple whitespaces between prefix and expiry date
        assertParseSuccess(parser, " e/\t\t31-1-2022\t ", expectedFindCommand);

        // using '/' instead of '-' for expiry date
        assertParseSuccess(parser, " e/31/1/2022", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate = Optional.empty();
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        HashSet<Tag> setOfTags = new HashSet<>();
        setOfTags.add(new Tag("sugar-free"));
        setOfTags.add(new Tag("raw"));
        Optional<TagSearchPredicate> tagPredicate = Optional.of(new TagSearchPredicate(setOfTags));

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(descriptionPredicate, priorityPredicate, expiryDatePredicate, tagPredicate);
        assertParseSuccess(parser, " t/sugar-free t/raw", expectedFindCommand);

        // multiple whitespaces between prefix, tag and subsequent tag prefix
        assertParseSuccess(parser, " t/\tsugar-free  \t  t/  \t \t \t raw \t  ", expectedFindCommand);
    }

    @Test
    public void parse_validSearchArgs_returnsFindCommand() {
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate =
                Optional.of(new DescriptionContainsKeywordsPredicate(Arrays.asList("Bagel")));
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate =
                Optional.of(new ExpiryDateSearchPredicate("01-02-2022"));
        Optional<PrioritySearchPredicate> priorityPredicate =
                Optional.of(new PrioritySearchPredicate(Priority.Level.LOW));
        HashSet<Tag> setOfTags = new HashSet<>();
        setOfTags.add(new Tag("sugar-free"));
        setOfTags.add(new Tag("cheese"));
        Optional<TagSearchPredicate> tagPredicate = Optional.of(new TagSearchPredicate(setOfTags));

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(descriptionPredicate, priorityPredicate, expiryDatePredicate, tagPredicate);
        assertParseSuccess(parser, " d/Bagel p/low e/1-2-2022 t/sugar-free t/cheese", expectedFindCommand);

        // multiple whitespaces between prefixes and arguments
        assertParseSuccess(parser, " d/ \n Bagel  \t  p/ \t low  \n "
                + "e/\t1-2-2022  \t  t/ \t  sugar-free \t   t/   \t \t cheese", expectedFindCommand);
    }

    @Test
    public void parse_invalidSearchValue_failure() {
        // invalid description
        assertParseFailure(parser, " d/Bag+el p/low e/1-2-2022 t/sugar-free t/cheese",
                Description.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, " d/Bagel p/lower e/1-2-2022 t/sugar-free t/cheese",
                Priority.MESSAGE_CONSTRAINTS);

        // invalid expiry date
        assertParseFailure(parser, " d/Bagel p/low e/1-2--2022 t/sugar-free t/cheese",
                ExpiryDate.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, " d/Bagel p/low e/1-2-2022 t/sugar-free} t/cheese",
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " d/Ba+gel p/lower e/1-2-2022 t/sugar-free t/cheese",
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, "sk d/Bagel p/low e/1-2-2022 t/sugar-free t/cheese",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

}
