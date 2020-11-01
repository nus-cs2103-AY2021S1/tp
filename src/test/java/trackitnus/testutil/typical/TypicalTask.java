package trackitnus.testutil.typical;

import static trackitnus.testutil.typical.TypicalDates.DATE_ONE;
import static trackitnus.testutil.typical.TypicalDates.DATE_THREE;
import static trackitnus.testutil.typical.TypicalDates.DATE_TWO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackitnus.model.task.Task;

public class TypicalTask {

    public static final Task TASK_FULL = new Task(TypicalNames.get(0), DATE_ONE, TypicalModule.getOneCode(0),
        "Sample Remark");
    public static final Task TASK_FULL_ANOTHER = new Task(TypicalNames.get(1), DATE_TWO, TypicalModule.getOneCode(1),
        "Sample Remark");
    public static final Task TASK_NO_REMARK = new Task(TypicalNames.get(2), DATE_THREE, TypicalModule.getOneCode(2),
        "");
    public static final Task TASK_NO_CODE = new Task(TypicalNames.get(1), DATE_THREE, null,
        "");

    public static List<Task> get() {
        return new ArrayList<>(Arrays.asList(TASK_FULL, TASK_FULL_ANOTHER, TASK_NO_REMARK, TASK_NO_CODE));
    }
}
