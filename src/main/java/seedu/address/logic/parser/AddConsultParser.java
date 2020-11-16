package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddConsultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.Address;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddConsultCommand object.
 */
public class AddConsultParser implements Parser<AddConsultCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddConsultCommand
     * and returns an AddConsultCommand object for execution.
     *
     * @param args Arguments passed over the command line.
     * @return AddConsultCommand after parsing the arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddConsultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_ADDRESS,
                        PREFIX_TYPE);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_ADDRESS,
                PREFIX_TYPE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Day date = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DATE).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Address location = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Consultation consultation = new Consultation(name, date, time, location, type);

        return new AddConsultCommand(consultation);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap argumentMultimap for comparison.
     * @param prefixes all prefixes inputted.
     * @return Presence of all prefixes.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

