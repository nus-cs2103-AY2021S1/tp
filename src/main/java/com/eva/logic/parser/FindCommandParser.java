package com.eva.logic.parser;

import java.util.Arrays;

import com.eva.commons.core.Messages;
import com.eva.logic.commands.FindApplicantCommand;
import com.eva.logic.commands.FindCommand;
import com.eva.logic.commands.FindStaffCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        String[] shiftedNameKeywords = Arrays.copyOfRange(nameKeywords, 1, nameKeywords.length);
        switch (nameKeywords[0]) {
        case "-applicant":
            return new FindApplicantCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(shiftedNameKeywords)));
        case "-staff":
            return new FindStaffCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(shiftedNameKeywords)));
        default:
            return new FindCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(nameKeywords)));
        }
    }

}
