//@@author jerrylchong
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddTagCommand.TagPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TagPersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * AddTagCommand.
 */
public class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
        new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person taggedPerson = new PersonBuilder(personInFilteredList).withTags("professor").build();
        AddTagCommand tagCommand = new AddTagCommand(taggedPerson.getName(),
                new TagPersonDescriptorBuilder(taggedPerson).build());
        Set<Tag> updatedTags = new HashSet<>(personInFilteredList.getTags());
        updatedTags.add(new Tag("professor"));
        Person finalPerson = new Person(taggedPerson.getName(), taggedPerson.getPhone(),
                taggedPerson.getEmail(), updatedTags);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, finalPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                                                new MeetingBook(model.getMeetingBook()),
                                                new ModuleBook(model.getModuleBook()),
                                                new UserPrefs());
        expectedModel.setPerson(personInFilteredList, finalPerson);
        expectedModel.updatePersonInMeetingBook(personInFilteredList, finalPerson);
        expectedModel.updatePersonInModuleBook(personInFilteredList, finalPerson);
        expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        TagPersonDescriptor descriptor = new TagPersonDescriptorBuilder().build();
        AddTagCommand tagCommand = new AddTagCommand(new Name("Fake"), descriptor);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
    }

    @Test
    public void equals() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        final AddTagCommand standardCommand = new AddTagCommand(firstPerson.getName(), LABEL_DESC_AMY);

        // same values -> returns true
        TagPersonDescriptor copyDescriptor = new TagPersonDescriptor(LABEL_DESC_AMY);
        AddTagCommand commandWithSameValues = new AddTagCommand(firstPerson.getName(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(secondPerson.getName(), LABEL_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(secondPerson.getName(), LABEL_DESC_BOB)));
    }

}
