package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ClientList;
import seedu.address.model.ReadOnlyClientList;
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;

/**
 * Contains utility methods for populating {@code ClientList} with sample data.
 */
public class SampleClientDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getClientSourceSet("friends"), new Note("friend"),
                    new Priority("u"),
                    new Policy(
                            new PolicyName("Life Time Policy"),
                            new PolicyDescription("Covers death, serious illnesses and serious disability."))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getClientSourceSet("colleagues", "friends"), new Note("friend"),
                    new Priority("l"),
                    new Policy(
                            new PolicyName("Savings Plan"),
                            new PolicyDescription("Earn up to 10% total amount put in."))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getClientSourceSet("neighbours"), new Note("friend"),
                    new Priority("m"),
                    new Policy(
                            new PolicyName("Life Time Policy"),
                            new PolicyDescription("Covers death, serious illnesses and serious disability."))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getClientSourceSet("family"), new Note("friend"),
                    new Priority("h"),
                    new Policy(
                            new PolicyName("Life Time Policy"),
                            new PolicyDescription("Covers death, serious illnesses and serious disability."))),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getClientSourceSet("classmates"), new Note("friend"),
                    new Priority("u"),
                    new Policy(
                            new PolicyName("Life Time Policy"),
                            new PolicyDescription("Covers death, serious illnesses and serious disability."))),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getClientSourceSet("colleagues"), new Note("friend"),
                    new Priority("l"),
                    new Policy(
                            new PolicyName("Savings Plan"),
                            new PolicyDescription("Earn up to 10% total amount put in.")))

        };
    }

    public static ReadOnlyClientList getSampleClientList() {
        ClientList sampleAb = new ClientList();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a clientsource set containing the list of strings given.
     */
    public static Set<ClientSource> getClientSourceSet(String... strings) {
        return Arrays.stream(strings)
                .map(ClientSource::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a note from the string given.
     */
    public static String getNote(String string) {
        return string;
    }

}
