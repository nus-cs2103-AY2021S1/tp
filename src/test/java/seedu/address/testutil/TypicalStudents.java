package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_VENUE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_VENUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_TYPE_BOB;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_MATH;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_NEWTON;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Reeve;
import seedu.address.model.student.SchoolType;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Admin;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withSchool("Anderson Secondary").withYear(SchoolType.SECONDARY, 3).withPhone("94351253")
            .withClassVenue("123, Jurong West Ave 6, #08-111")
            .withDetails("owesMoney", "friends")
            .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withSchool("Pei Hwa Secondary").withYear(SchoolType.SECONDARY, 2).withPhone("98765432")
            .withClassTime("2 1200-1430")
            .build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withSchool("Catholic High").withYear(SchoolType.SECONDARY, 5).withPhone("95352563")
            .withFee("450.50")
            .build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withSchool("Methodist Girls School").withYear(SchoolType.SECONDARY, 1).withPhone("87652533")
            .withPaymentDate("28/2/2018")
            .build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withSchool("River Valley High").withYear(SchoolType.JC, 2).withPhone("9482224")
            .withSolved(DEFAULT_SOLUTION, DEFAULT_QUESTION_NEWTON, DEFAULT_QUESTION_MATH)
            .build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withSchool("Raffles Girls School").withYear(SchoolType.SECONDARY, 2).build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withSchool("Montford Secondary").withYear(SchoolType.SECONDARY, 4).build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withSchool("NUS High School").withYear(SchoolType.JC, 2).build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withSchool("NUS High School").withYear(SchoolType.JC, 2).build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withSchool(VALID_SCHOOL_AMY)
            .withYear(VALID_SCHOOL_TYPE_AMY, VALID_SCHOOL_LEVEL_AMY)
            .withClassVenue(VALID_CLASS_VENUE_AMY)
            .withClassTime(VALID_CLASS_TIME_AMY)
            .withFee(VALID_FEE_AMY)
            .withPaymentDate(VALID_PAYMENT_DATE_AMY)
            .withDetails(VALID_ADDITIONAL_DETAILS_AMY)
            .withQuestions(VALID_QUESTION_AMY)
            .build();

    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withSchool(VALID_SCHOOL_BOB)
            .withYear(VALID_SCHOOL_TYPE_BOB, VALID_SCHOOL_LEVEL_BOB)
            .withClassVenue(VALID_CLASS_VENUE_BOB)
            .withClassTime(VALID_CLASS_TIME_BOB)
            .withFee(VALID_FEE_BOB)
            .withPaymentDate(VALID_PAYMENT_DATE_BOB)
            .withDetails(VALID_ADDITIONAL_DETAILS_BOB)
            .withSolved(DEFAULT_SOLUTION, VALID_QUESTION_BOB)
            .build();
    public static final Admin BOB_ADMIN = BOB.getAdmin();

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
