package seedu.address.logic.parser;

import static java.lang.Integer.parseInt;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.Template;

public class CreateTemplateCommandParser implements ExerciseParser<AddTemplateCommand> {
    public AddTemplateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_CALORIES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_CALORIES)
            /*|| !argMultimap.getPreamble().isEmpty()*/) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTemplateCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get());

        Template template = new Template(name.fullName, description.value, parseInt(calories.value));

        return new AddTemplateCommand(template);
    }

    public Template parseTemp(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_CALORIES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_CALORIES)
            /*|| !argMultimap.getPreamble().isEmpty()*/) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTemplateCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get());

        Template template = new Template(name.fullName, description.value, parseInt(calories.value));

        return template;
    }


    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
