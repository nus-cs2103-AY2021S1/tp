package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.studentgroup.StudentGroup;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BLOCK = "A";
    public static final String DEFAULT_ROOM = "210";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_MATRICULATION_NUMBER = "A0123456A";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Gender gender;
    private Set<StudentGroup> studentGroups;
    private Block block;
    private Room room;
    private MatriculationNumber matriculationNumber;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        studentGroups = new HashSet<>();
        block = new Block(DEFAULT_BLOCK);
        room = new Room(DEFAULT_ROOM);
        matriculationNumber = new MatriculationNumber(DEFAULT_MATRICULATION_NUMBER);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        gender = personToCopy.getGender();
        studentGroups = new HashSet<>(personToCopy.getStudentGroups());
        block = personToCopy.getBlock();
        room = personToCopy.getRoom();
        matriculationNumber = personToCopy.getMatriculationNumber();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code studentGroups} into a {@code Set<StudentGroup>} and
     * set it to the {@code Person} that we are building.
     */
    public PersonBuilder withStudentGroups(String ... studentGroups) {
        this.studentGroups = SampleDataUtil.getStudentGroupSet(studentGroups);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Block} of the {@code Person} that we are building.
     */
    public PersonBuilder withBlock(String block) {
        this.block = new Block(block);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Person} that we are building.
     */
    public PersonBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }
    /**
     * Sets the {@code gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }


    public Person build() {
        return new Person(name, phone, email, address, gender, studentGroups, block, room, matriculationNumber);
    }

    /**
     * Sets the {@code MatriculationNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = new MatriculationNumber(matriculationNumber);
        return this;
    }
}
