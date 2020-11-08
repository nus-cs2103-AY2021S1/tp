package seedu.pivot.testutil;

import seedu.pivot.model.Pivot;
import seedu.pivot.model.investigationcase.Case;

/**
 * A utility class to help with building PIVOT objects.
 * Example usage: <br>
 *     {@code Pivot pivot = new PivotBuilder().withCase("John", "Doe").build();}
 */
public class PivotBuilder {

    private Pivot pivot;

    public PivotBuilder() {
        pivot = new Pivot();
    }

    public PivotBuilder(Pivot pivot) {
        this.pivot = pivot;
    }

    /**
     * Adds a new {@code Case} to the {@code PIVOT} that we are building.
     */
    public PivotBuilder withCase(Case investigationCase) {
        pivot.addCase(investigationCase);
        return this;
    }

    public Pivot build() {
        return pivot;
    }
}
