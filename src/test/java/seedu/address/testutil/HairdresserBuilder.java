package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;
import seedu.address.model.util.SampleDataUtil;

public class HairdresserBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_TITLE = "Senior Stylist";

    private Name name;
    private Phone phone;
    private Email email;
    private Gender gender;
    private Title title;
    private Set<Specialisation> specs;

    /**
     * Creates a {@code HairdresserBuilder} with the default details.
     */
    public HairdresserBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gender = new Gender(DEFAULT_GENDER);
        title = new Title(DEFAULT_TITLE);
        specs = new HashSet<>();
        specs.add(new Specialisation("Perm"));
    }

    /**
     * Initializes the HairdresserBuilder with the data of {@code hairdresserToCopy}.
     */
    public HairdresserBuilder(Hairdresser hairdresserToCopy) {
        name = hairdresserToCopy.getName();
        phone = hairdresserToCopy.getPhone();
        email = hairdresserToCopy.getEmail();
        gender = hairdresserToCopy.getGender();
        title = hairdresserToCopy.getTitle();
        specs = new HashSet<>(hairdresserToCopy.getSpecs());
    }

    /**
     * Sets the {@code Name} of the {@code Hairdresser} that we are building.
     */
    public HairdresserBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code specs} into a {@code Set<Specialisation>} and set it
     * to the {@code Hairdresser} that we are building.
     */
    public HairdresserBuilder withSpecs(String ... specs) {
        this.specs = SampleDataUtil.getSpecSet(specs);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Hairdresser} that we are building.
     */
    public HairdresserBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Hairdresser} that we are building.
     */
    public HairdresserBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }


    /**
     * Sets the {@code Gender} of the {@code Hairdresser} that we are building.
     */
    public HairdresserBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code Hairdresser} that we are building.
     */
    public HairdresserBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    public Hairdresser build() {
        return new Hairdresser(name, phone, email, gender, title, specs);
    }

    public Hairdresser build(HairdresserId id) {
        return new Hairdresser(id, name, phone, email, gender, title, specs);
    }
}
