package tp.cap5buddy.grades;

/**
 * Represents the grades attained by user within the module.
 */
public class Grade {
    private String name;
    private int percentageOfFinalGrade;
    private double results;

    /**
     * Creates a Grade.
     *
     * @param name name of the module the grade is attached to.
     * @param percentageOfFinalGrade percentage the grade takes up.
     * @param results the result in this percentage.
     */
    public Grade(String name, int percentageOfFinalGrade, double results) {
        this.name = name;
        this.percentageOfFinalGrade = percentageOfFinalGrade;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public int getPercentageOfFinalGrade() {
        return percentageOfFinalGrade;
    }

    public double getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Grade{" + "name: " + name + '\'' + ", % of final grade: "
              + percentageOfFinalGrade + ", results: " + results + '}';
    }
}
