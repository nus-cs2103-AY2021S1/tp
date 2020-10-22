package com.eva.logic.parser;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.DeleteApplicantCommand;
import com.eva.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteApplicantCommand object
 */
public class DeleteApplicantCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicantCommand
     * and returns a DeleteApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteApplicantCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteApplicantCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicantCommand.MESSAGE_USAGE), pe);
        }
    }
}
