package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Reeve;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withSchool("Anderson Secondary").withYear("3").withPhone("94351253")
            .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withSchool("Pei Hwa Secondary").withYear("2").withPhone("98765432")
            .build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withSchool("Catholic High").withYear("5").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withSchool("Methodist Girls School").withYear("1").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withSchool("River Valley High").withYear("6").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withSchool("Raffles Girls School").withYear("2").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withSchool("Montford Secondary").withYear("4").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withSchool("NUS High School")
            .withYear("6").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withSchool("NUS High School")
            .withYear("6").build();


    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
            .withPhone(CommandTestUtil.VALID_PHONE_AMY)
            .withSchool(CommandTestUtil.VALID_SCHOOL_AMY)
            .withYear(CommandTestUtil.VALID_YEAR_AMY)
            .withClassVenue(CommandTestUtil.VALID_VENUE_AMY)
            .withClassTime(CommandTestUtil.VALID_TIME_AMY)
            .withFee(CommandTestUtil.VALID_FEE_AMY)
            .withPaymentDate(CommandTestUtil.VALID_PAYMENT_AMY)
            .build();
    public static final Student BOB = new StudentBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
            .withPhone(CommandTestUtil.VALID_PHONE_BOB)
            .withSchool(CommandTestUtil.VALID_SCHOOL_BOB)
            .withYear(CommandTestUtil.VALID_YEAR_BOB)
            .withClassVenue(CommandTestUtil.VALID_VENUE_BOB)
            .withClassTime(CommandTestUtil.VALID_TIME_BOB)
            .withFee(CommandTestUtil.VALID_FEE_BOB)
            .withPaymentDate(CommandTestUtil.VALID_PAYMENT_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static Reeve getTypicalAddressBook() {
        Reeve ab = new Reeve();
        for (Student student : getTypicalPersons()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
