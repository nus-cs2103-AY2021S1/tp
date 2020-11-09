package seedu.address.testutil.contact;

import seedu.address.model.ContactList;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building ContactList objects.
 * Example usage: <br>
 *     {@code ContactList list = new ContactListBuilder().withContact("John", "Doe").build();}
 */
public class ContactListBuilder {

    private ContactList contactList;

    public ContactListBuilder() {
        contactList = new ContactList();
    }

    public ContactListBuilder(ContactList contactList) {
        this.contactList = contactList;
    }

    /**
     * Creates a new Contact with the argument Contact.
     *
     * @param contact Contact.
     * @return ContactListBuilder object.
     */
    public ContactListBuilder withContact(Contact contact) {
        contactList.addContact(contact);
        return this;
    }

    public ContactList build() {
        return this.contactList;
    }
}
