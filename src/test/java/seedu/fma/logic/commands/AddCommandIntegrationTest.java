//package seedu.address.logic.commands;
//
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalLogs.getTypicalLogBook;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.log.Log;
//import seedu.address.testutil.LogBuilder;
//
///**
// * Contains integration tests (interaction with the Model) for {@code AddCommand}.
// */
//public class AddCommandIntegrationTest {
//
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(getTypicalLogBook(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_newPerson_success() {
//        Log validLog = new LogBuilder().build();
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.addLog(validLog);
//
//        assertCommandSuccess(new AddCommand(validLog), model,
//                String.format(AddCommand.MESSAGE_SUCCESS, validLog), expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Log logInList = model.getAddressBook().getPersonList().get(0);
//        assertCommandFailure(new AddCommand(logInList), model, AddCommand.MESSAGE_DUPLICATE_LOG);
//    }
//
//}
