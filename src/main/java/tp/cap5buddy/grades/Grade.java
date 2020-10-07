package tp.cap5buddy.grades;

public class Grade {
    private int percentageOfFinalGrade;
    private int results;
    private int total;

    /**
     *
     * @param percentageOfFinalGrade
     * @param results
     * @param total
     */
    public Grade( int percentageOfFinalGrade, int results, int total) {
        this.percentageOfFinalGrade = percentageOfFinalGrade;
        this.results = results;
        this.total = total;
    }

    public int getPercentageOfFinalGrade() {
        return percentageOfFinalGrade;
    }

    public int getResults() {
        return results;
    }

    public int getTotal() {
        return total;
    }
}
