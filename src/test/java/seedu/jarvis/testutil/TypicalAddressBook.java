package seedu.jarvis.testutil;

import java.util.List;

import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.task.Task;

public class TypicalAddressBook {

    public static AddressBook getTypicalAddressBookWithAllValues() {
        List<Mission> typicalMissions = TypicalMissions.getTypicalMissions();
        List<Quest> typicalQuests = TypicalQuests.getTypicalQuests();
        List<Consultation> typicalConsultations = TypicalConsultations.getTypicalConsultations();
        List<MasteryCheck> typicalMasteryChecks = TypicalMasteryChecks.getTypicalMasteryChecks();
        List<Task> typicalTasks = TypicalTasks.getTypicalTasks();

        AddressBook ab = new AddressBook();
        for (Mission mission : typicalMissions) {
            ab.addMission(mission);
        }

        for (Quest quest : typicalQuests) {
            ab.addQuest(quest);
        }

        for (Consultation consultation : typicalConsultations) {
            ab.addConsultation(consultation);
        }

        for (MasteryCheck masteryCheck : typicalMasteryChecks) {
            ab.addMasteryCheck(masteryCheck);
        }

        for (Task task : typicalTasks) {
            ab.addTask(task);
        }
        return ab;
    }
}
