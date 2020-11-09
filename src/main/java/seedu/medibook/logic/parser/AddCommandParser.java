package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.medibook.logic.commands.AddCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_IC, PREFIX_NAME, PREFIX_DATE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_BLOOD_TYPE, PREFIX_ALLERGY,
                        PREFIX_CONDITION, PREFIX_TREATMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_IC, PREFIX_NAME, PREFIX_DATE, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Ic ic = ParserUtil.parseIc(argMultimap.getValue(PREFIX_IC).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        DateOfBirth dateOfBirth = ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DATE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

        Optional<Email> email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL));
        Optional<Address> address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS));
        Optional<Height> height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT));
        Optional<Weight> weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT));
        Optional<BloodType> bloodType = ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOOD_TYPE));

        Set<Allergy> allergyList = ParserUtil.parseAllergies(argMultimap.getAllValues(PREFIX_ALLERGY));
        Set<Condition> conditionList = ParserUtil.parseConditions(argMultimap.getAllValues(PREFIX_CONDITION));
        Set<Treatment> treatmentList = ParserUtil.parseTreatments(argMultimap.getAllValues(PREFIX_TREATMENT));

        Patient patient = new Patient(ic, name, dateOfBirth, phone, email, address, height, weight,
                                   bloodType, allergyList, conditionList, treatmentList);

        height.ifPresent(h -> patient.getRecord().addHeightRecord(h));
        weight.ifPresent(w -> patient.getRecord().addWeightRecord(w));

        return new AddCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
