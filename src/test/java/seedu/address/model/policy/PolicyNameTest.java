package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPolicies;

public class PolicyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PolicyName(null));
    }

    @Test
    public void constructor_invalidPolicyName_throwsIllegalArgumentException() {
        String illegalPolicyName = "@!";
        assertThrows(IllegalArgumentException.class, () -> new PolicyName(illegalPolicyName));
    }

    @Test
    public void equals() {
        PolicyName lifeTime = new PolicyName(TypicalPolicies.LIFE_TIME_NAME);

        //Same object returns true
        assertEquals(lifeTime, lifeTime);

        //Same String returns true
        PolicyName lifeTimeCopy1 = new PolicyName(TypicalPolicies.LIFE_TIME_NAME);
        assertEquals(lifeTime, lifeTimeCopy1);

        //Same value returns true
        PolicyName lifeTimeCopy2 = new PolicyName(lifeTime.value);
        assertEquals(lifeTime, lifeTimeCopy2);

        //Different class returns false
        assertFalse(lifeTime.equals(TypicalPolicies.LIFE_TIME_NAME));

        //Different value returns false
        assertFalse(lifeTime.equals(new PolicyName(TypicalPolicies.SAVINGS_NAME)));
    }
    @Test
    public void isValidPolicyName() {
        assertTrue(PolicyName.isValidPolicyName("abcde 12345 XYZ"));

        assertTrue(PolicyName.isValidPolicyName("a"));

        assertFalse(PolicyName.isValidPolicyName(" "));

        assertFalse(PolicyName.isValidPolicyName("@!"));
    }
}
