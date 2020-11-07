package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Office;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Department("Computer Science"), new Office("COM1-02-01"), EMPTY_REMARK,
                getTagSet("lecturer")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Department("Business Analytics"), new Office("COM1-02-10"), EMPTY_REMARK,
                getTagSet("lecturer", "friend")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Department("Data Science and Analytics"), new Office("COM1-01-05"), EMPTY_REMARK,
                getTagSet("senior")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Department("Information Security"), new Office("COM1-01-06"), EMPTY_REMARK,
                getTagSet("friend")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Department("Information Systems"), new Office("COM1-02-04"), EMPTY_REMARK,
                getTagSet("senior", "friend")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Department("Computer Engineering"), new Office("COM1-02-05"), EMPTY_REMARK,
                getTagSet("associate"))
        };
    }

    public static Module[] getSampleSemOneModules() {
        return new Module[] {
            new Module(new ModuleCode("CS1010"), new ModuleName("Programming Methodology")),
            new Module(new ModuleCode("CS1231"), new ModuleName("Discrete Structures")),
            new Module(new ModuleCode("IS1103"), new ModuleName("Ethics in Computing")),
            new Module(new ModuleCode("MA1101R"), new ModuleName("Linear Algebra")),
            new Module(new ModuleCode("MA1521"), new ModuleName("Calculus for Computing")),
            new Module(new ModuleCode("CS2040"), new ModuleName("Data Structures and Algorithms")),
            new Module(new ModuleCode("CS2103"), new ModuleName("Software Engineering")),
            new Module(new ModuleCode("CS2105"), new ModuleName("Introduction to Computer Networks")),
            new Module(new ModuleCode("CS3203"), new ModuleName("Software Engineering Project")),
            new Module(new ModuleCode("IS2102"), new ModuleName(
                    "Enterprise Systems Architecture and Design")),
        };
    }

    public static Module[] getSampleSemTwoModules() {
        return new Module[] {
            new Module(new ModuleCode("CS1101S"), new ModuleName("Programming Methodology")),
            new Module(new ModuleCode("CS1231"), new ModuleName("Discrete Structures")),
            new Module(new ModuleCode("CS2030"), new ModuleName("Programming Methodology II")),
            new Module(new ModuleCode("CS2102"), new ModuleName("Database Systems")),
            new Module(new ModuleCode("CS2106"), new ModuleName("Introduction to Operating Systems")),
            new Module(new ModuleCode("IS2101"), new ModuleName("Business and Technical Communication")),
            new Module(new ModuleCode("ST2334"), new ModuleName("Probability and Statistics")),
            new Module(new ModuleCode("CS3210"), new ModuleName("Parallel Computing")),
            new Module(new ModuleCode("CS3230"), new ModuleName("Design and Analysis of Algorithms")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Module sampleModule : getSampleSemOneModules()) {
            sampleAb.addSemOneModule(sampleModule);
        }
        for (Module sampleModule : getSampleSemTwoModules()) {
            sampleAb.addSemTwoModule(sampleModule);
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
     * Returns a Person set containing the list of persons given.
     */
    public static Set<Person> getPersonSet(Person... persons) {
        return Arrays.stream(persons).collect(Collectors.toSet());
    }

}
