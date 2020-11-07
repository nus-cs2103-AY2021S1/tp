package com.eva.testutil.applicant;

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

import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.testutil.ApplicantBuilder;
import com.eva.testutil.PersonBuilder;

/**
 * Contains sample applicants that can be used for tests.
 */
public class TypicalApplicants {
    public static final Applicant ALICE = new ApplicantBuilder().withName("Alice Pauline")
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
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822243")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824273")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824423")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824242")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821312")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
}
