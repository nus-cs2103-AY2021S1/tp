package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_NUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_POLLEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.HospifyBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline").withNric("S0000001A")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withAllergies("friends")
            .withMedicalRecord("www.sample.com/01").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier").withNric("S0000002A")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAllergies("owesMoney", "friends")
            .withMedicalRecord("www.sample.com/02").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withNric("S0000003A")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withMedicalRecord("www.sample.com/03").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withNric("S0000004A")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withAllergies("friends").withMedicalRecord("www.sample.com/04").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withNric("S0000005A")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withMedicalRecord("www.sample.com/05").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withNric("S0000006A")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withMedicalRecord("www.sample.com/06").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withNric("S0000007A")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withMedicalRecord("www.sample.com/07").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withNric("S0000008A")
            .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india")
            .withMedicalRecord("www.sample.com/08").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withNric("S0000009A")
            .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave")
            .withMedicalRecord("www.sample.com/09").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withNric("S1234567A")
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withAllergies(VALID_ALLERGY_NUT).withMedicalRecord("www.sample.com/10").build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withNric("S7654321A")
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withAllergies(VALID_ALLERGY_POLLEN, VALID_ALLERGY_NUT).withMedicalRecord("www.sample.com/11").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code HospifyBook} with all the typical persons.
     */
    public static HospifyBook getTypicalHospifyBook() {
        HospifyBook ab = new HospifyBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
