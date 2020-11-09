package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.LINE;
import static seedu.address.commons.core.Messages.MESSAGE_GEM_FAILURE;
import static seedu.address.commons.core.Messages.MESSAGE_GEM_SUCCESS;
import static seedu.address.storage.GemCommandPaths.GEH_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GEH_SEM2_PATH;
import static seedu.address.storage.GemCommandPaths.GEQ_PATH;
import static seedu.address.storage.GemCommandPaths.GER_PATH;
import static seedu.address.storage.GemCommandPaths.GES_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GES_SEM2_PATH;
import static seedu.address.storage.GemCommandPaths.GET_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GET_SEM2_PATH;

import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.storage.GemCommandStorage;

public class GemCommand extends Command {
    private GemCommandStorage sem1Storage;
    private GemCommandStorage sem2Storage;
    private String compiledModules;

    /**
     * Retrieves the attribute sem1Storage of a GemCommand object.
     *
     * @return storage attribute of type GemCommandStorage.
     */
    public GemCommandStorage getSem1Storage() {
        return sem1Storage;
    }

    /**
     * Retrieves the attribute sem2Storage of a GemCommand object.
     *
     * @return storage attribute of type GemCommandStorage.
     */
    public GemCommandStorage getSem2Storage() {
        return sem2Storage;
    }

    /**
     * Loads the sem1Storage attribute with Semester 1 GE Modules by using
     * the various set() methods from the GemCommandStorage class.
     *
     * @throws IOException when the path in invalid.
     * @throws IllegalValueException when the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setSem1Storage() throws IOException, IllegalValueException {
        sem1Storage = new GemCommandStorage();
        sem1Storage.setGehModules(GEH_SEM1_PATH);
        sem1Storage.setGeqModules(GEQ_PATH);
        sem1Storage.setGerModules(GER_PATH);
        sem1Storage.setGesModules(GES_SEM1_PATH);
        sem1Storage.setGetModules(GET_SEM1_PATH);
    }

    /**
     * Loads the sem2Storage attribute with Semester 2 GE Modules by using
     * the various set() methods from the GemCommandStorage class.
     *
     * @throws IOException when the path in invalid.
     * @throws IllegalValueException when the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setSem2Storage() throws IOException, IllegalValueException {
        sem2Storage = new GemCommandStorage();
        sem2Storage.setGehModules(GEH_SEM2_PATH);
        sem2Storage.setGeqModules(GEQ_PATH);
        sem2Storage.setGerModules(GER_PATH);
        sem2Storage.setGesModules(GES_SEM2_PATH);
        sem2Storage.setGetModules(GET_SEM2_PATH);
    }

    /**
     * Sets up the all the GE modules in the sem1Storage and sem2Storage and displays them
     * with the CommandResult object.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult displaying all the available GE modules.
     */
    @Override
    public CommandResult execute(Model model) {
        try {
            requireNonNull(model);
            setSem1Storage();
            setSem2Storage();
            String modulesToAdd = "\n\nSemester 1:" + "\n\n";
            setCompiledModules(sem1Storage, model);
            modulesToAdd += compiledModules;
            modulesToAdd += "\n\n" + LINE + "Semester 2:" + "\n\n";
            setCompiledModules(sem2Storage, model);
            modulesToAdd += compiledModules;
            return new CommandResult(MESSAGE_GEM_SUCCESS + modulesToAdd);
        } catch (IOException | IllegalValueException e) {
            return new CommandResult(MESSAGE_GEM_FAILURE);
        }
    }

    /**
     * Takes a List of Modules and extracts out their Module Code and Modular Credits.
     *
     * @param modules List of Modules.
     * @param model {@code Model} which the command should operate on.
     * @return String of Module Codes and Modular Credits.
     */
    public StringBuilder moduleExtractor(ObservableList<Module> modules, Model model) {
        assert modules != null;
        StringBuilder modulesToAdd = new StringBuilder();
        for (Module module : modules) {
            if (!model.hasModule(module)) {
                String moduleToAdd = module.getModuleCode() + "\t" + module.getModuleTitle()
                    + " (" + module.getModularCredits() + " MCs)";
                modulesToAdd.append("\n").append(moduleToAdd);
            }
        }
        return modulesToAdd;
    }

    /**
     * Loads the compiledModules attribute with all the relevant GE modules in String form.
     *
     * @param storage Contains list of GE modules.
     * @param model {@code Model} which the command should operate on.
     */
    public void setCompiledModules(GemCommandStorage storage, Model model) {
        compiledModules = "";
        compiledModules += "Human Cultures\n" + moduleExtractor(storage.getGehModules(), model) + "\n\n";
        compiledModules += "Thinking and Expression\n" + moduleExtractor(storage.getGetModules(), model)
            + "\n\n";
        compiledModules += "Singapore Studies\n" + moduleExtractor(storage.getGesModules(), model) + "\n\n";
        compiledModules += "Asking Questions\n" + moduleExtractor(storage.getGeqModules(), model) + "\n\n";
        compiledModules += "Quantitative Reasoning\n" + moduleExtractor(storage.getGerModules(), model);
    }

    /**
     * Returns compiledModules attribute of GemCommandStorage object.
     *
     * @return compiledModules attribute of type String.
     */
    public String getCompiledModules() {
        return compiledModules;
    }
}
