package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_CLOSED_DEAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.property.AskingPricePredicate;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIsClosedDealPredicate;
import seedu.address.model.property.PropertyIsRentalPredicate;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.model.property.PropertyTypeContainsKeywordsPredicate;
import seedu.address.model.property.SellerIdContainsKeywordsPredicate;

/**
 * Finds and lists all properties in property book whose attributes contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPropertyCommand extends Command {

    public static final String COMMAND_WORD = "find-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties whose attributes contain"
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters:\n"
            + "[" + PREFIX_PROPERTY_PROPERTY_ID + "PROPERTY ID] "
            + "[" + PREFIX_PROPERTY_NAME + "NAME] "
            + "[" + PREFIX_PROPERTY_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PROPERTY_SELLER_ID + "SELLER ID] "
            + "[" + PREFIX_PROPERTY_ASKING_PRICE + "== / <= / >= / < / > ASKING PRICE] "
            + "[" + PREFIX_PROPERTY_TYPE + "TYPE] "
            + "[" + PREFIX_PROPERTY_IS_RENTAL + "YES / NO]\n"
            + "[" + PREFIX_PROPERTY_IS_CLOSED_DEAL + "Closed / Active]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROPERTY_ASKING_PRICE + "< 500 "
            + PREFIX_PROPERTY_IS_RENTAL + "Yes";
    public static final String MESSAGE_NO_FILTERS = "At least one field to filter must be provided.";

    private final FindPropertyDescriptor findPropertyDescriptor;

    /**
     * Finds and lists all properties in property book whose attributes contains any of the argument keywords.
     * Keyword matching is case insensitive.
     */
    public FindPropertyCommand(FindPropertyDescriptor findPropertyDescriptor) {
        this.findPropertyDescriptor = findPropertyDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(findPropertyDescriptor.getComposedPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PROPERTY_LISTED_OVERVIEW, model.getFilteredPropertyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPropertyCommand // instanceof handles nulls
                && findPropertyDescriptor.equals(((FindPropertyCommand) other).findPropertyDescriptor));
    }

    /**
     * Stores the details to find the property with. Search results will find properties that matches
     * all the non-empty fields.
     */
    public static class FindPropertyDescriptor {
        private PropertyNameContainsKeywordsPredicate propertyNamePredicate;
        private PropertyAddressContainsKeywordsPredicate addressPredicate;
        private SellerIdContainsKeywordsPredicate sellerIdPredicate;
        private PropertyTypeContainsKeywordsPredicate propertyTypePredicate;
        private AskingPricePredicate askingPricePredicate;
        private PropertyIsRentalPredicate isRentalPredicate;
        private PropertyIdContainsKeywordsPredicate propertyIdPredicate;
        private PropertyIsClosedDealPredicate isClosedDealPredicate;

        public FindPropertyDescriptor() {
        }

        /**
         * Returns true if at least one field is found.
         */
        public boolean isAnyFieldFound() {
            return CollectionUtil.isAnyNonNull(propertyNamePredicate, addressPredicate, sellerIdPredicate,
                    propertyTypePredicate, askingPricePredicate, isRentalPredicate, isClosedDealPredicate,
                    propertyIdPredicate);
        }

        public void setPropertyNameContainsKeywordsPredicate(PropertyNameContainsKeywordsPredicate
                                                                     propertyNamePredicate) {
            this.propertyNamePredicate = propertyNamePredicate;
        }

        public Optional<Predicate<Property>> getPropertyNameContainsKeywordsPredicate() {
            return Optional.ofNullable(propertyNamePredicate);
        }

        public void setSellerIdContainsKeywordsPredicate(SellerIdContainsKeywordsPredicate sellerIdPredicate) {
            this.sellerIdPredicate = sellerIdPredicate;
        }

        public Optional<Predicate<Property>> getSellerIdContainsKeywordsPredicate() {
            return Optional.ofNullable(sellerIdPredicate);
        }

        public void setPropertyAddressContainsKeywordsPredicate(PropertyAddressContainsKeywordsPredicate
                                                                        addressPredicate) {
            this.addressPredicate = addressPredicate;
        }

        public Optional<Predicate<Property>> getPropertyAddressContainsKeywordsPredicate() {
            return Optional.ofNullable(addressPredicate);
        }

        public void setPropertyTypeContainsKeywordsPredicate(PropertyTypeContainsKeywordsPredicate
                                                                     propertyTypePredicate) {
            this.propertyTypePredicate = propertyTypePredicate;
        }

        public Optional<Predicate<Property>> getPropertyTypeContainsKeywordsPredicate() {
            return Optional.ofNullable(propertyTypePredicate);
        }

        public void setAskingPricePredicate(AskingPricePredicate askingPricePredicate) {
            this.askingPricePredicate = askingPricePredicate;
        }

        public Optional<Predicate<Property>> getAskingPricePredicate() {
            return Optional.ofNullable(askingPricePredicate);
        }

        public void setIsRentalPredicate(PropertyIsRentalPredicate isRentalPredicate) {
            this.isRentalPredicate = isRentalPredicate;
        }

        public Optional<Predicate<Property>> getIsRentalPredicate() {
            return Optional.ofNullable(isRentalPredicate);
        }

        public void setPropertyIdPredicate(PropertyIdContainsKeywordsPredicate propertyIdPredicate) {
            this.propertyIdPredicate = propertyIdPredicate;
        }

        public Optional<Predicate<Property>> getPropertyIdPredicate() {
            return Optional.ofNullable(propertyIdPredicate);
        }

        public void setIsClosedDealPredicate(PropertyIsClosedDealPredicate isClosedDealPredicate) {
            this.isClosedDealPredicate = isClosedDealPredicate;
        }

        public Optional<Predicate<Property>> getIsClosedDealPredicate() {
            return Optional.ofNullable(isClosedDealPredicate);
        }

        public Predicate<Property> getComposedPredicate() {
            Stream<Optional<Predicate<Property>>> predicateStream = Stream.of(
                    getPropertyIdPredicate(),
                    getPropertyNameContainsKeywordsPredicate(),
                    getPropertyAddressContainsKeywordsPredicate(),
                    getPropertyTypeContainsKeywordsPredicate(),
                    getAskingPricePredicate(),
                    getSellerIdContainsKeywordsPredicate(),
                    getIsRentalPredicate(),
                    getIsClosedDealPredicate()
            );
            return predicateStream.filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(p -> true, (pred1, pred2) -> pred1.and(pred2));
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindPropertyCommand.FindPropertyDescriptor)) {
                return false;
            }

            // state check
            FindPropertyCommand.FindPropertyDescriptor e = (FindPropertyCommand.FindPropertyDescriptor) other;

            return getPropertyNameContainsKeywordsPredicate().equals(e.getPropertyNameContainsKeywordsPredicate())
                    && getPropertyAddressContainsKeywordsPredicate()
                    .equals(e.getPropertyAddressContainsKeywordsPredicate())
                    && getPropertyTypeContainsKeywordsPredicate().equals(e.getPropertyTypeContainsKeywordsPredicate())
                    && getAskingPricePredicate().equals(e.getAskingPricePredicate())
                    && getSellerIdContainsKeywordsPredicate().equals(e.getSellerIdContainsKeywordsPredicate())
                    && getIsRentalPredicate().equals(e.getIsRentalPredicate())
                    && getPropertyIdPredicate().equals(e.getPropertyIdPredicate())
                    && getIsClosedDealPredicate().equals(e.getIsClosedDealPredicate());
        }
    }
}
