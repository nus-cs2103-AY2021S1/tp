package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Description;
import seedu.address.model.person.Document;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reference;
import seedu.address.model.person.Status;
import seedu.address.model.person.Suspect;
import seedu.address.model.person.Title;
import seedu.address.model.person.Victim;
import seedu.address.model.person.Witness;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_TITLE = "Alice Pauline";
    public static final String DEFAULT_DESCRIPTION = "";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_STATUS = "active";

    private Title title;
    private Description description;
    private Phone phone;
    private Status status;
    private List<Document> documents;
    private List<Suspect> suspects;
    private List<Victim> victims;
    private Set<Tag> tags;
    private List<Witness> witnesses;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        phone = new Phone(DEFAULT_PHONE);
        status = Status.createStatus(DEFAULT_STATUS);
        documents = new ArrayList<>();
        suspects = new ArrayList<>();
        victims = new ArrayList<>();
        witnesses = new ArrayList<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        title = personToCopy.getTitle();
        description = personToCopy.getDescription();
        phone = personToCopy.getPhone();
        status = personToCopy.getStatus();
        documents = new ArrayList<>(personToCopy.getDocuments());
        suspects = personToCopy.getSuspects();
        victims = personToCopy.getVictims();
        witnesses = new ArrayList<>(personToCopy.getWitnesses());
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withWitnesses(String ... witnesses) {
        this.witnesses = SampleDataUtil.getWitnessList(witnesses);
        return this;
    }

    /**
     * Sets the {@code Document} of the {@code Person} that we are building.
     */
    public PersonBuilder withDocument(String name, String ref) {
        this.documents = new ArrayList<>();
        this.documents.add(new Document(new Name(name), new Reference(ref)));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = Status.createStatus(status);
        return this;
    }

    //TODO: Not used anywhere in code, use the witness example and use there
    /**
     * Parses the {@code suspects} into a {@code List<Suspect>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSuspects(String ... suspects) {
        this.suspects = SampleDataUtil.getSuspectList(suspects);
        return this;
    }

    /**
     * Parses the {@code victims} into a {@code List<Victim>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withVictims(String ... victims) {
        this.victims = SampleDataUtil.getVictimList(victims);
        return this;
    }

    /**
     * Generates a {@code Person} object with existing fields.
     * @return Person object
     */
    public Person build() {
        return new Person(title, description, phone, status, documents,
                suspects, victims, witnesses, tags);
    }


}
