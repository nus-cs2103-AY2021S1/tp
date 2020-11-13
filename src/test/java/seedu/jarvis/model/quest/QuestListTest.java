package seedu.jarvis.model.quest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalQuests.COLORFUL_CARPETS;
import static seedu.jarvis.testutil.TypicalQuests.RUNIC_CARPETS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jarvis.testutil.QuestBuilder;

class QuestListTest {


    private final QuestList questList = new QuestList();
    private final List<Quest> questListTest = new ArrayList<>();

    @Test
    void add_nullQuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> questList.add(null));
    }

    @Test
    void contains_nullQuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> questList.contains(null));
    }

    @Test
    public void contains_questNotInList_returnsFalse() {
        assertFalse(questList.contains(COLORFUL_CARPETS));
    }

    @Test
    public void contains_questInList_returnsTrue() {
        questList.add(COLORFUL_CARPETS);
        assertTrue(questList.contains(COLORFUL_CARPETS));
    }

    @Test
    void setNullQuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> questList.setQuests(null));
        questListTest.add(RUNIC_CARPETS);
        questList.setQuests(questListTest);
        assertFalse(questList.contains(COLORFUL_CARPETS));
    }

    @Test
    void changeQuestListTo_asObservableList() {
        assertFalse(questList.getClass().equals(questList.asObservableList().getClass()));
    }

    @Test
    void isQuestInList_questInList_returnsTrue() {
        questList.add(RUNIC_CARPETS);
        assertTrue(questList.isQuestInList("Runic Carpets"));
    }

    @Test
    void updateQuest_questInList_returnsTrue() {
        questList.add(COLORFUL_CARPETS);
        questList.updateQuest("Colorful Carpets");
        Quest editedColorfulCarpets = new QuestBuilder(COLORFUL_CARPETS).withIsGraded(false).build();
        assertTrue(questList.contains(editedColorfulCarpets));
    }

    @Test
    void updateQuest_questNotInList_returnsFalse() {
        questList.add(COLORFUL_CARPETS);
        assertFalse(questList.updateQuest("Runic Carpets"));
    }
}
