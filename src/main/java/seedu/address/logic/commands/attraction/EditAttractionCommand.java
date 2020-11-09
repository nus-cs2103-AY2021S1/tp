package seedu.address.logic.commands.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISITED;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
 * Edits the details of an existing attraction in the attractions list in TrackPad.
 */
public class EditAttractionCommand extends Command {

    public static final String COMMAND_WORD = "edit-attraction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the attraction identified "
            + "by the index number used in the displayed attraction list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX must be a number between 0 and 2147483648 "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_OPENING_HOURS + "OPENING HOURS] "
            + "[" + PREFIX_PRICE_RANGE + "PRICE RANGE] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_VISITED + "VISITED] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "62693411 "
            + PREFIX_EMAIL + "sgzoo@example.com.";

    public static final String MESSAGE_EDIT_ATTRACTION_SUCCESS = "Edited Attraction: %1$s.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ATTRACTION =
            "This attraction already exists in TrackPad, please choose a different name/location.";
    public static final String MESSAGE_FIELD_NOT_CHANGED =
            "Please provide a different value for the field(s) to be edited.";

    private final Index index;
    private final EditAttractionDescriptor editAttractionDescriptor;

    /**
     * @param index of the attraction in the filtered attraction list to edit
     * @param editAttractionDescriptor details to edit the attraction with
     */
    public EditAttractionCommand(Index index, EditAttractionDescriptor editAttractionDescriptor) {
        requireNonNull(index);
        requireNonNull(editAttractionDescriptor);

        this.index = index;
        this.editAttractionDescriptor = new EditAttractionDescriptor(editAttractionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Attraction> lastShownList = model.getFilteredAttractionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        Attraction attractionToEdit = lastShownList.get(index.getZeroBased());
        Attraction editedAttraction = createEditedAttraction(attractionToEdit, editAttractionDescriptor);

        if (attractionToEdit.equals(editedAttraction)) {
            throw new CommandException(MESSAGE_FIELD_NOT_CHANGED);
        }

        if (!attractionToEdit.isSameAttraction(editedAttraction) && model.hasAttraction(editedAttraction)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.setAttraction(attractionToEdit, editedAttraction);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_ATTRACTION_SUCCESS, editedAttraction));
    }

    /**
     * Creates and returns a {@code Attraction} with the details of {@code attractionToEdit}
     * edited with {@code editAttractionDescriptor}.
     */
    private static Attraction createEditedAttraction(Attraction attractionToEdit,
                                                     EditAttractionDescriptor editAttractionDescriptor) {
        assert attractionToEdit != null;

        Name updatedName = editAttractionDescriptor.getName().orElse(attractionToEdit.getName());
        Phone updatedPhone = editAttractionDescriptor.getPhone().orElse(attractionToEdit.getPhone());
        Email updatedEmail = editAttractionDescriptor.getEmail().orElse(attractionToEdit.getEmail());
        Address updatedAddress = editAttractionDescriptor.getAddress().orElse(attractionToEdit.getAddress());
        Description updatedDescription = editAttractionDescriptor
                .getDescription().orElse(attractionToEdit.getDescription());
        Location updatedLocation = editAttractionDescriptor.getLocation().orElse(attractionToEdit.getLocation());
        OpeningHours updatedOpeningHours = editAttractionDescriptor
                .getOpeningHours().orElse(attractionToEdit.getOpeningHours());
        PriceRange updatedPriceRange = editAttractionDescriptor
                .getPriceRange().orElse(attractionToEdit.getPriceRange());
        Rating updatedRating = editAttractionDescriptor.getRating().orElse(attractionToEdit.getRating());
        Visited updatedVisited = editAttractionDescriptor.getVisited().orElse(attractionToEdit.getVisited());
        Set<Tag> updatedTags = editAttractionDescriptor.getTags().orElse(attractionToEdit.getTags());

        return new Attraction(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedDescription, updatedLocation, updatedOpeningHours, updatedPriceRange,
                updatedRating, updatedVisited, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAttractionCommand)) {
            return false;
        }

        // state check
        EditAttractionCommand e = (EditAttractionCommand) other;
        return index.equals(e.index)
                && editAttractionDescriptor.equals(e.editAttractionDescriptor);
    }

    /**
     * Stores the details to edit the attraction with. Each non-empty field value will replace the
     * corresponding field value of the attraction.
     */
    public static class EditAttractionDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Description description;
        private Location location;
        private OpeningHours openingHours;
        private PriceRange priceRange;
        private Rating rating;
        private Visited visited;
        private Set<Tag> tags;

        public EditAttractionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAttractionDescriptor(EditAttractionDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDescription(toCopy.description);
            setLocation(toCopy.location);
            setOpeningHours(toCopy.openingHours);
            setPriceRange(toCopy.priceRange);
            setRating(toCopy.rating);
            setVisited(toCopy.visited);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, description,
                    location, openingHours, priceRange, rating, visited, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setOpeningHours(OpeningHours openingHours) {
            this.openingHours = openingHours;
        }

        public Optional<OpeningHours> getOpeningHours() {
            return Optional.ofNullable(openingHours);
        }

        public void setPriceRange(PriceRange priceRange) {
            this.priceRange = priceRange;
        }

        public Optional<PriceRange> getPriceRange() {
            return Optional.ofNullable(priceRange);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        public void setVisited(Visited visited) {
            this.visited = visited;
        }

        public Optional<Visited> getVisited() {
            return Optional.ofNullable(visited);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAttractionDescriptor)) {
                return false;
            }

            // state check
            EditAttractionDescriptor e = (EditAttractionDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getDescription().equals(e.getDescription())
                    && getLocation().equals(e.getLocation())
                    && getOpeningHours().equals(e.getOpeningHours())
                    && getPriceRange().equals(e.getPriceRange())
                    && getRating().equals(e.getRating())
                    && getVisited().equals(e.getVisited())
                    && getTags().equals(e.getTags());
        }
    }
}
