package seedu.address.logic.commands;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.NameComparator;

public class SortCommand extends Command {

    public static final String MESSAGE_USAGE = "empty command (to be filled)";
    public static final String[] SUPPORTED_COMPARISON_MEANS = new String[] {"name"};
    public static final String COMMAND_WORD = "sort";

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

        default:
            assert false; //shouldn't reach default branch
            throw new CommandException("Shouldn't be called"); //todo change later
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_SORTED, comparisonMeans));
    }
}
