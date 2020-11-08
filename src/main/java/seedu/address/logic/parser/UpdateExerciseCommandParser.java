package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateCommand.EditExerciseDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;

/**
 * Parses input arguments and creates a new UpdateExerciseCommand object
 */
public class UpdateExerciseCommandParser implements ExerciseParser<UpdateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateExerciseCommand
     * and returns an UpdateExerciseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION,
                        PREFIX_DATE, PREFIX_CALORIES, PREFIX_MUSCLE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateCommand.MESSAGE_USAGE), pe);
        }

        EditExerciseDescriptor editExerciseDescriptor =
                new UpdateCommand.EditExerciseDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editExerciseDescriptor.setName(ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editExerciseDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editExerciseDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_CALORIES).isPresent()) {
            editExerciseDescriptor.setCalories(ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get()));
        }

        parseMuscleTagsForEdit(argMultimap.getAllValues(PREFIX_MUSCLE))
                .ifPresent(editExerciseDescriptor::setMuscleTags);

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editExerciseDescriptor::setTags);

        if (!editExerciseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateCommand(index, editExerciseDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ExerciseTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<ExerciseTag>} containing zero tags.
     */
    private Optional<Set<ExerciseTag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseExerciseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> muscleTags} into a {@code Set<MuscleTag>} if {@code muscleTags} is non-empty.
     * If {@code muscleTags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<MuscleTag>} containing zero tags.
     */
    private Optional<Set<MuscleTag>> parseMuscleTagsForEdit(Collection<String> muscleTags) throws ParseException {
        assert muscleTags != null;

        if (muscleTags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> muscleTagSet = muscleTags.size() == 1 && muscleTags.contains("")
                ? Collections.emptySet() : muscleTags;
        return Optional.of(ParserUtil.parseMuscleTags(muscleTagSet));
    }
}
