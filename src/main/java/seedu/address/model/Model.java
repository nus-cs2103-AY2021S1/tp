package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.ReadOnlyReminders;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.tag.ReadOnlyTagTree;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' calendar file path.
     */
    Path getCalendarFilePath();

    /**
     * Returns the user prefs' tagtree file path.
     */
    Path getTagTreeFilePath();

    /**
     * Returns the user prefs' reminder file path.
     */
    Path getRemindersFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Sets the user prefs' calendar file path.
     */
    void setCalendarFilePath(Path calendarFilePath);

    /**
     * Sets the user prefs' calender file path.
     */
    void setTagTreeFilePath(Path tagTreeFilePath);

    /**
     * Sets the user prefs' reminder file path.
     */
    void setRemindersFilePath(Path remindersFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setCalendar(ReadOnlyCalendar calendar);

    /** Returns the Calendar */
    ReadOnlyCalendar getCalendar();

    /**
     * Replaces the tag tree data with the data in {@code tagTree}.
     */
    void setTagTree(ReadOnlyTagTree tagTree);

    /**
     * Returns the TagTree.
     */
    ReadOnlyTagTree getTagTree();

    /**
     * Replaces the reminders data with the data in {@code reminders}.
     */
    void setReminders(ReadOnlyReminders reminders);

    /**
     * Returns the Reminders.
     */
    ReadOnlyReminders getReminders();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the calendar.
     */
    boolean hasEvent(Event event);

    /**
     * Returns true if an event that clashes in time with {@code event} exists in the calendar.
     */
    boolean hasClashingEvent(Event event);

    /**
     * Returns true if the {@code tag} exists with at least one sub-tag and/or person assigned to it.
     */
    boolean hasTag(Tag tag);

    /**
     * Returns true if the {@code reminder} exists.
     */
    boolean hasReminder(Reminder reminder);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given event.
     * The person must exist in the calendar.
     */
    void deleteEvent(Event target);

    /**
     * Deletes the given {@code tag}.
     * All contacts with the assigned {@code tag} will have it removed as well.
     * All parent-tags of {@code tag} will be reconnected with the child-tags of {@code tag}.
     */
    void deleteTag(Tag tag);

    /**
     * Deletes the given {@code tag} and all its sub-tags.
     * Sub-tags include child-tags of {@code tag}, child-tags of child-tags, etc.
     */
    void deleteTagRecursive(Tag tag);

    /**
     * Deletes the given {@code reminder}.
     */
    void deleteReminder(Reminder reminder);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given person.
     */
    void addEvent(Event event);

    /**
     * Adds the reminder.
     */
    void addReminder(Reminder reminder);


    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in Athena.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the Athena.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getSortedFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered event list.
     */
    ObservableList<Event> getSortedFilteredEventList();

    /**
     * Returns an unmodifiable view of the filtered reminders list.
     * @return
     */
    ObservableList<Reminder> getSortedFilteredReminderList();

    // Tag-related operations

    /**
     * Returns a set of all {@code person}s containing the {@code tag}.
     */
    Set<Person> getPersonsWithTag(Tag tag);

    /**
     * Returns a set of all {@code tag}s with at least one {@code person} tagged, present in the {@code Model}.
     */
    Set<Tag> getPersonTags();

    /**
     * Returns a set of all {@code tag}s with at least one sub-tag.
     */
    Set<Tag> getSuperTags();

    /**
     * Returns a set of all child-tags of {@code tag}.
     */
    Set<Tag> getChildTags(Tag tag);

    /**
     * Returns a set of all tags below the provided {@code tag} in the tag hierarchy.
     * I.e. all sub-tags, all sub-tags of those sub-tags, etc.
     */
    Set<Tag> getSubTagsRecursive(Tag tag);

    /**
     * Returns a set of all {@code person}s under the specified {@code tag} and any of the tags below {@code tag}
     * in the tag hierarchy.
     * I.e. all persons with either {@code tag}, and/or any of its sub-tags, sub-tag of sub-tags, etc.
     */
    Set<Person> getPersonsRecursive(Tag tag);

    /**
     * Adds {@code person} to {@code tag}.
     * {@code person} will also reflect this change with a new {@code tag}.
     */
    void addPersonToTag(Tag tag, Person person);

    /**
     * Removes {@code person} from {@code tag}.
     * {@code person} will also reflect this change with {@code tag} removed.
     */
    void removePersonFromTag(Tag tag, Person person);

    /**
     * Removes all contacts with the specific tag.
     */
    void deletePersonsByTag(Tag tag);

    /**
     * Adds {@code subTag} as a sub-tag of {@code superTag}.
     */
    void addSubTagTo(Tag superTag, Tag subTag);

    /**
     * Removes {@code childTag} as a child-tag of {@code parentTag}.
     */
    void removeChildTagFrom(Tag parentTag, Tag childTag);

    /**
     * Returns true if {@code subTag} is a sub-tag of {@code superTag}.
     */
    boolean isSubTagOf(Tag superTag, Tag subTag);

    // Filter/sort-list methods

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Wraps the filtered list of persons in a sortedList with a specific comparator
     */
    void sortPerson(Comparator<Person> comparator);

    /**
     * Permanently sorts the address book by a specific comparator
     */
    void permaSortContacts(Comparator<Person> chooseComparator);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    void sortEvent(Comparator<Event> comparator);

    void deleteObsoleteReminders();

}
