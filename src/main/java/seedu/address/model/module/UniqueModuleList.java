package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of modules that enforces uniqueness between its elements and does not allow nulls.
 * A module is considered unique by comparing using {@code Module#isSameModule(Module)}. As such, adding and updating of
 * modlues uses Module#isSameModule(Module) for equality so as to ensure that the module being added or updated is
 * unique in terms of identity in the ModuleList. However, the removal of a module uses Module#equals(Object) so
 * as to ensure that the module with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Module#isSameModule(Module)
 */
public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(Module moduleToCheck) {
        requireNonNull(moduleToCheck);
        return internalList.stream().anyMatch(moduleToCheck::isSameModule);
    }

    /**
     * Returns true if the list contains a module with the given module code.
     */
    public boolean containsModuleCode(ModuleCode moduleCodeToCheck) {
        requireNonNull(moduleCodeToCheck);
        return internalList.stream().anyMatch(module -> module.hasModuleCode(moduleCodeToCheck));
    }

    /**
     * Adds a module to the list.
     * The module must not already exist in the list.
     */
    public void add(Module moduleToAdd) {
        requireNonNull(moduleToAdd);
        if (contains(moduleToAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(moduleToAdd);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the list.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }

        if (!target.isSameModule(editedModule) && contains(editedModule)) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
    }

    /**
     * Assigns an instructor to the module with the equivalent module code from the list.
     * The module with the module code must exist in the list.
     */
    public void assignInstructor(Person instructor, ModuleCode moduleCodeToAssign) {
        requireAllNonNull(instructor, moduleCodeToAssign);
        int indexOfModuleToAssign = 0;
        while (!internalList.get(indexOfModuleToAssign).hasModuleCode(moduleCodeToAssign)
                && indexOfModuleToAssign < internalList.size()) {
            indexOfModuleToAssign++;
        }
        Module moduleToAssign = internalList.get(indexOfModuleToAssign);
        moduleToAssign.assignInstructor(instructor);
        internalList.set((indexOfModuleToAssign), moduleToAssign);
    }

    /**
     * Unassigns all instructors from all modules.
     */
    public void unassignAllInstructors() {
        for (int index = 0; index < internalList.size(); index++) {
            Module toSet = internalList.get(index);
            Module moduleWithEmptyInstructors = toSet.moduleWithEmptyInstructors();
            internalList.set((index), moduleWithEmptyInstructors);
        }
    }

    /**
     * Unassigns an instructor from the module with the equivalent module code from the list.
     * The module with the module code must exist in the list.
     */
    public void unassignInstructor(Person instructor, ModuleCode moduleToUnassign) {
        requireAllNonNull(instructor, moduleToUnassign);
        int indexOfModuleToUnassign = 0;
        while (!internalList.get(indexOfModuleToUnassign).hasModuleCode(moduleToUnassign)
                && indexOfModuleToUnassign < internalList.size()) {
            indexOfModuleToUnassign++;
        }
        Module toSet = internalList.get(indexOfModuleToUnassign);
        toSet.unassignInstructor(instructor);
        internalList.set((indexOfModuleToUnassign), toSet);
    }

    /**
     * Checks whether an {@code instructor} in the module with the given {@code moduleCode} exists.
     * The module with the {@code moduleCode} must exist in the address book.
     * @return true if the {@code instructor} is an instructor of the module with the {@code moduleCode}
     */
    public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
        requireAllNonNull(instructor, moduleCode);

        for (int index = 0; index < internalList.size(); index++) {

            Module moduleToCheck = internalList.get(index);
            if (moduleToCheck.hasModuleCode(moduleCode)
                && !moduleToCheck.hasInstructor(instructor)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Clear all the modules inside the list.
     */
    public void clearAll() {
        internalList.clear();
    }

    /**
     * Returns true if the internal list is empty.
     */
    public boolean isEmptyList() {
        return internalList.isEmpty();
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    /**
     * Removes the module with the equivalent module code from the list.
     * The module with the module code must exist in the list.
     */
    public void removeModuleWithCode(ModuleCode moduleToRemove) {
        requireNonNull(moduleToRemove);
        int indexOfModuleToRemove = 0;
        while (!internalList.get(indexOfModuleToRemove).hasModuleCode(moduleToRemove)
                && indexOfModuleToRemove < internalList.size()) {
            indexOfModuleToRemove++;
        }
        internalList.remove(indexOfModuleToRemove);
    }

    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        setModules(replacement.getInternalList());
    }


    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code modules} contains only unique modules.
     */
    private boolean modulesAreUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModule(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public ObservableList<Module> getInternalList() {
        return internalList;
    }
}
