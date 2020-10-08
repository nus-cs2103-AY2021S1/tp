package tp.cap5buddy.grades;

public class Grade {
    private String name;
    private int percentageOfFinalGrade;
    private double results;

    /**
     *
     * @param name
     * @param percentageOfFinalGrade
     * @param results
     */
    public Grade(String name, int percentageOfFinalGrade, double results) {
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

}
