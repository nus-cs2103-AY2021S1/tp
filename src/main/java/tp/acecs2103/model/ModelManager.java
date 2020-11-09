package tp.acecs2103.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import tp.acecs2103.commons.core.GuiSettings;
import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.commons.util.CollectionUtil;
import tp.acecs2103.model.exceptions.InvalidTaskListOperationException;
import tp.acecs2103.model.exceptions.ModelException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.WeekNumber;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskList taskList;
    private final UiTaskList uiTaskList;
    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given taskList and userPrefs.
     */
    public ModelManager(TaskList taskList, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(taskList, userPrefs);

        logger.fine("Initializing with task list: " + taskList + " and user prefs " + userPrefs);

        this.taskList = new TaskList(taskList);
        this.userPrefs = new UserPrefs(userPrefs);
        this.uiTaskList = new UiTaskList(this.taskList.getUiTaskList());
        logger.info("The size of uitasklist is: " + this.uiTaskList.size());
    }

    /**
     * Initializes a ModelManager.
     */
    public ModelManager() {
        this.taskList = new TaskList();
        logger.info("The size of tasklist is: " + taskList.size());
        this.userPrefs = new UserPrefs();
        this.uiTaskList = new UiTaskList(taskList.getUiTaskList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        userPrefs.setTaskListFilePath(taskListFilePath);
    }

    //=========== TaskList ================================================================================

    @Override
    public void setTaskList(TaskList taskList) {
        this.taskList.resetData(taskList);
    }

    @Override
    public TaskList getTaskList() {
        return taskList;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        boolean result = taskList.hasTask(task);
        return result;
    }

    @Override
    public void addTask(Task task) {
        uiTaskList.addAll(taskList.add(task));
    }

    @Override

    public boolean isCustomizedTask(Index index) {
        return taskList.isCustomizedTask(index);
    }

    @Override
    public void deleteTask(Index index) throws ModelException {
        try {
            uiTaskList.addAll(taskList.delete(index));
        } catch (InvalidTaskListOperationException e) {
            throw new ModelException(e.getMessage());
        }
    }

    @Override
    public void markTaskAsDone(Index index) throws ModelException {
        uiTaskList.addAll(taskList.done(index));
    }

    @Override
    public void markTaskAsPending(Index index) throws ModelException {
        uiTaskList.addAll(taskList.undone(index));
    }

    @Override
    public void filterTasks(boolean isDone, boolean byOfficialDeadline, WeekNumber weekNumber) {
        uiTaskList.addAll(taskList.filter(isDone, byOfficialDeadline, weekNumber));
    }

    @Override
    public void findTasks(String keyword) {
        uiTaskList.addAll(taskList.find(keyword));
    };

    @Override
    public void listTasks(WeekNumber weekNumber) {
        uiTaskList.addAll(taskList.list(weekNumber));
    };

    @Override
    public void deadlineTask(Index index, CustomizedDeadline deadline) throws
            ModelException {
        try {
            uiTaskList.addAll(taskList.deadline(index, deadline));
        } catch (InvalidTaskListOperationException e) {
            throw new ModelException(e.getMessage());
        }

    }

    @Override
    public void setTask(Task target, Task editedTask) {
        uiTaskList.addAll(taskList.resetTask(target, editedTask));
    };

    //=========== UiTaskList ================================================================================

    @Override
    public UiTaskList getUiTaskList() {
        return uiTaskList;
    }

    @Override
    public ObservableList<Task> getAdminList() {
        return uiTaskList.getAdminList();
    }

    @Override
    public ObservableList<Task> getTopicList() {
        return uiTaskList.getTopicList();
    }

    @Override
    public ObservableList<Task> getIpList() {
        return uiTaskList.getIpList();
    }

    @Override
    public ObservableList<Task> getTpList() {
        return uiTaskList.getTpList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (!(other instanceof ModelManager)) {
            return false;
        } else {
            return userPrefs.equals(((ModelManager) other).userPrefs)
                    && taskList.equals(((ModelManager) other).taskList);
        }
    }
}

