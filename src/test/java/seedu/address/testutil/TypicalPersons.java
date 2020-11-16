package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Tasker;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withPhone("94351253")
        .withEmail("alice@example.com").withTelegram("alice_p").withMatricNumber("A1234567B")
        .withAttendance("27/03/1998").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withPhone("98765432")
        .withEmail("johnd@example.com").withTelegram("bm_99").withMatricNumber("A3256548H")
        .withTags("owesMoney", "friends").withAttendance("27/03/1998", "27/04/1998").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").withTelegram("cKurzzz").withMatricNumber("A9876543D")
        .withAttendance("27/03/1998").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withTelegram("12345").withMatricNumber("A1010101B")
        .withTags("friends").withAttendance("27/03/1998").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").withTelegram("ElleMeyer").withMatricNumber("A9999999F")
        .withAttendance("27/03/1998").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").withTelegram("Fiona_Kunz_").withMatricNumber("A5763762P")
        .withAttendance("27/03/1998").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").withTelegram("_gbest").withMatricNumber("A6372632E")
        .withAttendance("27/03/1998").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY).withMatricNumber(VALID_MATRIC_NUMBER_AMY)
        .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withMatricNumber(VALID_MATRIC_NUMBER_BOB)
        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code Tasker} with all the typical persons.
     */
    public static Tasker getTypicalAddressBook() {
        Tasker ab = new Tasker();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
