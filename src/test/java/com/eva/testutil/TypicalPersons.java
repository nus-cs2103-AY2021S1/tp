package com.eva.testutil;

import static com.eva.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static com.eva.model.util.SampleDataUtil.getCommentSet;
import static com.eva.model.util.SampleDataUtil.getTagSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.eva.model.EvaDatabase;
import com.eva.model.person.*;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withComments("title 1|2010-10-10|hi").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code EvaDatabase} with all the typical persons.
     */
    public static EvaDatabase<Person> getTypicalPersonDatabase() {
        EvaDatabase<Person> ab = new EvaDatabase<>();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code EvaDatabase} with all the typical staffs.
     */
    public static EvaDatabase<Staff> getTypicalStaffDatabase() {
        EvaDatabase<Staff> ab = new EvaDatabase<>();
        for (Staff staff : getTypicalStaffs()) {
            ab.addPerson(staff);
        }
        return ab;
    }

    /**
     * Returns an {@code EvaDatabase} with all the typical applicants.
     */
    public static EvaDatabase<Applicant> getTypicalApplicantDatabase() {
        EvaDatabase<Applicant> ab = new EvaDatabase<>();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addPerson(applicant);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Staff> getTypicalStaffs() {
        // TODO: Add some meaningful leaves here
        Set<Leave> leaves = new HashSet<>();
        return getTypicalPersons().stream()
                .map(person -> new Staff(person, leaves))
                .collect(Collectors.toList());
    }

    public static List<Applicant> getTypicalApplicants() {
        Applicant[] typicalApplicants = new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("applicant"),
                    getCommentSet("Interests|2010-10-10|passionate about marketing"),
                    new ApplicationStatus("received")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("applicant"),
                    getCommentSet("Duration|2010-11-10|Internship from Jan to Jun"),
                    Optional.of(new InterviewDate("02/11/2020")), new ApplicationStatus("processing")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("applicant"), getCommentSet(), new ApplicationStatus("received")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("applicant"),
                    getCommentSet("Review|2010-10-10|Have experience in marketing at big firm before"),
                    Optional.of(new InterviewDate("02/11/2020")), new ApplicationStatus("processing")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("applicant", "business"), getCommentSet("title 7|2010-07-10|hi"),
                    new ApplicationStatus("received")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("applicant", "tech"), getCommentSet("title 8|2011-11-10|hi"),
                    new ApplicationStatus("received"))
        };
        return Arrays.asList(typicalApplicants);
    }
}
