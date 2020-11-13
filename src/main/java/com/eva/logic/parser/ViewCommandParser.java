package com.eva.logic.parser;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.ViewCommand;
import com.eva.logic.parser.exceptions.IndexParseException;
import com.eva.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        } catch (IndexParseException pe) {
            throw new ParseException(pe.getMessage());
        }
    }
}
