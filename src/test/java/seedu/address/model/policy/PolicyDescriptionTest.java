package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPolicies;

public class PolicyDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PolicyDescription(null));
    }

    @Test
    public void constructor_invalidPolicyName_throwsIllegalArgumentException() {
        String illegalPolicyName = " ";
        assertThrows(IllegalArgumentException.class, () -> new PolicyDescription(illegalPolicyName));
    }

    @Test
    public void equals() {
        PolicyDescription lifeTime = new PolicyDescription(TypicalPolicies.LIFE_TIME_DESCRIPTION);

        //Same object returns true
        assertEquals(lifeTime, lifeTime);

        //Same String returns true
        PolicyDescription lifeTimeCopy1 = new PolicyDescription(TypicalPolicies.LIFE_TIME_DESCRIPTION);
        assertEquals(lifeTime, lifeTimeCopy1);

        //Same value returns true
        PolicyDescription lifeTimeCopy2 = new PolicyDescription(lifeTime.value);
        assertEquals(lifeTime, lifeTimeCopy2);

        //Different class returns false
        assertFalse(lifeTime.equals(TypicalPolicies.LIFE_TIME_DESCRIPTION));

        //Different value returns false
        assertFalse(lifeTime.equals(new PolicyDescription(TypicalPolicies.SAVINGS_DESCRIPTION)));
    }

    @Test
    public void isValidPolicyDescription() {
        assertTrue(PolicyDescription.isValidPolicyDescription("!azx $%^*XD"));

        assertTrue(PolicyDescription.isValidPolicyDescription(
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));

        assertFalse(PolicyDescription.isValidPolicyDescription(" "));

        assertFalse(PolicyDescription.isValidPolicyDescription("\n\n"));
    }
}
