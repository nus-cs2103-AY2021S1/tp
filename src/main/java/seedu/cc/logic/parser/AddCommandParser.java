package seedu.cc.logic.parser;

import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.cc.commons.core.category.Category;
import seedu.cc.logic.commands.entrylevel.AddCommand;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ArgumentMultimap;
import seedu.cc.logic.parser.util.ArgumentTokenizer;
import seedu.cc.logic.parser.util.ParserUtil;
import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Entry;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;
import seedu.cc.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_TAG);

        boolean arePrefixPresent = ParserUtil.arePrefixesPresent(argMultimap,
                PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_AMOUNT);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();

        if (!arePrefixPresent || !isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        boolean areNumberOfPrefixCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(argMultimap, PREFIX_CATEGORY,
                PREFIX_DESCRIPTION, PREFIX_AMOUNT);

        if (!areNumberOfPrefixCorrect) {
            throw new ParseException(String.format(MESSAGE_MULTIPLE_PREFIXES, AddCommand.PREFIXES));
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Entry entry;

        if (category.isExpense()) {
            entry = new Expense(description, amount, tagList);
        } else {
            assert category.isRevenue();
            entry = new Revenue(description, amount, tagList);
        }

        return new AddCommand(entry);
    }

}
