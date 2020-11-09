package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditAttractionDescriptor objects.
 */
public class EditAttractionDescriptorBuilder {

    private EditAttractionDescriptor descriptor;

    public EditAttractionDescriptorBuilder() {
        descriptor = new EditAttractionDescriptor();
    }

    public EditAttractionDescriptorBuilder(EditAttractionDescriptor descriptor) {
        this.descriptor = new EditAttractionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAttractionDescriptor} with fields containing {@code attraction}'s details
     */
    public EditAttractionDescriptorBuilder(Attraction attraction) {
        descriptor = new EditAttractionDescriptor();
        descriptor.setName(attraction.getName());
        descriptor.setPhone(attraction.getPhone());
        descriptor.setEmail(attraction.getEmail());
        descriptor.setAddress(attraction.getAddress());
        descriptor.setDescription(attraction.getDescription());
        descriptor.setLocation(attraction.getLocation());
        descriptor.setOpeningHours(attraction.getOpeningHours());
        descriptor.setPriceRange(attraction.getPriceRange());
        descriptor.setRating(attraction.getRating());
        descriptor.setVisited(attraction.getVisited());
        descriptor.setTags(attraction.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Opening Hours} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withOpeningHours(String openingHours) {
        descriptor.setOpeningHours(new OpeningHours(openingHours));
        return this;
    }

    /**
     * Sets the {@code Price Range} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withPriceRange(String priceRange) {
        descriptor.setPriceRange(new PriceRange(priceRange));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withVisited(String visited) {
        descriptor.setVisited(new Visited(visited));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditAttractionDescriptor}
     * that we are building.
     */
    public EditAttractionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditAttractionDescriptor build() {
        return descriptor;
    }
}
