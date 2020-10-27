package com.eva.logic.parser;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;

import java.util.Optional;

import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddCommand;
import com.eva.logic.commands.Command;
import com.eva.logic.parser.comment.AddCommentCommandParser;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.logic.parser.leave.AddLeaveCommandParser;


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
        boolean isAddCommentOrLeave = true;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF, PREFIX_APPLICANT,
                        PREFIX_COMMENT, PREFIX_LEAVE);
        Index index;
        Optional<String> addStaffCommand = argMultimap.getValue(PREFIX_STAFF);
        Optional<String> addApplicantCommand = argMultimap.getValue(PREFIX_APPLICANT);
        Optional<String> addCommentCommand = argMultimap.getValue(PREFIX_COMMENT);
        Optional<String> addLeaveCommand = argMultimap.getValue(PREFIX_LEAVE);
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            isAddCommentOrLeave = false;
        }


        if (!addApplicantCommand.isEmpty() && !addCommentCommand.isEmpty() && isAddCommentOrLeave) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddCommentCommandParser().parse(" " + index.getOneBased() + " a- " + addCommentCommand.get());
        } else if (!addStaffCommand.isEmpty() && !addCommentCommand.isEmpty() && isAddCommentOrLeave) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddCommentCommandParser().parse(" " + index.getOneBased() + " s- " + addCommentCommand.get());
        } else if (!addStaffCommand.isEmpty()) {
            return new AddStaffCommandParser().parse(" " + ArgumentTokenizer
                    .tokenize(args, PREFIX_STAFF).getValue(PREFIX_STAFF).get());
        } else if (!addApplicantCommand.isEmpty()) {
            return new AddApplicantCommandParser().parse(" " + ArgumentTokenizer
                    .tokenize(args, PREFIX_APPLICANT).getValue(PREFIX_APPLICANT).get());
        } else if (!addLeaveCommand.isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
            }
            return new AddLeaveCommandParser().parse(" " + index.getOneBased()
                    + " l/ " + addLeaveCommand.get());
        } else {
            throw new ParseException("Invalid input");
        }

    }


}
