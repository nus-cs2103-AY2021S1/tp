package seedu.address.model.module;

/**
 * Represents the 12 different types of grade
 * encapsulated with its corresponding grade point.
 */
public enum Cap {
    A_PLUS("A+", 5.0),
    A("A", 5.0),
    A_MINUS("A-", 4.5),
    B_PLUS("B+", 4.0),
    B("B", 3.5),
    B_MINUS("B-", 3.0),
    C_PLUS("C+", 2.5),
    C("C", 2.0),
    D_PLUS("D+", 1.5),
    D("D", 1.0),
    F("F", 0.0),
    NA("NA", 0.0),
    SU("SU", 0.0);

    /**
     * Represents the string representation of the grade.
     */
    private final String gradeString;

    /**
     * Represents the corresponding grade point of the grade.
     */
    private final double gradePoint;

    /**
     * Instantiates a Cap object with a grade
     * string and its respective grade point.
     *
     * @param gradeString the string representation of the grade.
     * @param gradePoint  the corresponding grade point of the grade.
     */
    Cap(String gradeString, double gradePoint) {
        this.gradeString = gradeString;
        this.gradePoint = gradePoint;
    }

    public String getGradeString() {
        return gradeString;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public static String getEmptyGrade() {
        return NA.getGradeString();
    }
}
