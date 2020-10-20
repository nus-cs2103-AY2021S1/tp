package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleList moduleList;
    private final UserPrefs userPrefs;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(moduleList, userPrefs);

        logger.fine("Initializing with address book: " + moduleList + " and user prefs " + userPrefs);

        this.moduleList = new ModuleList(moduleList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredModules = new FilteredList<>(this.moduleList.getModuleList());
    }

    public ModelManager() {
        this(new ModuleList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getModuleListFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setModuleListFilePath(Path moduleListFilePath) {
        requireNonNull(moduleListFilePath);
        userPrefs.setAddressBookFilePath(moduleListFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setModuleList(ReadOnlyModuleList moduleList) {
        this.moduleList.resetData(moduleList);
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return moduleList;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        moduleList.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        moduleList.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        moduleList.setModule(target, editedModule);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    //    @Override
    //    public void addTutorialGroup(TutorialGroup tutorialGroup) {
    //        moduleList.addTutorialGroup(tutorialGroup);
    //    }
    //
    //    @Override
    //    public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
    //        requireAllNonNull(tutorialGroup);
    //        return moduleList.hasTutorialGroup(tutorialGroup);
    //    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return moduleList.equals(other.moduleList)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules);
    }

}
