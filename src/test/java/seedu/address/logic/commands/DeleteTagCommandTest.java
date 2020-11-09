//@@author jerrylchong
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBookWithMember;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class DeleteTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());
    private Model modelWithMembersInMeetings = new ModelManager(getTypicalAddressBook(),
            getTypicalMeetingBookWithMember(), getTypicalModuleBook(), new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        HashSet<Tag> tags = new HashSet<>(firstPerson.getTags());
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(tags.iterator().next());
        tags.removeAll(tagsToDelete);

        Person taggedPerson = new Person(firstPerson.getName(), firstPerson.getPhone(), firstPerson.getEmail(),
                tags);

        DeleteTagCommand tagCommand = new DeleteTagCommand(firstPerson.getName(), tagsToDelete);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_PERSON_SUCCESS, taggedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.setPerson(firstPerson, taggedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentTag_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag("fake"));

        DeleteTagCommand tagCommand = new DeleteTagCommand(firstPerson.getName(), tagsToDelete);

        // one non-existent tag
        assertCommandFailure(tagCommand, model, String.format("The person '%s' does not have all the tags provided.",
                firstPerson.getName().toString()));

        HashSet<Tag> tags = new HashSet<>(firstPerson.getTags());
        tagsToDelete.add(tags.iterator().next());

        tagCommand = new DeleteTagCommand(firstPerson.getName(), tagsToDelete);

        // one existent tag and one non-existent tag
        assertCommandFailure(tagCommand, model, String.format("The person '%s' does not have all the tags provided.",
                firstPerson.getName().toString()));
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Tag"));
        DeleteTagCommand tagCommand = new DeleteTagCommand(new Name("Fake"), tags);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
    }
}
