package seedu.resireg.ui;

import seedu.resireg.ui.student.StudentListPanel;

/**
 * Represents a tab containing a {@code StudentListPanel}. The tab may contain other UI elements.
 */
interface StudentsTab extends TabContainer {
    void setStudentListPanel(StudentListPanel studentListPanel);
}
