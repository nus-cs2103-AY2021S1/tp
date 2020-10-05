package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAnimals.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newAnimal_success() {
        Animal validAnimal = new AnimalBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAnimal(validAnimal);

        assertCommandSuccess(new AddCommand(validAnimal), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validAnimal), expectedModel);
    }

    @Test
    public void execute_duplicateAnimal_throwsCommandException() {
        Animal animalInList = model.getAddressBook().getAnimalList().get(0);
        assertCommandFailure(new AddCommand(animalInList), model, AddCommand.MESSAGE_DUPLICATE_ANIMAL);
    }

}
