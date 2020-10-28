package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.ClientList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.policy.Policy;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME;
import static seedu.address.testutil.TypicalPolicies.getTypicalPolicyList;

public class AddPolicyCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPolicyCommand(null));
    }

    @Test
    public void execute_policyAcceptedByModel_addSuccessful() {
        Policy policy = LIFE_TIME;
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.addPolicy(LIFE_TIME);
        String expectedMessage = String.format(AddPolicyCommand.MESSAGE_SUCCESS, policy);
        assertCommandSuccess(new AddPolicyCommand(policy), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_policyIsDuplicatePolicy() {
        Model model = new ModelManager(new ClientList(), new UserPrefs(), getTypicalPolicyList());
        assertCommandFailure(new AddPolicyCommand(LIFE_TIME), model, AddPolicyCommand.MESSAGE_DUPLICATE_POLICY);
    }
}
