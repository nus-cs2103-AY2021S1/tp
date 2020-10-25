package seedu.address.model.policy;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person's policy in the client list.
 * Guarantees: policyName and description are present and not null,
 * field values are validated, immutable.
 */
public class Policy {

    private final PolicyName policyName;
    private final PolicyDescription description;

    /**
     *
     * @param policyName
     * @param description
     */
    public Policy(PolicyName policyName, PolicyDescription description) {
        requireAllNonNull(policyName, description);
        this.policyName = policyName;
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Policy Name: ").append(getPolicyName()).append(" Description: ").append(getDescription());
        return builder.toString();
    }

    /**
     * Returns true if the other object is an instance of Policy and if the other has the same name
     * and if other has the same description.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Policy // instanceof handles nulls
                && policyName.equals(((Policy) other).getPolicyName()) // state check
                && description.equals(((Policy) other).getDescription()));
    }

    @Override
    public int hashCode() {
        return policyName.hashCode();
    }

    /**
     * Returns true if otherPolicy has the same name and same description.
     * This method differs from equals method as isSamePolicy takes into account
     * both the name and description.
     *
     * @param otherPolicy
     * @return
     */
    public boolean isSamePolicy(Policy otherPolicy) {
        return otherPolicy == this
                || otherPolicy.getPolicyName().equals(this.policyName);
    }

    public PolicyName getPolicyName() {
        return policyName;
    }

    public PolicyDescription getDescription() {
        return description;
    }
}
