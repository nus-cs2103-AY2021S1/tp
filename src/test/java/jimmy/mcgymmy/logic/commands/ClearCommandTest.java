package jimmy.mcgymmy.logic.commands;

import org.junit.jupiter.api.Test;

<<<<<<< HEAD
import jimmy.mcgymmy.model.McGymmy;
=======
import jimmy.mcgymmy.model.AddressBook;
>>>>>>> a66faaeb3cdf8c8077dc66b6d8232165877fa617
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.testutil.TypicalPersons;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
<<<<<<< HEAD
        expectedModel.setAddressBook(new McGymmy());
=======
        expectedModel.setAddressBook(new AddressBook());
>>>>>>> a66faaeb3cdf8c8077dc66b6d8232165877fa617

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
