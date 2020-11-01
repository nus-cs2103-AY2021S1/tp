package trackitnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.model.contact.Contact;
import trackitnus.model.contact.exceptions.DuplicateContactException;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;
import trackitnus.testutil.Assert;
import trackitnus.testutil.builder.ContactBuilder;
import trackitnus.testutil.typical.TypicalContacts;

public class TrackIterTest {

    private final TrackIter trackIter = new TrackIter();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), trackIter.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> trackIter.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTrackIter_replacesData() {
        TrackIter newData = getTypicalTrackIter();
        trackIter.resetData(newData);
        assertEquals(newData, trackIter);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice =
            new ContactBuilder(TypicalContacts.ALICE).withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(TypicalContacts.ALICE, editedAlice);
        TrackIterStub newData = new TrackIterStub(newContacts);

        Assert.assertThrows(DuplicateContactException.class, () -> trackIter.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> trackIter.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInTrackIter_returnsFalse() {
        assertFalse(trackIter.hasContact(TypicalContacts.ALICE));
    }

    @Test
    public void hasContact_contactInTrackIter_returnsTrue() {
        trackIter.addContact(TypicalContacts.ALICE);
        assertTrue(trackIter.hasContact(TypicalContacts.ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInTrackIter_returnsTrue() {
        trackIter.addContact(TypicalContacts.ALICE);
        Contact editedAlice =
            new ContactBuilder(TypicalContacts.ALICE).withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertTrue(trackIter.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> trackIter.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyTrackIter whose contacts list can violate interface constraints.
     */
    private static class TrackIterStub implements ReadOnlyTrackIter {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();

        TrackIterStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }
    }

}
