package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ExportCommand.MESSAGE_EMAIL_SUCCESS;
import static seedu.address.logic.commands.ExportCommand.MESSAGE_PHONE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ExportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_exportEmail_exportSuccessful() throws CommandException, IOException {
        CommandResult commandResult = new ExportCommand("email").execute(model);

        // compare output
        assertTrue(compareOutput("./src/test/data/ExportTest/email.txt"));
        assertEquals(MESSAGE_EMAIL_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_exportPhone_exportSuccessful() throws CommandException, IOException {
        CommandResult commandResult = new ExportCommand("phone").execute(model);

        // compare output
        assertTrue(compareOutput("./src/test/data/ExportTest/phone.txt"));
        assertEquals(MESSAGE_PHONE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidType_throwsCommandException() {
        ExportCommand exportCommand = new ExportCommand("a");

        assertThrows(CommandException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ExportCommand.MESSAGE_USAGE), () -> exportCommand.execute(model));
    }

    //@@author yanchenglee98-reused
    //Solution below reused from https://javaconceptoftheday.com/compare-two-text-files-in-java/
    /**
     * Compares the output text at /data/hally.txt with the .txt file at the specified location.
     * @param compare Path of the .txt file to be compared to.
     * @return True if both text files have the same content, false otherwise.
     * @throws IOException If file reading is interrupted.
     */
    public static boolean compareOutput(String compare) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader("./data/hally.txt"));

        BufferedReader reader2 = new BufferedReader(new FileReader(compare));

        String line1 = reader1.readLine();

        String line2 = reader2.readLine();

        boolean areEqual = true;

        int lineNum = 1;

        while (line1 != null || line2 != null) {
            if (line1 == null || line2 == null) {
                areEqual = false;

                break;
            } else if (!line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                break;
            }

            line1 = reader1.readLine();

            line2 = reader2.readLine();

            lineNum++;
        }

        reader1.close();

        reader2.close();

        if (areEqual) {
            System.out.println("Two files have same content.");
            return true;
        } else {
            System.out.println("Two files have different content. They differ at line " + lineNum);

            System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
            return false;
        }
    }
}
