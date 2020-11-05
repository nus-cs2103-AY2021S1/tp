package seedu.cc.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Revenue;
import seedu.cc.model.tag.Tag;
import seedu.cc.model.util.SampleCommonCentsUtilData;

public class RevenueBuilder {
    private static final String DEFAULT_DESCRIPTION = "buying paint supplies";
    private static final String DEFAULT_AMOUNT = "131.73";

    private Description description;
    private Amount amount;
    private Set<Tag> tags;

    /**
     * Creates a {@code RevenueBuilder} with the default details.
     */
    public RevenueBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(DEFAULT_AMOUNT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the RevenueBuilder with the data of {@code revenueToCopy}.
     */
    public RevenueBuilder(Revenue revenueToCopy) {
        description = revenueToCopy.getDescription();
        amount = revenueToCopy.getAmount();
        tags = revenueToCopy.getTags();
    }

    /**
     * Sets the {@code Description} of the {@code Revenue} that we are building.
     */
    public RevenueBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Revenue} that we are building.
     */
    public RevenueBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Revenue} that we are building.
     */
    public RevenueBuilder withTags(String ... tags) {
        this.tags = SampleCommonCentsUtilData.getTagSet(tags);
        return this;
    }

    public Revenue build() {
        return new Revenue(description, amount, tags);
    }
}
