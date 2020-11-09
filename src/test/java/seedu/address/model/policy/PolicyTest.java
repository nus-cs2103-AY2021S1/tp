package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_NAME;
import static seedu.address.testutil.TypicalPolicies.SAVINGS_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.SAVINGS_NAME;

import org.junit.jupiter.api.Test;

public class PolicyTest {
    private PolicyName lifeTimeName = new PolicyName(LIFE_TIME_NAME);
    private PolicyDescription lifeTimeDescription = new PolicyDescription(LIFE_TIME_DESCRIPTION);
    private Policy lifeTimePolicy = new Policy(lifeTimeName, lifeTimeDescription);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Policy(null, null));
        assertThrows(NullPointerException.class, () -> new Policy(lifeTimeName, null));
        assertThrows(NullPointerException.class, () -> new Policy(null, lifeTimeDescription));
    }

    @Test
    public void equals() {
        //returns true if same object
        assertTrue(lifeTimePolicy.equals(lifeTimePolicy));

        //returns true if constructed from same PolicyName and PolicyDescription
        assertTrue(lifeTimePolicy.equals(new Policy(lifeTimeName, lifeTimeDescription)));

        //returns false if null
        assertFalse(lifeTimePolicy.equals(null));

        //returns false if different PolicyName
        PolicyName otherName = new PolicyName(SAVINGS_NAME);
        assertFalse(lifeTimePolicy.equals(new Policy(otherName, lifeTimeDescription)));

        //returns false if different description
        PolicyDescription otherDescription = new PolicyDescription(SAVINGS_DESCRIPTION);
        assertFalse(lifeTimePolicy.equals(new Policy(lifeTimeName, otherDescription)));

        //returns false if both description and name is different
        assertFalse(lifeTimePolicy.equals(new Policy(otherName, otherDescription)));

    }
}
