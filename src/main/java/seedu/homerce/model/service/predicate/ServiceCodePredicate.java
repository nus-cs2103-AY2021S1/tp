package seedu.homerce.model.service.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;

/**
 * Tests that a {@code Service}'s {@code ServiceCode} matches the given service code.
 */
public class ServiceCodePredicate implements Predicate<Service> {
    private final ServiceCode serviceCode;

    public ServiceCodePredicate(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public boolean test(Service service) {
        if (service == null) {
            return false;
        }
        return service.getServiceCode().value.contains(serviceCode.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ServiceCodePredicate // instanceof handles nulls
            && serviceCode.equals(((ServiceCodePredicate) other).serviceCode)); // state check
    }
}
