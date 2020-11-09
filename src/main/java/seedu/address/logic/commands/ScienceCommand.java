package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FAILURE_SCIENCE;
import static seedu.address.commons.core.Messages.MESSAGE_SCIENCE_SUCCESS;
import static seedu.address.storage.RequiredCommandMessages.SCIENCE_PATH;

import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.storage.RequiredCommandStorage;

public class ScienceCommand extends Command {
    private ObservableList<Module> scienceModules;

    /**
     * Returns the scienceModules attribute of a given ScienceCommand object.
     *
     * @return scienceModules attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getScienceModules() {
        return scienceModules;
    }

    /**
     * Loads the scienceModules attribute with Science Modules by using
     * the setRequiredScience() method from the RequiredCommandStorage class.
     *
     * @param path Path of the Science Modules file.
     * @throws IOException When the path in invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setScienceModules(String path) throws IOException, IllegalValueException {
        RequiredCommandStorage storage = new RequiredCommandStorage();
        storage.setRequiredScience(path);
        scienceModules = storage.getRequiredScience();
    }

    /**
     * Goes through the scienceModules attribute and parses all Science Modules, to be read by the user.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object with the relevant Science Modules or Failure Message if modules
     * are absent.
     */
    @Override
    public CommandResult execute(Model model) {
        try {
            requireNonNull(model);
            setScienceModules(SCIENCE_PATH);
            StringBuilder modulesToAdd = new StringBuilder();
            for (Module module : scienceModules) {
                if (!model.hasModule(module)) {
                    String moduleToAdd = module.getModuleCode() + "\t" + module.getModuleTitle()
                        + " (" + module.getModularCredits() + " MCs)";
                    modulesToAdd.append("\n").append(moduleToAdd);
                }
            }
            return new CommandResult(MESSAGE_SCIENCE_SUCCESS + "\n" + modulesToAdd);
        } catch (IOException | IllegalValueException e) {
            return new CommandResult(MESSAGE_FAILURE_SCIENCE);
        }
    }
}
