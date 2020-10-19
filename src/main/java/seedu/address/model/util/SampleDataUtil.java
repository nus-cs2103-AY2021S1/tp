package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Nric("S0000001A"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getAppointmentSet("23/12/2021 12:09"),
                    new MedicalRecord("www.sample.com/01")),
            new Patient(new Name("Bernice Yu"), new Nric("S0000002A"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getAppointmentSet("23/12/2021 12:09"),
                    new MedicalRecord("www.sample.com/02")),
            new Patient(new Name("Charlotte Oliveiro"), new Nric("S0000003A"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getAppointmentSet("23/12/2021 12:09"),
                    new MedicalRecord("www.sample.com/03")),
            new Patient(new Name("David Li"), new Nric("S0000004A"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getAppointmentSet("23/12/2021 12:09"),
                    new MedicalRecord("www.sample.com/04")),
            new Patient(new Name("Irfan Ibrahim"), new Nric("S0000005A"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getAppointmentSet("23/12/2021 12:09"),
                    new MedicalRecord("www.sample.com/05")),
            new Patient(new Name("Roy Balakrishnan"), new Nric("S0000006A"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getAppointmentSet("23/12/2021 12:09"),
                    new MedicalRecord("www.sample.com/06"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an appointment set containing the list of strings given.
     */
    public static Set<Appointment> getAppointmentSet(String... strings) {
        return Arrays.stream(strings)
                .map(string -> new Appointment().setTime(string))
                .collect(Collectors.toSet());
    }

}
