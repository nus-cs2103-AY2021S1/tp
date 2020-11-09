package tp.acecs2103.model.task;


import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tp.acecs2103.model.exceptions.InvalidTaskOperationException;



public class TaskTest {
    private Task defaultTaskOne = new Task(new Index("0101"), new WeekNumber("1"), new Description("Test Task One"),
            new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
            null, new Remark("no remark"), false, false);
    private Task defaultTaskTwo = new Task(new Index("0101"), new WeekNumber("1"), new Description("Test Task Two"),
            new OfficialDeadline("2021-01-01", LocalDate.of(2021, 1, 1)),
            null, new Remark("no remark"), false, false);
    private Task defaultTaskThree = new Task(new Index("0102"), new WeekNumber("1"), new Description("Test Task Two"),
            new OfficialDeadline("2021-01-01", LocalDate.of(2021, 1, 1)),
            null, new Remark("no remark"), false, false);
    private Task customizedTaskOne = new Task(new Index("0302"), new WeekNumber("3"),
            new Description("Test Task Three"), null,
            new CustomizedDeadline("2020-09-20", LocalDate.of(2020, 9, 20)),
            new Remark("no remark"), true, false);
    private Task ip = new IP(new Index("0101"), new WeekNumber("1"), new Description("IP task"),
            new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
            null, new Remark("no remark"), false, false);
    private Task tp = new TP(new Index("0101"), new WeekNumber("1"), new Description("TP task"),
            new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
            null, new Remark("no remark"), false, false);
    private Task topic = new Topic(new Index("0101"), new WeekNumber("1"), new Description("Topic task"),
            new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
            null, new Remark("no remark"), false, false);
    private Task admin = new Admin(new Index("0101"), new WeekNumber("1"), new Description("Admin task"),
            new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
            new CustomizedDeadline("2020-09-01", LocalDate.of(2020, 9, 1)), new Remark("no remark"), false, false);

    private Task taskWithoutRemark = new Task(new Index("0302"), new WeekNumber("3"),
            new Description("no description"), null,
            new CustomizedDeadline("2020-09-20", LocalDate.of(2020, 9, 20)),
            null, true, false);

    @Test
    public void hasIndex_indexToCheck_returnTrue() {
        // same indexes -> true
        Assertions.assertTrue(defaultTaskOne.hasIndex(new Index("0101")));
    }

    @Test
    public void hasIndex_indexToCheck_returnFalse() {
        // difference indexes -> false
        Assertions.assertFalse(defaultTaskOne.hasIndex(new Index("0102")));
    }

    @Test
    public void hasDeadline_deadlineToCheck_returnTrue() {
        Assertions.assertTrue(customizedTaskOne.hasDeadline());
    }

    @Test
    public void hasDeadline_deadlineToCheck_returnFalse() {
        Assertions.assertFalse(defaultTaskOne.hasDeadline());
    }

    @Test
    public void statusChangeTest_null_returnTrue() {
        defaultTaskOne.markAsDone();
        Assertions.assertTrue(defaultTaskOne.isDone());
    }
    @Test
    public void statusChangeTest_null_returnFalse() {
        defaultTaskOne.markAsDone();
        defaultTaskOne.markAsPending();
        Assertions.assertFalse(defaultTaskOne.isDone());
    }

    @Test
    public void setDeadlineTest() {
        try {
            defaultTaskOne.setDeadline(
                    new CustomizedDeadline("2020-10-10",
                            LocalDate.of(2020, 10, 10)));
        } catch (InvalidTaskOperationException e) {
            Assertions.assertFalse(true);
        }
        try {
            defaultTaskTwo.setDeadline(
                    new CustomizedDeadline("2020-10-10",
                            LocalDate.of(2020, 10, 10)));
        } catch (InvalidTaskOperationException e) {
            Assertions.assertFalse(true);
        }
        boolean test = false;
        try {
            defaultTaskTwo.setDeadline(
                    new CustomizedDeadline("2021-01-02",
                            LocalDate.of(2021, 1, 2)));
        } catch (InvalidTaskOperationException e) {
            test = true;
        }
        Assertions.assertTrue(true);

        CustomizedDeadline newDeadline = new CustomizedDeadline("2020-10-10",
                LocalDate.of(2020, 10, 10));
        Assertions.assertTrue(defaultTaskOne.getCustomizedDeadline().equals(newDeadline));
    }

    @Test
    public void isValidTest_null_returnTrue() {
        Assertions.assertTrue(defaultTaskOne.isValid());
        Assertions.assertTrue(defaultTaskTwo.isValid());
        Assertions.assertTrue(defaultTaskThree.isValid());
        Assertions.assertTrue(customizedTaskOne.isValid());

        Task invalidTask = new Task(new Index("0202"), new WeekNumber("2"), new Description("Test Task Two"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), true, false);
        Task invalidTaskTwo = new Task(new Index("0202"), new WeekNumber("4"), new Description("Test Task Two"),
                null, new CustomizedDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                 new Remark("no remark"), true, false);
        Task invalidTaskThree = new Task(new Index("0202"), new WeekNumber("2"), new Description("Test Task Two"),
                null, null,
                new Remark("no remark"), true, false);
        Assertions.assertFalse(invalidTask.isValid());
        Assertions.assertFalse(invalidTaskTwo.isValid());
        Assertions.assertFalse(invalidTaskThree.isValid());
    }

