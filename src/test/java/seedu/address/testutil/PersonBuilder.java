package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_TELEGRAM = "alice";
    public static final String DEFAULT_MATRIC_NUMBER = "A1234567H";

    private Name name;
    private Phone phone;
    private Email email;
    private Telegram telegram;
    private MatricNumber matricNumber;
    private Set<Tag> tags;
    private SortedSet<Attendance> attendances;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        matricNumber = new MatricNumber(DEFAULT_MATRIC_NUMBER);
        tags = new HashSet<>();
        attendances = new TreeSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        telegram = personToCopy.getTelegram();
        matricNumber = personToCopy.getMatricNumber();
        tags = new HashSet<>(personToCopy.getTags());
        attendances = new TreeSet<>(personToCopy.getAttendances());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code MatricNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withMatricNumber(String matricNumber) {
        this.matricNumber = new MatricNumber(matricNumber);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code attendances} into a {@code SortedSet<Attendances>} and set it to the {@code Person} that we are
     * building.
     */
    public PersonBuilder withAttendance(String... attendances) {
        this.attendances = SampleDataUtil.getAttendanceSet(attendances);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, telegram, matricNumber, tags, attendances);
    }

}
