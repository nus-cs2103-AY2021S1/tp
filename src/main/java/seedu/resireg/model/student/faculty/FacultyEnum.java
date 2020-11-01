package seedu.resireg.model.student.faculty;

import java.util.Arrays;

public enum FacultyEnum {
    ARTS_SOCIAL_SCIENCES("FASS"),
    BUSINESS("BIZ"),
    COMPUTING("SOC"),
    CONTINUING_LIFELONG_EDUCATION("CLE"),
    DENTISTRY("DEN"),
    DESIGN_AND_ENVIRONMENT("SDE"),
    DUKE_NUS("DNUS"),
    ENGINEERING("ENG"),
    INTEGRATIVE_SCIENCES_AND_ENGINEERING("ISE"),
    LAW("LAW"),
    MEDICINE("MED"),
    MUSIC("MUS"),
    PUBLIC_HEALTH("PH"),
    PUBLIC_POLICY("PP"),
    SCIENCE("FOS"),
    UNIVERSITY_SCHOLARS_PROGRAMME("USP"),
    YALE_NUS("YNUS");

    private String abbreviation;

    FacultyEnum (String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean matchesFacultyAbbreviation(String test) {
        return test.equals(abbreviation);
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    /**
     * Returns all values of the enum as a string in list form.
     */
    public static String toListString() {
        return Arrays.asList(FacultyEnum.values()).toString();

    }
}
