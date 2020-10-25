package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyList;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * An immutable PolicyList that is serializable to JSON format.
 */
@JsonRootName(value = "policylist")
public class JsonAdaptedPolicyList {

    public static final String MESSAGE_DUPLICATE_POLICY = "Policy list contains duplicate Policy(s).";

    private final List<JsonAdaptedPolicy> policyList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPolicyList} with the given policy list.
     */
    @JsonCreator
    public JsonAdaptedPolicyList(@JsonProperty("policyList") List<JsonAdaptedPolicy> policyList) {
        this.policyList.addAll(policyList);
    }

    /**
     * Converts a given {@code PolicyList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonAdaptedPolicyList}.
     */
    public JsonAdaptedPolicyList(PolicyList source) {
        Hashtable<String, Policy> policies = source.getHashtableCopy();

        for(Policy p : policies.values()) {
            policyList.add(new JsonAdaptedPolicy(p));
        }
    }

    /**
     * Converts this policy list into the model's {@code PolicyList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PolicyList toModelType() throws IllegalValueException{
        PolicyList policies = new PolicyList();
        for (JsonAdaptedPolicy p : policyList) {
            Policy policy = p.toModelType();
            if (policies.contains(policy)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POLICY);
            }
            policies.add(policy);
        }
        return policies;
    }
}
