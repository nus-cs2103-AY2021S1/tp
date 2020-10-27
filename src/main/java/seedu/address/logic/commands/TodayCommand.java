package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

import seedu.address.model.Model;

public class TodayCommand extends Command {
    public static final String COMMAND_WORD = "c-today";

    public static final String MESSAGE_SUCCESS = "Today is %2$s."
            + "\nThere are total %1$s employees working today."
            + "\nThey are listed below.";
    public static final String MESSAGE_NO_EMPLOYEE = "Today is %1$s."
            + "\nBased on the contact list, no employee is working today.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        LocalDate todayDate = LocalDate.now();
        DayOfWeek dayOfWeek = todayDate.getDayOfWeek();

        switch (dayOfWeek) {
        case SUNDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("sunday"));
            break;
        case MONDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("monday"));
            break;
        case TUESDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("tuesday"));
            break;
        case WEDNESDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("wednesday"));
            break;
        case THURSDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("thursday"));
            break;
        case FRIDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("friday"));
            break;
        case SATURDAY:
            model.updateFilteredPersonList(person -> person.getTags().toString().toLowerCase().contains("saturday"));
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
