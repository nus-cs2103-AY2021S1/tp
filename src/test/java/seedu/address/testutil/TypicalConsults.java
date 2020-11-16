package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Tasker;
import seedu.address.model.consultation.Consultation;


/**
 * A utility class containing a list of {@code Consultation} objects to be used in tests.
 */
public class TypicalConsults {

    public static final String ALICE_DAY = "10/10/2020";
    public static final String ALICE_TIME = "18:00";
    public static final String ALICE_LOCATION = "Tembusu College";
    public static final String ALICE_CONSULT_TYPE = "personal";

    public static final String BENSON_DAY = "12/10/2020";
    public static final String BENSON_TIME = "10:00";
    public static final String BENSON_LOCATION = "Kent Ridge Drive";
    public static final String BENSON_CONSULT_TYPE = "group";

    public static final Consultation ALICE_PERSONAL_CONSULT = new ConsultationBuilder()
        .withName(ALICE.getName().toString())
        .withDay(ALICE_DAY)
        .withTime(ALICE_TIME)
        .withLocation(ALICE_LOCATION)
        .withType(ALICE_CONSULT_TYPE)
        .build();

    public static final Consultation BENSON_GROUP_CONSULT = new ConsultationBuilder()
        .withName(BENSON.getName().toString())
        .withDay(BENSON_DAY)
        .withTime(BENSON_TIME)
        .withLocation(BENSON_LOCATION)
        .withType(BENSON_CONSULT_TYPE)
        .build();

    // Manually added - Consultation's details found in {@code CommandTestUtil}
    public static final Consultation PERSONAL_CONSULT = new ConsultationBuilder().withName(VALID_NAME_AMY)
        .withDay(VALID_DAY_AMY).withTime(VALID_TIME_AMY).withLocation(VALID_LOCATION_AMY)
        .withType(VALID_TYPE_AMY).build();
    public static final Consultation GROUP_CONSULT = new ConsultationBuilder().withName(VALID_NAME_BOB)
        .withDay(VALID_DAY_BOB).withTime(VALID_TIME_BOB).withLocation(VALID_LOCATION_BOB)
        .withType(VALID_TYPE_BOB).build();

    private TypicalConsults() {
    } // prevents instantiation

    /**
     * Returns a {@code Tasker} with all the typical consultations.
     *
     * @return a {@code Tasker} with all the typical consultations
     */
    public static Tasker getTypicalTasker() {
        Tasker ab = new Tasker();
        for (Consultation consultation : getTypicalConsultations()) {
            ab.addConsultation(consultation);
        }
        return ab;
    }

    /**
     * Returns a list of typical {@code Consultation}s.
     *
     * @return list of typical {@code Consultation}s
     */
    public static List<Consultation> getTypicalConsultations() {
        return new ArrayList<>(Arrays.asList(ALICE_PERSONAL_CONSULT, BENSON_GROUP_CONSULT));
    }
}
