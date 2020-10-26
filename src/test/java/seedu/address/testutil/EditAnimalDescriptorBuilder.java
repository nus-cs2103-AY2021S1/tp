package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditAnimalDescriptor;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.feedtime.FeedTime;
import seedu.address.model.medicalcondition.MedicalCondition;

/**
 * A utility class to help with building EditAnimalDescriptor objects.
 */
public class EditAnimalDescriptorBuilder {

    private EditAnimalDescriptor descriptor;

    public EditAnimalDescriptorBuilder() {
        descriptor = new EditAnimalDescriptor();
    }

    public EditAnimalDescriptorBuilder(EditAnimalDescriptor descriptor) {
        this.descriptor = new EditAnimalDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAnimalDescriptor} with fields containing {@code animal}'s details
     */
    public EditAnimalDescriptorBuilder(Animal animal) {
        descriptor = new EditAnimalDescriptor();
        descriptor.setName(animal.getName());
        descriptor.setId(animal.getId());
        descriptor.setSpecies(animal.getSpecies());
        descriptor.setMedicalConditions(animal.getMedicalConditions());
        descriptor.setFeedTimes(animal.getFeedTimes());
    }

    /**
     * Sets the {@code Name} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withId(String id) {
        descriptor.setId(new Id(id));
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withSpecies(String species) {
        descriptor.setSpecies(new Species(species));
        return this;
    }

    /**
     * Parses the {@code medicalConditions} into a {@code Set<MedicalCondition>}
     * and set it to the {@code EditAnimalDescriptor}
     * that we are building.
     */
    public EditAnimalDescriptorBuilder withMedicalConditions(String... medicalConditions) {
        Set<MedicalCondition> medicalConditionSet = Stream.of(medicalConditions).map(MedicalCondition::new)
                .collect(Collectors.toSet());
        descriptor.setMedicalConditions(medicalConditionSet);
        return this;
    }

    /**
     * Parses the {@code feedTimes} into a {@code Set<FeedTime>}
     * and set it to the {@code EditAnimalDescriptor}
     * that we are building.
     */
    public EditAnimalDescriptorBuilder withFeedTimes(String... feedTimes) {
        Set<FeedTime> feedTimeSet = Stream.of(feedTimes).map(FeedTime::new)
                .collect(Collectors.toSet());
        descriptor.setFeedTimes(feedTimeSet);
        return this;
    }

    public EditAnimalDescriptor build() {
        return descriptor;
    }

}
