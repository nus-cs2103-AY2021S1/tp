package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import seedu.address.model.MainCatalogue;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

public class TypicalMainCatalogue {
    /**
     * Returns an {@code MainCatalogue} with all the typical projects and persons.
     */
    public static MainCatalogue getTypicalMainCatalogue() {
        MainCatalogue ab = new MainCatalogue();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }
}
