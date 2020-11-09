package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLACKLIST_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LINK_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PersonAddressBook;
import seedu.address.model.information.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withDateOfApplication("02-10-19")
            .withPhone("94351253").withExperience("3.5")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withExperience("2.0").withDateOfApplication("12-01-21")
            .withEmail("johnd@example.com").withPhone("98765432").withSalary("13000")
            .withUrlLink("linkedin.com").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withSalary("4500").withEmail("heinz@example.com").withAddress("wall street")
            .withExperience("0.5").withDateOfApplication("02-01-19").withBlacklistStatus("true").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withSalary("3000")
            .withExperience("0").withUrlLink("tinder.com").withTags("friends")
            .withDateOfApplication("1-10-20").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withExperience("1.5")
            .withDateOfApplication("20-12-20").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withSalary("2900").withEmail("lydia@example.com").withAddress("little tokyo")
            .withExperience("10").withDateOfApplication("12-12-12").withBlacklistStatus("true").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withSalary("8888")
            .withExperience("1").withUrlLink("github.com").withDateOfApplication("11-08-20").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withDateOfApplication("01-01-01").withEmail("stefan@example.com").withAddress("little india")
            .withExperience("4.4").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withExperience("20")
            .withDateOfApplication("12-12-12").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withExperience(VALID_EXPERIENCE_AMY).withDateOfApplication(VALID_DATE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withUrlLink(VALID_URL_LINK_BOB)
            .withSalary(VALID_SALARY_BOB).withExperience(VALID_EXPERIENCE_BOB).withDateOfApplication(VALID_DATE_BOB)
            .withBlacklistStatus(VALID_BLACKLIST_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static PersonAddressBook getTypicalPersonAddressBook() {
        PersonAddressBook pab = new PersonAddressBook();
        for (Person person : getTypicalPersons()) {
            pab.addPerson(person);
        }
        return pab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
