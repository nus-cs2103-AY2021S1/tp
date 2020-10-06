package jimmy.mcgymmy.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.logic.parser.CommandParserTestUtil;
import jimmy.mcgymmy.model.AddressBook;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.model.person.Name;
import jimmy.mcgymmy.model.person.Person;
import jimmy.mcgymmy.testutil.PersonBuilder;
import jimmy.mcgymmy.testutil.TypicalIndexes;
import jimmy.mcgymmy.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final String VALID_NAME_BOB = "Robert Donald";
    private static final String VALID_PHONE_BOB = "99999999";
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().withTags("friends").build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_PERSON),
                new CommandParserTestUtil.OptionalParameterStub<>("n", editedPerson.getName()),
                new CommandParserTestUtil.OptionalParameterStub<>("p", editedPerson.getPhone()),
                new CommandParserTestUtil.OptionalParameterStub<>("e", editedPerson.getEmail())
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", indexLastPerson),
                new CommandParserTestUtil.OptionalParameterStub<>("n", editedPerson.getName()),
                new CommandParserTestUtil.OptionalParameterStub<>("p", editedPerson.getPhone()),
                new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_PERSON),
                new CommandParserTestUtil.OptionalParameterStub<>("n"),
                new CommandParserTestUtil.OptionalParameterStub<>("p"),
                new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        Person editedPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList =
                model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_PERSON),
                new CommandParserTestUtil.OptionalParameterStub<>("n", editedPerson.getName()),
                new CommandParserTestUtil.OptionalParameterStub<>("p"),
                new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_SECOND_PERSON),
                new CommandParserTestUtil.OptionalParameterStub<>("n", firstPerson.getName()),
                new CommandParserTestUtil.OptionalParameterStub<>("p", firstPerson.getPhone()),
                new CommandParserTestUtil.OptionalParameterStub<>("e", firstPerson.getEmail())
        );

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList =
                model.getAddressBook().getPersonList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_PERSON),
                new CommandParserTestUtil.OptionalParameterStub<>("n", personInList.getName()),
                new CommandParserTestUtil.OptionalParameterStub<>("p", personInList.getPhone()),
                new CommandParserTestUtil.OptionalParameterStub<>("e", personInList.getEmail())
        );

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", outOfBoundIndex),
                new CommandParserTestUtil.OptionalParameterStub<>("n", new Name(VALID_NAME_BOB)),
                new CommandParserTestUtil.OptionalParameterStub<>("p"),
                new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", outOfBoundIndex),
                new CommandParserTestUtil.OptionalParameterStub<>("n", new Name(VALID_NAME_BOB)),
                new CommandParserTestUtil.OptionalParameterStub<>("p"),
                new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


}
