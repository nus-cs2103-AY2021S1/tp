package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CliniCal;
import seedu.address.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline").withPhone("94351253")
            .withIcNumber("S4623454A").withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("F").withBloodType("A+")
            .withAllergies("aspirin").withColorTag("None")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryAlice()).build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier").withPhone("98765432")
            .withIcNumber("S7435696B").withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("M").withBloodType("B+")
            .withAllergies("penicillin", "sulfa").withColorTag("None")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryBenson()).build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withIcNumber("S8864424C").withAddress("wall street").withEmail("heinz@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("M").withBloodType("O+")
            .withColorTag("None")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryCarl()).build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withIcNumber("G3011359D").withAddress("10th street").withEmail("cornelia@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("M").withBloodType("O+").withAllergies("aspirin")
            .withColorTag("None")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryDaniel()).build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withIcNumber("S6131369E").withAddress("michegan ave").withEmail("werner@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("F").withBloodType("AB+")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryElle()).withColorTag("None").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withIcNumber("S6787656F").withAddress("little tokyo").withEmail("lydia@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("F").withBloodType("A+")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryFiona()).withColorTag("None").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withPhone("9482442")
            .withIcNumber("S2444153G").withAddress("4th street").withEmail("anna@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("M").withBloodType("B+")
            .withVisitHistory(TypicalVisits.getTypicalVisitHistoryGeorge()).withColorTag("None").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withIcNumber("S1678497H").withAddress("little india").withEmail("stefan@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("M").withBloodType("O+")
            .withColorTag("None").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withIcNumber("G8775328I").withAddress("chicago ave").withEmail("hans@example.com")
            .withProfilePicture("data/stock_picture.png").withSex("F").withBloodType("AB+")
            .withColorTag("None").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withIcNumber(VALID_ICNUMBER_AMY).withAddress(VALID_ADDRESS_AMY).withEmail(VALID_EMAIL_AMY)
            .withProfilePicture(VALID_PROFILE_PICTURE_AMY).withSex(VALID_SEX_AMY).withBloodType(VALID_BLOODTYPE_AMY)
            .withAllergies(VALID_ALLERGY_ASPIRIN).withColorTag("None").build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withIcNumber(VALID_ICNUMBER_BOB).withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB)
            .withProfilePicture(VALID_PROFILE_PICTURE_BOB).withSex(VALID_SEX_BOB).withBloodType(VALID_BLOODTYPE_BOB)
            .withAllergies(VALID_ALLERGY_PENICILLIN).withColorTag("None").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code CliniCal} with all the typical patients.
     */
    public static CliniCal getTypicalCliniCal() {
        CliniCal ab = new CliniCal();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
