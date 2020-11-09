package ay2021s1_cs2103_w16_3.finesse.logic.parser.bookmarkparsers;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_DATE;
import static ay2021s1_cs2103_w16_3.finesse.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark.EditBookmarkCommand;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark.EditBookmarkTransactionDescriptor;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.ArgumentMultimap;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.ArgumentTokenizer;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.Parser;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.ParserUtil;
import ay2021s1_cs2103_w16_3.finesse.logic.parser.exceptions.ParseException;
import ay2021s1_cs2103_w16_3.finesse.model.bookmark.BookmarkTransaction;

/**
 * Parses input arguments and creates a new EditBookmarkCommand object
 */
public class EditBookmarkCommandParser implements Parser<EditBookmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBookmarkIncomeCommand
     * and returns an EditBookmarkIncomeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBookmarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AMOUNT, PREFIX_DATE,
                PREFIX_CATEGORY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBookmarkCommand.MESSAGE_USAGE));
        }

        if (argMultimap.moreThanOneValuePresent(PREFIX_TITLE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookmarkTransaction.MESSAGE_TITLE_CONSTRAINTS));
        }

        if (argMultimap.moreThanOneValuePresent(PREFIX_AMOUNT)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookmarkTransaction.MESSAGE_AMOUNT_CONSTRAINTS));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookmarkTransaction.MESSAGE_CANNOT_CONTAIN_DATE));
        }

        EditBookmarkTransactionDescriptor editBookmarkIncomeDescriptor = new EditBookmarkTransactionDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editBookmarkIncomeDescriptor.setTitle(ParserUtil
                    .parseTitleAndTrimBetweenWords(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editBookmarkIncomeDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }

        ParserUtil.parseCategoriesForEdit(argMultimap.getAllValues(PREFIX_CATEGORY))
                .ifPresent(editBookmarkIncomeDescriptor::setCategories);

        if (!editBookmarkIncomeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBookmarkCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookmarkCommand(index, editBookmarkIncomeDescriptor);
    }
}
