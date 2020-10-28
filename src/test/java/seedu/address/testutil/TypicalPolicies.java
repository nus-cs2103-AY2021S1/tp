package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;

public class TypicalPolicies {
    public static final String LIFE_TIME_NAME = "Life Time";
    public static final String LIFE_TIME_DESCRIPTION =
            "Covers death, serious illnesses, and disabilities.";

    public static final String SAVINGS_NAME = "Savings Scheme";
    public static final String SAVINGS_DESCRIPTION = "Profits of up to 10% of input.";

    public static final Policy LIFE_TIME = new Policy(
            new PolicyName(LIFE_TIME_NAME),
            new PolicyDescription(LIFE_TIME_DESCRIPTION));

    public static final Policy SAVINGS = new Policy(
            new PolicyName(SAVINGS_NAME),
            new PolicyDescription(SAVINGS_DESCRIPTION));

    public static final String MISSING_FROM_LIST_POLICY_NAME = "Medishield";
    public static final String MISSING_FROM_LIST_POLICY_DESCRIPTION = "Covers COVID.";

    public static final Policy MISSING_FROM_LIST_POLICY = new Policy(
            new PolicyName(MISSING_FROM_LIST_POLICY_NAME),
            new PolicyDescription(MISSING_FROM_LIST_POLICY_DESCRIPTION));

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
