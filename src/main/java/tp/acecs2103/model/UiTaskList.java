package tp.acecs2103.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.TP;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.TaskCategory;
import tp.acecs2103.model.task.Topic;


public class UiTaskList {
    private ObservableList<Task> adminList = FXCollections.observableArrayList();
    private ObservableList<Task> topicList = FXCollections.observableArrayList();
    private ObservableList<Task> ipList = FXCollections.observableArrayList();
    private ObservableList<Task> tpList = FXCollections.observableArrayList();
    private ArrayList<Task> combinedTaskList;

    /**
     * Creates a {@code UiTaskList} with given source.
     * @param source A array list consisting of tasks which need to be displayed.
     */
    public UiTaskList(ArrayList<Task> source) {
        addAll(source);
    }

    /**
     * Clears four sub lists first and dds all tasks of {@code taskList} into sub lists based on category of each task.
     * @param taskList A array list consisting of tasks which need to be added to {@code UiTaskList}.
     */
    public void addAll(ArrayList<Task> taskList) {
        combinedTaskList = taskList;
        adminList.clear();
        topicList.clear();
        ipList.clear();
        tpList.clear();
        for (Task task: taskList) {
            if (TaskCategory.isAdmin(task.getCategory())) {
                adminList.add((Admin) task);
            } else if (TaskCategory.isTopic(task.getCategory())) {
                topicList.add((Topic) task);
            } else if (TaskCategory.isIP(task.getCategory())) {
                ipList.add((IP) task);
            } else if (TaskCategory.isTP(task.getCategory())) {
                tpList.add((TP) task);
            }
        }
    }
    /**
     * Gets a list consisting of all tasks.
     */
    public ArrayList<Task> getCombinedTaskList() {
        return combinedTaskList;
    }

    /**
     * Gets a list consisting of all admin tasks.
     */
    public ObservableList<Task> getAdminList() {
        return adminList;
    }

    /**
     * Gets a list consisting of all ip tasks.
     */
    public ObservableList<Task> getIpList() {
        return ipList;
    }

    /**
     * Gets a list consisting of all tp tasks.
     */
    public ObservableList<Task> getTpList() {
        return tpList;
    }

    /**
     * Gets a list consisting of all topic tasks.
     */
    public ObservableList<Task> getTopicList() {
        return topicList;
    }

    /**
     * Gets the number of tasks which need to be displayed.
     */
    public int size() {
        return adminList.size() + ipList.size() + tpList.size() + topicList.size();
    }

    public ArrayList<Integer> getAdminWeekRange() {
        if (adminList.size() == 0) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(getMinWeekNumber(adminList));
        res.add(getMaxWeekNumber(adminList));
        return res;
    }

    public ArrayList<Integer> getTopicWeekRange() {
        if (topicList.size() == 0) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(getMinWeekNumber(topicList));
        res.add(getMaxWeekNumber(topicList));
        return res;
    }

    public ArrayList<Integer> getTpWeekRange() {
        if (tpList.size() == 0) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(getMinWeekNumber(tpList));
        res.add(getMaxWeekNumber(tpList));
        return res;
    }

    public ArrayList<Integer> getIpWeekRange() {
        if (ipList.size() == 0) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(getMinWeekNumber(ipList));
        res.add(getMaxWeekNumber(ipList));
        return res;
    }
    /**
     * Gets the largest week number of all tasks to be displayed.
     */
    public int getMaxWeekNumber(ObservableList<Task> taskList) {
        int weekNumber = -1;
        for (Task task: taskList) {
            if (task.getWeekNumber().getWeekValueInt() > weekNumber) {
                weekNumber = task.getWeekNumber().getWeekValueInt();
            }
        }
        assert weekNumber != -1;
        return weekNumber;
    }

    /**
     * Gets the smallest week number of all tasks to be displayed.
     */
    public int getMinWeekNumber(ObservableList<Task> taskList) {
        int weekNumber = 10000;
        for (Task task: taskList) {
            if (task.getWeekNumber().getWeekValueInt() < weekNumber) {
                weekNumber = task.getWeekNumber().getWeekValueInt();
            }
        }
        assert weekNumber != 10000;
        return weekNumber;
    }
}
