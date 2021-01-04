package seedu.homerce.testutil.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.service.Service;

/**
 * A utility class containing a list of {@code Service} objects to be used in tests.
 */
public class TypicalServices {

    public static final Service LASH_LIFT = new ServiceBuilder().withTitle("Lash Lift")
        .withDuration(0.5)
        .withAmount(17.9)
        .withServiceCode("SC000").build();

    public static final Service HAIR_TREATMENT = new ServiceBuilder().withTitle("Hair Treatment")
        .withDuration(2.0)
        .withAmount(55.5)
        .withServiceCode("SC001").build();

    public static final Service MANICURE = new ServiceBuilder().withTitle("Manicure")
        .withDuration(0.5)
        .withAmount(25.9)
        .withServiceCode("SC002").build();

    // Manually added
    public static final Service TOE_MASSAGE = new ServiceBuilder().withTitle("Toe Massage")
        .withDuration(1.5)
        .withAmount(49.9)
        .withServiceCode("SC003").build();

    public static final Service EYE_WASH = new ServiceBuilder().withTitle("Eye Wash")
        .withDuration(0.5)
        .withAmount(55.5)
        .withServiceCode("SC005").build();

    private TypicalServices() {
    } // prevents instantiation

    /**
     * Returns an {@code ServiceManager} with all the typical services.
     */
    public static ServiceManager getTypicalServiceManager() {
        ServiceManager sm = new ServiceManager();
        for (Service service: getTypicalServices()) {
            sm.addService(service);
        }
        return sm;
    }

    public static List<Service> getTypicalServices() {
        return new ArrayList<>(Arrays.asList(LASH_LIFT, HAIR_TREATMENT, MANICURE));
    }

}
