package seedu.address.model.util;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;

public class SamplePolicyDataUtil {
    public static Policy[] getSamplePolicys() {
        return new Policy[]
            {new Policy(
                new PolicyName("Life Time Policy"),
                new PolicyDescription("Covers death, serious illnesses and serious disability.")),
            new Policy(
                new PolicyName("Savings Plan"),
                new PolicyDescription("Earn up to 10% total amount put in."))
            };
    }

    public static PolicyList getSamplePolicyList() {
        PolicyList sampleList = new PolicyList();
        for (Policy p : getSamplePolicys()) {
            sampleList.add(p);
        }
        return sampleList;
    }
}
