package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.patient.Name;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalVisits {

    public static final Visit VISIT_1 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2020, 05, 15))
            .withPatientName("Alice Pauline")
            .withDiagnosis("Headache")
            .withPrescription("Aspirin")
            .withComment("No need for follow up")
            .build();
    public static final Visit VISIT_2 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2019, 03, 27))
            .withPatientName("Benson Meier")
            .withDiagnosis("Depression")
            .withPrescription("Antifreeze")
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
            .build();
    public static final Visit VISIT_5 = new VisitBuilder()
            .withVisitDate(LocalDate.of(2016, 8, 18))
            .withPatientName("Elle Meyer")
            .withDiagnosis("COVID-19")
            .withPrescription("Remdesivir")
            .withComment("Need to be quarantined immediately")
            .build();
    public static final Visit VISIT_6 = new VisitBuilder()
            .build();
    public static final Visit VISIT_7 = new VisitBuilder()
            .withPatientName("Benson Meier")
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
