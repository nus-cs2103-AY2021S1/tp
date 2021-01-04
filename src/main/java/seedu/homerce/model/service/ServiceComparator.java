package seedu.homerce.model.service;

import java.util.Comparator;

/**
 * Compares two services and orders the service with a smaller code before a service with
 * a larger code.
 */
public class ServiceComparator implements Comparator<Service> {

    @Override
    public int compare(Service s1, Service s2) {
        int firstServiceID = s1.getServiceCode().getID();
        int otherServiceID = s2.getServiceCode().getID();
        if (firstServiceID < otherServiceID) {
            return -1;
        } else if (firstServiceID > otherServiceID) {
            return 1;
        } else { // This case should not even happen as ServiceCodes are unique
            return 0;
        }
    }
}
