package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.model.policy.exception.DuplicatePolicyException;

public class PolicyList {
    private final HashMap<String, Policy> policies;

    /**
     * Constructs a {@code PolicyList} from a HashMap.
     */
    public PolicyList(HashMap<String, Policy> policies) {
        requireNonNull(policies);
        this.policies = policies;
    }

    public PolicyList() {
        policies = new HashMap<>();
    }

    /**
     * Returns true if the HashMap contains an equivalent policy as the given argument.
     */
    public boolean contains(Policy toCheck) {
        requireNonNull(toCheck);
        return policies.containsValue(toCheck);
    }

    /**
     * Returns true if the HashMap contains the key as the given argument using PolicyName.
     */
    public boolean contains(PolicyName policyName) {
        return policies.containsKey(policyName.value);
    }

    /**
     * Returns true if the HashMap contains the key as the given argument using String.
     */
    public boolean contains(String policyName) {
        return policies.containsKey(policyName);
    }

    /**
     * Adds a {@link Policy} to policies.
     * @param toAdd is required to be non null.
     */
    public void add(Policy toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePolicyException();
        }
        policies.put(toAdd.getPolicyName().value, toAdd);
    }

    /**
     * clear policies of all the data.
     */
    public void clear() {
        policies.clear();
    }

    /**
     * Returns an empty {@code PolicyList}
     */
    public static PolicyList createNewPolicyList() {
        HashMap<String, Policy> policies = new HashMap<>();
        return new PolicyList(policies);
    }

    /**
     * Returns a copy of the internal HashMap
     */
    public HashMap<String, Policy> getHashMapCopy() {
        HashMap<String, Policy> copy = new HashMap<>();
        copy.putAll(policies);
        return copy;
    }

    /**
     * Returns the Policy in the HashMap if present using a {@code PolicyName}
     * else returns null.
     */
    public Policy getPolicy(PolicyName name) {
        return policies.get(name.value);
    }

    /**
     * Returns the Policy in the HashMap if present using String name,
     * else returns null.
     */
    public Policy getPolicy(String name) {
        return policies.get(name);
    }

    /**
     * Returns true if both Policys have the same values in the HashMap.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyList // instanceof handles nulls
                && policies.equals(((PolicyList) other).getHashMapCopy()));
    }

    @Override
    public int hashCode() {
        return policies.hashCode();
    }
}
