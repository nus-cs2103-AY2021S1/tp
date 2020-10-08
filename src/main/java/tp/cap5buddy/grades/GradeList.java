package tp.cap5buddy.grades;

import java.util.ArrayList;
import java.util.List;

public class GradeList {
    private final String moduleName;
    private List<Grade> gradesList;

    /**
     *
     * @param moduleName
     */
    public GradeList(String moduleName) {
        this.moduleName = moduleName;
        this.gradesList = new ArrayList<Grade>();
    }

    /**
     *
     * @return
     */
    public List<Grade> getGradesList() {
        return gradesList;
    }

    /**
     *
     * @param grade
     */
    public void addGrade(Grade grade) {
        gradesList.add(grade);
    }
}
