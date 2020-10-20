package com.eva.logic.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.eva.logic.commands.AddApplicationCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.applicant.application.Education;
import com.eva.model.person.applicant.application.Experience;

public class AddApplicationCommandParser implements Parser<AddApplicationCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public AddApplicationCommand parse(String userInput) throws ParseException, FileNotFoundException {
        String filePath = userInput.split(" ")[1];
        File file = new File(filePath);

        Scanner sc = new Scanner(file);
        List<Education> eduList = new ArrayList<>();
        List<Experience> expList = new ArrayList<>();

        // Name
        String name = sc.nextLine().split(":")[1];
        sc.nextLine(); // read blank line

        // Education
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // System.out.println(line.split(" ")[0]);
            if (line.equals("Experience:")) {
                break;
            }
            if (line.length() > 6 && line.substring(0, 6).equals("School")) {
                String schoolName = line.split(" ")[1];
                String startDate = sc.nextLine().split(" ")[1];
                String endDate = sc.nextLine().split(" ")[1];
                Education edu = new Education(startDate, endDate, schoolName);
                eduList.add(edu);
            }
        }

        // Experience
        while (sc.hasNextLine()) {
            sc.nextLine(); // this should be number
            String company = sc.nextLine().split(" ")[1];
            String position = sc.nextLine().split(" ")[1];
            String description = sc.nextLine().split(":")[1]; // take note of semi colon, as 2nd element needs to be entire desc
            String startDate = sc.nextLine().split(" ")[1];
            String endDate = sc.nextLine().split(" ")[1];
            Experience exp = new Experience(startDate, endDate, company, position, description);
            expList.add(exp);
        }
        sc.close();

        Application application = new Application(name, expList, eduList);

        return new AddApplicationCommand(application);
    }
}
