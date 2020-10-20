package com.eva.model.person.applicant.application;

import java.util.List;

/**
 * Represents an Applicant's application, similar to a resume.
 */
public class Application {
    private String applicantName;
    private List<Experience> experienceSection;
    private List<Education> educationSection;

    /**
     * Creates an Application object that contains name, experience and education.
     * @param applicantName The name of the applicant.
     * @param experienceSection A list of experiences.
     * @param educationSection A list of educational history.
     */
    public Application(String applicantName, List<Experience> experienceSection, List<Education> educationSection) {
        this.applicantName = applicantName;
        this.experienceSection = experienceSection;
        this.educationSection = educationSection;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        String experienceString = "";
        String educationString = "";
        for (Experience exp : this.experienceSection) {
            experienceString += exp.toString() + "\n";
        }
        for (Education edu : this.educationSection) {
            educationString += edu.toString() + "\n";
        }
        builder.append("Name: ")
                .append(this.applicantName)
                .append("\n\nExperience ---------------------\n\n")
                .append(experienceString)
                .append("\nEducation ---------------------\n\n")
                .append(educationString);
        return builder.toString();
    }
}
