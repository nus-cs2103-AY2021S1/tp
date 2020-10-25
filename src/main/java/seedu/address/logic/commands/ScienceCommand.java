package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.storage.RequiredCommandMessages.SCIENCE_PATH;

import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.storage.RequiredCommandStorage;

public class ScienceCommand extends Command {
    public static final String COMMAND_WORD = "science";
    public static final String MESSAGE_SUCCESS = "These are the Science Modules that you can take:";
    public static final String MESSAGE_FAILURE = "There was an error loading the required modules :(";
    private ObservableList<Module> scienceModules;

    /**
     * Returns the scienceModules attribute of a given ScienceCommand object.
     * @return scienceModules attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getScienceModules() {
        return scienceModules;
    }

    /**
     * Loads the scienceModules attribute with Science Modules by using
     * the setRequiredScience() method from the RequiredCommandStorage class.
     * @throws IOException When the path in invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setScienceModules(String path) throws IOException, IllegalValueException {
        RequiredCommandStorage storage = new RequiredCommandStorage();
        storage.setRequiredScience(path);
        scienceModules = storage.getRequiredScience();
    }

    /**
     * Goes through the scienceModules attribute and parses all Science Modules, to be read by the user.
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
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
                modulesToAdd.append("\n").append(moduleToAdd);
            }
            return new CommandResult(MESSAGE_SUCCESS + modulesToAdd);
        } catch (IOException | IllegalValueException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
