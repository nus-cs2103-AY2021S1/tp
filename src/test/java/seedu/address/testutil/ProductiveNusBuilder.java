package seedu.address.testutil;

import seedu.address.model.ProductiveNus;
import seedu.address.model.assignment.Assignment;

/**
 * A utility class to help with building ProductiveNus objects.
 * Example usage: <br>
 *     {@code ProductiveNus pn = new ProductiveNusBuilder().withAssignment("John", "Doe").build();}
 */
public class ProductiveNusBuilder {

    private ProductiveNus productiveNus;

    public ProductiveNusBuilder() {
        productiveNus = new ProductiveNus();
    }

    public ProductiveNusBuilder(ProductiveNus productiveNus) {
        this.productiveNus = productiveNus;
    }

    /**
     * Adds a new {@code Assignment} to the {@code ProductiveNus} that we are building.
     */
    public ProductiveNusBuilder withAssignment(Assignment assignment) {
        productiveNus.addAssignment(assignment);
        return this;
    }

    public ProductiveNus build() {
        return productiveNus;
    }
}
