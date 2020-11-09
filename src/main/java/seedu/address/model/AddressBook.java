package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private UniqueModuleList activeModules;
    private final UniqueModuleList semOneModules;
    private final UniqueModuleList semTwoModules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        semOneModules = new UniqueModuleList();
        semTwoModules = new UniqueModuleList();
        activeModules = semOneModules;
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the active module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setActiveModules(List<Module> modules) {
        this.activeModules.setModules(modules);
    }

    /**
     * Replaces the contents of the semester one list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setSemOneModules(List<Module> modules) {
        this.semOneModules.setModules(modules);
    }

    /**
     * Replaces the contents of the semester two list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setSemTwoModules(List<Module> modules) {
        this.semTwoModules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSemOneModules(newData.getSemOneModuleList());
        setSemTwoModules(newData.getSemTwoModuleList());
    }


    //// person-level operations
    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        reassignEditedInstructor(target, editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        deleteInstructorAssignments(key);
    }

    /**
     * Removes all the contacts from the list and unassigns all contacts from all modules.
     */
    public void clearContacts() {
        semOneModules.unassignAllInstructors();
        semTwoModules.unassignAllInstructors();
        persons.clearAll();
    }

    /**
     * Removes the module with the specified {@code moduleCode} from this {@code AddressBook}.
     * Module with the {@code moduleCode} must exist in the address book.
     */
    public void removeModule(ModuleCode moduleCode) {
        activeModules.removeModuleWithCode(moduleCode);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeModule(Module key) {
        activeModules.remove(key);
    }

    /**
     * Removes all the modules from the list.
     */
    public void clearMod() {
        activeModules.clearAll();
    }

    //// module-level operations
    /**
     * Returns true if a module with the same identity as {@code module} exists in the active module list.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return activeModules.contains(module);
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in the semester one module list.
     */
    public boolean hasSemOneModule(Module module) {
        requireNonNull(module);
        return semOneModules.contains(module);
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in the semester two module list.
     */
    public boolean hasSemTwoModule(Module module) {
        requireNonNull(module);
        return semTwoModules.contains(module);
    }

    /**
     * Returns true if a module with the same module code as {@code moduleCode} exists in the active module list.
     */
    public boolean hasModuleCode(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return activeModules.containsModuleCode(moduleCode);
    }

    /**
     * Adds a module to the address book.
     * The module must not already exist in the active module list.
     */
    public void addModule(Module m) {
        activeModules.add(m);
    }

    /**
     * Adds a module to the semester one module list.
     * The module must not already exist in the semester one module list.
     */
    public void addSemOneModule(Module m) {
        semOneModules.add(m);
    }

    /**
     * Adds a module to the semester two module list.
     * The module must not already exist in the semester two module list.
     */
    public void addSemTwoModule(Module m) {
        semTwoModules.add(m);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The module identity of {@code editedModule} must not be the same as another existing module in the address book.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        activeModules.setModule(target, editedModule);
    }

    /**
     * Assigns an {@code instructor} to the module with the given {@code moduleCode}.
     * The module with the {@code moduleCode} must exist in the address book.
     */
    public void assignInstructor(Person instructor, ModuleCode moduleCode) {
        requireAllNonNull(instructor, moduleCode);

        activeModules.assignInstructor(instructor, moduleCode);
    }

    public void unassignAllInstructors() {
        activeModules.unassignAllInstructors();
    }

    /**
     * Unassigns an {@code instructor} from the module with the given {@code moduleCode}.
     * The module with the {@code moduleCode} must exist in the address book.
     */
    public void unassignInstructor(Person instructor, ModuleCode moduleCode) {
        requireAllNonNull(instructor, moduleCode);

        activeModules.unassignInstructor(instructor, moduleCode);
    }

    /**
     * Unassigns an {@code instructor} from all the modules that contains that instructor.
     * The instructor must exist in at least one module.
     * @throws CommandException if {@code instructor} does not exist in any of the modules.
     */
    public void unassignInstructorFromAll(Person instructor) throws CommandException {
        requireAllNonNull(instructor);

        activeModules.unassignInstructorFromAll(instructor);
    }

    /**
     * Checks whether an {@code instructor} in the module with the given {@code moduleCode} exists.
     * The module with the {@code moduleCode} must exist in the address book.
     * @return true if the {@code instructor} is an instructor of the module with the {@code moduleCode}
     */
    public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
        requireAllNonNull(instructor, moduleCode);
        return activeModules.moduleCodeHasInstructor(moduleCode, instructor);
    }

    /**
     * Switches the active module list.
     * */
    public void switchModuleList() {
        activeModules = activeModules == semOneModules ? semTwoModules : semOneModules;
    }

    /**
     * Returns the active semester.
     */
    public int getSemester() {
        return activeModules == semOneModules ? 1 : 2;
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return activeModules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getSemOneModuleList() {
        return semOneModules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getSemTwoModuleList() {
        return semTwoModules.asUnmodifiableObservableList();
    }

    private void reassignEditedInstructor(Person target, Person editedPerson) {
        for (Module m: semOneModules) {
            if (m.hasInstructor(target)) {
                m.unassignInstructor(target);
                m.assignInstructor(editedPerson);
                semOneModules.setModule(m, m);
            }
        }

        for (Module m: semTwoModules) {
            if (m.hasInstructor(target)) {
                m.unassignInstructor(target);
                m.assignInstructor(editedPerson);
                semTwoModules.setModule(m, m);
            }
        }
    }

    private void deleteInstructorAssignments(Person toBeRemoved) {
        for (Module m : semOneModules) {
            if (m.hasInstructor(toBeRemoved)) {
                m.unassignInstructor(toBeRemoved);
                semOneModules.setModule(m, m);
            }
        }
        for (Module m : semTwoModules) {
            if (m.hasInstructor(toBeRemoved)) {
                m.unassignInstructor(toBeRemoved);
                semTwoModules.setModule(m, m);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && activeModules.equals(((AddressBook) other).activeModules)
                && semOneModules.equals(((AddressBook) other).semOneModules)
                && semTwoModules.equals(((AddressBook) other).semTwoModules));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons.hashCode(), activeModules.hashCode());
    }
}
