package seedu.resireg.testutil;

import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ALIAS_ROOMS_RO;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ALIAS_STUDENTS_ST;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_COMMAND_ROOMS_RO;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_COMMAND_STUDENTS_ST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalCommandWordAliases {

    public static final CommandWordAlias ROOMS_R = new CommandWordAliasBuilder().withCommandWord("rooms")
            .withAlias("r").build();
    public static final CommandWordAlias STUDENTS_STU = new CommandWordAliasBuilder().withCommandWord("students")
            .withAlias("stu").build();
    public static final CommandWordAlias ALLOCATE_AL = new CommandWordAliasBuilder().withCommandWord("allocate")
            .withAlias("al").build();
    public static final CommandWordAlias REALLOCATE_REL = new CommandWordAliasBuilder().withCommandWord("reallocate")
            .withAlias("rel").build();

    // Manually added
    public static final CommandWordAlias EDIT_E = new CommandWordAliasBuilder().withCommandWord("edit-student")
            .withAlias("e").build();
    public static final CommandWordAlias DELETE_D = new CommandWordAliasBuilder().withCommandWord("delete-student")
            .withAlias("d").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final CommandWordAlias ROOMS_RO = new CommandWordAliasBuilder()
            .withCommandWord(VALID_COMMAND_ROOMS_RO)
            .withAlias(VALID_ALIAS_ROOMS_RO).build();
    public static final CommandWordAlias STUDENTS_ST = new CommandWordAliasBuilder()
            .withCommandWord(VALID_COMMAND_STUDENTS_ST)
            .withAlias(VALID_ALIAS_STUDENTS_ST).build();

    private TypicalCommandWordAliases() {
    } // prevents instantiation

    /**
     * Returns an {@code UserPrefs} with all the typical command word aliases.
     */
    public static ReadOnlyUserPrefs getTypicalUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        for (CommandWordAlias commandWordAlias : getTypicalCommandWordAliases()) {
            userPrefs.addAlias(commandWordAlias);
        }
        return userPrefs;
    }

    public static List<CommandWordAlias> getTypicalCommandWordAliases() {
        return new ArrayList<>(Arrays.asList(ROOMS_R, STUDENTS_STU, ALLOCATE_AL, REALLOCATE_REL));
    }
}
