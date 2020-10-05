package tp.cap5buddy.contacts;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tp.cap5buddy.contacts.exceptions.ContactNotFoundException;
import tp.cap5buddy.contacts.exceptions.DuplicateContactException;

public class ContactList {

    public final List<Contact> contacts;

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean hasContact(Contact toCheck) {
        requireNonNull(toCheck);
        return contacts.stream().anyMatch(toCheck::isSameContact);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void addContact(Contact toAdd) {
        requireNonNull(toAdd);
        if (hasContact(toAdd)) {
            // throw new DuplicatePersonException();
        }
        contacts.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing
     * person in the list.
     */
    public void editContact(Contact target, Contact editedContact) throws
            ContactNotFoundException, DuplicateContactException {
        // requireAllNonNull(target, editedPerson);

        int index = contacts.indexOf(target);
        if (index == -1) {
            String error = "Invalid Person!";
            throw new ContactNotFoundException(error);
        }

        if (!target.isSameContact(editedContact) && hasContact(editedContact)) {
            String error = "The operation could not be completed as a similar person is already in the contact list!";
            throw new DuplicateContactException(error);
        }

        contacts.set(index, editedContact);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void removeContact(Contact toRemove) throws ContactNotFoundException {
        requireNonNull(toRemove);
        if (!contacts.remove(toRemove)) {
            String error = "The contact does not exist!";
            throw new ContactNotFoundException(error);
        }
    }

    public List<Contact> getContactList() {
        return this.contacts;
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean contactsAreUnique(List<Contact> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSameContact(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
