package seedu.address.logic.commands;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.NameComparator;
import seedu.address.model.student.YearComparator;
import seedu.address.model.student.admin.ClassTimeComparator;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of students in Reeve by a given"
            + " sorting method.\n"
            + "Supported sorting methods: "
            + "name: sorts students by their name (case insensitive)"
            + "classTime: sorts students by the day followed by time of their class"
            + "year: sorts students by the year they are in school, with Primary 1 coming first"
            + "and JC 2 coming last.\n\n"
            + "Example: "
            + COMMAND_WORD + " " + "year";

    public static final String[] SUPPORTED_COMPARISON_MEANS = new String[] {"name", "classTime", "year"};

    private final String comparisonMeans;

    public SortCommand(String comparisonMeans) {
        this.comparisonMeans = comparisonMeans;
    }

    public static boolean isValidComparisonMeans(String means) {
        return Arrays.asList(SUPPORTED_COMPARISON_MEANS).contains(means);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        switch (comparisonMeans) {

        case "name":
            model.updateSortedStudentList(new NameComparator());
            break;

        case "classTime":
            model.updateSortedStudentList(new ClassTimeComparator());
            break;

        case "year":
            model.updateSortedStudentList(new YearComparator());
            break;

        default:
            assert false; //shouldn't reach default branch
            throw new CommandException("Shouldn't be called");
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_SORTED, comparisonMeans));
    }
}
