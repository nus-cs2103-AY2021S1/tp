package com.eva.logic.parser;

import com.eva.commons.core.Messages;
import com.eva.logic.commands.LoadCommand;
import com.eva.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LoadCommand object.
 */
public class LoadCommandParser implements Parser<LoadCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LoadCommand
     * and returns a LoadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public LoadCommand parse(String args) throws ParseException {
        String scriptFilePath = args.trim();
        if (scriptFilePath.isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE));
        }
        return new LoadCommand(scriptFilePath);
    }
}
