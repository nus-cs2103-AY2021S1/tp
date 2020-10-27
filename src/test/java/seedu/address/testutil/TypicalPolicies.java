package seedu.address.testutil;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalPolicies {
    public static final Policy LIFE_TIME = new Policy(
            new PolicyName("Life Time"),
            new PolicyDescription("Covers death, serious illnesses, and disabilities."));

    public static final Policy SAVINGS = new Policy(
            new PolicyName("Savings Scheme"),
            new PolicyDescription("Profits of up to 10% of input."));

    private TypicalPolicies() {} // prevents instantiation

    public static PolicyList getTypicalPolicyList() {
        PolicyList list = new PolicyList();
        for (Policy p : getTypicalPolicyListasList()) {
            list.add(p);
        }
        return list;
    }

    public static List<Policy> getTypicalPolicyListasList() {
        return new ArrayList<>(Arrays.asList(LIFE_TIME, SAVINGS));
    }
}
