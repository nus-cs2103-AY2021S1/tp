package com.eva.model.person.applicant.application;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
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
        requireAllNonNull(applicantName, experienceSection, educationSection);
        this.applicantName = applicantName;
        this.experienceSection = experienceSection;
        this.educationSection = educationSection;
    }

    /**
     * Creates a blank Application object without details.
     */
    public Application() {
        this.applicantName = "Default";
        this.experienceSection = new ArrayList<>();
        this.educationSection = new ArrayList<>();
    }

    public List<Experience> getExperienceSection() {
        return experienceSection;
    }

    public List<Education> getEducationSection() {
        return educationSection;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getExperienceSectionString() {
        StringBuilder output = new StringBuilder();
        for (Experience e : this.experienceSection) {
            output.append(e.toString()).append("\n\n");
        }
        return output.toString();
    }

    public String getEducationSectionString() {
        StringBuilder output = new StringBuilder();
        for (Education e : this.educationSection) {
            output.append(e.toString()).append("\n\n");
        }
        return output.toString();
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder experienceString = new StringBuilder();
        StringBuilder educationString = new StringBuilder();
        for (Experience exp : this.experienceSection) {
            experienceString.append(exp.toString()).append("\n");
        }
        for (Education edu : this.educationSection) {
            educationString.append(edu.toString()).append("\n");
        }
        builder.append("\nName: ")
                .append(this.applicantName)
                .append("\n\nExperience ---------------------\n\n")
                .append(experienceString)
                .append("\nEducation ---------------------\n\n")
                .append(educationString);
        return builder.toString();
    }
}
