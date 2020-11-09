package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;


/**
 * A utility class to help with building EditItineraryAttractionDescriptor objects.
 */
public class EditItineraryAttractionDescriptorBuilder {

    private EditItineraryAttractionDescriptor descriptor;

    public EditItineraryAttractionDescriptorBuilder() {
        descriptor = new EditItineraryAttractionDescriptor();
    }

    public EditItineraryAttractionDescriptorBuilder(EditItineraryAttractionDescriptor descriptor) {
        this.descriptor = new EditItineraryAttractionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItineraryAttractionDescriptor} with fields containing {@code attraction}'s details
     */
    public EditItineraryAttractionDescriptorBuilder(ItineraryAttraction attraction) {
        descriptor = new EditItineraryAttractionDescriptor();
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
        descriptor.setStartTime(attraction.getStartTime());
        descriptor.setEndTime(attraction.getEndTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Opening Hours} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withOpeningHours(String openingHours) {
        descriptor.setOpeningHours(new OpeningHours(openingHours));
        return this;
    }

    /**
     * Sets the {@code Price Range} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withPriceRange(String priceRange) {
        descriptor.setPriceRange(new PriceRange(priceRange));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withVisited(String visited) {
        descriptor.setVisited(new Visited(visited));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditItineraryAttractionDescriptor}
     * that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code 0EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(new ItineraryTime(startTime));
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code EditItineraryAttractionDescriptor} that we are building.
     */
    public EditItineraryAttractionDescriptorBuilder withEndTime(String endTime) {
        descriptor.setEndTime(new ItineraryTime(endTime));
        return this;
    }

    public EditItineraryAttractionDescriptor build() {
        return descriptor;
    }
}
