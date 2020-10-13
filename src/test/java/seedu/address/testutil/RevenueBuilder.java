package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.account.entry.Amount;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleCommonCentsUtilData;

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
    public RevenueBuilder withDesciption(Description description) {
        this.description = description;
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
