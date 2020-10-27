package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.propertybook.PropertyModel.PREDICATE_SHOW_ALL_PROPERTIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.property.PropertyBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new BidderAddressBook(), new BidderAddressBook(modelManager.getBidderAddressBook()));
        assertEquals(new SellerAddressBook(), new SellerAddressBook(modelManager.getSellerAddressBook()));
        assertEquals(new BidBook(), new BidBook(modelManager.getBidBook()));
        assertEquals(new MeetingBook(), new MeetingBook(modelManager.getMeetingBook()));
        assertEquals(new PropertyBook(), new PropertyBook(modelManager.getPropertyBook()));
    }

    // ------------------- USER PREFS -------------------


    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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

    // ------------------- ADDRESS BOOK -------------------

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    // ------------------- PROPERTY BOOK -------------------

    @Test
    public void setPropertyBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPropertyBookFilePath(null));
    }

    @Test
    public void setPropertyBookFilePath_validPath_setsPropertyBookFilePath() {
        Path path = Paths.get("property/book/file/path");
        modelManager.setPropertyBookFilePath(path);
        assertEquals(path, modelManager.getPropertyBookFilePath());
    }

    @Test
    public void hasProperty_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyBook_returnsFalse() {
        assertFalse(modelManager.hasProperty(PROPERTY_A));
    }

    @Test
    public void hasProperty_propertyInPropertyBook_returnsTrue() {
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.addProperty(PROPERTY_A);
        assertTrue(modelManager.hasProperty(PROPERTY_A));
    }

    @Test
    public void getFilteredPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPropertyList().remove(0));
    }

    // ------------------- GENERAL -------------------

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();
        BidderAddressBook bidderAddressBook = new BidderAddressBook();
        SellerAddressBook sellerAddressBook = new SellerAddressBook();
        BidBook bidBook = new BidBook();
        MeetingBook meetingBook = new MeetingBook();
        PropertyBook propertyBook = new PropertyBookBuilder()
                .withProperty(PROPERTY_A).withProperty(PROPERTY_B).build();
        PropertyBook differentPropertyBook = new PropertyBook();

        modelManager = new ModelManager(addressBook, userPrefs,
                bidBook,
                propertyBook,
                bidderAddressBook,
                sellerAddressBook,
                meetingBook);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs,
                bidBook,
                propertyBook,
                bidderAddressBook,
                sellerAddressBook,
                meetingBook);

        // same values -> returns true

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false

        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs,
                bidBook, propertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));

        // different propertyBook -> returns false

        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                bidBook, differentPropertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));

        // different filteredPersonList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));

        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                bidBook, propertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));

        // different filteredPropertyList -> returns false
        modelManager.updateFilteredPropertyList(property -> property.equals(PROPERTY_A));

        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                bidBook, propertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManagerCopy.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));

        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs, bidBook,
                propertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));
    }
}
