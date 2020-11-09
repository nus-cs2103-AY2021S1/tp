package seedu.address.testutil;

import static seedu.address.testutil.command.PatientCommandTestUtil.COMMENT_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_AGE_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PERIOD_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PERIOD_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_TEMP_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_TEMP_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PatientRecords;
import seedu.address.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
            .withTemperature("36.8").withPeriodOfStay("20200911-20200918")
            .withPhone("94351253").withAge("31").withComment("Vegan").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
            .withTemperature("38.0").withPeriodOfStay("20200810-20200824")
            .withPhone("98765432").withAge("67").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz")
            .withTemperature("38.3").withPeriodOfStay("20200910-20200917")
            .withPhone("95352563").withAge("17").withComment("asthmatic").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier")
            .withTemperature("36.3").withPeriodOfStay("20200808-20200809")
            .withPhone("87652533").withAge("55").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer")
            .withTemperature("37.4").withPeriodOfStay("20200707-20200719")
            .withPhone("9482224").withAge("81").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz")
            .withTemperature("36.1").withPeriodOfStay("20201004-20201019")
            .withPhone("9482427").withAge("11").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best")
            .withTemperature("36.4").withPeriodOfStay("20201101-20201114")
            .withPhone("9482442").withAge("28").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier")
            .withTemperature("37.0").withPeriodOfStay("20200913-20200920")
            .withPhone("8482424").withAge("90").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller")
            .withTemperature("37.6").withPeriodOfStay("20200404-20200414")
            .withPhone("8482131").withAge("54").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTemperature(VALID_TEMP_AMY).withPeriodOfStay(VALID_PERIOD_AMY)
            .withAge(VALID_AGE_AMY).withComment(COMMENT_AMY).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTemperature(VALID_TEMP_BOB).withPeriodOfStay(VALID_PERIOD_BOB)
            .withAge(VALID_AGE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code PatientRecords} with all the typical patients.
     */
    public static PatientRecords getTypicalPatientRecords() {
        PatientRecords ab = new PatientRecords();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
