package seedu.medibook.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.EditCommand;
import seedu.medibook.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;

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
                ArgumentTokenizer.tokenize(args, PREFIX_IC, PREFIX_NAME, PREFIX_DATE, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_ADDRESS, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_BLOOD_TYPE, PREFIX_ALLERGY,
                    PREFIX_CONDITION, PREFIX_TREATMENT);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_IC).isPresent()) {
            editPatientDescriptor.setIc(ParserUtil.parseIc(argMultimap.getValue(PREFIX_IC).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editPatientDescriptor.setDateOfBirth(ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPatientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL)).get());
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPatientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS)).get());
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            editPatientDescriptor.setHeight(ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT)).get());
        }
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            editPatientDescriptor.setWeight(ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT)).get());
        }
        if (argMultimap.getValue(PREFIX_BLOOD_TYPE).isPresent()) {
            editPatientDescriptor.setBloodType(ParserUtil
                    .parseBloodType(argMultimap.getValue(PREFIX_BLOOD_TYPE)).get());
        }
        parseAllergiesForEdit(argMultimap.getAllValues(PREFIX_ALLERGY)).ifPresent(editPatientDescriptor::setAllergies);
        parseConditionsForEdit(argMultimap.getAllValues(PREFIX_CONDITION))
                .ifPresent(editPatientDescriptor::setConditions);
        parseTreatmentsForEdit(argMultimap.getAllValues(PREFIX_TREATMENT))
                .ifPresent(editPatientDescriptor::setTreatments);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPatientDescriptor);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>} if {@code allergies} is non-empty.
     * If {@code allergies} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Allergy>} containing zero allergy tags.
     */
    private Optional<Set<Allergy>> parseAllergiesForEdit(Collection<String> allergies) throws ParseException {
        assert allergies != null;

        if (allergies.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> allergySet = allergies.size() == 1
            && allergies.contains("") ? Collections.emptySet() : allergies;
        return Optional.of(ParserUtil.parseAllergies(allergySet));
    }

    /**
     * Parses {@code Collection<String> conditions} into a {@code Set<Condition>} if {@code conditions} is non-empty.
     * If {@code conditions} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Condition>} containing zero condition tags.
     */
    private Optional<Set<Condition>> parseConditionsForEdit(Collection<String> conditions) throws ParseException {
        assert conditions != null;

        if (conditions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> conditionSet = conditions.size() == 1
            && conditions.contains("") ? Collections.emptySet() : conditions;
        return Optional.of(ParserUtil.parseConditions(conditionSet));
    }

    /**
     * Parses {@code Collection<String> treatments} into a {@code Set<Treatment>} if {@code treatments} is non-empty.
     * If {@code treatments} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Treatment>} containing zero treatment tags.
     */
    private Optional<Set<Treatment>> parseTreatmentsForEdit(Collection<String> treatments) throws ParseException {
        assert treatments != null;

        if (treatments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> treatmentSet = treatments.size() == 1
                && treatments.contains("") ? Collections.emptySet() : treatments;
        return Optional.of(ParserUtil.parseTreatments(treatmentSet));
    }

}
