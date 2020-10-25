package seedu.taskmaster.ui;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.session.StudentRecord;
import seedu.taskmaster.model.session.StudentRecordList;

public class SessionStub {
    private final String name;
    private final ObservableList<StudentRecord> srList;
    SessionStub(String name, ObservableList<StudentRecord> srList) {
        this.name = name;
        this.srList = srList;
    }

    public String getName() {
        return this.name;
    }

    public ObservableList<StudentRecord> getSrList() {
        return srList;
    }
}