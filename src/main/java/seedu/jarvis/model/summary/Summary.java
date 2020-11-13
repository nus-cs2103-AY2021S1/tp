package seedu.jarvis.model.summary;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Summary {

    // The following strings are the keys for the summary values.
    public static final String UNGRADED_MISSIONS = "Ungraded Missions";
    public static final String UNGRADED_QUESTS = "Ungraded Quests";
    public static final String UPCOMING_CONSULTATIONS = "Upcoming Consultations";
    public static final String UPCOMING_MASTERY_CHECKS = "Upcoming Mastery Checks";
    public static final String TASKS = "Outstanding Tasks";

    // Strings used in the displayed summary.
    public static final String MESSAGE_REMAINING = "Remaining";
    public static final String MESSAGE_MISSIONS = "Missions";
    public static final String MESSAGE_QUESTS = "Quests";
    public static final String MESSAGE_CONSULTATIONS = "Consultations";
    public static final String MESSAGE_MASTERY_CHECKS = "Mastery Checks";
    public static final String MESSAGE_TASKS = "Tasks";

    // an always updated hash map of summary values.
    private HashMap<String, Integer> summaryHashMap = new HashMap<>();

    private StringProperty summaryDetails = new SimpleStringProperty();

    /**
     * Creates an instance of Summary object, with all summary values set to 0.
     */
    public Summary() {
        summaryHashMap.put(UNGRADED_MISSIONS, 0);
        summaryHashMap.put(UNGRADED_QUESTS, 0);
        summaryHashMap.put(UPCOMING_CONSULTATIONS, 0);
        summaryHashMap.put(UPCOMING_MASTERY_CHECKS, 0);
        summaryHashMap.put(TASKS, 0);
        formatSummaryString();
    }

    /**
     * Sets the number of ungraded missions, when the hashMap has an existing value.
     * @param numUm number of ungraded missions
     */
    public void setNumUngradedMissions(int numUm) {
        assert numUm >= 0 : "Trying to set a negative number of ungraded missions";
        summaryHashMap.replace(UNGRADED_MISSIONS, numUm);
    }

    public int getNumUngradedMissions() {
        Integer numUngradedMissions = summaryHashMap.get(UNGRADED_MISSIONS);
        assert numUngradedMissions >= 0 : "Number of ungraded missions is negative";
        return numUngradedMissions;
    }

    /**
     * Sets the number of ungraded quests, when the hashMap has an existing value.
     * @param numUq number of ungraded quests.
     */
    public void setNumUngradedQuests(int numUq) {
        assert numUq >= 0 : "Trying to set a negative number of ungraded quests";
        summaryHashMap.replace(UNGRADED_QUESTS, numUq);
    }

    public int getNumUngradedQuests() {
        Integer numUngradedQuests = summaryHashMap.get(UNGRADED_QUESTS);
        assert numUngradedQuests >= 0 : "Number of ungraded quests is negative";
        return numUngradedQuests;
    }

    /**
     * Sets the number of upcoming Consultations, when the hashMap has an existing value.
     * @param numConsult number of upcoming consultations
     */
    public void setNumUpcomingConsultations(int numConsult) {
        assert numConsult >= 0 : "Trying to set a negative number of consultations";
        summaryHashMap.replace(UPCOMING_CONSULTATIONS, numConsult);
    }

    public int getNumUpcomingConsultations() {
        Integer numConsultations = summaryHashMap.get(UPCOMING_CONSULTATIONS);
        assert numConsultations >= 0 : "Number of consultations is negative";
        return numConsultations;
    }

    /**
     * Sets the number of Mastery Checks, when the hashMap has an existing value.
     * @param numMc number of upcoming mastery checks
     */
    public void setNumUpcomingMasteryChecks(int numMc) {
        assert numMc >= 0 : "Trying to set a negative number of mastery checks";
        summaryHashMap.replace(UPCOMING_MASTERY_CHECKS, numMc);
    }

    public int getNumUpcomingMasteryChecks() {
        Integer numMasteryChecks = summaryHashMap.get(UPCOMING_MASTERY_CHECKS);
        assert numMasteryChecks >= 0 : "Number of mastery checks is negative";
        return numMasteryChecks;
    }

    /**
     * Sets the number of Tasks, when the hashMap has an existing value.
     * @param numT number of remaining tasks
     */
    public void setNumTasks(int numT) {
        assert numT >= 0 : "Trying to set a negative number of tasks";
        summaryHashMap.replace(TASKS, numT);
    }

    public int getNumTasks() {
        Integer numTasks = summaryHashMap.get(TASKS);
        assert numTasks >= 0 : "Number of tasks is negative";
        return numTasks;
    }

    /**
     * Generates a string of ungraded missions and quests. If there are none, an empty string is returned.
     * @return A string of ungraded missions and quests. If there are none, an empty string is returned.
     */
    public String generateMissionsQuestsString() {

        StringBuilder missionsQuestSb = new StringBuilder();
        int numUngradedMissions = getNumUngradedMissions();
        int numUngradedQuests = getNumUngradedQuests();

        // Generate the string of mission and quests
        if (numUngradedMissions > 0 && numUngradedQuests > 0) {
            missionsQuestSb.append(MESSAGE_MISSIONS).append(": ")
                    .append(numUngradedMissions).append(", ");
            missionsQuestSb.append(MESSAGE_QUESTS).append(": ")
                    .append(numUngradedQuests);
        } else if (numUngradedMissions > 0) {
            missionsQuestSb.append(MESSAGE_MISSIONS).append(": ")
                    .append(numUngradedMissions);
        } else if (numUngradedQuests > 0) {
            missionsQuestSb.append(MESSAGE_QUESTS).append(": ")
                    .append(numUngradedQuests);
        }

        return missionsQuestSb.toString();
    }

    /**
     * Generates a string of upcoming consultations and mastery checks.
     * If there are none, an empty string is returned.
     * @return A string of upcoming consultations and mastery checks.
     * If there are none, an empty string is returned.
     */
    public String generateConsultationsMcsString() {

        StringBuilder consultationsMcSb = new StringBuilder();
        int numUpcomingConsultations = getNumUpcomingConsultations();
        int numUpcomingMasteryChecks = getNumUpcomingMasteryChecks();

        // Generate the string of consultations and mastery checks
        if (numUpcomingConsultations > 0 && numUpcomingMasteryChecks > 0) {
            consultationsMcSb.append(MESSAGE_CONSULTATIONS).append(": ")
                    .append(numUpcomingConsultations).append(", ");
            consultationsMcSb.append(MESSAGE_MASTERY_CHECKS)
                    .append(": ").append(numUpcomingMasteryChecks);
        } else if (numUpcomingConsultations > 0) {
            consultationsMcSb.append(MESSAGE_CONSULTATIONS)
                    .append(": ").append(numUpcomingConsultations);
        } else if (numUpcomingMasteryChecks > 0) {
            consultationsMcSb.append(MESSAGE_MASTERY_CHECKS)
                    .append(": ").append(numUpcomingMasteryChecks);
        }

        return consultationsMcSb.toString();
    }

    /**
     * Generates a string of the remaining tasks. If there are none, an empty string is returned.
     * @return A string of the remaining tasks. If there are none, an empty string is returned.
     */
    public String generateTaskString() {

        StringBuilder taskSb = new StringBuilder();
        int numTasks = getNumTasks();

        // Generate the string of tasks
        if (numTasks > 0) {
            taskSb.append(MESSAGE_TASKS).append(": ").append(numTasks);
        }

        return taskSb.toString();
    }

    /**
     * Updates the summary string, allowing the updates done by the setter methods above to be reflected.
     */
    private void formatSummaryString() {
        StringBuilder summary = new StringBuilder();

        String missionsQuestsString = generateMissionsQuestsString();
        String consultationsMcsString = generateConsultationsMcsString();
        String tasksString = generateTaskString();

        ArrayList<String> summaryComponents = new ArrayList<>();

        if (missionsQuestsString.length() > 0) {
            summaryComponents.add(missionsQuestsString);
        }

        if (consultationsMcsString.length() > 0) {
            summaryComponents.add(consultationsMcsString);
        }

        if (tasksString.length() > 0) {
            summaryComponents.add(tasksString);
        }

        // Generate the final summary output
        int numComponents = summaryComponents.size();
        summary.append(MESSAGE_REMAINING).append(" - ");

        if (missionsQuestsString.length() == 0
                && consultationsMcsString.length() == 0 && tasksString.length() == 0) {
            summary.append("Nothing!");
        }

        for (int i = 0; i < numComponents; i++) {
            String component = summaryComponents.get(i);
            if (i == numComponents - 1) {
                summary.append(component);
            } else {
                summary.append(component).append(", ");
            }
        }

        summaryDetails.setValue(summary.toString());
    }

    public boolean isEmpty() {
        return summaryHashMap == null || summaryDetails.getValue().isEmpty();
    }

    public StringProperty getSummaryDetails() {
        formatSummaryString();
        assert summaryDetails.isNotNull().get() : "summaryDetails is null";
        return summaryDetails;
    }

    @Override
    public String toString() {
        formatSummaryString();
        assert summaryDetails.isNotNull().get() : "summaryDetails is null";
        return summaryDetails.getValue();
    }

}
