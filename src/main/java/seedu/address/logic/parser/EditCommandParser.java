package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAnimalDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medicalcondition.MedicalCondition;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_SPECIES, PREFIX_MEDICAL_CONDITION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditAnimalDescriptor editAnimalDescriptor = new EditAnimalDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAnimalDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editAnimalDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_SPECIES).isPresent()) {
            editAnimalDescriptor.setSpecies(ParserUtil.parseSpecies(argMultimap.getValue(PREFIX_SPECIES).get()));
        }
        parseMedicalConditionsForEdit(argMultimap.getAllValues(PREFIX_MEDICAL_CONDITION))
                .ifPresent(editAnimalDescriptor::setMedicalConditions);

        if (!editAnimalDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editAnimalDescriptor);
    }

    /**
     * Parses {@code Collection<String> medicalConditions} into a {@code Set<MedicalCondition>}
     * if {@code medicalConditions} is non-empty.
     * If {@code medicalConditions} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<MedicalCondition>} containing zero medicalConditions.
     */
    private Optional<Set<MedicalCondition>> parseMedicalConditionsForEdit(
            Collection<String> medicalConditions) throws ParseException {
        assert medicalConditions != null;

        if (medicalConditions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medicalConditionSet = medicalConditions.size() == 1 && medicalConditions.contains("")
                ? Collections.emptySet() : medicalConditions;
        return Optional.of(ParserUtil.parseMedicalConditions(medicalConditionSet));
    }

}
