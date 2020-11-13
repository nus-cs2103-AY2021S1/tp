package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_PHONE;

import seedu.address.logic.commands.global.AddPersonCommand;
import seedu.address.logic.commands.project.EditTeammateCommand;
import seedu.address.model.person.Person;

public class PersonUtil {
    public static String getCommandInfo(Person p) {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getGitUserName()).append(" ")
            .append(p.getPhone()).append(" ")
            .append(p.getEmail()).append(" ")
            .append(p.getAddress());
        return builder.toString();
    }

    /**
     * Returns an addTeammateCommand string for adding the {@code project}.
     */
    public static String getAddTeammateCommand(Person person) {
        return AddPersonCommand.COMMAND_WORD + " " + getProjectDetails(person);
    }

    /**
     * Returns an editTeammateCommand string for adding the {@code project}.
     */
    public static String getEditTeammateCommand(Person person) {

        return EditTeammateCommand.COMMAND_WORD + " " + getProjectDetails(person);
    }


    /**
     * Returns the part of command string for the given {@code teammate}'s details.
     */
    public static String getProjectDetails(Person teammate) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PERSON_NAME + teammate.getPersonName().toString() + " ");
        sb.append(PREFIX_PERSON_GIT_USERNAME + teammate.getGitUserName().toString() + " ");
        sb.append(PREFIX_PERSON_PHONE + teammate.getPhone().toString() + " ");
        sb.append(PREFIX_PERSON_EMAIL + teammate.getEmail().toString() + " ");
        sb.append(PREFIX_PERSON_ADDRESS + teammate.getAddress().toString() + " ");

        return sb.toString();
    }


}
