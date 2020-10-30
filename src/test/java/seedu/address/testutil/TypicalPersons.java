package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTSOURCE_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTSOURCE_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_NAME;
import static seedu.address.testutil.TypicalPolicies.SAVINGS_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.SAVINGS_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ClientList;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withClientSources("friends")
            .withNote("Real Pal")
            .withPriority("u")
            .withPolicy(LIFE_TIME_NAME, LIFE_TIME_DESCRIPTION)
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withClientSources("owesMoney", "friends").withNote("Snake").withPriority("l")
            .withPolicy(LIFE_TIME_NAME, LIFE_TIME_DESCRIPTION)
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withNote("Snake")
            .withPriority("m")
            .withPolicy(LIFE_TIME_NAME, LIFE_TIME_DESCRIPTION)
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withClientSources("friends").withNote("Snake")
            .withPolicy(LIFE_TIME_NAME, LIFE_TIME_DESCRIPTION)
            .withPriority("h").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withNote("snake")
            .withPriority("u")
            .withPolicy(SAVINGS_NAME, SAVINGS_DESCRIPTION)
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withNote("snake")
            .withPriority("l")
            .withPolicy(SAVINGS_NAME, SAVINGS_DESCRIPTION)
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withNote("Snake")
            .withPriority("m")
            .withPolicy(SAVINGS_NAME, SAVINGS_DESCRIPTION).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withNote("sd")
            .withPriority("h").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withPriority("u").build();

    // People in archive
    public static final Person JASON = new PersonBuilder().withName("Jason Dallar").withPhone("848242411")
            .withEmail("jason@example.com").withAddress("toa payoh").withNote("hi")
            .withPriority("h").addToArchive().build();
    public static final Person KING = new PersonBuilder().withName("King Arthur").withPhone("848212231")
            .withEmail("king@example.com").withAddress("west mall").withClientSources("friends")
            .withPriority("u").addToArchive().build();
    public static final Person LINDA = new PersonBuilder().withName("Linda Blinda").withPhone("842382424")
            .withEmail("linda@example.com").withAddress("chinatown").withNote("hello").withClientSources("snake")
            .withPriority("h").addToArchive().build();
    public static final Person MONK = new PersonBuilder().withName("Monkey").withPhone("841482131")
            .withEmail("monk@example.com").withAddress("clementi avenue")
            .withPriority("u").addToArchive().build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withClientSources(VALID_CLIENTSOURCE_FRIEND).withNote(VALID_NOTE_CAT).withPriority(VALID_PRIORITY_HIGH)
            .withoutPolicy()
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withClientSources(VALID_CLIENTSOURCE_HUSBAND, VALID_CLIENTSOURCE_FRIEND)
            .withNote(VALID_NOTE_DOG).withPriority(VALID_PRIORITY_LOW)
            .withoutPolicy()
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ClientList} with all the typical persons.
     */
    public static ClientList getTypicalClientList() {
        ClientList ab = new ClientList();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns an {@code ClientList} with all the typical persons, including cases with archive.
     */
    public static ClientList getTypicalClientListWithArchive() {
        ClientList ab = new ClientList();
        for (Person person : getTypicalPersonsWithArchive()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersonsWithArchive() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE,
                FIONA, GEORGE, JASON, KING, LINDA, MONK));
    }

    /**
     * Returns an {@code ClientList} with all the typical persons, with only the ones in archive.
     */
    public static ClientList getTypicalClientListOnlyArchive() {
        ClientList ab = new ClientList();
        for (Person person : getTypicalPersonsOnlyArchive()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersonsOnlyArchive() {
        return new ArrayList<>(Arrays.asList(JASON, KING, LINDA, MONK));
    }

}
