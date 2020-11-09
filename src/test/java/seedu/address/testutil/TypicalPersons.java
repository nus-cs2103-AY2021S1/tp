package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DOUBLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_SUITE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DOUBLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SUITE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DOUBLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SUITE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_DOUBLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_SUITE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DOUBLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_SUITE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PersonBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final int VALID_PERSON_ID_ALICE = 1;

    public static final Person ALICE = new PersonBuilder()
            .withId(VALID_PERSON_ID_ALICE).withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder()
            .withId(2).withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder()
            .withId(3).withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder()
            .withId(4).withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder()
            .withId(5).withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder()
            .withId(6).withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder()
            .withId(7).withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withId(8).withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder()
            .withId(9).withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withId(VALID_ID_AMY).withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder()
            .withId(VALID_ID_BOB).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final Person GENE = new PersonBuilder()
            .withId(VALID_PERSON_ID_GENE).withName(VALID_NAME_GENE).withPhone(VALID_PHONE_GENE)
            .withEmail(VALID_EMAIL_GENE).withAddress(VALID_ADDRESS_GENE).withTags(VALID_TAG_FRIEND).build();

    public static final Person SINGLE_HARRY = new PersonBuilder()
            .withId(VALID_PERSON_ID_SINGLE_HARRY).withName(VALID_NAME_SINGLE_HARRY)
            .withPhone(VALID_PHONE_SINGLE_HARRY).withEmail(VALID_EMAIL_SINGLE_HARRY)
            .withAddress(VALID_ADDRESS_SINGLE_HARRY).withTags(VALID_TAG_FRIEND).build();

    public static final Person DOUBLE_HARRY = new PersonBuilder()
            .withId(VALID_PERSON_ID_DOUBLE_HARRY).withName(VALID_NAME_DOUBLE_HARRY)
            .withPhone(VALID_PHONE_DOUBLE_HARRY).withEmail(VALID_EMAIL_DOUBLE_HARRY)
            .withAddress(VALID_ADDRESS_DOUBLE_HARRY).withTags(VALID_TAG_FRIEND).build();

    public static final Person SUITE_HARRY = new PersonBuilder()
            .withId(VALID_PERSON_ID_SUITE_HARRY).withName(VALID_NAME_SUITE_HARRY)
            .withPhone(VALID_PHONE_SUITE_HARRY).withEmail(VALID_EMAIL_SUITE_HARRY)
            .withAddress(VALID_ADDRESS_SUITE_HARRY).withTags(VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code PersonBook} with all the typical persons.
     */
    public static PersonBook getTypicalPersonBook() {
        PersonBook ab = new PersonBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
