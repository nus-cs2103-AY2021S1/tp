package com.eva.logic.parser;

import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF, PREFIX_APPLICANT);
        if (argMultimap.isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getNonEmptyPrefixCount() > 1) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(PREFIX_STAFF).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_STAFF).get().split("\\s+");
            return new FindStaffCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(keywords)));
        } else if (argMultimap.getValue(PREFIX_APPLICANT).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_APPLICANT).get().split("\\s+");
            return new FindApplicantCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(keywords)));
        } else {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
