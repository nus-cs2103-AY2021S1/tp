package seedu.homerce.model.manager;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.service.UniqueServiceList;

/**
 * Wraps all data at the ServiceManager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class ServiceManager implements ReadOnlyServiceManager {

    private final UniqueServiceList services;
    private final Logger logger;

    /**
     * Initializes a new ServiceManager with an empty list of services.
     */
    public ServiceManager() {
        this.logger = Logger.getLogger("Service Manager");
        this.services = new UniqueServiceList();
        logger.info("Initialized new Service Manager.");
    }

    /**
     * Creates an ServiceManager using the Services in the {@code toBeCopied}
     */
    public ServiceManager(ReadOnlyServiceManager toBeCopied) {
        this.logger = Logger.getLogger("Service Manager");
        this.services = new UniqueServiceList();
        resetData(toBeCopied);
        logger.info("A new Service Manager is created from ReadOnlyServiceManager.");
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the service list with {@code services}.
     * {@code services} must not contain duplicate services.
     */
    public void setServices(List<Service> services) {
        this.services.setItems(services);
    }

    /**
     * Resets the existing data of this {@code ServiceManager} with {@code newData}.
     */
    public void resetData(ReadOnlyServiceManager newData) {
        requireNonNull(newData);
        setServices(newData.getServiceList());
        logger.info("Service Manager data is reseted.");
    }

    //// service-level operations

    /**
     * Returns true if a service with the same identity as {@code service} exists in the homerce.
     */
    public boolean hasService(Service service) {
        requireNonNull(service);
        return services.contains(service);
    }

    /**
     * Returns true if a service with the same service code as {@code service code} exists in the homerce.
     */
    public boolean hasService(ServiceCode serviceCode) {
        requireNonNull(serviceCode);
        return services.contains(serviceCode);
    }


    /**
     * Adds a service to homerce.
     * The service must not already exist in homerce.
     */
    public void addService(Service p) {
        services.add(p);
    }

    /**
     * Replaces the given service {@code target} in the list with {@code editedService}.
     * {@code target} must exist in homerce.
     * The service identity of {@code editedService} must not be the same as another existing service in homerce.
     */
    public void setService(Service target, Service editedService) {
        requireNonNull(editedService);
        services.setItem(target, editedService);
    }

    /**
     * Removes {@code key} from this {@code ServiceManager}.
     * {@code key} must exist in the homerce.
     */
    public void removeService(Service key) {
        services.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return "Service Manager:\n"
            + services.stream().map(Service::toString).collect(Collectors.joining("\n"))
            + "\n Total number of activities: " + services.size();

    }

    @Override
    public ObservableList<Service> getServiceList() {
        return services.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ServiceManager // instanceof handles nulls
            && services.equals(((ServiceManager) other).services));
    }

    @Override
    public int hashCode() {
        return Objects.hash(services);
    }

    public Service getServiceByServiceCode(ServiceCode serviceCode) {
        requireNonNull(serviceCode);
        Service service = services.getServiceByServiceCode(serviceCode);
        // Return a deep copy so that there will not be issues when services are cleared.
        Service copy = new Service(service.getTitle(), service.getDuration(), service.getAmount());
        copy.addServiceCode(service.getServiceCode().value);
        return copy;
    }

    /**
     * Creates a deep copy of all the services in the unique list of services.
     *
     * @return a service manager with a list of deep copied services.
     */
    public ServiceManager deepCopy() {
        List<Service> internalListCopy = services.deepCopy();
        ServiceManager serviceManagerCopy = new ServiceManager();
        serviceManagerCopy.setServices(internalListCopy);
        return serviceManagerCopy;
    }
}
