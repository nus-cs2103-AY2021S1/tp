package seedu.address.testutil;

import seedu.address.model.CliniCal;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalVisits {

    public static final Visit VISIT_1 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2020, 05, 15))
            .withPatientName(new Name("Alice Pauline"))
            .withDiagnosis("Headache")
            .withPrescription("Aspirin")
            .withComment("No need for follow up")
            .build();
    public static final Visit VISIT_2 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2019, 03, 27))
            .withPatientName(new Name("Benson Meier"))
            .withDiagnosis("Depression")
            .withPrescription("Antifreeze")
            .build();
    public static final Visit VISIT_3 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2018, 10, 5))
            .withPatientName(new Name("Carl Kurz"))
            .withDiagnosis("Obesity")
            .withPrescription("Peloton")
            .withComment("Need to check daily")
            .build();
    public static final Visit VISIT_4 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2017, 12, 10))
            .withPatientName(new Name("Daniel Meier"))
            .withDiagnosis("Back Pain")
            .withPrescription("Painkiller")
            .build();
    public static final Visit VISIT_5 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2016, 8, 18))
            .withPatientName(new Name("Elle Meyer"))
            .withDiagnosis("COVID-19")
            .withPrescription("Remdesivir")
            .withComment("Need to be quarantined immediately")
            .build();
    public static final Visit VISIT_6 = new VisitBuilder()
            .build();
    public static final Visit VISIT_7 = new VisitBuilder()
            .withPatientName(new Name("Benson Meier"))
            .build();

    private TypicalVisits() {} // prevents instantiation

    /**
     * Returns a VisitHistory {@code CliniCal} with all the typical visits from the same patient.
     */
    public static VisitHistory getTypicalVisitHistory1() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Arrays.asList(VISIT_1, VISIT_6));
        return new VisitHistory(typicalVisits);
    }

    /**
     * Returns a VisitHistory {@code CliniCal} with all the typical visits from the same patient.
     */
    public static VisitHistory getTypicalVisitHistory2() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Arrays.asList(VISIT_2, VISIT_7));
        return new VisitHistory(typicalVisits);
    }

}
