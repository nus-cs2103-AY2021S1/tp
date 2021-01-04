package seedu.homerce.model.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.service.exceptions.MaximumServiceException;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;

/**
 * Generates a unique service code for each service.
 */
public class ServiceCodeGenerator {

    /**
     * Creates a new unique identification code for a Service to use.
     *
     * @return a unique identification code for a Service object.
     */
    public static String generateNewServiceCode(List<Service> serviceList, List<Appointment> appointmentList) {
        if (serviceList == null && appointmentList == null) {
            return "SC000";
        }
        // Check against appointment list as well

        Set<Service> serviceSet = new HashSet<>(serviceList);
        Set<Service> serviceInAppointmentSet = new HashSet<>();
        appointmentList.forEach(appointment -> serviceInAppointmentSet.add(appointment.getService()));

        String serviceCode = "";

        for (int codeNum = 0; codeNum < 1000; codeNum++) {
            String potentialServiceCode = "SC" + "0".repeat(3 - String.valueOf(codeNum).length())
                + String.valueOf(codeNum);

            Service testService = new Service(new Title("Test"), new Duration(0.5), new Amount(0.5))
                .addServiceCode(potentialServiceCode);
            // .equals for two services return true as long as value of ServiceCode is the same
            if (!serviceSet.contains(testService) && !serviceInAppointmentSet.contains(testService)) {
                serviceCode = potentialServiceCode;
                break;
            }
        }

        if (serviceCode.equals("")) { // No suitable service code found from codeNum 0 - 999
            throw new MaximumServiceException("You have reached the maximum number of services that you can add"
                + "for homerce");
        }

        return serviceCode;
    }
}
