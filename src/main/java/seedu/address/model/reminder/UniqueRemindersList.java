package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.reminder.exceptions.DuplicateReminderException;
import seedu.address.model.reminder.exceptions.ReminderNotFoundException;

/**
 * A list of reminders that enforces uniqueness between its elements and does not allow nulls.
 * An reminder is considered unique by comparing using {@code Reminder#isSameReminder(Reminder)}.
 * As such, adding and updating of reminders uses Reminder#isSameReminder(Reminder)
 * for equality so as to ensure that the reminder being added or updated is
 * unique in terms of identity in the UniqueRemindersList.
 * However, the removal of a reminder uses reminders#equals(Reminder) so
 * as to ensure that the reminder with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueRemindersList implements Iterable<Reminder> {

    private final ObservableList<Reminder> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reminder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent reminder as the given argument.
     */
    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReminder);
    }

    /**
     * Adds an reminder to the list.
     * The reminder must not already exist in the list.
     */
    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateReminderException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the reminder {@code target} in the list with {@code editedReminder}.
     * {@code target} must exist in the list.
     * The reminder identity of {@code editedReminder} must not be the same as another existing reminder in the list.
     */
    public void setReminder(Reminder target, Reminder editedReminder) {
        requireAllNonNull(target, editedReminder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReminderNotFoundException();
        }

        if (!target.isSameReminder(editedReminder) && contains(editedReminder)) {
            throw new DuplicateReminderException();
        }

        internalList.set(index, editedReminder);
    }

    /**
     * Removes the equivalent reminder from the list.
     * The reminder must exist in the list.
     */
    public void remove(Reminder toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReminderNotFoundException();
        }
    }

    public void setReminders(UniqueRemindersList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code reminders}.
     * {@code reminders} must not contain duplicate reminders.
     */
    public void setReminders(List<Reminder> reminders) {
        requireAllNonNull(reminders);
        if (!remindersAreUnique(reminders)) {
            throw new DuplicateReminderException();
        }

        internalList.setAll(reminders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRemindersList // instanceof handles nulls
                && internalList.equals(((UniqueRemindersList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code reminders} contains only unique reminders.
     */
    private boolean remindersAreUnique(List<Reminder> reminders) {
        for (int i = 0; i < reminders.size() - 1; i++) {
            for (int j = i + 1; j < reminders.size(); j++) {
                if (reminders.get(i).isSameReminder(reminders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if there are active reminders.
     */
    public boolean hasRemindersDue() {
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getReminderDate().getTime().toLocalDate().isEqual(LocalDate.now())
                    || internalList.get(i).getReminderDate().getTime().toLocalDate().isBefore(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }
}
