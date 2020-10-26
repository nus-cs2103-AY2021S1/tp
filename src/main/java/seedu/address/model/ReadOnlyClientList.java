package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.policy.PolicyList;

/**
 * Unmodifiable view of an client list
 */
public interface ReadOnlyClientList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    void updateClientListWithPolicyList(PolicyList policyList);

}
