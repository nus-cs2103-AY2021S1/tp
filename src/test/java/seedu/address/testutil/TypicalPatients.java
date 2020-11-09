package seedu.address.testutil;

import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.patient.Patient;



/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
            .withGender("FEMALE")
            .withBirthdate("1998-04-03")
            .withBloodType("O+")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withRemark("")
            .withTags("friends").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
            .withGender("MALE")
            .withBirthdate("1998-08-03")
            .withBloodType("O+")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withRemark("")
            .withTags("owesMoney", "friends").build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz")
            .withGender("MALE")
            .withBirthdate("1998-12-03")
            .withBloodType("O+")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withRemark("").withAddress("wall street").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier")
            .withGender("MALE")
            .withBirthdate("1999-04-16")
            .withBloodType("O+")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withRemark("").withAddress("10th street").withTags("friends").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer")
            .withGender("FEMALE")
            .withBirthdate("1990-07-03")
            .withBloodType("A+")
            .withPhone("9482224")
            .withEmail("werner@example.com").withRemark("").withAddress("michegan ave").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz")
            .withGender("FEMALE")
            .withBirthdate("2000-04-11")
            .withBloodType("B+")
            .withPhone("9482427")
            .withEmail("lydia@example.com").withRemark("").withAddress("little tokyo").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best")
            .withGender("MALE")
            .withBirthdate("2001-05-11")
            .withBloodType("AB+")
            .withPhone("9482442")
            .withEmail("anna@example.com").withRemark("").withAddress("4th street").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier")
            .withGender("MALE")
            .withBirthdate("1999-08-11")
            .withBloodType("B+")
            .withPhone("8482424")
            .withEmail("stefan@example.com").withRemark("").withAddress("little india").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller")
            .withGender("FEMALE")
            .withBirthdate("1999-12-25")
            .withBloodType("AB+")
            .withPhone("8482424")
            .withEmail("hans@example.com").withRemark("").withAddress("chicago ave").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY)
            .withGender(VALID_GENDER_AMY)
            .withBirthdate(VALID_BIRTHDATE_AMY)
            .withBloodType(VALID_BLOODTYPE_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRemark("")
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB)
            .withGender(VALID_GENDER_BOB)
            .withBirthdate(VALID_BIRTHDATE_BOB)
            .withBloodType(VALID_BLOODTYPE_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withRemark("")
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
