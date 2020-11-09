package seedu.zookeep.logic.parser;

import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_FEED_TIME;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_SPECIES;

import java.util.Set;
import java.util.stream.Stream;

import seedu.zookeep.logic.commands.AddCommand;
import seedu.zookeep.logic.parser.exceptions.ParseException;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_SPECIES, PREFIX_MEDICAL_CONDITION,
                        PREFIX_FEED_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SPECIES, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Species species = ParserUtil.parseSpecies(argMultimap.getValue(PREFIX_SPECIES).get());
        Set<MedicalCondition> medicalConditionList = ParserUtil
                .parseMedicalConditions(argMultimap.getAllValues(PREFIX_MEDICAL_CONDITION));
        Set<FeedTime> feedTimeList = ParserUtil
                .parseFeedTimes(argMultimap.getAllValues(PREFIX_FEED_TIME));
        Animal animal = new Animal(name, id, species, medicalConditionList, feedTimeList);

        return new AddCommand(animal);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
