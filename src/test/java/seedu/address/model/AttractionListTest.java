package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;
import seedu.address.testutil.AttractionBuilder;

public class AttractionListTest {

    private final AttractionList attractionList = new AttractionList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), attractionList.getAttractionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attractionList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTrackPad_replacesData() {
        AttractionList newData = getTypicalAttractionList();
        attractionList.resetData(newData);
        assertEquals(newData, attractionList);
    }

    @Test
    public void resetData_withDuplicateAttractions_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Attraction editedMbs = new AttractionBuilder(MBS).withAddress(VALID_ADDRESS_MBS).withTags(VALID_TAG_SIGHTSEEING)
                .build();
        List<Attraction> newAttractions = Arrays.asList(MBS, editedMbs);
        AttractionListStub newData = new AttractionListStub(newAttractions);

        assertThrows(DuplicateAttractionException.class, () -> attractionList.resetData(newData));
    }

    @Test
    public void hasAttraction_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attractionList.hasAttraction(null));
    }

    @Test
    public void hasAttraction_attractionNotInTrackPad_returnsFalse() {
        assertFalse(attractionList.hasAttraction(MBS));
    }

    @Test
    public void hasAttraction_attractionInTrackPad_returnsTrue() {
        attractionList.addAttraction(MBS);
        assertTrue(attractionList.hasAttraction(MBS));
    }

    @Test
    public void hasAttraction_attractionWithSameIdentityFieldsInTrackPad_returnsTrue() {
        attractionList.addAttraction(MBS);
        Attraction editedMbs = new AttractionBuilder(MBS).withAddress(VALID_ADDRESS_MBS).withTags(VALID_TAG_SIGHTSEEING)
                .build();
        assertTrue(attractionList.hasAttraction(editedMbs));
    }

    @Test
    public void getAttractionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> attractionList.getAttractionList().remove(0));
    }

    /**
     * A stub ReadOnlyAttractionList whose persons list can violate interface constraints.
     */
    private static class AttractionListStub implements ReadOnlyAttractionList {
        private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

        AttractionListStub(Collection<Attraction> persons) {
            this.attractions.setAll(persons);
        }

        @Override
        public ObservableList<Attraction> getAttractionList() {
            return attractions;
        }
    }

}
