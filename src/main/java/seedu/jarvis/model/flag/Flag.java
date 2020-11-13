package seedu.jarvis.model.flag;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.commons.util.AppUtil.checkArgument;
import static seedu.jarvis.logic.parser.CliSyntax.DELETE_TASK;
import static seedu.jarvis.logic.parser.CliSyntax.EDIT_LOGIN;
import static seedu.jarvis.logic.parser.CliSyntax.EDIT_STUDENT;
import static seedu.jarvis.logic.parser.CliSyntax.MISSION_DEADLINE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.jarvis.logic.parser.CliSyntax.QUEST_DEADLINE;
import static seedu.jarvis.logic.parser.CliSyntax.TASK_DEADLINE;
import static seedu.jarvis.logic.parser.CliSyntax.TASK_EVENT;
import static seedu.jarvis.logic.parser.CliSyntax.TASK_TODO;
import static seedu.jarvis.logic.parser.CliSyntax.UNGRADED_MISSION;
import static seedu.jarvis.logic.parser.CliSyntax.UNGRADED_QUEST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_CONSULTATION_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_DEADLINE_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_EVENT_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_MASTERY_CHECK_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_PAST_CONSULTATION_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_PAST_MASTERY_CHECK_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_STUDENT;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_TASK_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_TODO_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_UPCOMING_CONSULTATION_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_UPCOMING_MASTERY_CHECK_LIST;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * Flag class encapsulates the flags, eg: -m, etc, that will appear after a command.
 */
public class Flag {
    public static final String MESSAGE_CONSTRAINTS = "Flag provided is invalid";
    private static final Set<String> VALID_FLAGS = ImmutableSet.of(MISSION_DEADLINE, QUEST_DEADLINE, UNGRADED_QUEST,
            UNGRADED_MISSION, VIEW_STUDENT, TASK_TODO, TASK_EVENT, TASK_DEADLINE, PREFIX_DATE, PREFIX_TIME,
            VIEW_TASK_LIST, VIEW_TODO_LIST, VIEW_EVENT_LIST, VIEW_DEADLINE_LIST, VIEW_CONSULTATION_LIST,
            VIEW_PAST_CONSULTATION_LIST, VIEW_UPCOMING_CONSULTATION_LIST, VIEW_UPCOMING_MASTERY_CHECK_LIST,
            VIEW_PAST_MASTERY_CHECK_LIST, VIEW_MASTERY_CHECK_LIST, DELETE_TASK, EDIT_STUDENT, EDIT_LOGIN);

    public final String flag;

    /**
     * Creates a Flag object.
     * @param flag must not be null or invalid.
     */
    public Flag(String flag) {
        requireNonNull(flag);
        checkArgument(isValidFlag(flag), MESSAGE_CONSTRAINTS);
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }

    /**
     * Checks whether given test flag is a valid flag.
     * @param test Flag to be tested
     * @return A Boolean denoting whether the flag is valid
     */
    public static boolean isValidFlag(String test) {
        return VALID_FLAGS.contains(test);
    }
}
