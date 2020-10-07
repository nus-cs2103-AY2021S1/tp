package tp.cap5buddy.grades;

import java.util.ArrayList;
import java.util.List;

public class GradeList {
    private final String moduleName;
    private List<Grade> gradeList;

    GradeList(String moduleName) {
        this.moduleName = moduleName;
        this.gradeList = new ArrayList<Grade>();
    }
}
