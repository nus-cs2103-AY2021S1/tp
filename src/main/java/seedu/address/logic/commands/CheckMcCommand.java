package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CHECKMC_SUCCESS;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Displays the cumulative Modular Credits of the modules in the GradPad.
 */
public class CheckMcCommand extends Command {
    private ObservableList<Module> modules;
    private double totalMc = 0;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        modules = model.getGradPad().getModuleList();

        for (Module module : modules) {
            totalMc += Double.parseDouble(module.getModularCredits().toString());
        }
        return new CommandResult(String.format(MESSAGE_CHECKMC_SUCCESS, totalMc));
    }
}
