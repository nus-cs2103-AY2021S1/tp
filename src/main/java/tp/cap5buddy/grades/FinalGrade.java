package tp.cap5buddy.grades;

public class FinalGrade extends Grade {
    private String moduleName;
    private int percentageOfFinalGrade;
    private int results;
    private int total;

    public FinalGrade(String moduleName, int percentageOfFinalGrade, int results, int total) {
        super(100, results, 100);
        this.moduleName = moduleName;
    }


}
