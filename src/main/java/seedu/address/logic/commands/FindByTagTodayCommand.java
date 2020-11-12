package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

import seedu.address.model.Model;

/**
 * Finds and lists all active(unarchived) persons in address book whose tag(s) contains today's day (i.e. Monday,
 * Tuesday, etc).
 *
 * For example, assume today is Tuesday, after command "c-today", all active(unarchived) employees whose tag(s)
 * contains "tuesday", case-insensitive, will be listed out.
 */
public class FindByTagTodayCommand extends Command {
    public static final String COMMAND_WORD = "c-today";

    public static final String MESSAGE_SUCCESS = "Today is %2$s."
            + "\nThere are total %1$s employees available today."
            + "\nThey are listed below in the Employee Directory.";
    public static final String MESSAGE_NO_EMPLOYEE = "Today is %1$s."
            + "\nBased on the tCheck's Employee Directory, no employee is available today.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        LocalDate todayDate = LocalDate.now();
        DayOfWeek dayOfWeek = todayDate.getDayOfWeek();

        switch (dayOfWeek) {
        case SUNDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_SUNDAY_PERSONS);
            break;
        case MONDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_MONDAY_PERSONS);
            break;
        case TUESDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_TUESDAY_PERSONS);
            break;
        case WEDNESDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_WEDNESDAY_PERSONS);
            break;
        case THURSDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_THURSDAY_PERSONS);
            break;
        case FRIDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_FRIDAY_PERSONS);
            break;
        case SATURDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_SATURDAY_PERSONS);
            break;
        default:
            model.updateFilteredPersonList(person -> false);
        }

        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(String.format(MESSAGE_NO_EMPLOYEE, todayDate.getDayOfWeek()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size(),
                todayDate.getDayOfWeek()));
    }
}
