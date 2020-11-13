package seedu.internhunter.logic.parser.add;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_JOB_TITLE;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_PERIOD;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_WAGE;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.argumentsAreValid;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getIndexInPreamble;
import static seedu.internhunter.logic.parser.util.InternshipParserUtil.parseJobTitle;
import static seedu.internhunter.logic.parser.util.InternshipParserUtil.parseRequirements;

import java.util.Set;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.add.AddInternshipCommand;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.ArgumentTokenizer;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.logic.parser.util.InternshipParserUtil;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

/**
 * Parses input arguments and creates a new AddInternshipCommand object.
 */
public class AddInternshipCommandParser implements Parser<AddInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInternshipCommand
     * and returns an AddInternshipCommand object for execution.
     *
     * @param args to be parsed.
     * @return AddInternshipCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddInternshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_TITLE,
                PREFIX_PERIOD, PREFIX_WAGE, PREFIX_REQUIREMENT);

        if (!argumentsAreValid(true, argMultimap, PREFIX_JOB_TITLE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE));
        }

        Index companyIndex = getIndexInPreamble(argMultimap);
        JobTitle jobTitle = parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get());
        Wage wage = getWage(argMultimap);
        Period period = getPeriod(argMultimap);
        Set<Requirement> requirements = parseRequirements(argMultimap.getAllValues(PREFIX_REQUIREMENT));

        return new AddInternshipCommand(companyIndex, jobTitle, wage, period, requirements);
    }

    /**
     * Obtains the period from the user input. Returns default "-" if unspecified.
     *
     * @param argMultimap ArgumentMultimap.
     * @return Period for this Internship.
     * @throws ParseException if the given {@code Period} is invalid.
     */
    private Period getPeriod(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_PERIOD).isPresent()) {
            return InternshipParserUtil.parsePeriod(argMultimap.getValue(PREFIX_PERIOD).get());
        } else {
            return new Period("-");
        }
    }

    /**
     * Obtains the wage from the user input. Returns default "-" if unspecified.
     *
     * @param argMultimap ArgumentMultimap.
     * @return Wage for this Internship.
     * @throws ParseException if the given {@code Wage} is invalid.
     */
    private Wage getWage(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_WAGE).isPresent()) {
            return InternshipParserUtil.parseWage(argMultimap.getValue(PREFIX_WAGE).get());
        } else {
            return new Wage("-");
        }
    }
}
