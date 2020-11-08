package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddHeightCommand;
import seedu.address.logic.commands.AddWeightCommand;
import seedu.address.logic.commands.CalorieAddCommand;
import seedu.address.logic.commands.CalorieMinusCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.exercise.ExerciseAddCommand;
import seedu.address.logic.commands.exercise.ExerciseDeleteCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand;
import seedu.address.logic.commands.exercise.ExerciseFindCommand;
import seedu.address.logic.commands.exercise.ExerciseListCommand;
import seedu.address.logic.commands.lessons.LessonAddCommand;
import seedu.address.logic.commands.lessons.LessonDeleteCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand;
import seedu.address.logic.commands.lessons.LessonFindCommand;
import seedu.address.logic.commands.lessons.LessonListCommand;
import seedu.address.logic.commands.routines.RoutineAddExerciseCommand;
import seedu.address.logic.commands.routines.RoutineCreateCommand;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;
import seedu.address.logic.commands.routines.RoutineDeleteExerciseCommand;
import seedu.address.logic.commands.routines.RoutineFindCommand;
import seedu.address.logic.commands.routines.RoutineListCommand;
import seedu.address.logic.commands.routines.RoutineViewCommand;
import seedu.address.logic.commands.timetable.TimetableAddLessonCommand;
import seedu.address.logic.commands.timetable.TimetableAddRoutineCommand;
import seedu.address.logic.commands.timetable.TimetableDeleteSlotCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exercise.ExerciseAddCommandParser;
import seedu.address.logic.parser.exercise.ExerciseDeleteCommandParser;
import seedu.address.logic.parser.exercise.ExerciseEditCommandParser;
import seedu.address.logic.parser.exercise.ExerciseFindCommandParser;
import seedu.address.logic.parser.lessons.LessonAddCommandParser;
import seedu.address.logic.parser.lessons.LessonDeleteCommandParser;
import seedu.address.logic.parser.lessons.LessonEditCommandParser;
import seedu.address.logic.parser.lessons.LessonFindCommandParser;
import seedu.address.logic.parser.routines.RoutineAddExerciseCommandParser;
import seedu.address.logic.parser.routines.RoutineCreateCommandParser;
import seedu.address.logic.parser.routines.RoutineDeleteCommandParser;
import seedu.address.logic.parser.routines.RoutineDeleteExerciseCommandParser;
import seedu.address.logic.parser.routines.RoutineFindCommandParser;
import seedu.address.logic.parser.routines.RoutineViewCommandParser;
import seedu.address.logic.parser.timetable.TimetableAddLessonCommandParser;
import seedu.address.logic.parser.timetable.TimetableAddRoutineCommandParser;
import seedu.address.logic.parser.timetable.TimetableDeleteSlotCommandParser;

/**
 * Parses user input.
 */
public class FitNusParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(arguments);

        case AddHeightCommand.COMMAND_WORD:
            return new AddHeightCommandParser().parse(arguments);

        case AddWeightCommand.COMMAND_WORD:
            return new AddWeightCommandParser().parse(arguments);

        case CalorieAddCommand.COMMAND_WORD:
            return new CalorieAddCommandParser().parse(arguments);

        case CalorieMinusCommand.COMMAND_WORD:
            return new CalorieMinusCommandParser().parse(arguments);

        case ExerciseAddCommand.COMMAND_WORD:
            return new ExerciseAddCommandParser().parse(arguments);

        case ExerciseDeleteCommand.COMMAND_WORD:
            return new ExerciseDeleteCommandParser().parse(arguments);

        case ExerciseEditCommand.COMMAND_WORD:
            return new ExerciseEditCommandParser().parse(arguments);

        case ExerciseFindCommand.COMMAND_WORD:
            return new ExerciseFindCommandParser().parse(arguments);

        case ExerciseListCommand.COMMAND_WORD:
            return new ExerciseListCommand();

        case RoutineCreateCommand.COMMAND_WORD:
            return new RoutineCreateCommandParser().parse(arguments);

        case RoutineDeleteCommand.COMMAND_WORD:
            return new RoutineDeleteCommandParser().parse(arguments);

        case RoutineFindCommand.COMMAND_WORD:
            return new RoutineFindCommandParser().parse(arguments);

        case RoutineListCommand.COMMAND_WORD:
            return new RoutineListCommand();

        case RoutineAddExerciseCommand.COMMAND_WORD:
            return new RoutineAddExerciseCommandParser().parse(arguments);

        case RoutineDeleteExerciseCommand.COMMAND_WORD:
            return new RoutineDeleteExerciseCommandParser().parse(arguments);

        case RoutineViewCommand.COMMAND_WORD:
            return new RoutineViewCommandParser().parse(arguments);

        case LessonAddCommand.COMMAND_WORD:
            return new LessonAddCommandParser().parse(arguments);

        case LessonDeleteCommand.COMMAND_WORD:
            return new LessonDeleteCommandParser().parse(arguments);

        case LessonEditCommand.COMMAND_WORD:
            return new LessonEditCommandParser().parse(arguments);

        case LessonFindCommand.COMMAND_WORD:
            return new LessonFindCommandParser().parse(arguments);

        case LessonListCommand.COMMAND_WORD:
            return new LessonListCommand();

        case TimetableAddRoutineCommand.COMMAND_WORD:
            return new TimetableAddRoutineCommandParser().parse(arguments);

        case TimetableAddLessonCommand.COMMAND_WORD:
            return new TimetableAddLessonCommandParser().parse(arguments);

        case TimetableDeleteSlotCommand.COMMAND_WORD:
            return new TimetableDeleteSlotCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
