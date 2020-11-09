package seedu.address.model.util;

import java.util.PriorityQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.module.Module;

public class ModuleListSorter {

    /**
     * Sorts the list of modules according to semester.
     * @param moduleList the list of modules to be sorted.
     * @return a sorted list of modules.
     */
    public static FilteredList<Module> sortModuleList(FilteredList<Module> moduleList) {
        ModuleComparator moduleComparator = new ModuleComparator();
        PriorityQueue<Module> priorityQueue = new PriorityQueue<>(moduleComparator);
        priorityQueue.addAll(moduleList);
        ObservableList<Module> observableList = FXCollections.observableArrayList();
        while (!priorityQueue.isEmpty()) {
            observableList.add(priorityQueue.poll());
        }
        return new FilteredList<>(observableList, mod -> true);
    }
}
