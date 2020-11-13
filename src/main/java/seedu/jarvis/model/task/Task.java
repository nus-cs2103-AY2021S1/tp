package seedu.jarvis.model.task;

public abstract class Task {

    public static final String EVENT = "E";
    public static final String DEADLINE = "D";
    public static final String NO_DATE_TIME_DESCRIPTION = " ";

    private static int taskNum = 1;

    protected static void taskNumInc() {
        int currentTaskNum = getTaskNum();
        setTaskCount(currentTaskNum + 1);
    }

    /**
     * Compares tasknum from taskid of a task to current tasknum in Task.
     * @param taskId String
     */
    protected static void compareTaskNum(String taskId) {
        int taskNum = Integer.parseInt(taskId.substring(1));
        if (taskNum >= Task.getTaskNum()) {
            Task.setTaskCount(taskNum);
            Task.taskNumInc();
        }
    }

    //-------------------------- Setters ---------------------------------------------------------------------
    private static void setTaskCount(int count) {
        taskNum = count;
    }

    //-------------------------- Getters ---------------------------------------------------------------------
    protected static int getTaskNum() {
        return taskNum;
    }

    public static String getType(Task task) {
        return task.getTaskId().substring(0, 1);
    }

    public String getUnformattedPossibleDateTime(Task task) {
        String type = getType(task);

        if (type.equals(EVENT)) {
            Event taskEvent = (Event) task;
            return taskEvent.getUnformattedDateTime();

        } else if (type.equals(DEADLINE)) {
            Deadline taskDeadline = (Deadline) task;
            return taskDeadline.getUnformattedDateTime();

        } else { // all other task types have no date time attributes
            return NO_DATE_TIME_DESCRIPTION;
        }
    }

    public String getFormattedPossibleDateTime(Task task) {
        String type = getType(task);
        if (type.equals(EVENT)) {
            Event taskEvent = (Event) task;
            return taskEvent.getDateTime();

        } else if (type.equals(DEADLINE)) {
            Deadline taskDeadline = (Deadline) task;
            return taskDeadline.getDateTime();

        } else { // all other task types have no date time attributes
            return NO_DATE_TIME_DESCRIPTION;
        }
    }

    //-------------------------- Inherited Methods ---------------------------------------------------------
    public abstract String generateTaskId();

    public abstract String getDescription();

    public abstract String getTaskId();

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract String toString();
}
