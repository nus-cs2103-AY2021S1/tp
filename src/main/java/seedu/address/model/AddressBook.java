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
    private final UniqueModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        modules = new UniqueModuleList();
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
     * Replaces the contents of the person list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setModules(newData.getModuleList());
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
    }

    public void clearContacts() {
        persons.clearAll();
    }

    /**
     * Removes the module with the specified {@code moduleCode} from this {@code AddressBook}.
     * Module with the {@code moduleCode} must exist in the address book.
     */
    public void removeModule(ModuleCode moduleCode) {
        modules.removeModuleWithCode(moduleCode);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    /**
     * Removes all the modules from the list.
     */
    public void clearMod() {
        modules.clearAll();
    }

    //// module-level operations
    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if a module with the same module code as {@code moduleCode} exists in the address book.
     */
    public boolean hasModuleCode(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return modules.containsModuleCode(moduleCode);
    }

    /**
     * Adds a module to the address book.
     * The module must not already exist in the address book.
     */
    public void addModule(Module m) {
        modules.add(m);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedModule} must not be the same as another existing person in the address book.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Assigns an {@code instructor} to the module with the given {@code moduleCode}.
     * The module with the {@code moduleCode} must exist in the address book.
     */
    public void assignInstructor(Person instructor, ModuleCode moduleCode) {
        requireAllNonNull(instructor, moduleCode);

        modules.assignInstructor(instructor, moduleCode);
    }

    public void unassignAllInstructors() {
        modules.unassignAllInstructors();
    }

    /**
     * Unassigns an {@code instructor} from the module with the given {@code moduleCode}.
     * The module with the {@code moduleCode} must exist in the address book.
     */
    public void unassignInstructor(Person instructor, ModuleCode moduleCode) {
        requireAllNonNull(instructor, moduleCode);

        modules.unassignInstructor(instructor, moduleCode);
    }

    /**
     * Unassigns an {@code instructor} from all the modules that contains that instructor.
     * The instructor must exist in at least one module.
     * @throws CommandException if {@code instructor} does not exist in any of the modules.
     */
    public void unassignInstructorFromAll(Person instructor) throws CommandException {
        requireAllNonNull(instructor);

        modules.unassignInstructorFromAll(instructor);
    }

    /**
     * Checks whether an {@code instructor} in the module with the given {@code moduleCode} exists.
     * The module with the {@code moduleCode} must exist in the address book.
     * @return true if the {@code instructor} is an instructor of the module with the {@code moduleCode}
     */
    public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
        requireAllNonNull(instructor, moduleCode);
        return modules.moduleCodeHasInstructor(moduleCode, instructor);
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
        return modules.asUnmodifiableObservableList();
    }

    private void reassignEditedInstructor(Person target, Person editedPerson) {
        for (Module m: modules) {
            if (m.hasInstructor(target)) {
                m.unassignInstructor(target);
                m.assignInstructor(editedPerson);
                modules.setModule(m, m);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && modules.equals(((AddressBook) other).modules));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons.hashCode(), modules.hashCode());
    }
}
