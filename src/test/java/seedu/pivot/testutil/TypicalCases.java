package seedu.pivot.testutil;

import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_NAME_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_NAME_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.pivot.model.Pivot;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalCases {

    // CasePersons
    public static final Suspect PETER = new CasePersonBuilder().withName("Peter").withGender("M").withPhone("912345678")
            .withEmail("peter@gmail.com").withAddress("Blk 123").buildSuspect();
    public static final Victim TOM = new CasePersonBuilder().withName("Tom").withGender("M").withPhone("912345678")
            .withEmail("tom@gmail.com").withAddress("Blk 123").buildVictim();
    public static final Witness JANICE = new CasePersonBuilder().withName("Janice").withGender("F").buildWitness();

    // Cases
    public static final Case ALICE = new CaseBuilder().withTitle("Alice Pauline")
            .withDocument("name", "test1.txt")
            .withStatus("COLD")
            .withVictims(TOM)
            .withWitnesses(JANICE)
            .withSuspects(PETER)
            .withTags("friends")
            .build();
    public static final Case BENSON = new CaseBuilder().withTitle("Benson Meier")
            .withStatus("CLOSED")
            .withDocument("name", "test1.txt")
            .withVictims(TOM)
            .withWitnesses(JANICE)
            .withTags("owesMoney", "friends")
            .build();

    public static final Case CARL = new CaseBuilder().withTitle("Carl Kurz")
            .build();
    public static final Case DANIEL = new CaseBuilder().withTitle("Daniel Meier")
            .withTags("friends").build();
    public static final Case ELLE = new CaseBuilder().withTitle("Elle Meyer")
            .build();
    public static final Case FIONA = new CaseBuilder().withTitle("Fiona Kunz")
            .build();
    public static final Case GEORGE = new CaseBuilder().withTitle("George Best")
            .build();

    // Manually added
    public static final Case HOON = new CaseBuilder().withTitle("Hoon Meier")
            .build();
    public static final Case IDA = new CaseBuilder().withTitle("Ida Mueller")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Case AMY = new CaseBuilder().withTitle(VALID_NAME_AMY)
            .withStatus(VALID_STATUS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Case BOB = new CaseBuilder().withTitle(VALID_NAME_BOB)
            .withStatus(VALID_STATUS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCases() {} // prevents instantiation

    /**
     * Returns an {@code Pivot} with all the typical persons.
     */
    public static Pivot getTypicalPivot() {
        Pivot ab = new Pivot();
        for (Case investigationCase : getTypicalPersons()) {
            ab.addCase(investigationCase);
        }
        return ab;
    }

    public static List<Case> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
