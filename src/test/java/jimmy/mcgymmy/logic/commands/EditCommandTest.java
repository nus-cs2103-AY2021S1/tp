package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

<<<<<<< Updated upstream:src/test/java/seedu/address/logic/commands/EditCommandTest.java
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.CommandParserTestUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
=======
import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.logic.parser.CommandParserTestUtil;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.model.person.Name;
import jimmy.mcgymmy.model.person.Person;
import jimmy.mcgymmy.testutil.PersonBuilder;
import jimmy.mcgymmy.testutil.TypicalIndexes;
import jimmy.mcgymmy.testutil.TypicalPersons;
>>>>>>> Stashed changes:src/test/java/jimmy/mcgymmy/logic/commands/EditCommandTest.java

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final String VALID_NAME_BOB = "Robert Donald";
    private static final String VALID_PHONE_BOB = "99999999";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().withTags("friends").build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", editedPerson.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p", editedPerson.getPhone()),
            new CommandParserTestUtil.OptionalParameterStub<>("e", editedPerson.getEmail())
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new McGymmy(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
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

        Model expectedModel = new ModelManager(new McGymmy(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n"),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new McGymmy(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", editedPerson.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new McGymmy(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_SECOND_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", firstPerson.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p", firstPerson.getPhone()),
            new CommandParserTestUtil.OptionalParameterStub<>("e", firstPerson.getEmail())
        );

<<<<<<< Updated upstream:src/test/java/seedu/address/logic/commands/EditCommandTest.java
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
=======
        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
>>>>>>> Stashed changes:src/test/java/jimmy/mcgymmy/logic/commands/EditCommandTest.java
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
<<<<<<< Updated upstream:src/test/java/seedu/address/logic/commands/EditCommandTest.java
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
=======
        Person personInList =
            model.getAddressBook().getFoodList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
>>>>>>> Stashed changes:src/test/java/jimmy/mcgymmy/logic/commands/EditCommandTest.java
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", personInList.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p", personInList.getPhone()),
            new CommandParserTestUtil.OptionalParameterStub<>("e", personInList.getEmail())
        );

<<<<<<< Updated upstream:src/test/java/seedu/address/logic/commands/EditCommandTest.java
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
=======
        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
>>>>>>> Stashed changes:src/test/java/jimmy/mcgymmy/logic/commands/EditCommandTest.java
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

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFoodList().size());

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", outOfBoundIndex),
            new CommandParserTestUtil.OptionalParameterStub<>("n", new Name(VALID_NAME_BOB)),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("e")
        );

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


}
