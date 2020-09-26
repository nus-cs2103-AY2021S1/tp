package seedu.address.testutil;

import seedu.address.flashcard.Flashcard;
import seedu.address.model.QuickCache;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code QuickCache ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private QuickCache quickCache;

    public AddressBookBuilder() {
        quickCache = new QuickCache();
    }

    public AddressBookBuilder(QuickCache quickCache) {
        this.quickCache = quickCache;
    }

    /**
     * Adds a new {@code Person} to the {@code QuickCache} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        quickCache.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code QuickCache} that we are building.
     */
    public AddressBookBuilder withFlashcard(Flashcard flashcard) {
        quickCache.addFlashcard(flashcard);
        return this;
    }

    public QuickCache build() {
        return quickCache;
    }
}
