package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;

/**
 * Jackson-friendly version of {@link Policy}.
 */
public class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String policyName;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("policyName") String policyName,
                             @JsonProperty("description") String description) {
        this.policyName = policyName;
        this.description = description;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.getPolicyName().value;
        description = source.getDescription().value;
    }

    /**
     * Converts this Jackson-friendly adapted Policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Policy toModelType() throws IllegalValueException {
        if (policyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyName.class.getSimpleName()));
        }
        if (!PolicyName.isValidPolicyName(policyName)) {
            throw new IllegalValueException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        final PolicyName modelPolicyName = new PolicyName(policyName);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PolicyDescription.class.getSimpleName()));
        }
        if (!PolicyDescription.isValidPolicyDescription(description)) {
            throw new IllegalValueException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        final PolicyDescription modelPolicyDescription = new PolicyDescription(description);

        return new Policy(modelPolicyName, modelPolicyDescription);
    }
}
