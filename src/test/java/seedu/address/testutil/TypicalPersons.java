package seedu.address.testutil;

import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_ADDRESS_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_ADDRESS_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_ADDRESS_DESC_C;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_EMAIL_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_EMAIL_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_EMAIL_DESC_C;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_GIT_USERNAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_GIT_USERNAME_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_GIT_USERNAME_DESC_C;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_NAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_NAME_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_NAME_DESC_C;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_PHONE_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_PHONE_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_PHONE_DESC_C;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Persons} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Person ALICE = new PersonBuilder().withPersonName("Alice Pauline").withGitUserName("Allie32")
        .withPhone("12345678").withEmail("alicepauline@sample.com").withAddress("123, Jurong West Ave 6, #08-111")
        .build();

    public static PersonBuilder DESC_A = new PersonBuilder()
        .withPersonName(TEAMMATE_NAME_DESC_A)
        .withGitUserName(TEAMMATE_GIT_USERNAME_DESC_A)
        .withPhone(TEAMMATE_PHONE_DESC_A)
        .withEmail(TEAMMATE_EMAIL_DESC_A)
        .withAddress(TEAMMATE_ADDRESS_DESC_A);

    public static PersonBuilder DESC_B = new PersonBuilder()
        .withPersonName(TEAMMATE_NAME_DESC_B)
        .withGitUserName(TEAMMATE_GIT_USERNAME_DESC_B)
        .withPhone(TEAMMATE_PHONE_DESC_B)
        .withEmail(TEAMMATE_EMAIL_DESC_B)
        .withAddress(TEAMMATE_ADDRESS_DESC_B);

    public static PersonBuilder DESC_C = new PersonBuilder()
        .withPersonName(TEAMMATE_NAME_DESC_C)
        .withGitUserName(TEAMMATE_GIT_USERNAME_DESC_C)
        .withPhone(TEAMMATE_PHONE_DESC_C)
        .withEmail(TEAMMATE_EMAIL_DESC_C)
        .withAddress(TEAMMATE_ADDRESS_DESC_C);

}
