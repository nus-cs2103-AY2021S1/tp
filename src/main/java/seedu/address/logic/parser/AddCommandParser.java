package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BLOCK_ROOM_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCKROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_GROUP;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.studentgroup.StudentGroup;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_STUDENT_GROUP, PREFIX_GENDER, PREFIX_BLOCKROOM,
                        PREFIX_MATRICULATION_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GENDER,
                PREFIX_BLOCKROOM, PREFIX_MATRICULATION_NUMBER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<StudentGroup> studentGroupList = ParserUtil.parseStudentGroups(argMultimap
                .getAllValues(PREFIX_STUDENT_GROUP));
        String blockRoomString = argMultimap.getValue(PREFIX_BLOCKROOM).orElse("");
        if (blockRoomString.length() != 4) {
            throw new ParseException(MESSAGE_INVALID_BLOCK_ROOM_FORMAT);
        }
        String blockString = blockRoomString.substring(0, 1);
        if (!blockString.matches(Block.VALIDATION_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_BLOCK_ROOM_FORMAT);
        }
        String roomString = blockRoomString.substring(1);
        Block block = ParserUtil.parseBlock(blockString);
        Room room = ParserUtil.parseRoom(roomString);
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        MatriculationNumber matriculationNumber = ParserUtil.parseMatriculationNumber(
                argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).get());
        Person person = new Person(name, phone, email, address, gender, studentGroupList,
                block, room, matriculationNumber);
        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
