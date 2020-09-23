package seedu.address.timetable;

public class TimetableData {
    private int semester;
    private String[] moduleDataArray;
    private String[] moduleCodeArray;
    private String[] moduleLessonArray;

    /**
     * Constructor for TimetableData.
     */
    public TimetableData(int semester, String[] moduleDataArray, String[] moduleCodeArray, String[] moduleLessonArray) {
        this.semester = semester;
        this.moduleDataArray = moduleDataArray;
        this.moduleCodeArray = moduleCodeArray;
        this.moduleLessonArray = moduleLessonArray;
    }

    public int getSemester() {
        return this.semester;
    }

    public String[] getModuleDataArray() {
        return this.moduleDataArray;
    }

    public String[] getModuleCodeArray() {
        return this.moduleCodeArray;
    }

    public String[] getModuleLessonArray() {
        return this.moduleLessonArray;
    }
}
