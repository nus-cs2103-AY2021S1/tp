package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.Template;
import seedu.address.model.exercise.TemplateList;


public class AddExerciseFromTemplateParser implements ExerciseParser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddExerciseFromTemplateCommand
     * and returns an AddExerciseFromTemplateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEMP, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_CALORIES,
                        PREFIX_MUSCLE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TEMP, PREFIX_DESCRIPTION,
                PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.address.logic.commands.AddExerciseFromTemplate.MESSAGE_USAGE));
        }

        Name templateName = ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_TEMP).get());

        Template template = TemplateList.getTemp(templateName.toString());

        if (template == null) {
            throw new ParseException("The template does not exist.");
        }

        Name name = ParserUtil.parseExerciseName(template.getName());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES)
                .orElse(template.getCalories().toString()));

        Set<MuscleTag> muscleTagList = ParserUtil.parseMuscleTags(argMultimap.getAllValues(PREFIX_MUSCLE));

        if (muscleTagList.isEmpty()) {
            muscleTagList = template.getMuscleTags();
        }

        Set<ExerciseTag> tagList = ParserUtil.parseExerciseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (tagList.isEmpty()) {
            tagList = template.getTags();
        }

        Exercise exercise = new Exercise(name, description, date, calories, muscleTagList, tagList);

        return new AddCommand(exercise);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
