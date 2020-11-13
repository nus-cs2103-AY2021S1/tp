package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Task;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String projectName} into a {@code ProjectName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectName} is invalid.
     */
    public static ProjectName parseProjectName(String projectName) throws ParseException {
        requireNonNull(projectName);
        String trimmedProjectName = projectName.trim();
        if (!ProjectName.isValidProjectName(trimmedProjectName)) {
            throw new ParseException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectName(trimmedProjectName);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String projectDescription} into an {@code ProjectDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectDescription} is invalid.
     */
    public static ProjectDescription projectDescription(String projectDescription) throws ParseException {
        requireNonNull(projectDescription);
        String trimmedProjectDescription = projectDescription.trim();
        if (!ProjectDescription.isValidProjectDescription(trimmedProjectDescription)) {
            throw new ParseException(ProjectDescription.MESSAGE_CONSTRAINTS);
        }
        return new ProjectDescription(trimmedProjectDescription);
    }

    /**
     * Parses a {@code String repoUrl} into an {@code RepoUrl}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code repoUrl} is invalid.
     */
    public static RepoUrl parseRepoUrl(String repoUrl) throws ParseException {
        requireNonNull(repoUrl);
        String trimmedRepoUrl = repoUrl.trim();
        if (!RepoUrl.isValidRepoUrl(trimmedRepoUrl)) {
            throw new ParseException(RepoUrl.MESSAGE_CONSTRAINTS);
        }
        return new RepoUrl(trimmedRepoUrl);
    }

    /**
     * Parses a {@code String projectTag} into a {@code ProjectTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectTag} is invalid.
     */
    public static ProjectTag parseProjectTag(String projectTag) throws ParseException {
        requireNonNull(projectTag);
        String trimmedTag = projectTag.trim();
        if (!ProjectTag.isValidProjectTagName(trimmedTag)) {
            throw new ParseException(ProjectTag.MESSAGE_CONSTRAINTS);
        }
        return new ProjectTag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> projectTags} into a {@code Set<ProjectTag>}.
     */
    public static Set<ProjectTag> parseTags(Collection<String> projectTags) throws ParseException {
        requireNonNull(projectTags);
        final Set<ProjectTag> projectTagSet = new HashSet<>();
        for (String tagName : projectTags) {
            projectTagSet.add(parseProjectTag(tagName));
        }
        return projectTagSet;
    }


    /**
     * Parses a {@code String task} into a {@code Task}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Task parseTask(String task) {
        requireNonNull(task);
        String trimmedTask = task.trim();
        return new Task(trimmedTask, null, null, 0);
    }


    /**
     * Parses {@code Collection<String> tasks} into a {@code Set<Task>}.
     */
    public static Set<Task> parseTasks(Collection<String> tasks) {
        requireNonNull(tasks);
        final Set<Task> taskSet = new HashSet<>();
        for (String taskName : tasks) {
            taskSet.add(parseTask(taskName));
        }
        return taskSet;
    }

    /**
     * Parses a {@code String description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static String parseTaskDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Task.isValidTaskDescription(trimmedDescription)) {
            throw new ParseException(Task.DESCRIPTION_MESSAGE_CONSTRAINTS);
        }
        return trimmedDescription;
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String taskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskName} is invalid.
     */
    public static String parseTaskName(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedTaskName = taskName.trim();
        if (!Task.isValidTaskName(trimmedTaskName)) {
            throw new ParseException(Task.NAME_MESSAGE_CONSTRAINTS);
        }
        return trimmedTaskName;
    }

    /**
     * Parses searching keywords for task name.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keywords} is invalid.
     */
    public static String[] parseTaskNameKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(TaskFilterCommand.MESSAGE_BLANK_KEYWORDS);
        }
        String[] taskNameKeywords = trimmedKeywords.split("\\s+");
        return taskNameKeywords;
    }

    /**
     * Parses a {@code String progress} into a {@code Double}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code progress} is invalid.
     */
    public static Double parseTaskProgress(String progress) throws ParseException {
        requireNonNull(progress);
        String trimmedTaskProgress = progress.trim();
        if (!Task.isValidProgress(trimmedTaskProgress)) {
            throw new ParseException(Task.PROGRESS_MESSAGE_CONSTRAINTS);
        }
        try {
            return Double.parseDouble(progress);
        } catch (NumberFormatException e) {
            throw new ParseException(Task.PROGRESS_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String doneStatus} into a {@code Boolean}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code doneStatus} is invalid.
     */
    public static Boolean parseDoneStatus(String doneStatus) throws ParseException {
        requireNonNull(doneStatus);
        String trimmedDoneStatus = doneStatus.trim();
        if (!Task.isValidIsDone(trimmedDoneStatus)) {
            throw new ParseException(Task.IS_DONE_MESSAGE_CONSTRAINTS);
        }
        return Boolean.parseBoolean(doneStatus);
    }

}
