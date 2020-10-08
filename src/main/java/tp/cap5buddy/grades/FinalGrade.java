package tp.cap5buddy.grades;

public class FinalGrade extends Grade {

    /**
     *
     * @param moduleName
     * @param percentageOfFinalGrade
     * @param results
     */
    public FinalGrade(String moduleName, int percentageOfFinalGrade, double results) {
        super(moduleName, 100, results);
    }

}
