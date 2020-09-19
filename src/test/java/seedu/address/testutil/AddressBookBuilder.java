package seedu.address.testutil;

import seedu.address.model.MainCatalogue;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code MainCatalogue ab = new AddressBookBuilder().withProject("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private MainCatalogue mainCatalogue;

    public AddressBookBuilder() {
        mainCatalogue = new MainCatalogue();
    }

    public AddressBookBuilder(MainCatalogue mainCatalogue) {
        this.mainCatalogue = mainCatalogue;
    }

    /**
     * Adds a new {@code Project} to the {@code MainCatalogue} that we are building.
     */
    public AddressBookBuilder withProject(Project project) {
        mainCatalogue.addProject(project);
        return this;
    }

    public MainCatalogue build() {
        return mainCatalogue;
    }
}
