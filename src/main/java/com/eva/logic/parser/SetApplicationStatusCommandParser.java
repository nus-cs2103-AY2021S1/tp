package com.eva.logic.parser;

import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;

import java.io.FileNotFoundException;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.Command;
import com.eva.logic.commands.SetApplicationStatusCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.applicant.ApplicationStatus;

/**
 * Parses the given {@code String} of arguments in the context of the SetApplicationStatusCommand
 * and returns an SetApplicationStatusCommand object for execution.
 */
public class SetApplicationStatusCommandParser implements Parser {
    @Override
    public Command parse(String userInput) throws ParseException, FileNotFoundException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_APPLICATION_STATUS);
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        // ensure application status and index are provided
        if (!arePrefixesPresent(argMultimap, PREFIX_APPLICATION_STATUS)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetApplicationStatusCommand.MESSAGE_USAGE));
        }
        ApplicationStatus newApplicationStatus =
                ParserUtil.parseApplicationStatus(argMultimap.getValue(PREFIX_APPLICATION_STATUS).get());

        return new SetApplicationStatusCommand(index, newApplicationStatus);
    }
}
