package seedu.address.timetable;

public class TimetableData {
    private int semester;
    private String[] moduleCodeArray;
    private String[] moduleLessonArray;

    /**
     * Constructor for TimetableData.
     */
    public TimetableData(int semester, String[] moduleCodeArray, String[] moduleLessonArray) {
        this.semester = semester;
        this.moduleCodeArray = moduleCodeArray;
        this.moduleLessonArray = moduleLessonArray;
    }

    public int getSemester() {
        return this.semester;
    }

    public String[] getModuleCodeArray() {
        return this.moduleCodeArray;
    }

    public String[] getModuleLessonArray() {
        return this.moduleLessonArray;
    }
}
