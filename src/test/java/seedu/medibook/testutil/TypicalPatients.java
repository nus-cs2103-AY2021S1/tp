package seedu.medibook.testutil;

import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_SHELLFISH;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_BACK;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_DIABETES;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PARACETAMOL;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final MedicalNoteList ALICE_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote ALICE_MEDICAL_NOTE_1 = new MedicalNote(new Date("19-02-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is good."));
    public static final MedicalNote ALICE_MEDICAL_NOTE_2 = new MedicalNote(new Date("25-08-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is bad."));
    static {
        ALICE_MEDICAL_NOTE_LIST.add(ALICE_MEDICAL_NOTE_1);
        ALICE_MEDICAL_NOTE_LIST.add(ALICE_MEDICAL_NOTE_2);
    }
    public static final Patient ALICE = new PatientBuilder().withIc("S9777777R").withName("Alice Pauline")
            .withDateOfBirth("15-09-1997").withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withHeight("174").withWeight("48.5").withBloodType("A+")
            .withAllergies("cat fur (Fel d 1)").withConditions("Migraine").withTreatments("Ibuprofen")
            .withMedicalNoteList(ALICE_MEDICAL_NOTE_LIST).build();
    public static final int ALICE_NUM_OF_MEDICAL_NOTES = 2;

    public static final MedicalNoteList BENSON_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote BENSON_MEDICAL_NOTE_1 = new MedicalNote(new Date("14-04-2020", true),
            new Doctor(new Name("Gary Lin"), new Mcr("MX1239B")),
            new Content("Patient is good."));
    public static final MedicalNote BENSON_MEDICAL_NOTE_2 = new MedicalNote(new Date("28-08-2020", true),
            new Doctor(new Name("Mary Ann"), new Mcr("MY1039B")),
            new Content("Patient is bad."));
    static {
        BENSON_MEDICAL_NOTE_LIST.add(BENSON_MEDICAL_NOTE_1);
        BENSON_MEDICAL_NOTE_LIST.add(BENSON_MEDICAL_NOTE_2);
    }
    public static final Patient BENSON = new PatientBuilder().withIc("S9234567A").withName("Benson Meier")
            .withDateOfBirth("01-03-1992").withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withHeight("170").withWeight("63.5")
            .withBloodType("AB+").withAllergies("shellfish", "dust mites").withConditions("knee sprain")
            .withTreatments("Physiotherapy").withMedicalNoteList(BENSON_MEDICAL_NOTE_LIST).build();

    public static final MedicalNoteList CARL_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote CARL_MEDICAL_NOTE_1 = new MedicalNote(new Date("02-05-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is good."));
    public static final MedicalNote CARL_MEDICAL_NOTE_2 = new MedicalNote(new Date("12-07-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is bad."));
    static {
        CARL_MEDICAL_NOTE_LIST.add(CARL_MEDICAL_NOTE_1);
        CARL_MEDICAL_NOTE_LIST.add(CARL_MEDICAL_NOTE_2);
    }
    public static final Patient CARL = new PatientBuilder().withIc("S9876543W").withName("Carl Kurz")
            .withDateOfBirth("16-01-1998").withPhone("95352563").withEmail("heinz@example.com")
            .withAddress("wall street").withHeight("162").withWeight("80.8").withBloodType("B+")
            .withAllergies("peanut").withConditions("fever")
            .withTreatments("epinephrine", "Paracetamol").withMedicalNoteList(CARL_MEDICAL_NOTE_LIST).build();

    public static final MedicalNoteList DANIEL_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote DANIEL_MEDICAL_NOTE_1 = new MedicalNote(new Date("19-02-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is good."));
    public static final MedicalNote DANIEL_MEDICAL_NOTE_2 = new MedicalNote(new Date("25-08-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is bad."));
    static {
        DANIEL_MEDICAL_NOTE_LIST.add(DANIEL_MEDICAL_NOTE_1);
        DANIEL_MEDICAL_NOTE_LIST.add(DANIEL_MEDICAL_NOTE_2);
    }
    public static final Patient DANIEL = new PatientBuilder().withIc("T0054321P").withName("Daniel Meier")
            .withDateOfBirth("10-10-2000").withPhone("87652533").withEmail("cornelia@example.com")
            .withAddress("10th street").withHeight("183").withWeight("70.3").withBloodType("A-")
            .withAllergies("potatoes").withConditions("Type I diabetes").withTreatments("Insulin pump")
            .withMedicalNoteList(DANIEL_MEDICAL_NOTE_LIST).build();

    public static final MedicalNoteList ELLE_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote ELLE_MEDICAL_NOTE_1 = new MedicalNote(new Date("19-02-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is good."));
    public static final MedicalNote ELLE_MEDICAL_NOTE_2 = new MedicalNote(new Date("25-08-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is bad."));
    static {
        ELLE_MEDICAL_NOTE_LIST.add(ELLE_MEDICAL_NOTE_1);
        ELLE_MEDICAL_NOTE_LIST.add(ELLE_MEDICAL_NOTE_2);
    }
    public static final Patient ELLE = new PatientBuilder().withIc("F7654321Q").withName("Elle Meyer")
            .withDateOfBirth("05-10-1976").withPhone("9482224").withEmail("werner@example.com")
            .withAddress("michegan ave").withHeight("177").withWeight("43.5").withBloodType("B-")
            .withMedicalNoteList(ELLE_MEDICAL_NOTE_LIST).build();

    public static final MedicalNoteList FIONA_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote FIONA_MEDICAL_NOTE_1 = new MedicalNote(new Date("19-02-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is good."));
    public static final MedicalNote FIONA_MEDICAL_NOTE_2 = new MedicalNote(new Date("25-08-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is bad."));
    static {
        FIONA_MEDICAL_NOTE_LIST.add(FIONA_MEDICAL_NOTE_1);
        FIONA_MEDICAL_NOTE_LIST.add(FIONA_MEDICAL_NOTE_2);
    }
    public static final Patient FIONA = new PatientBuilder().withIc("S7543210A").withName("Fiona Kunz")
            .withDateOfBirth("02-07-1975").withPhone("9482427").withEmail("lydia@example.com")
            .withAddress("little tokyo").withHeight("168").withWeight("50.5").withBloodType("O+")
            .withAllergies("Penicillin").withMedicalNoteList(FIONA_MEDICAL_NOTE_LIST).build();

    public static final MedicalNoteList GEORGE_MEDICAL_NOTE_LIST = new MedicalNoteList();
    public static final MedicalNote GEORGE_MEDICAL_NOTE_1 = new MedicalNote(new Date("19-02-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is good."));
    public static final MedicalNote GEORGE_MEDICAL_NOTE_2 = new MedicalNote(new Date("25-08-2020", true),
            new Doctor(new Name("John"), new Mcr("MP2819J")),
            new Content("Patient is bad."));
    static {
        GEORGE_MEDICAL_NOTE_LIST.add(GEORGE_MEDICAL_NOTE_1);
        GEORGE_MEDICAL_NOTE_LIST.add(GEORGE_MEDICAL_NOTE_2);
    }
    public static final Patient GEORGE = new PatientBuilder().withIc("T0232323I").withName("George Best")
            .withDateOfBirth("14-07-2002").withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withHeight("169").withWeight("69.2").withBloodType("A+").withConditions("fever")
            .withTreatments("Paracetemol").withMedicalNoteList(GEORGE_MEDICAL_NOTE_LIST).build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withIc("S8756432F").withName("Hoon Meier")
            .withDateOfBirth("23-03-1987").withPhone("8482424").withEmail("stefan@example.com")
            .withAddress("little india").withHeight("171").withBloodType("A+").build();
    public static final Patient IDA = new PatientBuilder().withIc("T0066441J").withName("Ida Mueller")
            .withDateOfBirth("01-03-1992").withPhone("8482131").withEmail("hans@example.com")
            .withAddress("chicago ave").withHeight("160").withBloodType("A+").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withIc(VALID_IC_AMY).withName(VALID_NAME_AMY)
            .withDateOfBirth(VALID_DOB_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withHeight(VALID_HEIGHT_AMY).withWeight(VALID_WEIGHT_AMY)
            .withBloodType(VALID_BLOOD_TYPE_AMY).withTreatments(VALID_TREATMENT_PARACETAMOL)
            .withAllergies(VALID_ALLERGY_PENICILLIN).withConditions(VALID_CONDITION_DIABETES).build();
    public static final Patient BOB = new PatientBuilder().withIc(VALID_IC_BOB).withName(VALID_NAME_BOB)
            .withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
            .withBloodType(VALID_BLOOD_TYPE_BOB).withAllergies(VALID_ALLERGY_PENICILLIN, VALID_ALLERGY_SHELLFISH)
            .withConditions(VALID_CONDITION_BACK, VALID_CONDITION_DIABETES)
            .withTreatments(VALID_TREATMENT_PHYSIOTHERAPY, VALID_TREATMENT_PARACETAMOL).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    public static final int VALID_NOTE_INDEX = 2;
    public static final int OUT_OF_RANGE_NOTE_INDEX = 3;

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code MediBook} with all the typical patients.
     */
    public static MediBook getTypicalMediBook() {
        MediBook mediBook = new MediBook();
        for (Patient patient : getTypicalPatients()) {
            Patient newCopyOfPatient = new PatientBuilder(patient).build();
            mediBook.addPatient(newCopyOfPatient);
        }
        return mediBook;
    }

    /**
     * Returns an {@code MediBook} with all the typical patients with empty medical note list.
     */
    public static MediBook getTypicalMediBookWithAllEmptyMedicalNoteList() {
        MediBook mediBook = new MediBook();
        for (Patient patient : getTypicalPatients()) {
            Patient newCopyOfPatient = new PatientBuilder(patient).build();
            newCopyOfPatient.setMedicalNoteList(new MedicalNoteList());
            mediBook.addPatient(newCopyOfPatient);
        }
        return mediBook;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(new PatientBuilder(ALICE).build(), new PatientBuilder(BENSON).build(),
                new PatientBuilder(CARL).build(), new PatientBuilder(DANIEL).build(),
                new PatientBuilder(ELLE).build(), new PatientBuilder(FIONA).build(),
                new PatientBuilder(GEORGE).build()));
    }
}
