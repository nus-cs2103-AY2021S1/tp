package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

public class CalculateCapCommand extends Command {
    public static final String COMMAND_WORD = "calculatecap";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates the user's CAP "
            + "Example: " + COMMAND_WORD;
    private double cap;
    /**
     * Creates and initialises a new CalculateCapCommand object.
     */
    public CalculateCapCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredArchivedModuleList();
        lastShownList.addAll(model.getFilteredUnarchivedModuleList());
        cap = calculateCap(lastShownList);
        return new CommandResult(createSuccessMessage(cap));
    }

    /**
     * Creates a success message when the CAP has been successfully calculated.
     *
     * @param cap Double containing the calculated CAP
     * @return String containing the success message.
     */
    public String createSuccessMessage(double cap) {
        String message = "Your CAP for completed mods has been successfully calculated : " + Double.toString(cap);
        return message;
    }

    /**
     * Calculates CAP score with a given list of modules.
     * @param modules List of modules
     */
    public double calculateCap(List<Module> modules) {
        double totalPoints = 0.0;
        double totalMC = 0.0;
        for (Module m : modules) {
            if (m.isCompleted()) {
                double modularCredits = m.getModularCredits().moduleCredits;
                double gradePoints = m.getGradeTracker().getGradePoint().get().gradePoint;
                totalMC += modularCredits;
                totalPoints += gradePoints * modularCredits;
            }
        }
        return totalPoints / totalMC;
    }
    /**
     * Indicates if the application session has ended.
     *
     * @return False since the sessions has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
