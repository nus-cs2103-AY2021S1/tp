package seedu.address.testutil;

import seedu.address.model.AttractionList;
import seedu.address.model.attraction.Attraction;

/**
 * A utility class to help with building AttractionList objects.
 * Example usage: <br>
 *     {@code AttractionList al = new AttractionListBuilder().withAttraction("USS", "MBS").build();}
 */
public class AttractionListBuilder {

    private AttractionList attractionList;

    public AttractionListBuilder() {
        attractionList = new AttractionList();
    }

    public AttractionListBuilder(AttractionList attractionList) {
        this.attractionList = attractionList;
    }

    /**
     * Adds a new {@code Attraction} to the {@code AttractionList} that we are building.
     */
    public AttractionListBuilder withAttraction(Attraction attraction) {
        attractionList.addAttraction(attraction);
        return this;
    }

    public AttractionList build() {
        return attractionList;
    }
}
