package seedu.address.testutil.property;

import java.util.Arrays;

import seedu.address.logic.commands.property.FindPropertyCommand.FindPropertyDescriptor;
import seedu.address.model.price.PriceFilter;
import seedu.address.model.property.AskingPricePredicate;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.PropertyAddressContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIsClosedDealPredicate;
import seedu.address.model.property.PropertyIsRentalPredicate;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.model.property.PropertyTypeContainsKeywordsPredicate;
import seedu.address.model.property.SellerIdContainsKeywordsPredicate;

/**
 * A utility class to help with building FindPropertyDescriptor objects.
 */
public class FindPropertyDescriptorBuilder {

    private FindPropertyDescriptor descriptor;

    public FindPropertyDescriptorBuilder() {
        descriptor = new FindPropertyDescriptor();
    }

    /**
     * Sets the {@code PropertyNameContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindPropertyDescriptorBuilder withPropertyNamePredicate(String keywords) {
        descriptor.setPropertyNameContainsKeywordsPredicate(
                new PropertyNameContainsKeywordsPredicate(
                        Arrays.asList(keywords.split("\\s+")))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyAddressContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindPropertyDescriptorBuilder withAddressPredicate(String keywords) {
        descriptor.setPropertyAddressContainsKeywordsPredicate(
                new PropertyAddressContainsKeywordsPredicate(
                        Arrays.asList(keywords.trim().split("\\s+"))
                )
        );
        return this;
    }

    /**
     * Sets the {@code PropertyTypeContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindPropertyDescriptorBuilder withPropertyTypePredicate(String keywords) {
        descriptor.setPropertyTypeContainsKeywordsPredicate(
                new PropertyTypeContainsKeywordsPredicate(
                        Arrays.asList(keywords.trim().split("\\s+"))
                )
        );
        return this;
    }

    /**
     * Sets the {@code SellerIdContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindPropertyDescriptorBuilder withSellerIdPredicate(String keywords) {
        descriptor.setSellerIdContainsKeywordsPredicate(
                new SellerIdContainsKeywordsPredicate(
                        Arrays.asList(keywords.trim().split("\\s+"))
                )
        );
        return this;
    }

    /**
     * Sets the {@code AskingPricePredicate} of the {@code FindPropertyDescriptor} that we are building.
     */
    public FindPropertyDescriptorBuilder withAskingPricePredicate(String priceFilter) {
        descriptor.setAskingPricePredicate(
                new AskingPricePredicate(new PriceFilter(priceFilter))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyIsRentalPredicate} of the {@code FindPropertyDescriptor} that we are building.
     */
    public FindPropertyDescriptorBuilder withIsRentalPredicate(String isRental) {
        descriptor.setIsRentalPredicate(
                new PropertyIsRentalPredicate(new IsRental(isRental))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyIsClosedDeal} of the {@code FindPropertyDescriptor} that we are building.
     */
    public FindPropertyDescriptorBuilder withIsClosedDealPredicate(String isClosedDeal) {
        descriptor.setIsClosedDealPredicate(
                new PropertyIsClosedDealPredicate(new IsClosedDeal(isClosedDeal))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyIdContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindPropertyDescriptorBuilder withPropertyIdPredicate(String keywords) {
        descriptor.setPropertyIdPredicate(
                new PropertyIdContainsKeywordsPredicate(
                        Arrays.asList(keywords.trim().split("\\s+"))
                )
        );
        return this;
    }

    public FindPropertyDescriptor build() {
        return descriptor;
    }
}
