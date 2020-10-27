package com.eva.logic.parser;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.Command;
import com.eva.logic.commands.SetApplicationStatusCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.applicant.ApplicationStatus;

import java.io.FileNotFoundException;

import static com.eva.logic.parser.CliSyntax.*;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;


public class SetApplicationStatusCommandParser implements Parser {
    @Override
    public Command parse(String userInput) throws ParseException, FileNotFoundException {
        String[] args = userInput.split(" ");
        Index index = ParserUtil.parseIndex(args[0]);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args[1], PREFIX_APPLICATION_STATUS);
        if (!arePrefixesPresent(argMultimap, PREFIX_APPLICATION_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetApplicationStatusCommand.MESSAGE_USAGE));
        }
        ApplicationStatus newApplicationStatus =
                ParserUtil.parseApplicationStatus(argMultimap.getValue(PREFIX_APPLICATION_STATUS).get());

        return new SetApplicationStatusCommand(index, newApplicationStatus);
    }
}
