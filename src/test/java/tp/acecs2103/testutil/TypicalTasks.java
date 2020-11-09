package tp.acecs2103.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.TP;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.Topic;


/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks<taskList> {

    public static final Admin ADMIN_ONE = new TaskBuilder().withIndex("0101").withWeekNumber("1")
            .withDescription("Admin One").withOfficialDeadline("2020-10-10").withCustomizedDeadline(null)
            .withRemark("No remark").withIsCustomized(false).withIsDone(false).buildAdmin();
    public static final Admin ADMIN_TWO = new TaskBuilder().withIndex("0102").withWeekNumber("1")
            .withDescription("Admin Two").withOfficialDeadline(null).withCustomizedDeadline("2020-10-20")
            .withRemark("No remark").withIsCustomized(true).withIsDone(false).buildAdmin();
    public static final Topic TOPIC_ONE = new TaskBuilder().withIndex("0201").withWeekNumber("2")
            .withDescription("Topic One").withOfficialDeadline("2020-10-10").withCustomizedDeadline(null)
            .withRemark("No remark").withIsCustomized(false).withIsDone(false).buildTopic();
    public static final Topic TOPIC_TWO = new TaskBuilder().withIndex("0202").withWeekNumber("2")
            .withDescription("Topic Two").withOfficialDeadline(null).withCustomizedDeadline("2020-10-20")
            .withRemark("No remark").withIsCustomized(true).withIsDone(false).buildTopic();
    public static final IP IP_ONE = new TaskBuilder().withIndex("0301").withWeekNumber("3")
            .withDescription("Ip One").withOfficialDeadline("2020-10-10").withCustomizedDeadline(null)
            .withRemark("No remark").withIsCustomized(false).withIsDone(true).buildIp();
    public static final IP IP_TWO = new TaskBuilder().withIndex("0302").withWeekNumber("3")
            .withDescription("Ip Two").withOfficialDeadline(null).withCustomizedDeadline("2020-10-20")
            .withRemark("No remark").withIsCustomized(true).withIsDone(false).buildIp();
    public static final TP TP_ONE = new TaskBuilder().withIndex("0401").withWeekNumber("4")
            .withDescription("Tp One").withOfficialDeadline("2021-10-10").withCustomizedDeadline(null)
            .withRemark("No remark").withIsCustomized(false).withIsDone(false).buildTp();
    public static final TP TP_TWO = new TaskBuilder().withIndex("0402").withWeekNumber("4")
            .withDescription("Tp Two").withOfficialDeadline(null).withCustomizedDeadline("2020-10-20")
            .withRemark("No remark").withIsCustomized(true).withIsDone(false).buildTp();
    public static final Admin ADMIN_EXTRA_ONE = new TaskBuilder().withIndex("0103").withWeekNumber("1")
            .withDescription("Admin Extra One").withOfficialDeadline("2021-10-10").withCustomizedDeadline(null)
            .withRemark("No remark").withIsCustomized(false).withIsDone(true).buildAdmin();
    public static final Admin ADMIN_EXTRA_TWO = new TaskBuilder().withIndex("0104").withWeekNumber("1")
            .withDescription("Admin Extra Two").withOfficialDeadline("2021-10-10").withCustomizedDeadline(null)
            .withRemark("No remark").withIsCustomized(false).withIsDone(false).buildAdmin();


    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList taskList = new TaskList();
        for (Task task: getTypicalTasks()) {
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Returns an {@code TaskList} with all the extra tasks.
     */
    public static TaskList getExtraTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(ADMIN_EXTRA_ONE);
        taskList.add(ADMIN_EXTRA_TWO);
        return taskList;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ADMIN_ONE, ADMIN_TWO, TOPIC_ONE, TOPIC_TWO,
                IP_ONE, IP_TWO, TP_ONE, TP_TWO));
    }

}
