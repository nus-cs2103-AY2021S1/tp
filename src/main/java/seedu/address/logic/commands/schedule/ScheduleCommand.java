package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Finds and list all students with classes on a given date.
 * This is also the schedule of the user on that particular date.
 */
public abstract class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String INCORRECT_DATE_FORMAT = "Date should be in the format dd/mm/yyyy";
    public static final String EMPTY_DATE_MESSAGE = "Please input a date";

    public static final String COMMAND_SUCCESS_MESSAGE = "Here is your schedule";


    public ScheduleCommand() {};


}
