package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

/**
 * Edits the details of an existing property in the property book.
 */
public class EditPropertyCommand extends Command {

    public static final String COMMAND_WORD = "edit-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the property identified "
            + "by the index number used in the displayed property list. "
            + "Existing values will be overwritten by the input values.\n"
            + "\n\nParameters: INDEX (must be a positive integer) "
            + "\n[" + PREFIX_PROPERTY_NAME + "NAME] "
            + "\n[" + PREFIX_PROPERTY_ADDRESS + "ADDRESS] "
            + "\n[" + PREFIX_PROPERTY_SELLER_ID + "SELLER ID] "
            + "\n[" + PREFIX_PROPERTY_ASKING_PRICE + "ASKING PRICE] "
            + "\n[" + PREFIX_PROPERTY_TYPE + "TYPE] "
            + "\n[" + PREFIX_PROPERTY_IS_RENTAL + "YES / NO]\n"
            + "\n\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_PROPERTY_ASKING_PRICE + "500 "
            + PREFIX_PROPERTY_IS_RENTAL + "Yes";

    public static final String MESSAGE_EDIT_PROPERTY_SUCCESS = "Edited Property: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the property book.";

    private final Index index;
    private final EditPropertyDescriptor editPropertyDescriptor;

    /**
     * @param index of the property in the filtered property list to edit
     * @param editPropertyDescriptor details to edit the property with
     */
    public EditPropertyCommand(Index index, EditPropertyDescriptor editPropertyDescriptor) {
        requireNonNull(index);
        requireNonNull(editPropertyDescriptor);

        this.index = index;
        this.editPropertyDescriptor = new EditPropertyDescriptor(editPropertyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToEdit = lastShownList.get(index.getZeroBased());
        Property editedProperty = createEditedProperty(propertyToEdit, editPropertyDescriptor);

        if (!propertyToEdit.isSameProperty(editedProperty) && model.hasProperty(editedProperty)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        assert propertyToEdit.getPropertyId().equals(editedProperty.getPropertyId());
        // not allowed to edit property id

        model.setProperty(propertyToEdit, editedProperty);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(String.format(MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty))
                .setEntity(EntityType.PROPERTY);
    }

    /**
     * Creates and returns a {@code Property} with the details of {@code propertyToEdit}
     * edited with {@code editPropertyDescriptor}.
     */
    private static Property createEditedProperty(Property propertyToEdit,
                                                 EditPropertyDescriptor editPropertyDescriptor) {
        assert propertyToEdit != null;

        PropertyName updatedPropertyName = editPropertyDescriptor.getPropertyName()
                .orElse(propertyToEdit.getPropertyName());
        Address updatedAddress = editPropertyDescriptor.getAddress().orElse(propertyToEdit.getAddress());
        Price updatedAskingPrice = editPropertyDescriptor.getAskingPrice().orElse(propertyToEdit.getAskingPrice());
        SellerId updatedSellerId = editPropertyDescriptor.getSellerId().orElse(propertyToEdit.getSellerId());
        PropertyType updatedPropertyType = editPropertyDescriptor.getPropertyType()
                .orElse(propertyToEdit.getPropertyType());
        IsRental updatedIsRental = editPropertyDescriptor.getIsRental().orElse(propertyToEdit.getIsRental());
        PropertyId updatedPropertyId = editPropertyDescriptor.getPropertyId().orElse(propertyToEdit.getPropertyId());

        return new Property(updatedPropertyId, updatedPropertyName, updatedSellerId, updatedAddress,
                updatedAskingPrice, updatedPropertyType, updatedIsRental, propertyToEdit.isClosedDeal());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPropertyCommand)) {
            return false;
        }

        // state check
        EditPropertyCommand e = (EditPropertyCommand) other;
        return index.equals(e.index)
                && editPropertyDescriptor.equals(e.editPropertyDescriptor);
    }

    /**
     * Stores the details to edit the property with. Each non-empty field value will replace the
     * corresponding field value of the property.
     */
    public static class EditPropertyDescriptor {
        private PropertyName propertyName;
        private Address address;
        private SellerId sellerId;
        private PropertyType propertyType;
        private Price askingPrice;
        private IsRental isRental;
        private PropertyId propertyId;

        public EditPropertyDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditPropertyDescriptor(EditPropertyDescriptor toCopy) {
            setPropertyName(toCopy.propertyName);
            setAddress(toCopy.address);
            setSellerId(toCopy.sellerId);
            setPropertyType(toCopy.propertyType);
            setAskingPrice(toCopy.askingPrice);
            setIsRental(toCopy.isRental);
            setPropertyId(toCopy.propertyId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(propertyName, address, sellerId, propertyType, askingPrice, isRental);
        }

        public void setPropertyName(PropertyName propertyName) {
            this.propertyName = propertyName;
        }

        public Optional<PropertyName> getPropertyName() {
            return Optional.ofNullable(propertyName);
        }

        public void setSellerId(SellerId sellerId) {
            this.sellerId = sellerId;
        }

        public Optional<SellerId> getSellerId() {
            return Optional.ofNullable(sellerId);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setPropertyType(PropertyType propertyType) {
            this.propertyType = propertyType;
        }

        public Optional<PropertyType> getPropertyType() {
            return Optional.ofNullable(propertyType);
        }

        public void setAskingPrice(Price askingPrice) {
            this.askingPrice = askingPrice;
        }

        public Optional<Price> getAskingPrice() {
            return Optional.ofNullable(askingPrice);
        }

        public void setIsRental(IsRental isRental) {
            this.isRental = isRental;
        }

        public Optional<IsRental> getIsRental() {
            return Optional.ofNullable(isRental);
        }

        public void setPropertyId(PropertyId propertyId) {
            this.propertyId = propertyId;
        }

        public Optional<PropertyId> getPropertyId() {
            return Optional.ofNullable(propertyId);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPropertyDescriptor)) {
                return false;
            }

            // state check
            EditPropertyDescriptor e = (EditPropertyDescriptor) other;

            return getPropertyName().equals(e.getPropertyName())
                   && getAddress().equals(e.getAddress())
                    && getPropertyType().equals(e.getPropertyType())
                    && getAskingPrice().equals(e.getAskingPrice())
                    && getSellerId().equals(e.getSellerId())
                    && getIsRental().equals(e.getIsRental())
                    && getPropertyId().equals(e.getPropertyId());
        }
    }
}
