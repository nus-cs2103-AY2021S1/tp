package seedu.address.model;

import static java.time.temporal.ChronoUnit.WEEKS;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.task.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final SortedList<Person> sortedPersons;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Task> sortedTasks;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> dueSoonTasks;
    private final TaskList taskList;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyTaskList taskList) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.taskList = new TaskList(taskList);
        sortedPersons = new SortedList<>(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(sortedPersons);
        sortedTasks = new SortedList<>(this.taskList.getTaskList());
        filteredTasks = new FilteredList<>(sortedTasks);
        dueSoonTasks = new FilteredList<>(sortedTasks, task -> {
            if (task.getStatus()) {
                return false;
            } else {
                LocalDateTime currentDateTimePlusOneWeek = LocalDateTime.now().plus(1, WEEKS);
                LocalDateTime deadline = null;
                if (task instanceof Todo) {
                    deadline = task.getDeadline();
                } else if (task instanceof Event) {
                    deadline = task.getEnd();
                }
                assert deadline != null : "Task's deadline is not defined properly!";
                return deadline.isBefore(currentDateTimePlusOneWeek) && deadline.isAfter(LocalDateTime.now());
            }
        });
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new TaskList());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean filteredAddressBookIsEmpty() {
        return filteredPersons.isEmpty();
    }
    @Override
    public boolean addressBookIsEmpty() {
        return addressBook.isEmpty();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks);
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> personComparator) {
        sortedPersons.setComparator(personComparator);
    }
    //=========== TaskList ================================================================================
    @Override
    public void addTodo(Todo todo) {
        taskList.addTask(todo);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }
    @Override
    public void addEvent(Event event) {
        this.taskList.addTask(event);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }
    @Override
    public void addTask(Task task) {
        this.taskList.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }
    @Override
    public void deleteTodo(Task task) {
        this.taskList.removeTask(task);
    }
    @Override
    public void deleteEvent(Task task) {
        this.taskList.removeTask(task);
    }
    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    public boolean hasTask(Task task) {
        return this.taskList.hasTask(task);
    }
    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        taskList.setTask(target, editedTask);
    }
    @Override
    public void markAsDone(Task target) {
        requireAllNonNull(target);
        taskList.markAsDone(target);
    }
    @Override
    public boolean filteredTaskListIsEmpty() {
        return filteredTasks.isEmpty();
    }

    @Override
    public boolean taskListIsEmpty() {
        return taskList.isEmpty();
    }

    //=========== Filtered Task List Accessors =============================================================
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public ObservableList<Task> getDueSoonTaskList() {
        return dueSoonTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<? super Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }
    @Override
    public void updateSortedTaskList(Comparator<Task> taskComparator) {
        sortedTasks.setComparator(taskComparator);
    }

}
