package com.eva.logic.parser;

import java.util.ArrayList;
import java.util.List;

import com.eva.model.person.applicant.application.Education;
import com.eva.model.person.applicant.application.Experience;

public class SampleResume {
    private final String name = "Royce";
    private final List<Education> educationList = new ArrayList<>();
    private final List<Experience> experienceList = new ArrayList<>();

    /**
     * Creates a Sample Resume object for use in application parser.
     */
    public SampleResume() {
        Education edu1 = new Education("01/01/2019", "01/01/2023", "NUS");
        Education edu2 = new Education("01/01/2011", "01/01/2015", "SJI");
        Experience exp1 = new Experience("01/01/2020", "01/02/2020", "DSO",
                "Intern", "I did some coding.");
        educationList.add(edu1);
        educationList.add(edu2);
        experienceList.add(exp1);
    }

    public String getName() {
        return name;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }
}
