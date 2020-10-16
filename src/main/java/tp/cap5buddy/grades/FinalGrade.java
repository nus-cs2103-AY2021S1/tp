package tp.cap5buddy.grades;

/**
 * Represents the final grade achieved by the user for the module.
 */
public class FinalGrade extends Grade {

    /**
     * Creates a final grade for the module.
     *
     * @param moduleName
     * @param results
     */
    public FinalGrade(String moduleName, double results) {
        super(moduleName, 100, results);
    }

}
