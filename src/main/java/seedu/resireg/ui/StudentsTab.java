package seedu.resireg.ui;

/**
 * Represents a tab containing a {@code StudentListPanel}. The tab may contain other UI elements.
 */
interface StudentsTab extends TabContainer {
    void setStudentListPanel(StudentListPanel studentListPanel);
}
