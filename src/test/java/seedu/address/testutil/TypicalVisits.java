package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalVisits {

    public static final Visit VISIT_1 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2020, 5, 15))
            .withPatientName("Alice Pauline")
            .withDiagnosis("Headache")
            .withPrescription("Aspirin")
            .withComment("No need for follow up")
            .build();
    public static final Visit VISIT_2 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2019, 3, 27))
            .withPatientName("Benson Meier")
            .withDiagnosis("Depression")
            .withPrescription("Antifreeze")
            .withComment("")
            .build();
    public static final Visit VISIT_3 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2018, 10, 5))
            .withPatientName("Carl Kurz")
            .withDiagnosis("Obesity")
            .withPrescription("Peloton")
            .withComment("Need to check daily")
            .build();
    public static final Visit VISIT_4 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2017, 12, 10))
            .withPatientName("Daniel Meier")
            .withDiagnosis("Back Pain")
            .withPrescription("Painkiller")
            .withComment("")
            .build();
    public static final Visit VISIT_5 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2016, 8, 18))
            .withPatientName("Elle Meyer")
            .withDiagnosis("COVID-19")
            .withPrescription("Remdesivir")
            .withComment("Need to be quarantined immediately")
            .build();
    public static final Visit VISIT_6 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2017, 1, 25))
            .withPatientName("Fiona Kunz")
            .withDiagnosis("Fever")
            .withPrescription("Paracetamol")
            .withComment("Dislikes swallowing pills")
            .build();
    public static final Visit VISIT_7 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2018, 9, 13))
            .withPatientName("George Best")
            .withDiagnosis("Sprained ankle")
            .withPrescription("Painkillers")
            .withComment("Follow up in 2 weeks")
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

    /**
     * Returns a VisitHistory {@code CliniCal} with all the typical visits from the same patient.
     */
    public static VisitHistory getTypicalVisitHistoryAlice() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_1));
        return new VisitHistory(typicalVisits);
    }

    /**
     * Returns a VisitHistory {@code CliniCal} with all the typical visits from the same patient.
     */
    public static VisitHistory getTypicalVisitHistoryBenson() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_2));
        return new VisitHistory(typicalVisits);
    }

    public static VisitHistory getTypicalVisitHistoryCarl() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_3));
        return new VisitHistory(typicalVisits);
    }

    public static VisitHistory getTypicalVisitHistoryDaniel() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_4));
        return new VisitHistory(typicalVisits);
    }

    public static VisitHistory getTypicalVisitHistoryElle() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_5));
        return new VisitHistory(typicalVisits);
    }

    public static VisitHistory getTypicalVisitHistoryFiona() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_6));
        return new VisitHistory(typicalVisits);
    }

    public static VisitHistory getTypicalVisitHistoryGeorge() {
        ArrayList<Visit> typicalVisits = new ArrayList<>(Collections.singletonList(VISIT_7));
        return new VisitHistory(typicalVisits);
    }
}
