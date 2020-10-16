package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;


public class CalculateCapCommand extends Command {
    private double cap;
    public static final String COMMAND_WORD = "calculatecap";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a zoom link to the module. "
            + "Parameters: " + "INDEX (must be a positive integer) "
            + PREFIX_ZOOM_LINK + "ZOOM LINK "
            + "Example: " + COMMAND_WORD + " "
            + "1" + PREFIX_ZOOM_LINK + "www.zoom.com";
    /**
     * Creates and initialises a new CalculateCapCommand object.
     */
    public CalculateCapCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
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
        double totalMC = 0.0;
        double totalGradePoints = 0.0;
        for (Module m : modules) {
            if (m.isCompleted()) {
                totalMC += m.getModularCredits();
                totalGradePoints += m.getGradeTracker().getGrade();
            }
        }
        return totalGradePoints / totalMC;
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
