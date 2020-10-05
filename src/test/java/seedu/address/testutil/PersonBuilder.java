package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Amount;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_DESCRIPTION = "Lunch Bak Chor Mee";
    public static final String DEFAULT_AMOUNT = "3.00";
    public static final String DEFAULT_DATE = "04-10-2020";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Description description;
    private Amount amount;
    private Date date;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        description = personToCopy.getDescription();
        amount = personToCopy.getAmount();
        date = personToCopy.getDate();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Person} that we are building.
     */
    public PersonBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Person} that we are building.
     */
    public PersonBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Person build() {
        return new Person(description, amount, date, remark, tags);
    }

}
