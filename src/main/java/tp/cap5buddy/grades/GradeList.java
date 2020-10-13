package tp.cap5buddy.grades;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the grades for the module as a list.
 */
public class GradeList {
    private final String moduleName;
    private List<Grade> gradesList;

    /**
     * Creates a list of grades for the particular module.
     *
     * @param moduleName the name of the module.
     */
    public GradeList(String moduleName) {
        this.moduleName = moduleName;
        this.gradesList = new ArrayList<Grade>();
    }

    /**
     * Returns the list of grades.
     *
     * @return list of grades.
     */
    public List<Grade> getGradesList() {
        return gradesList;
    }

    /**
     * Adds a grade to the list of grades.
     *
     * @param grade grade to be added.
     */
    public void addGrade(Grade grade) {
        gradesList.add(grade);
    }
}
