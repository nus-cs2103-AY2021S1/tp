package seedu.address.model.semester;

/**
 * Represents a Semester in MyMods.
 * Guarantees: immutable; is valid as declared in {@link #isValidSemester(String)}
 */
public class SemesterManager {

    private static SemesterManager semesterManager = null;

    private Semester currentSem = Semester.NA;

    /**
     * Constructs a {@code SemesterManager}.
     * Set constructor to private to prevent other classes
     * from instantiating a SemesterManager object at will.
     */
    private SemesterManager() {

    }

    /**
     * Instantiates a single copy of the singleton SemesterManager class
     * when it is executed for the first time. Returns the single
     * instance of the class for subsequent calls to this operation;
     *
     * @return a single copy of the SemesterManager class.
     */
    public static SemesterManager getInstance() {
        if (semesterManager == null) {
            semesterManager = new SemesterManager();
        }
        return semesterManager;
    }

    /**
     * Sets the current semester to the semester entered by the user.
     *
     * @param currentSemester the semester entered by the user.
     */
    public void setCurrentSemester(Semester currentSemester) {
        currentSem = currentSemester;
    }

    /**
     * Gets the current semester where modules are being added or modified.
     *
     * @return the current semester.
     */
    public Semester getCurrentSemester() {
        return currentSem;
    }

    /**
     * Returns true if a given string is a valid Semester.
     *
     * @param sem the semester entered by the user.
     * @return true if a given string is a valid Semester,
     * false if a given string is an invalid Semester.
     */
    public static boolean isValidSemester(String sem) {
        if (sem.equals(Semester.NA.toString())) {
            return false;
        }
        for (Semester s : Semester.values()) {
            if (s.name().equals(sem)) {
                return true;
            }
        }
        return false;
    }
}
