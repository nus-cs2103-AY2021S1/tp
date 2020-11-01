package trackitnus.testutil.typical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.model.contact.Contact;
import trackitnus.testutil.builder.ContactBuilder;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
        .withEmail("alice@example.com")
        .withPhone("94351253")
        .withTags("friends").build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
        .withEmail("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").build();

    // Manually added - Contact's details found in {@code CommandTestUtil}
    public static final Contact AMY =
        new ContactBuilder().withName(ContactCommandTestUtil.VALID_NAME_AMY)
            .withPhone(ContactCommandTestUtil.VALID_PHONE_AMY)
            .withEmail(ContactCommandTestUtil.VALID_EMAIL_AMY)
            .withTags(ContactCommandTestUtil.VALID_TAG_FRIEND).build();
    public static final Contact BOB =
        new ContactBuilder().withName(ContactCommandTestUtil.VALID_NAME_BOB)
            .withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
            .withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB)
            .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND, ContactCommandTestUtil.VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalContacts() {
    } // prevents instantiation


    public static List<Contact> get() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
