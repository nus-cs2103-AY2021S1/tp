package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Block;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BLOCKROOM, PREFIX_STUDENT_GROUP, PREFIX_MATRICULATION_NUMBER, PREFIX_GENDER);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOCKROOM).isPresent()) {
            String blockRoomString = argMultimap.getValue(PREFIX_BLOCKROOM).orElse("");
            if (blockRoomString.length() != 4) {
                throw new ParseException(MESSAGE_INVALID_BLOCK_ROOM_FORMAT);
            }
            String blockString = blockRoomString.substring(0, 1);
            if (!blockString.matches(Block.VALIDATION_REGEX)) {
                throw new ParseException(MESSAGE_INVALID_BLOCK_ROOM_FORMAT);
            }
            String roomString = blockRoomString.substring(1);
            editPersonDescriptor.setBlock(ParserUtil.parseBlock(blockString));
            editPersonDescriptor.setRoom(ParserUtil.parseRoom(roomString));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        parseStudentGroupsForEdit(argMultimap.getAllValues(PREFIX_STUDENT_GROUP))
                .ifPresent(editPersonDescriptor::setStudentGroups);
        if (argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).isPresent()) {
            editPersonDescriptor.setMatriculationNumber(
                    ParserUtil.parseMatriculationNumber(argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> studentGroups} into a {@code Set<StudentGroup>} if {@code studentGroups}
     * is non-empty.
     * If {@code studentGroups} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<StudentGroup>} containing zero studentGroups.
     */
    private Optional<Set<StudentGroup>> parseStudentGroupsForEdit(Collection<String> studentGroups)
        throws ParseException {
        assert studentGroups != null;

        if (studentGroups.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> studentGroupSet = studentGroups.size() == 1 && studentGroups.contains("")
                ? Collections.emptySet() : studentGroups;
        return Optional.of(ParserUtil.parseStudentGroups(studentGroupSet));
    }

}
