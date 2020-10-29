package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.HospifyBook;
import seedu.address.model.ReadOnlyHospifyBook;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;

/**
 * Contains utility methods for populating {@code HospifyBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[]{
            new Patient(new Name("Alex Yeoh"), new Nric("S0000001A"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                        getAllergySet("shellfish"),
                        getAppointmentSet(
                                apptString("X - ray", "23/10/2020 12:09"),
                                apptString("Medical screening", "25/11/2020 14:00"),
                                apptString("Dental appointment", "26/12/2020 13:00")
                        ),
                        new MedicalRecord("www.sample.com/01")),
            new Patient(new Name("Bernice Yu"), new Nric("S0000002A"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getAllergySet("soy", "pollen"), getAppointmentSet(
                        apptString("Medical screening", "25/11/2020 14:00")
                ),
                        new MedicalRecord("www.sample.com/02")),
            new Patient(new Name("Charlotte Oliveiro"), new Nric("S0000003A"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getAllergySet("mould"), getAppointmentSet(
                        apptString("Medical screening", "25/11/2020 14:00")
                ),
                        new MedicalRecord("www.sample.com/03")),
            new Patient(new Name("David Li"), new Nric("S0000004A"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getAllergySet("milk"), getAppointmentSet(
                        apptString("Medical screening", "25/11/2020 14:00"),
                        apptString("Follow up for kidney stones", "30/12/2020 14:00")
                ),
                        new MedicalRecord("www.sample.com/04")),
            new Patient(new Name("Irfan Ibrahim"), new Nric("S0000005A"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        getAllergySet("eggs"), getAppointmentSet(
                        apptString("Medical screening", "25/11/2020 14:00"),
                        apptString("Medical Appointment", "30/12/2020 14:00")
                ),
                        new MedicalRecord("www.sample.com/05")),
            new Patient(new Name("Roy Balakrishnan"), new Nric("S0000006A"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getAllergySet("wheat"), getAppointmentSet(
                        apptString("Medical screening", "25/11/2020 14:00")
                ),
                        new MedicalRecord("www.sample.com/06"))
        };
    }

    public static ReadOnlyHospifyBook getSampleAddressBook() {
        HospifyBook sampleAb = new HospifyBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns an allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an appointment set containing the list of strings given.
     */
    public static Set<Appointment> getAppointmentSet(String[]... appts) {
        return Arrays.stream(appts).map(
            appt -> new Appointment().setDescription(appt[0]).setTime(appt[1])
        ).collect(Collectors.toSet());
    }

    private static String[] apptString(String description, String time) {
        return new String[]{description, time};
    }
}
