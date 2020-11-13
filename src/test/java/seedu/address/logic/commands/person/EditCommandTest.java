package seedu.address.logic.commands.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.person.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.person.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.person.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.EditCommand.EditPersonDescriptor;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ModelPerson;
import seedu.address.model.person.ModelPersonManager;
import seedu.address.model.person.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private ModelPerson modelPerson = new ModelPersonManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        ModelPerson expectedModelPerson =
                new ModelPersonManager(new AddressBook(modelPerson.getAddressBook()), new UserPrefs());
        expectedModelPerson.setPerson(modelPerson.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, modelPerson, expectedMessage, expectedModelPerson);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(modelPerson.getFilteredPersonList().size());
        Person lastPerson = modelPerson.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        ModelPerson expectedModelPerson =
                new ModelPersonManager(new AddressBook(modelPerson.getAddressBook()), new UserPrefs());
        expectedModelPerson.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, modelPerson, expectedMessage, expectedModelPerson);
    }

    @Test
    public void execute_unchangedPerson_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST, new EditPersonDescriptor());
        Person editedPerson = modelPerson.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_UNCHANGED, editedPerson);

        ModelPerson expectedModelPerson =
                new ModelPersonManager(new AddressBook(modelPerson.getAddressBook()), new UserPrefs());

        assertCommandFailure(editCommand, modelPerson, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(modelPerson, INDEX_FIRST);

        Person personInFilteredList = modelPerson.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        ModelPerson expectedModelPerson =
                new ModelPersonManager(new AddressBook(modelPerson.getAddressBook()), new UserPrefs());
        expectedModelPerson.setPerson(modelPerson.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, modelPerson, expectedMessage, expectedModelPerson);
    }

    @Test
    public void execute_duplicatePersonSameName_failure() {
        // both persons are in list and have all fields same except for name
        Person firstPerson = modelPerson.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withName(VALID_NAME_BOB).build();

        ModelPerson expectedModelPerson =
                new ModelPersonManager(new AddressBook(modelPerson.getAddressBook()), new UserPrefs());
        expectedModelPerson.addPerson(editedPerson);

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        // try to change name
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, modelPerson, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonSameEmail_failure() {
        // both persons are in list and have all fields same except for email
        Person firstPerson = modelPerson.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withEmail(VALID_EMAIL_BOB).build();

        ModelPerson expectedModelPerson =
                new ModelPersonManager(new AddressBook(modelPerson.getAddressBook()), new UserPrefs());
        expectedModelPerson.addPerson(editedPerson);

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        // try to change email
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, modelPerson, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = modelPerson.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, modelPerson, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(modelPerson, INDEX_FIRST);

        // edit person in filtered list into a duplicate in address book
        Person personInList = modelPerson.getAddressBook().getPersonList().get(INDEX_SECOND.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, modelPerson, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelPerson.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelPerson, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(modelPerson, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelPerson.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, modelPerson, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_BOB)));
    }

}
