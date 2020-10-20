package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAdminDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, ALL_PREFIXES);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = this.parseStudent(argMultimap);
        EditAdminDescriptor editAdminDescriptor = this.parseAdmin(argMultimap);

        if (!editStudentDescriptor.isAnyFieldEdited() && !editAdminDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor, editAdminDescriptor);
    }

    /**
     * Parses given input student fields into discernible values.
     * @param argMultimap Tokenized input by user.
     * @return EditStudentDescriptor with parsed values to edit.
     * @throws ParseException if the user input does not conform the expected format
     */
    private EditStudentDescriptor parseStudent(ArgumentMultimap argMultimap) throws ParseException {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            editStudentDescriptor.setSchool(ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SCHOOL).get()));
        }

        if (argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            editStudentDescriptor.setYear(ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get()));
        }

        return editStudentDescriptor;
    }

    /**
     * Parses given input admin fields into discernible values.
     * @param argMultimap Tokenized input by user.
     * @return EditAdminDescriptor with parsed values to edit.
     * @throws ParseException if the user input does not conform the expected format
     */
    private EditAdminDescriptor parseAdmin(ArgumentMultimap argMultimap) throws ParseException {
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptor();

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editAdminDescriptor.setTime(ParserUtil.parseClassTime(argMultimap.getValue(PREFIX_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editAdminDescriptor.setVenue(ParserUtil.parseClassVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        }
        if (argMultimap.getValue(PREFIX_FEE).isPresent()) {
            editAdminDescriptor.setFee(ParserUtil.parseFee(argMultimap.getValue(PREFIX_FEE).get()));
        }
        if (argMultimap.getValue(PREFIX_PAYMENT).isPresent()) {
            editAdminDescriptor.setPaymentDate(ParserUtil.parsePaymentDate(argMultimap.getValue(PREFIX_PAYMENT).get()));
        }

        return editAdminDescriptor;
    }

}
