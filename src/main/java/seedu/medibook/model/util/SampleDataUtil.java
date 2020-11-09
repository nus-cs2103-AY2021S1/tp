package seedu.medibook.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Record;
import seedu.medibook.model.patient.Weight;

/**
 * Contains utility methods for populating {@code MediBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Ic("S9123456A"), new Name("Alex Yeoh"), new DateOfBirth("15-05-1991"),
                    new Phone("87438807"), Optional.of(new Email("alexyeoh@example.com")),
                    Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")), Optional.of(new Height("173")),
                    Optional.of(new Weight("67.4")), Optional.of(new BloodType("A+")), getAllergySet("Penicillin"),
                    getConditionSet("back sprain"), getTreatmentSet("physiotherapy")),
            new Patient(new Ic("S8786858D"), new Name("Bernice Yu"), new DateOfBirth("17-09-1987"),
                    new Phone("99272758"), Optional.of(new Email("berniceyu@example.com")),
                    Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
                    Optional.of(new Height("166")), Optional.of(new Weight("48.2")),
                    Optional.of(new BloodType("B+")), getAllergySet(),
                    getConditionSet("ankle sprain", "fever"), getTreatmentSet("physiotherapy", "Ibuprofen")),
            new Patient(new Ic("T1234567G"), new Name("Charlotte Oliveiro"), new DateOfBirth("28-02-2012"),
                    new Phone("93210283"), Optional.of(new Email("charlotte@example.com")),
                    Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")), Optional.of(new Height("175")),
                    Optional.of(new Weight("58.5")), Optional.of(new BloodType("A+")), getAllergySet("peanut"),
                    getConditionSet(), getTreatmentSet("epinephrine")),
            new Patient(new Ic("F7654321K"), new Name("David Li"), new DateOfBirth("20-12-1976"),
                    new Phone("91031282"), Optional.of(new Email("lidavid@example.com")),
                    Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
                    Optional.of(new Height("169")), Optional.of(new Weight("64.9")), Optional.of(new BloodType("O+")),
                    getAllergySet("NSAIDs"), getConditionSet(), getTreatmentSet()),
            new Patient(new Ic("G1928374R"), new Name("Irfan Ibrahim"), new DateOfBirth("11-04-2000"),
                    new Phone("92492021"), Optional.of(new Email("irfan@example.com")),
                    Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")), Optional.of(new Height("180")),
                    Optional.of(new Weight("80.5")), Optional.of(new BloodType("AB+")), getAllergySet("shellfish"),
                    getConditionSet("knee sprain"), getTreatmentSet("physiotherapy")),
            new Patient(new Ic("S9182736Q"), new Name("Roy Balakrishnan"), new DateOfBirth("14-06-1991"),
                    new Phone("92624417"), Optional.of(new Email("royb@example.com")),
                    Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")), Optional.of(new Height("165")),
                    Optional.of(new Weight("60.6")), Optional.of(new BloodType("A-")),
                    getAllergySet("fish", "Phenytoin"), getConditionSet("mitral valve prolapse"),
                    getTreatmentSet())
        };
    }

    public static ReadOnlyMediBook getSampleMediBook() {
        MediBook sampleMb = new MediBook();
        Patient[] initPatients = generateRecords(getSamplePatients());
        for (Patient samplePatient : initPatients) {
            sampleMb.addPatient(samplePatient);
        }
        return sampleMb;
    }

    /**
     * Returns a allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
            .map(Allergy::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns a condition set containing the list of strings given.
     */
    public static Set<Condition> getConditionSet(String... strings) {
        return Arrays.stream(strings)
            .map(Condition::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns a treatment set containing the list of strings given.
     */
    public static Set<Treatment> getTreatmentSet(String... strings) {
        return Arrays.stream(strings)
                .map(Treatment::new)
                .collect(Collectors.toSet());
    }

    private static Patient[] generateRecords(Patient[] patients) {
        for (Patient patient : patients) {
            LocalDate date = LocalDate.now();
            String currentHeight = patient.getHeight().get().value;
            String currentWeight = patient.getWeight().get().value;
            Record patientRecord = patient.getRecord();
            double weightVariance = Math.random() * 10;
            for (int i = 0; i < Math.random() * 20; i++) {
                String dateString = toDateString(date);
                Date recordDate = new Date(dateString, true);
                Height recordHeight = new Height(currentHeight);
                Weight recordWeight = new Weight(currentWeight);
                patientRecord.addHeightRecord(recordDate, recordHeight);
                patientRecord.addWeightRecord(recordDate, recordWeight);

                date = date.minusDays((int) (Math.random() * 20));
                int updatedHeight = Integer.parseInt(currentHeight) - (int) (Math.random() * 1.5);
                currentHeight = Integer.toString(updatedHeight);

                double updatedWeight = Double.parseDouble(currentWeight) + (weightVariance * (Math.random() - 0.5));
                currentWeight = toOneDecimal(Double.toString(updatedWeight));
            }
        }
        return patients;
    }

    private static String toDateString(LocalDate date) {
        // LocalDate toString returns dates as yyyy-mm-dd
        List<String> dateInfo = Arrays.asList(date.toString().split("-"));
        Collections.reverse(dateInfo);
        // Reverse the year, month and date position to create a valid date string for the Date class
        return String.join("-", dateInfo);
    }

    private static String toOneDecimal(String value) {
        int decimalPos = value.indexOf(".");
        return value.substring(0, decimalPos + 2);
    }

}
