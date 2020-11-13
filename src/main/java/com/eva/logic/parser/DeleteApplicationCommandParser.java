package com.eva.logic.parser;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.DeleteApplicationCommand;
import com.eva.logic.parser.exceptions.IndexParseException;
import com.eva.logic.parser.exceptions.ParseException;

public class DeleteApplicationCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicationCommand
     * and returns a DeleteApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteApplicationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteApplicationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicationCommand.MESSAGE_USAGE), pe);
        } catch (IndexParseException pe) {
            throw new ParseException(pe.getMessage());
        }
    }
}
