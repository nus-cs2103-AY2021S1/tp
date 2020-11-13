package seedu.address.logic.commands.person;

import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModelPerson;
import seedu.address.model.person.ModelPersonManager;
import seedu.address.model.person.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private ModelPerson modelPerson;

    @BeforeEach
    public void setUp() {
        modelPerson = new ModelPersonManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        ModelPerson expectedModelPerson = new ModelPersonManager(modelPerson.getAddressBook(), new UserPrefs());
        expectedModelPerson.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), modelPerson,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModelPerson);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = modelPerson.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), modelPerson, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
