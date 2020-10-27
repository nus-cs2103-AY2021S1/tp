package seedu.address.model.policy;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.*;

public class PolicyListTest {


    @Test
    public void constructor() {
        PolicyList policyList = new PolicyList();
        assertThrows(NullPointerException.class, () -> new PolicyList(null));
        assertEquals(policyList.getHashtableCopy(), new Hashtable<>());
        assertEquals(policyList, new PolicyList(new Hashtable<>()));

        PolicyList typicalPolicyList = getTypicalPolicyList();
        assertEquals(typicalPolicyList, new PolicyList(typicalPolicyList.getHashtableCopy()));
    }

    @Test
    public void contains() {
        PolicyList typicalPolicyList = getTypicalPolicyList();

        //check by Policy
        assertTrue(typicalPolicyList.contains(LIFE_TIME));
        assertFalse(typicalPolicyList.contains(MISSING_FROM_LIST_POLICY));

        //check by PolicyName
        assertTrue(typicalPolicyList.contains(new PolicyName(LIFE_TIME_NAME)));
        assertFalse(typicalPolicyList.contains(new PolicyName(MISSING_FROM_LIST_POLICY_NAME)));

        //check by String
        assertTrue(typicalPolicyList.contains(LIFE_TIME_NAME));
        assertFalse(typicalPolicyList.contains(MISSING_FROM_LIST_POLICY_NAME));
    }

    @Test
    public void add() {
        PolicyList policyList = new PolicyList();
        policyList.add(LIFE_TIME);
        assertTrue(policyList.contains(LIFE_TIME));
        assertTrue(policyList.contains(LIFE_TIME_NAME));
    }

    @Test
    public void clear() {
        PolicyList emptyList = getTypicalPolicyList();
        emptyList.clear();
        assertEquals(emptyList, new PolicyList());
        assertFalse(emptyList.contains(LIFE_TIME));
    }

    @Test
    public void getHashTableCopy() {
        //check if its a copy
        PolicyList policyList = getTypicalPolicyList();
        policyList.getHashtableCopy().clear();
        assertFalse(policyList.equals(new PolicyList()));

        policyList.getHashtableCopy().put(MISSING_FROM_LIST_POLICY_NAME, MISSING_FROM_LIST_POLICY);
        assertFalse(policyList.contains(MISSING_FROM_LIST_POLICY));
    }

}
