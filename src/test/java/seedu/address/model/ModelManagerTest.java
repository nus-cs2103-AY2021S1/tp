package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_ADDRESS_BOB;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTags.CS2040S;
import static seedu.address.testutil.TypicalTags.CS2103T;
import static seedu.address.testutil.TypicalTags.GROUPMATE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameComparator;
import seedu.address.model.person.PersonNameContainsKeywordsPredicate;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagNameComparator;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProjactBuilder;
import seedu.address.testutil.TagBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Projact(), new Projact(modelManager.getProjact()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setProjactFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setProjactFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setProjactFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setProjactFilePath(null));
    }

    @Test
    public void setProjactFilePath_validPath_setsProjactFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setProjactFilePath(path);
        assertEquals(path, modelManager.getProjactFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInModelManager_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInModelManager_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInModelManager_returnsTrue() {
        modelManager.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTelegramAddress(VALID_TELEGRAM_ADDRESS_BOB)
                .withTagNames(VALID_TAG_NAME_HUSBAND).build();
        assertTrue(modelManager.hasPerson(editedAlice));
    }

    @Test
    public void addPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addPerson(null));
    }

    @Test
    public void addPerson_personNotInModelManager_success() {
        assertFalse(modelManager.hasPerson(ALICE));
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void addPerson_personInModelManager_throwsDuplicatePersonException() {
        modelManager.addPerson(ALICE);
        assertThrows(DuplicatePersonException.class, () -> modelManager.addPerson(ALICE));
    }

    @Test
    public void setPerson_nullTarget_throwsNullPointerException() {
        modelManager.addPerson(ALICE);
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedTarget_throwsNullPointerException() {
        modelManager.addPerson(ALICE);
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_nonExistentTarget_throwsPersonNotFoundException() {
        modelManager.addPerson(ALICE);
        assertThrows(PersonNotFoundException.class, () -> modelManager.setPerson(BENSON, ALICE));
    }

    @Test
    public void setPerson_unchangedEditedPerson_success() {
        modelManager.addPerson(ALICE);
        modelManager.setPerson(ALICE, ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void setPerson_valueFields_success() {
        modelManager.addPerson(ALICE);
        modelManager.setPerson(ALICE, BENSON);
        assertFalse(modelManager.hasPerson(ALICE));
        assertTrue(modelManager.hasPerson(BENSON));
    }

    @Test
    public void removePerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deletePerson(null));
    }

    @Test
    public void removePerson_personNotInModelManager_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> modelManager.deletePerson(ALICE));
    }

    @Test
    public void removePerson_personInModelManager_success() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
        modelManager.deletePerson(ALICE);
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInModelManager_returnsFalse() {
        assertFalse(modelManager.hasTag(CS2103T));
    }

    @Test
    public void hasTag_tagInModelManager_returnsTrue() {
        modelManager.addTag(CS2103T);
        assertTrue(modelManager.hasTag(CS2103T));
    }

    @Test
    public void hasTag_tagWithSameNameInModelManager_returnsTrue() {
        modelManager.addTag(CS2103T);
        Tag editedTag = new TagBuilder(CS2103T).build();
        assertTrue(modelManager.hasTag(editedTag));
    }

    @Test
    public void addTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addTag(null));
    }

    @Test
    public void addTag_tagNotInModelManager_success() {
        assertFalse(modelManager.hasTag(CS2103T));
        modelManager.addTag(CS2103T);
        assertTrue(modelManager.hasTag(CS2103T));
    }

    @Test
    public void addTag_tagInModelManager_throwsDuplicateTagException() {
        modelManager.addTag(CS2103T);
        assertThrows(DuplicateTagException.class, () -> modelManager.addTag(CS2103T));
    }

    @Test
    public void setTag_nullTarget_throwsNullPointerException() {
        modelManager.addTag(CS2103T);
        assertThrows(NullPointerException.class, () -> modelManager.setTag(null, CS2103T));
    }

    @Test
    public void setTag_nullEditedTarget_throwsNullPointerException() {
        modelManager.addTag(CS2103T);
        assertThrows(NullPointerException.class, () -> modelManager.setTag(CS2103T, null));
    }

    @Test
    public void setTag_nonExistentTarget_throwsTagNotFoundException() {
        modelManager.addTag(CS2103T);
        assertThrows(TagNotFoundException.class, () -> modelManager.setTag(GROUPMATE, CS2103T));
    }

    @Test
    public void setTag_unchangedEditedTag_success() {
        modelManager.addTag(CS2103T);
        modelManager.setTag(CS2103T, CS2103T);
        assertTrue(modelManager.hasTag(CS2103T));
    }

    @Test
    public void setTag_valueFields_success() {
        modelManager.addTag(CS2103T);
        modelManager.setTag(CS2103T, GROUPMATE);
        assertFalse(modelManager.hasTag(CS2103T));
        assertTrue(modelManager.hasTag(GROUPMATE));
    }

    @Test
    public void removeTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteTag(null));
    }

    @Test
    public void removeTag_tagNotInModelManager_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> modelManager.deleteTag(GROUPMATE));
    }

    @Test
    public void removeTag_tagInModelManager_success() {
        modelManager.addTag(GROUPMATE);
        assertTrue(modelManager.hasTag(GROUPMATE));
        modelManager.deleteTag(GROUPMATE);
        assertFalse(modelManager.hasTag(GROUPMATE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTagList().remove(0));
    }

    @Test
    public void getSortedPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getSortedPersonList().remove(0));
    }

    @Test
    public void equals() {
        Projact projact = new ProjactBuilder()
                .withPerson(ALICE)
                .withPerson(BENSON)
                .withTag(CS2040S)
                .build();
        Projact differentProjact = new Projact();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(projact, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(projact, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different projact -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentProjact, userPrefs)));

        // different filteredPersonList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new PersonNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(projact, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different filteredTagList -> returns false
        keywords = CS2103T.getTagName().tagName.split("\\s+");
        modelManager.updateFilteredTagList(new TagNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(projact, userPrefs)));


        // different sortedTagList -> returns false
        modelManager.updateSortedTagList(new TagNameComparator());

        // different sortedPersonList -> returns false
        modelManager.updateSortedPersonList(new PersonNameComparator());

        assertFalse(modelManager.equals(new ModelManager(projact, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setProjactFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(projact, differentUserPrefs)));
    }
}
