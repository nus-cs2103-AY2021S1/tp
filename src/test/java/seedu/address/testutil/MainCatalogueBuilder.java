package seedu.address.testutil;

import seedu.address.model.MainCatalogue;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code MainCatalogue ab = new MainCatalogueBuilder().withProject("John", "Doe").build();}
 */
public class MainCatalogueBuilder {

    private MainCatalogue mainCatalogue;

    public MainCatalogueBuilder() {
        mainCatalogue = new MainCatalogue();
    }

    public MainCatalogueBuilder(MainCatalogue mainCatalogue) {
        this.mainCatalogue = mainCatalogue;
    }

    /**
     * Adds a new {@code Project} to the {@code MainCatalogue} that we are building.
     */
    public MainCatalogueBuilder withProject(Project project) {
        mainCatalogue.addProject(project);
        return this;
    }

    public MainCatalogue build() {
        return mainCatalogue;
    }
}
