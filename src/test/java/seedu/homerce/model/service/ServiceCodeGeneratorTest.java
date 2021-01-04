package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.service.exceptions.MaximumServiceException;
import seedu.homerce.testutil.service.ServiceBuilder;

public class ServiceCodeGeneratorTest {
    private String generateRandomString() {
        Random random = new Random();
        return random.ints(97, 122) // a to z
            .limit(5)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    @Test
    public void generateNewServiceCode_fullSerivces_throwsException() {
        List<Service> fullServiceList = new ArrayList<>();
        List<Appointment> emptyAppointments = new ArrayList<>();
        for (int codeNum = 0; codeNum < 1000; codeNum++) {
            String serviceCodeNum = "SC" + "0".repeat(3 - String.valueOf(codeNum).length())
                + String.valueOf(codeNum);

            Service testService = new ServiceBuilder().withTitle(generateRandomString())
                .withServiceCode(serviceCodeNum).build();
            fullServiceList.add(testService);
        }

        assertThrows(MaximumServiceException.class, () ->
            ServiceCodeGenerator.generateNewServiceCode(fullServiceList, emptyAppointments));
    }

    @Test
    public void generateNewServiceCode_emptyServices_returnsValidServiceCode() {
        List<Service> emptyServiceList = new ArrayList<>();
        List<Appointment> emptyAppointments = new ArrayList<>();
        assertEquals("SC000",
            ServiceCodeGenerator.generateNewServiceCode(emptyServiceList, emptyAppointments));
    }

    @Test
    public void generateNewServiceCode_someExistingServices_returnsValidServiceCode() {
        List<Service> someExistingServicesList = new ArrayList<>();
        List<Appointment> emptyAppointments = new ArrayList<>();
        for (int codeNum = 0; codeNum < 3; codeNum++) {
            String serviceCodeNum = "SC" + "0".repeat(3 - String.valueOf(codeNum).length())
                + String.valueOf(codeNum);

            Service testService = new ServiceBuilder().withTitle(generateRandomString())
                .withServiceCode(serviceCodeNum).build();
            someExistingServicesList.add(testService);
        }
        assertEquals("SC003",
            ServiceCodeGenerator.generateNewServiceCode(someExistingServicesList, emptyAppointments));

    }

    @Test
    public void generateNewServiceCode_servicesSkipped_returnsSmallestValidServiceCode() {
        // List of services has skipped service codes, could occur if user deletes a service
        List<Service> someExistingServicesList = new ArrayList<>();
        List<Appointment> emptyAppointments = new ArrayList<>();
        for (int codeNum = 0; codeNum < 8; codeNum++) {
            String serviceCodeNum = "SC" + "0".repeat(3 - String.valueOf(codeNum).length())
                + String.valueOf(codeNum);

            Service testService = new ServiceBuilder().withTitle(generateRandomString())
                .withServiceCode(serviceCodeNum).build();
            someExistingServicesList.add(testService);
        }
        someExistingServicesList.remove(3);

        assertEquals("SC003",
            ServiceCodeGenerator.generateNewServiceCode(someExistingServicesList, emptyAppointments));
    }

    @Test
    public void generateNewServiceCode_null_returnsFirstServiceCode() {
        assertEquals("SC000",
            ServiceCodeGenerator.generateNewServiceCode(null, null));

    }
}
