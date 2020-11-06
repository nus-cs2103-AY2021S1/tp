package com.eva.model.person.applicant.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class used to generate a sample resume text file for reference.
 */
public class ResumeTextFileGenerator {

    /**
     * Generates a sample resume text file in the same directory.
     */
    public void generateResumeTextFile() {
        try {
            File myObj = new File("data/resume.txt");
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter("data/"
                        + "resume.txt");
                myWriter.write("Name: Royce\n");
                myWriter.write("\n");
                myWriter.write("Education:\n");
                myWriter.write("1.\nSchool: NUS\nStart: 01/01/2019\nEnd: 01/01/2023\n");
                myWriter.write("2.\nSchool: SJI\nStart: 01/01/2011\nEnd: 01/01/2015\n");
                myWriter.write("\n");
                myWriter.write("Experience:\n");
                myWriter.write("1.\nCompany: DSO\nPosition: Intern\nDescription: I did some coding."
                        + "\nStart: 01/01/2020\nEnd: 01/02/2020");
                myWriter.close();
                System.out.println("File created in same directory: " + myObj.getName());
            } else {
                System.out.println("Sample resume.txt already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
