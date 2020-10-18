package com.eva.logic.parser;
import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDORDELETE_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;

import java.util.Optional;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddCommand;
import com.eva.logic.commands.Command;
import com.eva.logic.commands.DeleteCommand;
import com.eva.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF, PREFIX_APPLICANT, PREFIX_ADDORDELETE_COMMENT);
        Index index;
        Optional<String> deleteStaffCommand = argMultimap.getValue(PREFIX_STAFF);
        Optional<String> deleteApplicantCommand = argMultimap.getValue(PREFIX_APPLICANT);
        Optional<String> deleteCommentCommand = argMultimap.getValue(PREFIX_ADDORDELETE_COMMENT);
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_2), pe);
        }


        if (!deleteStaffCommand.isEmpty()) {
            return new DeleteStaffCommandParser().parse(" " + index.getOneBased());
        } else if (!deleteCommentCommand.isEmpty()) {
            return new CommentCommandParser()
                    .parseDelete(" " + index.getOneBased()
                            + " c- " + deleteCommentCommand.get());
        } else {
            try {
                index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }

}
