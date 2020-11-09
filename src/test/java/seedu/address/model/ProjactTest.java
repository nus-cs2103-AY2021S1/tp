package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;
import static seedu.address.testutil.TypicalTags.CS2040S;
import static seedu.address.testutil.TypicalTags.CS2103T;
import static seedu.address.testutil.TypicalTags.GROUPMATE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TagBuilder;

public class ProjactTest {

    private final Projact projact = new Projact();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), projact.getPersonList());
        assertEquals(Collections.emptyList(), projact.getTagList());
    }

    @Test
    public void constructor_withNullProjact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Projact(null));
    }

    @Test
    public void constructor_withValidReadOnlyProjact_initializesData() {
        Projact typicalProjact = getTypicalProjact();
        Projact newProjact = new Projact(typicalProjact);
        assertEquals(typicalProjact, newProjact);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyProjact_replacesData() {
        Projact newData = getTypicalProjact();
        projact.resetData(newData);
        assertEquals(newData, projact);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTelegramAddress(VALID_TELEGRAM_ADDRESS_BOB)
                .withTagNames(VALID_TAG_NAME_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Tag> newTags = Arrays.asList(CS2103T);
        ProjactStub newData = new ProjactStub(newPersons, newTags);

        assertThrows(DuplicatePersonException.class, () -> projact.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTags_throwsDuplicateTagException() {
        // Two tags with same tag names
        Tag editedCS2103T = new TagBuilder(CS2040S).withTagName("CS2103T").build();
        List<Person> newPersons = Arrays.asList(ALICE);
        List<Tag> newTags = Arrays.asList(CS2103T, editedCS2103T);
        ProjactStub newData = new ProjactStub(newPersons, newTags);

        assertThrows(DuplicateTagException.class, () -> projact.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInProjact_returnsFalse() {
        assertFalse(projact.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInProjact_returnsTrue() {
        projact.addPerson(ALICE);
        assertTrue(projact.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInProjact_returnsTrue() {
        projact.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTelegramAddress(VALID_TELEGRAM_ADDRESS_BOB)
                .withTagNames(VALID_TAG_NAME_HUSBAND).build();
        assertTrue(projact.hasPerson(editedAlice));
    }

    @Test
    public void addPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.addPerson(null));
    }

    @Test
    public void addPerson_personNotInProjact_success() {
        assertFalse(projact.hasPerson(ALICE));
        projact.addPerson(ALICE);
        assertTrue(projact.hasPerson(ALICE));
    }

    @Test
    public void addPerson_personInProjact_throwsDuplicatePersonException() {
        projact.addPerson(ALICE);
        assertThrows(DuplicatePersonException.class, () -> projact.addPerson(ALICE));
    }

    @Test
    public void setPerson_nullTarget_throwsNullPointerException() {
        projact.addPerson(ALICE);
        assertThrows(NullPointerException.class, () -> projact.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedTarget_throwsNullPointerException() {
        projact.addPerson(ALICE);
        assertThrows(NullPointerException.class, () -> projact.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_nonExistentTarget_throwsPersonNotFoundException() {
        projact.addPerson(ALICE);
        assertThrows(PersonNotFoundException.class, () -> projact.setPerson(BENSON, ALICE));
    }

    @Test
    public void setPerson_unchangedEditedPerson_success() {
        projact.addPerson(ALICE);
        projact.setPerson(ALICE, ALICE);
        assertTrue(projact.hasPerson(ALICE));
    }

    @Test
    public void setPerson_valueFields_success() {
        projact.addPerson(ALICE);
        projact.setPerson(ALICE, BENSON);
        assertFalse(projact.hasPerson(ALICE));
        assertTrue(projact.hasPerson(BENSON));
    }

    @Test
    public void removePerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.removePerson(null));
    }

    @Test
    public void removePerson_personNotInProjact_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> projact.removePerson(ALICE));
    }

    @Test
    public void removePerson_personInProjact_success() {
        projact.addPerson(ALICE);
        assertTrue(projact.hasPerson(ALICE));
        projact.removePerson(ALICE);
        assertFalse(projact.hasPerson(ALICE));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> projact.getPersonList().remove(0));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInProjact_returnsFalse() {
        assertFalse(projact.hasTag(CS2103T));
    }

    @Test
    public void hasTag_tagInProjact_returnsTrue() {
        projact.addTag(CS2103T);
        assertTrue(projact.hasTag(CS2103T));
    }

    @Test
    public void hasTag_tagWithSameNameInProjact_returnsTrue() {
        projact.addTag(CS2103T);
        Tag editedTag = new TagBuilder(CS2103T).build();
        assertTrue(projact.hasTag(editedTag));
    }

    @Test
    public void addTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.addTag(null));
    }

    @Test
    public void addTag_tagNotInProjact_success() {
        assertFalse(projact.hasTag(CS2103T));
        projact.addTag(CS2103T);
        assertTrue(projact.hasTag(CS2103T));
    }

    @Test
    public void addTag_tagInProjact_throwsDuplicateTagException() {
        projact.addTag(CS2103T);
        assertThrows(DuplicateTagException.class, () -> projact.addTag(CS2103T));
    }

    @Test
    public void setTag_nullTarget_throwsNullPointerException() {
        projact.addTag(CS2103T);
        assertThrows(NullPointerException.class, () -> projact.setTag(null, CS2103T));
    }

    @Test
    public void setTag_nullEditedTarget_throwsNullPointerException() {
        projact.addTag(CS2103T);
        assertThrows(NullPointerException.class, () -> projact.setTag(CS2103T, null));
    }

    @Test
    public void setTag_nonExistentTarget_throwsTagNotFoundException() {
        projact.addTag(CS2103T);
        assertThrows(TagNotFoundException.class, () -> projact.setTag(GROUPMATE, CS2103T));
    }

    @Test
    public void setTag_unchangedEditedTag_success() {
        projact.addTag(CS2103T);
        projact.setTag(CS2103T, CS2103T);
        assertTrue(projact.hasTag(CS2103T));
    }

    @Test
    public void setTag_valueFields_success() {
        projact.addTag(CS2103T);
        projact.setTag(CS2103T, GROUPMATE);
        assertFalse(projact.hasTag(CS2103T));
        assertTrue(projact.hasTag(GROUPMATE));
    }

    @Test
    public void removeTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projact.removeTag(null));
    }

    @Test
    public void removeTag_tagNotInProjact_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> projact.removeTag(GROUPMATE));
    }

    @Test
    public void removeTag_tagInProjact_success() {
        projact.addTag(GROUPMATE);
        assertTrue(projact.hasTag(GROUPMATE));
        projact.removeTag(GROUPMATE);
        assertFalse(projact.hasTag(GROUPMATE));
    }

    @Test
    public void getTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> projact.getTagList().remove(0));
    }

    @Test
    public void hashCode_differentPersonListAndSameTagList_notEquals() {
        Projact projact1 = new Projact();
        projact1.setPersons(Arrays.asList(ALICE, BENSON));
        projact1.setTags(Arrays.asList(CS2103T, GROUPMATE));

        Projact projact2 = new Projact();
        projact2.setPersons(Arrays.asList(ALICE));
        projact2.setTags(Arrays.asList(CS2103T, GROUPMATE));

        assertNotEquals(projact1.hashCode(), projact2.hashCode());
    }

    @Test
    public void hashCode_samePersonListAndDifferentTagList_notEquals() {
        Projact projact1 = new Projact();
        projact1.setPersons(Arrays.asList(ALICE, BENSON));
        projact1.setTags(Arrays.asList(CS2103T, GROUPMATE));

        Projact projact2 = new Projact();
        projact2.setPersons(Arrays.asList(ALICE, BENSON));
        projact2.setTags(Arrays.asList(GROUPMATE));

        assertNotEquals(projact1.hashCode(), projact2.hashCode());
    }

    @Test
    public void hashCode_samePersonListAndTagList_equals() {
        Projact projact1 = new Projact();
        projact1.setPersons(Arrays.asList(ALICE, BENSON));
        projact1.setTags(Arrays.asList(CS2103T, GROUPMATE));

        Projact projact2 = new Projact();
        projact2.setPersons(Arrays.asList(ALICE, BENSON));
        projact2.setTags(Arrays.asList(CS2103T, GROUPMATE));

        assertEquals(projact1.hashCode(), projact2.hashCode());
    }

    @Test
    public void toString_correctFormat() {
        String expected = "0 persons\t0 tags";
        assertEquals(projact.toString(), expected);
    }

    @Test
    public void equals() {
        // null -> return false
        assertNotEquals(projact, null);

        // same object -> return true
        assertEquals(projact, projact);

        // different instance -> return false
        assertNotEquals(projact, ALICE);

        // different personlist -> return false
        Projact newProjact = new Projact();
        newProjact.addPerson(BENSON);
        assertNotEquals(projact, newProjact);

        // different taglist -> return false
        newProjact = new Projact();
        newProjact.addTag(CS2103T);
        assertNotEquals(projact, newProjact);

        // same values -> return true
        newProjact = new Projact();
        assertEquals(projact, newProjact);
    }

    /**
     * A stub ReadOnlyProjact whose persons list can violate interface constraints.
     */
    private static class ProjactStub implements ReadOnlyProjact {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        ProjactStub(Collection<Person> persons, Collection<Tag> tags) {
            this.persons.setAll(persons);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
