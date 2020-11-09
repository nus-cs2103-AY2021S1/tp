package seedu.zookeep.logic.commands;

import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.testutil.AnimalBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());
    }

    @Test
    public void execute_newAnimal_success() {
        Animal validAnimal = new AnimalBuilder().build();

        Model expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
        expectedModel.addAnimal(validAnimal);

        assertCommandSuccess(new AddCommand(validAnimal), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validAnimal), expectedModel);
    }

    @Test
    public void execute_duplicateAnimal_throwsCommandException() {
        Animal animalInList = model.getZooKeepBook().getAnimalList().get(0);
        assertCommandFailure(new AddCommand(animalInList), model, AddCommand.MESSAGE_DUPLICATE_ANIMAL);
    }

}
