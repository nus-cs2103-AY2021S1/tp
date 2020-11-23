package seedu.address.logic.parser.hairdresser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.hairdresser.EditHairdresserCommand;
import seedu.address.logic.commands.hairdresser.EditHairdresserCommand.EditHairdresserDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.model.specialisation.Specialisation;

/**
 * Parses input arguments and creates a new EditHairdresserCommand object
 */
public class EditHairdresserCommandParser implements Parser<EditHairdresserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditHairdresserCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_GENDER, PREFIX_TITLE, PREFIX_SPECIALISATION);

        HairdresserId hairdresserId;

        try {
            hairdresserId = ParserUtil.parseHairdresserId(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditHairdresserCommand.MESSAGE_USAGE), pe);
        }

        EditHairdresserDescriptor editHairdresserDescriptor = new EditHairdresserDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editHairdresserDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editHairdresserDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editHairdresserDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editHairdresserDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editHairdresserDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        parseSpecsForEdit(argMultimap.getAllValues(PREFIX_SPECIALISATION))
                .ifPresent(editHairdresserDescriptor::setSpecs);

        if (!editHairdresserDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditHairdresserCommand.MESSAGE_NOT_EDITED);
        }

        return new EditHairdresserCommand(hairdresserId, editHairdresserDescriptor);
    }
    /**
     * Parses {@code Collection<String> specs} into a {@code Set<Specialisation>} if {@code specs} is non-empty.
     * If {@code specs} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Specialisation>} containing zero specs.
     */
    private Optional<Set<Specialisation>> parseSpecsForEdit(Collection<String> specs) throws ParseException {
        assert specs != null;

        if (specs.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> specSet = specs.size() == 1 && specs.contains("") ? Collections.emptySet() : specs;
        return Optional.of(ParserUtil.parseSpecialisations(specSet));
    }


}
