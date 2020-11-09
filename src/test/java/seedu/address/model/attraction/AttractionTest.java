package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_EIFFEL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.SUNTEC;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AttractionBuilder;

public class AttractionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Attraction attraction = new AttractionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> attraction.getTags().remove(0));
    }

    @Test
    public void isSameAttraction() {
        // same object -> returns true
        assertTrue(MBS.isSameAttraction(MBS));

        // null -> returns false
        assertFalse(MBS.isSameAttraction(null));

        // different name and location -> returns false
        Attraction editedMbs = new AttractionBuilder(MBS).withName(VALID_NAME_EIFFEL)
                .withLocation(VALID_LOCATION_EIFFEL)
                .build();
        assertFalse(MBS.isSameAttraction(editedMbs));

        // different name -> returns false
        editedMbs = new AttractionBuilder(MBS).withName(VALID_NAME_EIFFEL).build();
        assertFalse(MBS.isSameAttraction(editedMbs));

        // different location -> returns false
        editedMbs = new AttractionBuilder(MBS).withLocation(VALID_LOCATION_EIFFEL).build();
        assertFalse(MBS.isSameAttraction(editedMbs));

        // same name, same location, different attributes -> returns true
        editedMbs = new AttractionBuilder(MBS).withPriceRange(VALID_PRICE_RANGE_EIFFEL)
                .withAddress(VALID_ADDRESS_EIFFEL)
                .withDescription(VALID_DESCRIPTION_EIFFEL)
                .withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(MBS.isSameAttraction(editedMbs));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Attraction mbsCopy = new AttractionBuilder(MBS).build();
        assertTrue(MBS.equals(mbsCopy));

        // same object -> returns true
        assertTrue(MBS.equals(MBS));

        // null -> returns false
        assertFalse(MBS.equals(null));

        // different type -> returns false
        assertFalse(MBS.equals(5));

        // different attraction -> returns false
        assertFalse(MBS.equals(SUNTEC));

        // different name -> returns false
        Attraction editedSuntec = new AttractionBuilder(SUNTEC).withName(VALID_NAME_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different phone -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withPhone(VALID_PHONE_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different email -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withEmail(VALID_EMAIL_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different address -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withAddress(VALID_ADDRESS_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different description -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withDescription(VALID_DESCRIPTION_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different location -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withLocation(VALID_LOCATION_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different opening hours -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withOpeningHours(VALID_OPENING_HOURS_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different price range -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withPriceRange(VALID_PRICE_RANGE_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different rating -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withRating(VALID_RATING_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different visited -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withVisited(VALID_VISITED_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different tags -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withTags(VALID_TAG_SIGHTSEEING).build();
        assertFalse(SUNTEC.equals(editedSuntec));
    }

    @Test
    public void getNumOfFilledFieldsTest() {
        //O fields test
        Attraction zeroFields = new Attraction(new Name(VALID_NAME_EIFFEL), new Phone(""), new Email(""),
                new Address(""), new Description(""), new Location(VALID_LOCATION_EIFFEL),
                new OpeningHours(""), new PriceRange(""), new Rating(""), new Visited(""), new HashSet<Tag>());
        assertEquals(0, zeroFields.getNumOfFilledFields());

        //1 fields test
        Attraction oneFields = new Attraction(new Name(VALID_NAME_EIFFEL), new Phone(VALID_PHONE_EIFFEL), new Email(""),
                new Address(""), new Description(""), new Location(VALID_LOCATION_EIFFEL),
                new OpeningHours(""), new PriceRange(""), new Rating(""), new Visited(""), new HashSet<Tag>());
        assertEquals(1, oneFields.getNumOfFilledFields());

        //2 fields test
        Attraction twoFields = new Attraction(new Name(VALID_NAME_EIFFEL), new Phone(VALID_PHONE_EIFFEL), new Email(""),
                new Address(VALID_ADDRESS_EIFFEL), new Description(""), new Location(VALID_LOCATION_EIFFEL),
                new OpeningHours(""), new PriceRange(""), new Rating(""), new Visited(""), new HashSet<Tag>());
        assertTrue(twoFields.getNumOfFilledFields() == 2);

        //3 fields test
        Attraction threeFields = new Attraction(new Name(VALID_NAME_EIFFEL), new Phone(VALID_PHONE_EIFFEL),
                new Email(VALID_EMAIL_EIFFEL), new Address(VALID_ADDRESS_EIFFEL), new Description(""),
                new Location(VALID_LOCATION_EIFFEL),
                new OpeningHours(""), new PriceRange(""), new Rating(""), new Visited(""), new HashSet<Tag>());
        assertTrue(threeFields.getNumOfFilledFields() == 3);

        //4 fields test
        Attraction fourFields = new Attraction(new Name(VALID_NAME_EIFFEL), new Phone(VALID_PHONE_EIFFEL),
                new Email(VALID_EMAIL_EIFFEL), new Address(VALID_ADDRESS_EIFFEL),
                new Description(VALID_DESCRIPTION_EIFFEL), new Location(VALID_LOCATION_EIFFEL),
                new OpeningHours(""), new PriceRange(""), new Rating(""), new Visited(""), new HashSet<Tag>());
        assertTrue(fourFields.getNumOfFilledFields() == 4);

        //5 fields test
        Attraction fiveFields = new Attraction(new Name(VALID_NAME_EIFFEL), new Phone(VALID_PHONE_EIFFEL),
                new Email(VALID_EMAIL_EIFFEL), new Address(VALID_ADDRESS_EIFFEL),
                new Description(VALID_DESCRIPTION_EIFFEL), new Location(VALID_LOCATION_EIFFEL),
                new OpeningHours(VALID_OPENING_HOURS_EIFFEL), new PriceRange(""), new Rating(""), new Visited(""),
                new HashSet<Tag>());
        assertTrue(fiveFields.getNumOfFilledFields() == 5);
    }
}
