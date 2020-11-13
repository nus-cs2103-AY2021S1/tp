package seedu.jarvis.model.summary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.exceptions.IllegalValueException;

public class SummaryTest {

    private static Summary defaultSummaryObject;
    private static Summary editableSummaryObject;
    private static Summary summaryWithSpecificValues;

    @BeforeAll
    public static void setUp() throws IllegalValueException {
        defaultSummaryObject = new Summary();
        editableSummaryObject = new Summary();

        summaryWithSpecificValues = new Summary();
        summaryWithSpecificValues.setNumUngradedMissions(1);
        summaryWithSpecificValues.setNumUngradedQuests(2);
        summaryWithSpecificValues.setNumUpcomingConsultations(3);
        summaryWithSpecificValues.setNumUpcomingMasteryChecks(4);
        summaryWithSpecificValues.setNumTasks(5);
    }

    @Test
    public void getNumUngradedMissions_defaultValueZero() {
        assertTrue(defaultSummaryObject.getNumUngradedMissions() == 0);
    }

    @Test
    public void getNumUngradedQuests_defaultValueZero() {
        assertTrue(defaultSummaryObject.getNumUngradedQuests() == 0);
    }

    @Test
    public void getNumUpcomingConsultations_defaultValueZero() {
        assertTrue(defaultSummaryObject.getNumUpcomingConsultations() == 0);
    }

    @Test
    public void getNumUpcomingMasteryChecks_defaultValueZero() {
        assertTrue(defaultSummaryObject.getNumUpcomingMasteryChecks() == 0);
    }

    @Test
    public void getNumTasks_defaultValueZero() {
        assertTrue(defaultSummaryObject.getNumTasks() == 0);
    }

    @Test
    public void setNumUngradedMissions_twenty_success() {
        editableSummaryObject.setNumUngradedMissions(20);
        assertTrue(editableSummaryObject.getNumUngradedMissions() == 20);
    }

    @Test
    public void setNumUngradedQuests_fourty_success() {
        editableSummaryObject.setNumUngradedQuests(40);
        assertTrue(editableSummaryObject.getNumUngradedQuests() == 40);
    }

    @Test
    public void setNumUpcomingConsultations_twelve_success() {
        editableSummaryObject.setNumUpcomingConsultations(12);
        assertTrue(editableSummaryObject.getNumUpcomingConsultations() == 12);
    }

    @Test
    public void setNumUpcomingMasteryChecks_fifteen_success() {
        editableSummaryObject.setNumUpcomingMasteryChecks(15);
        assertTrue(editableSummaryObject.getNumUpcomingMasteryChecks() == 15);
    }

    @Test
    public void setNumTasks_hundredAndFour_success() {
        editableSummaryObject.setNumTasks(104);
        assertTrue(editableSummaryObject.getNumTasks() == 104);
    }

    // Combined test for formatSummaryString and getSummaryDetails()
    @Test
    public void getSummaryString_containsAccurateSummaryValues() {
        String generatedString = summaryWithSpecificValues.getSummaryDetails().get();
        assertTrue(generatedString.contains("Missions: 1"));
        assertTrue(generatedString.contains("Quests: 2"));
        assertTrue(generatedString.contains("Consultations: 3"));
        assertTrue(generatedString.contains("Mastery Checks: 4"));
        assertTrue(generatedString.contains("Tasks: 5"));
    }

    @Test
    public void generateMissionsQuestsString_containsAccurateSummaryValues() {
        String generatedString = summaryWithSpecificValues.generateMissionsQuestsString();
        assertTrue(generatedString.contains("Missions: 1"));
        assertTrue(generatedString.contains("Quests: 2"));
    }

    @Test
    public void generateMissionsQuestsString_doesNotEndWithComma() {
        String generatedString = summaryWithSpecificValues.generateMissionsQuestsString();
        assertFalse(generatedString.endsWith(", "));
    }

    @Test
    public void generateConsultationsMcString_doesNotEndWithComma() {
        String generatedString = summaryWithSpecificValues.generateConsultationsMcsString();
        assertFalse(generatedString.endsWith(", "));
    }

    @Test
    public void generateTasksString_doesNotEndWithComma() {
        String generatedString = summaryWithSpecificValues.generateTaskString();
        assertFalse(generatedString.endsWith(", "));
    }

    // Integration tests
    @Test
    public void constructor_createsHashMapWithDefaultValues() {
        assertTrue(!defaultSummaryObject.isEmpty());
        assertTrue(defaultSummaryObject.getNumUngradedMissions() == 0);
        assertTrue(defaultSummaryObject.getNumUngradedQuests() == 0);
        assertTrue(defaultSummaryObject.getNumUpcomingConsultations() == 0);
        assertTrue(defaultSummaryObject.getNumUpcomingMasteryChecks() == 0);
        assertTrue(defaultSummaryObject.getNumTasks() == 0);
    }

    @Test
    public void constructor_formatsSummaryString() {
        assertTrue(defaultSummaryObject.getSummaryDetails().getValue().length() != 0);
    }

}
