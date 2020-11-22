package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_GROUP_BASKETBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_GROUP_DANCE;
import static seedu.address.testutil.TypicalEvents.getTypicalEvents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withGender("F").withMatriculationNumber("A0123456B")
            .withBlock("A").withRoom("315").withStudentGroups("badminton").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withGender("M").withMatriculationNumber("A0123456C")
            .withBlock("C").withRoom("410").withStudentGroups("hackers", "soccer").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withBlock("B").withRoom("119").withGender("M").withMatriculationNumber("A0123456D").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withGender("M")
            .withBlock("A").withRoom("201").withMatriculationNumber("A0123456E").withStudentGroups("dance").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withBlock("D").withRoom("118").withGender("F").withMatriculationNumber("A0123456F").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withBlock("C").withRoom("301").withMatriculationNumber("A0123456G").withGender("F")
            .withStudentGroups("badminton").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withBlock("B").withRoom("310").withMatriculationNumber("A0123456H").withGender("M")
            .withStudentGroups("basketball").build();


    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withMatriculationNumber("A0123456I")
            .withBlock("A").withRoom("420").withGender("M").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withMatriculationNumber("A0123456J")
            .withBlock("B").withRoom("320").withGender("F").build();


    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withStudentGroups(VALID_STUDENT_GROUP_BASKETBALL)
            .withGender(VALID_GENDER_AMY).withBlock(VALID_BLOCK_AMY).withRoom(VALID_ROOM_AMY)
            .withMatriculationNumber(VALID_MATRICULATION_NUMBER_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withStudentGroups(VALID_STUDENT_GROUP_DANCE, VALID_STUDENT_GROUP_BASKETBALL).withGender(VALID_GENDER_BOB)
            .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB)
            .withBlock(VALID_BLOCK_BOB).withRoom(VALID_ROOM_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns an {@code List<StudentGroup>} with all the student groups.
     */
    public static List<String> getTypicalStudentGroups() {
        Set<String> studentGroupSet = new HashSet<>();
        for (Person person : getTypicalPersons()) {
            for (StudentGroup studentGroup : person.getStudentGroups()) {
                studentGroupSet.add(studentGroup.studentGroupName);
            }
        }
        return studentGroupSet.stream().sorted().collect(Collectors.toList());
    }

}
