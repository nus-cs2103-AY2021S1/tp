package seedu.address.testutil;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalPolicies {
    public static String LIFE_TIME_NAME = "Life Time";
    public static String LIFE_TIME_DESCRIPTION =
            "Covers death, serious illnesses, and disabilities.";

    public static String SAVINGS_NAME = "Savings Scheme";
    public static String SAVINGS_DESCRIPTION = "Profits of up to 10% of input.";

    public static final Policy LIFE_TIME = new Policy(
            new PolicyName(LIFE_TIME_NAME),
            new PolicyDescription(LIFE_TIME_DESCRIPTION));

    public static final Policy SAVINGS = new Policy(
            new PolicyName(SAVINGS_NAME),
            new PolicyDescription(SAVINGS_DESCRIPTION));

    public static String MISSING_FROM_LIST_POLICY_NAME = "Medishield";
    public static String MISSING_FROM_LIST_POLICY_DESCRIPTION = "Covers COVID.";

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
