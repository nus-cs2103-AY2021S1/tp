package tp.acecs2103.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tp.acecs2103.testutil.TypicalTasks;

public class UiTaskListTest {
    private TaskList taskList = TypicalTasks.getTypicalTaskList();
    private UiTaskList uiTaskList = new UiTaskList(taskList.getTaskList());

    @Test
    public void getCombinedTaskList_withValidTaskList_success() {
        Assertions.assertTrue(taskList.getTaskList().equals(uiTaskList.getCombinedTaskList()));
    }

    @Test
    public void size_success() {
        Assertions.assertTrue(uiTaskList.size() == taskList.size());
    }

    @Test
    public void getAdminWeekRange_success() {
        ArrayList<Integer> weekRange = new ArrayList<>();
        weekRange.add(1);
        weekRange.add(1);
        Assertions.assertTrue(uiTaskList.getAdminWeekRange().equals(weekRange));
    }

    @Test
    public void getTopicWeekRange_success() {
        ArrayList<Integer> weekRange = new ArrayList<>();
        weekRange.add(2);
        weekRange.add(2);
        Assertions.assertTrue(uiTaskList.getTopicWeekRange().equals(weekRange));
    }

    @Test
    public void getIpWeekRange_success() {
        ArrayList<Integer> weekRange = new ArrayList<>();
        weekRange.add(3);
        weekRange.add(3);
        Assertions.assertTrue(uiTaskList.getIpWeekRange().equals(weekRange));
    }

    @Test
    public void getTpWeekRange_success() {
        ArrayList<Integer> weekRange = new ArrayList<>();
        weekRange.add(4);
        weekRange.add(4);
        Assertions.assertTrue(uiTaskList.getTpWeekRange().equals(weekRange));
    }
}
