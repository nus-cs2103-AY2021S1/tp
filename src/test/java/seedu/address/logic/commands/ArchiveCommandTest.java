package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Tasker;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class ArchiveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void archive_unfilteredList_success() {
        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void archive_appendedList_success() {
        Person validPerson = new PersonBuilder().build();
        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());

        model.addPerson(validPerson);
        expectedModel.addPerson(validPerson);
        // archive model with add operation executed
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void archive_croppedList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.deletePerson(personToDelete);
        model.deletePerson(personToDelete);
        // archive model with delete operation executed
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void archive_editedList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        expectedModel.setPerson(lastPerson, editedPerson);
        model.setPerson(lastPerson, editedPerson);
        // archive model with edit operation executed
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void archive_clear_success() {
        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());
        expectedModel.setAddressBook(new Tasker());
        model.setAddressBook(new Tasker());

        // archive model with edit operation executed
        assertCommandSuccess(new ArchiveCommand(), model, ArchiveCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

