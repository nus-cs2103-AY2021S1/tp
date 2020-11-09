package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACANCY;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Address;
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Email;
import seedu.address.model.information.Job;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddJobCommand object
 */
public class AddJobCommandParser implements Parser<AddJobCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddJobCommand
     * and returns an AddJobommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddJobCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOB_TITLE, PREFIX_COMPANY_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_VACANCY);

        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_TITLE, PREFIX_COMPANY_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_VACANCY) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE));
        }

        Name jobTitle = ParserUtil.parseName(argMultimap.getValue(PREFIX_JOB_TITLE).get());
        CompanyName companyName = ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Priority priority = ParserUtil.parsePriority(argMultimap.getAllValues(PREFIX_PRIORITY));
        Vacancy vacancy = ParserUtil.parseVacancy(argMultimap.getValue(PREFIX_VACANCY).get());

        Job job = new Job(jobTitle, companyName, phone, email, address, tagList, priority, vacancy);

        return new AddJobCommand(job);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
