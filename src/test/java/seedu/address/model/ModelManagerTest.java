package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalRooms.ROOM7_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM7_PATIENT_BENSON_NO_TASK;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.room.Room;
import seedu.address.testutil.PatientRecordsBuilder;
import seedu.address.testutil.RoomBuilder;
import seedu.address.testutil.TypicalRooms;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PatientRecords(), new PatientRecords(modelManager.getPatientRecords()));
        assertEquals(new RoomList(), new RoomList(modelManager.getModifiableRoomList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setCovigentAppFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setCovigentAppFilePath(Paths.get("new/address/book/file/path"));
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
    public void setCovigentAppFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCovigentAppFilePath(null));
    }

    @Test
    public void setCovigentAppFilePath_validPath_setsCovigentAppFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setCovigentAppFilePath(path);
        assertEquals(path, modelManager.getCovigentAppFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient(null));
    }

    @Test
    public void hasPerson_personNotInCovigentApp_returnsFalse() {
        assertFalse(modelManager.hasPatient(ALICE));
    }

    @Test
    public void hasPerson_personInCovigentApp_returnsTrue() {
        modelManager.addPatient(ALICE);
        assertTrue(modelManager.hasPatient(ALICE));
    }

    //@@author LeeMingDe
    @Test
    public void setPatient_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPatient(null, ALICE));
    }

    @Test
    public void setPatient_nullEditedPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPatient(ALICE, null));
    }

    @Test
    public void setPatient_targetAndEditedPatient_success() {
        modelManager.addPatient(ALICE);
        modelManager.setPatient(ALICE, BENSON);
        assertFalse(modelManager.hasPatient(ALICE));
        assertTrue(modelManager.hasPatient(BENSON));
    }

    @Test
    public void isPatientAssignedToRoom_null_throwsNullPointerException() {
        modelManager.addRooms(1);
        modelManager.getRoomListObservableList().get(0).setPatient(ALICE);
        assertThrows(NullPointerException.class, () -> modelManager.isPatientAssignedToRoom(null));
    }

    @Test
    public void isPatientAssignedToRoom_personInRoom_returnsTrue() {
        modelManager.addRooms(1);
        modelManager.getRoomListObservableList().get(0).setPatient(ALICE);
        assertTrue(modelManager.isPatientAssignedToRoom(ALICE.getName()));
    }

    @Test
    public void isPatientAssignedToRoom_personNotInRoom_returnsFalse() {
        modelManager.addRooms(1);
        modelManager.getRoomListObservableList().get(0).setPatient(ALICE);
        assertFalse(modelManager.isPatientAssignedToRoom(BENSON.getName()));
    }
    //@@author LeeMingDe

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().remove(0));
    }

    @Test
    public void getRoomList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getRoomListObservableList().remove(0));
    }

    //@@author LeeMingDe
    @Test
    public void hasRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRoom(null));
    }

    @Test
    public void hasRoom_roomInCovigentApp_returnsTrue() {
        modelManager.addRooms(1);
        assertTrue(modelManager.hasRoom(new Room(1)));
    }

    @Test
    public void hasRoom_roomNotInCovigentApp_returnsFalse() {
        modelManager.addRooms(1);
        assertFalse(modelManager.hasRoom(new Room(2)));
    }

    @Test
    public void setSingleRoom_nullEditedRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            modelManager.setSingleRoom(ROOM7_PATIENT_ALICE_NO_TASK, null));
    }

    @Test
    public void setSingleRoom_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            modelManager.setSingleRoom(null, ROOM7_PATIENT_ALICE_NO_TASK));
    }

    @Test
    public void setSingleRoom_targetEditedRoom_success() {
        modelManager.addRooms(8);
        Room room = modelManager.getRoomListObservableList().get(6);
        room.setPatient(ALICE);
        room.setOccupied(true);
        modelManager.setSingleRoom(ROOM7_PATIENT_ALICE_NO_TASK, ROOM7_PATIENT_BENSON_NO_TASK);
        assertEquals(true, modelManager.getRoomListObservableList().get(6).equals(ROOM7_PATIENT_BENSON_NO_TASK));
        assertEquals(false, modelManager.getRoomListObservableList().get(6).equals(ROOM7_PATIENT_ALICE_NO_TASK));

    }

    @Test
    public void updateRoomListWhenPatientsChanges_nullPatientToEdit_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            modelManager.updateRoomListWhenPatientsChanges(null, ALICE));
    }
    //@@author LeeMingDe

    //@@author chiamyunqing
    @Test
    public void removePatientFromRoom_success() {
        modelManager.addRooms(1);
        Room roomWithPatient = modelManager.getRoomListObservableList().get(0);
        Room duplicateRoomWithoutAlice = new RoomBuilder(roomWithPatient).build();
        roomWithPatient.setPatient(ALICE);
        roomWithPatient.setOccupied(true);
        modelManager.setSingleRoom(roomWithPatient, modelManager.getRoomListObservableList().get(0));
        modelManager.removePatientFromRoom(ALICE.getName());
        assertEquals(modelManager.getRoomListObservableList().get(0), duplicateRoomWithoutAlice);
    }
    //@@author chiamyunqing

    //@@author itssodium
    @Test
    public void numOfExcessOccupiedRooms_success() {
        modelManager.setRoomList(TypicalRooms.getTypicalRoomList());
        modelManager.setInitNumOfRooms(0); // same number of rooms, number of occupied room is 4
        assertEquals(modelManager.getNumOfExcessOccupiedRooms(), 2);
    }

    @Test
    public void hasSpaceForRooms_success() {
        modelManager.setRoomList(TypicalRooms.getTypicalRoomList());
        modelManager.setInitNumOfRooms(2); // number of occupied rooms is 2, therefore has space
        assertTrue(modelManager.hasSpaceForRooms());

        modelManager.setInitNumOfRooms(3); // number of occupied rooms is 2, therefore has space
        assertTrue(modelManager.hasSpaceForRooms());

        modelManager.setInitNumOfRooms(1); // number of occupied rooms is 2, therefore has no space
        assertFalse(modelManager.hasSpaceForRooms());
    }

    @Test
    public void numOfRooms_success() {
        modelManager.setRoomList(TypicalRooms.getTypicalRoomList());
        //the number of rooms in Typical Room List is 15 -> modelManager should contain 15 rooms
        assertEquals(modelManager.getNumOfRooms(), 15);
    }

    @Test
    public void addRooms_success() {
        modelManager.setRoomList(TypicalRooms.getTypicalRoomList());
        //by adding 50 much rooms(increase) there should be 50 rooms in modelManager
        modelManager.addRooms(50);
        assertEquals(modelManager.getNumOfRooms(), 50);

        //by adding 5 much rooms(decrease) there should be 5 rooms in modelManager
        modelManager.addRooms(5);
        assertEquals(modelManager.getNumOfRooms(), 5);
    }
    //@@author itssodium

    @Test
    public void equals() {
        PatientRecords patientRecords = new PatientRecordsBuilder().withPatient(ALICE).withPatient(BENSON).build();
        PatientRecords differentPatientRecords = new PatientRecords();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(patientRecords, new RoomList(), userPrefs);
        ModelManager modelManagerCopy = new ModelManager(patientRecords, new RoomList(), userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different covigentApp -> returns false
        assertFalse(modelManager
                .equals(new ModelManager(differentPatientRecords, new RoomList(), userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(patientRecords, new RoomList(), userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setCovigentAppFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager
                .equals(new ModelManager(patientRecords, new RoomList(), differentUserPrefs)));
    }
}