    @Test
    public void isValidTest_null_returnFalse() {
        Task invalidTask = new Task(new Index("0202"), new WeekNumber("2"), new Description("Test Task Two"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), true, false);
        Task invalidTaskTwo = new Task(new Index("0202"), new WeekNumber("4"), new Description("Test Task Two"),
                null, new CustomizedDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                new Remark("no remark"), true, false);
        Task invalidTaskThree = new Task(new Index("0202"), new WeekNumber("2"), new Description("Test Task Two"),
                null, null,
                new Remark("no remark"), true, false);
        Assertions.assertFalse(invalidTask.isValid());
        Assertions.assertFalse(invalidTaskTwo.isValid());
        Assertions.assertFalse(invalidTaskThree.isValid());
    }

    @Test
    public void isWeekXTest_weekNumber_returnTrue() {
        Assertions.assertTrue(defaultTaskOne.isWeekX(new WeekNumber("1")));
        Assertions.assertTrue(customizedTaskOne.isWeekX(new WeekNumber("3")));
        Assertions.assertFalse(defaultTaskOne.isWeekX(new WeekNumber("2")));
        Assertions.assertFalse(customizedTaskOne.isWeekX(new WeekNumber("2")));
    }

    @Test
    public void isWeekXTest_weekNumber_returnFalse() {
        Assertions.assertFalse(defaultTaskOne.isWeekX(new WeekNumber("2")));
        Assertions.assertFalse(customizedTaskOne.isWeekX(new WeekNumber("2")));
    }

    @Test
    public void containsTest_keyword_returnTrue() {
        Assertions.assertTrue(defaultTaskOne.contains("Test"));
        Assertions.assertTrue(defaultTaskOne.contains("test"));
        Assertions.assertTrue(defaultTaskOne.contains("remark"));
        Assertions.assertTrue(taskWithoutRemark.contains("no"));
    }

    @Test
    public void containsTest_keyword_returnFalse() {
        Assertions.assertFalse(defaultTaskOne.contains("no meaning"));
    }

    @Test
    public void isSameTaskTest_taskToCompare_returnTrue() {
        Assertions.assertTrue(defaultTaskOne.isSameTask(defaultTaskTwo));
    }

    @Test
    public void isSameTaskTest_taskToCompare_returnFalse() {
        Assertions.assertFalse(defaultTaskOne.isSameTask(customizedTaskOne));
        Assertions.assertFalse(defaultTaskTwo.isSameTask(defaultTaskThree));
    }

    @Test
    public void isOverDueTest_null_returnTrue() {
        Assertions.assertTrue(defaultTaskOne.isOverdue());
    }

    @Test
    public void isOverDueTest_null_returnFalse() {
        Assertions.assertFalse(defaultTaskTwo.isOverdue());
    }

    @Test
    public void compareToTest_taskToCompare_returnTrue() {
        Assertions.assertTrue(defaultTaskOne.compareTo(defaultTaskTwo) < 0);
        Assertions.assertTrue(defaultTaskTwo.compareTo(defaultTaskOne) > 0);
    }

    @Test
    public void compareToTest_taskToCompare_returnFalse() {
        Assertions.assertFalse(defaultTaskOne.compareTo(defaultTaskTwo) > 0);
        Assertions.assertFalse(defaultTaskTwo.compareTo(defaultTaskOne) < 0);
    }

    @Test
    public void toString_null_stringOutput() {
        String str1 = "[Week 1] IP Task 0101 with description: IP task\n"
                + "Official deadline: 2020-09-10\nRemark: no remark";
        String str2 = "[Week 1] ADMIN Task 0101 with description: Admin task"
                + "\nOfficial deadline: 2020-09-10\nCustomized deadline: 2020-09-01\n"
                + "Remark: no remark";
        Assertions.assertEquals(str1, ip.toString());
        Assertions.assertEquals(str2, admin.toString());
    }

    @Test
    public void getCategory_null_category() {
        Assertions.assertEquals(TaskCategory.TOPIC, topic.getCategory());
        Assertions.assertEquals(TaskCategory.IP, ip.getCategory());
        Assertions.assertEquals(TaskCategory.TP, tp.getCategory());
        Assertions.assertEquals(TaskCategory.ADMIN, admin.getCategory());
    }

    @Test
    public void getRemark_null_remark() {

    }

    @Test
    public void equals_objectToCompare_returnTrue() {
        Assertions.assertTrue(ip.equals(ip));
    }

    @Test
    public void equals_objectToCompare_returnFalse() {
        Assertions.assertFalse(ip.equals(tp));
        Task ip1 = new IP(new Index("0102"), new WeekNumber("1"), new Description("IP task"),
                new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
                null, new Remark("no remark"), false, false);
        Assertions.assertFalse(ip.equals(ip1));
    }
}
