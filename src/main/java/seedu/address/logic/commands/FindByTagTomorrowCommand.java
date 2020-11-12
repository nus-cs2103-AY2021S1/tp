package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

import seedu.address.model.Model;

/**
 * Finds and lists all active(unarchived) persons in address book whose tag(s) contains the next-day's day (i.e.
 * Monday, Tuesday, etc).
 *
 * For example, assume today is Tuesday, after command "c-tomorrow", all active(unarchived) employees whose tag(s)
 * contains "wednesday", case-insensitive, will be listed out.
 */
public class FindByTagTomorrowCommand extends Command {
    public static final String COMMAND_WORD = "c-tomorrow";

    public static final String MESSAGE_SUCCESS = "Tomorrow is %2$s."
            + "\nThere are a total of %1$s employees available tomorrow."
            + "\nThey are listed below in the Employee Directory.";
    public static final String MESSAGE_NO_EMPLOYEE = "Tomorrow is %1$s."
            + "\nBased on the tCheck's Employee Directory, no employee is available tomorrow.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        LocalDate tmrDate = LocalDate.now().plusDays(1);
        DayOfWeek dayOfWeek = tmrDate.getDayOfWeek();

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
            return new CommandResult(String.format(MESSAGE_NO_EMPLOYEE, tmrDate.getDayOfWeek()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size(),
                tmrDate.getDayOfWeek()));
    }
}
