package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import seedu.address.model.project.Project;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class MainCatalogueTest {

    private final MainCatalogue mainCatalogue = new MainCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mainCatalogue.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        MainCatalogue newData = getTypicalAddressBook();
        mainCatalogue.resetData(newData);
        assertEquals(newData, mainCatalogue);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two projects with the same identity fields
        Project editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Project> newProjects = Arrays.asList(ALICE, editedAlice);
        MainCatalogueStub newData = new MainCatalogueStub(newProjects);

        assertThrows(DuplicatePersonException.class, () -> mainCatalogue.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(mainCatalogue.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        mainCatalogue.addPerson(ALICE);
        assertTrue(mainCatalogue.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        mainCatalogue.addPerson(ALICE);
        Project editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(mainCatalogue.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mainCatalogue.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyMainCatalogue whose projects list can violate interface constraints.
     */
    private static class MainCatalogueStub implements ReadOnlyMainCatalogue {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        MainCatalogueStub(Collection<Project> projects) {
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Project> getPersonList() {
            return projects;
        }
    }

}
