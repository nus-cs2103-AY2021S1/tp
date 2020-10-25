package seedu.address.model.policy;

import seedu.address.model.person.exceptions.DuplicatePersonException;


import java.util.Hashtable;

import static java.util.Objects.requireNonNull;

public class PolicyList {
    private final Hashtable<String,Policy> policies;

    public PolicyList(Hashtable<String,Policy> policies) {
        requireNonNull(policies);
        this.policies = policies;
    }

    public PolicyList() {
        policies = new Hashtable<>();
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Policy toCheck) {
        requireNonNull(toCheck);
        return policies.contains(toCheck);
    }

    public void add(Policy toAdd) {
        requireNonNull(toAdd);
        if(contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        policies.put(toAdd.getPolicyName().policyName, toAdd);
    }

    public void clear() {
        policies.clear();
    }

    public static PolicyList createNewPolicyList() {
        Hashtable<String, Policy> policies = new Hashtable<>();
        return new PolicyList(policies);
    }

    public Hashtable<String,Policy> getHashtableCopy() {
        Hashtable<String,Policy> copy = new Hashtable<>();
        copy.putAll(policies);
        return copy;
    }

    public Policy getPolicy(PolicyName name) {
        return policies.get(name.policyName);
    }

    public Policy getPolicy(String name) {
        return policies.get(name);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyList // instanceof handles nulls
                && policies.equals(((PolicyList) other).getHashtableCopy()));
    }

    @Override
    public int hashCode() {
        return policies.hashCode();
    }
}
