package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Birthdate;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Gender("MALE"),
                    new Birthdate("2019-02-28"), new BloodType("A+"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                    getTagSet("friends")),
            new Patient(new Name("Bernice Yu"), new Gender("FEMALE"),
                    new Birthdate("1998-10-03"), new BloodType("B+"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                    getTagSet("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new Gender("FEMALE"),
                    new Birthdate("1998-08-13"), new BloodType("A-"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                    getTagSet("neighbours")),
            new Patient(new Name("David Li"), new Gender("MALE"),
                    new Birthdate("1997-12-28"), new BloodType("B-"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                    getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new Gender("MALE"),
                    new Birthdate("1996-08-17"), new BloodType("AB+"),
                    new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                    getTagSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new Gender("MALE"),
                    new Birthdate("1990-01-23"), new BloodType("AB-"),
                    new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                    getTagSet("colleagues"))
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

}
