package com.eva.logic.parser;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;

import java.util.Optional;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.Command;
import com.eva.logic.commands.DeleteCommand;
import com.eva.logic.parser.comment.DeleteCommentCommandParser;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.logic.parser.leave.DeleteLeaveCommandParser;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF, PREFIX_APPLICANT,
                        PREFIX_COMMENT, PREFIX_LEAVE);
        Index index;
        Optional<String> deleteStaffCommand = argMultimap.getValue(PREFIX_STAFF);
        Optional<String> deleteApplicantCommand = argMultimap.getValue(PREFIX_APPLICANT);
        Optional<String> deleteCommentCommand = argMultimap.getValue(PREFIX_COMMENT);
        Optional<String> deleteLeaveCommand = argMultimap.getValue(PREFIX_LEAVE);
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
        if (!deleteApplicantCommand.isEmpty() && !deleteCommentCommand.isEmpty()) {
            return new DeleteCommentCommandParser()
                    .parse(" " + index.getOneBased() + " a- " + deleteCommentCommand.get());
        } else if (!deleteStaffCommand.isEmpty() && !deleteCommentCommand.isEmpty()) {
            return new DeleteCommentCommandParser()
                    .parse(" " + index.getOneBased() + " s- " + deleteCommentCommand.get());
        } else if (!deleteLeaveCommand.isEmpty()) {
            return new DeleteLeaveCommandParser()
                    .parse(" " + index.getOneBased()
                            + " " + deleteLeaveCommand.get());
        } else if (!deleteStaffCommand.isEmpty() && deleteStaffCommand.get().length() < 1) {
            return new DeleteStaffCommandParser()
                    .parse(" " + index.getOneBased());
        } else if (!deleteApplicantCommand.isEmpty() && deleteApplicantCommand.get().length() < 1) {
            return new DeleteApplicantCommandParser()
                    .parse(" " + index.getOneBased()
                        + " " + deleteApplicantCommand.get());
        } else {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }
}
