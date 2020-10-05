package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Assignment;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment CS1231S_HW = new AssignmentBuilder().withName("CS1231S Homework")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Assignment CS2103T_TUT = new AssignmentBuilder().withName("CS2103T Tutorial")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Assignment CS2106_LAB = new AssignmentBuilder().withName("CS2106 Lab").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Assignment CS2106_TUTORIAL_QUIZ = new AssignmentBuilder()
            .withName("CS2106 Tutorial Quiz").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Assignment IS1103_MISSION = new AssignmentBuilder()
            .withName("IS1103 Mission").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Assignment PEER_REVIEW = new AssignmentBuilder()
            .withName("Peer review").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Assignment ORAL_PRESENTATION = new AssignmentBuilder()
            .withName("Oral presentation").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Assignment READ = new AssignmentBuilder().withName("Read notes").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Assignment SLIDE = new AssignmentBuilder().withName("Prepare slide").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Assignment's details found in {@code CommandTestUtil}
    public static final Assignment HW = new AssignmentBuilder().withName(VALID_NAME_HW).withPhone(VALID_PHONE_HW)
            .withEmail(VALID_EMAIL_HW).withAddress(VALID_ADDRESS_HW).withTags(VALID_TAG_FRIEND).build();
    public static final Assignment LAB = new AssignmentBuilder().withName(VALID_NAME_LAB).withPhone(VALID_PHONE_LAB)
            .withEmail(VALID_EMAIL_LAB).withAddress(VALID_ADDRESS_LAB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    private TypicalAssignments() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical assignments.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Assignment assignment : getTypicalAssignments()) {
            ab.addAssignment(assignment);
        }
        return ab;
    }

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(CS1231S_HW, CS2103T_TUT, CS2106_LAB, CS2106_TUTORIAL_QUIZ,
                IS1103_MISSION, PEER_REVIEW, ORAL_PRESENTATION));
    }
}
