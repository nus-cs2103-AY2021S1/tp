package com.eva.logic.parser;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDORDELETE_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddCommand;
import com.eva.logic.commands.Command;
import com.eva.logic.parser.comment.AddCommentCommandParser;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.logic.parser.leave.AddLeaveCommandParser;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<Command> {

    /**
     * Parses Add command: 'c-' for comment, 's-' for staff, 'a-' for applicant.
     * @param args
     * @return command
     * @throws ParseException when there are missing fields
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF, PREFIX_APPLICANT,
                        PREFIX_ADDORDELETE_COMMENT, PREFIX_LEAVE);
        Index index;
        Optional<String> addStaffCommand = argMultimap.getValue(PREFIX_STAFF);
        Optional<String> addApplicantCommand = argMultimap.getValue(PREFIX_APPLICANT);
        Optional<String> addCommentCommand = argMultimap.getValue(PREFIX_ADDORDELETE_COMMENT);
        Optional<String> addLeaveCommand = argMultimap.getValue(PREFIX_LEAVE);

        if (!addApplicantCommand.isEmpty() && !addCommentCommand.isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddCommentCommandParser().parse(" " + index.getOneBased() + " a- c- " + addCommentCommand.get());
        } else if (!addStaffCommand.isEmpty() && !addCommentCommand.isEmpty()) {
            System.out.println("in here");
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddCommentCommandParser().parse(" " + index.getOneBased() + " s- c- " + addCommentCommand.get());
        } else if (!addStaffCommand.isEmpty()) {
            return new AddStaffCommandParser().parse(" " + addStaffCommand.get());
        } else if (!addApplicantCommand.isEmpty()) {
            return new AddApplicantCommandParser().parse(" " + addApplicantCommand.get());
        } else if (!addLeaveCommand.isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddLeaveCommandParser().parse(" " + index.getOneBased()
                    + " l/ " + addLeaveCommand.get());
        } else if (!addCommentCommand.isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddCommentCommandParser().parse(" " + index.getOneBased() + " c- " + addCommentCommand.get());
        } else {
            return this.addParse(args);
        }

    }



    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand addParse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_COMMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Comment> commentList = ParserUtil.parseComments(argMultimap.getAllValues(PREFIX_COMMENT));

        Person person = new Person(name, phone, email, address, tagList, commentList);

        return new AddCommand(person);
    }

}
