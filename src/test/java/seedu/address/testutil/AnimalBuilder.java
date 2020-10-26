package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.feedtime.FeedTime;
import seedu.address.model.feedtime.FeedTimeComparator;
import seedu.address.model.medicalcondition.MedicalCondition;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Animal objects.
 */
public class AnimalBuilder {

    public static final String DEFAULT_NAME = "Ah Meng";

    public static final String DEFAULT_ID = "199";

    public static final String DEFAULT_SPECIES = "Panthera leo";

    private Name name;
    private Id id;
    private Species species;
    private Set<MedicalCondition> medicalConditions;
    private Set<FeedTime> feedTimes;

    /**
     * Creates a {@code AnimalBuilder} with the default details.
     */
    public AnimalBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new Id(DEFAULT_ID);
        species = new Species(DEFAULT_SPECIES);
        medicalConditions = new HashSet<>();
        feedTimes = new TreeSet<>(new FeedTimeComparator());
    }

    /**
     * Initializes the AnimalBuilder with the data of {@code animalToCopy}.
     */
    public AnimalBuilder(Animal animalToCopy) {
        name = animalToCopy.getName();
        id = animalToCopy.getId();
        species = animalToCopy.getSpecies();
        medicalConditions = new HashSet<>(animalToCopy.getMedicalConditions());
        feedTimes = new TreeSet<>(animalToCopy.getFeedTimes());
    }

    /**
     * Sets the {@code Name} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code medicalConditions} into a {@code Set<MedicalCondition>} and
     * set it to the {@code Animal} that we are building.
     */
    public AnimalBuilder withMedicalConditions(String ... medicalConditions) {
        this.medicalConditions = SampleDataUtil.getMedicalConditionSet(medicalConditions);
        return this;
    }

    /**
     * Parses the {@code medicalConditions} into a {@code Set<MedicalCondition>} and
     * appends it to the {@code Animal} that we are building.
     */
    public AnimalBuilder withAppendedMedicalConditions(String ... medicalConditions) {
        Set<MedicalCondition> appendedMedicalConditions = SampleDataUtil.getMedicalConditionSet(medicalConditions);
        this.medicalConditions.addAll(appendedMedicalConditions);
        return this;
    }

    /**
     * Parses the {@code feedTimes} into a {@code Set<FeedTime>} and
     * set it to the {@code Animal} that we are building.
     */
    public AnimalBuilder withFeedTimes(String ... feedTimes) {
        this.feedTimes = SampleDataUtil.getFeedTimeSet(feedTimes);
        return this;
    }

    /**
     * Parses the {@code feedTimes} into a {@code Set<FeedTime>} and
     * appends it to the {@code Animal} that we are building.
     */
    public AnimalBuilder withAppendedFeedTimes(String ... feedTimes) {
        Set<FeedTime> combinedFeedTimes = new TreeSet<>(new FeedTimeComparator());
        combinedFeedTimes.addAll(this.feedTimes);
        combinedFeedTimes.addAll(SampleDataUtil.getFeedTimeSet(feedTimes));
        this.feedTimes = combinedFeedTimes;
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    public Animal build() {
        return new Animal(name, id, species, medicalConditions, feedTimes);
    }

}
