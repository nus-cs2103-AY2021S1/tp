package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.LESSON_OVERLAP_CONSTRAINTS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CALENDAR_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.util.Overlap;

/**
 * Edits the details of an existing lesson in PlaNus lesson list.
 */
public class EditLessonCommand extends Command {

    public static final String COMMAND_WORD = "edit-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the lesson identified "
            + "by the index number used in the displayed lesson list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DAY + "DAY] "
            + "[" + PREFIX_START_TIME + "TIME] "
            + "[" + PREFIX_END_TIME + "TIME] "
            + "[" + PREFIX_START_DATE + "DATE] "
            + "[" + PREFIX_END_DATE + "DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "CS2103T Lecture "
            + PREFIX_DESCRIPTION + "Most exciting lecture in NUS! "
            + PREFIX_DAY + "Monday "
            + PREFIX_START_TIME + "19:00 "
            + PREFIX_END_TIME + "21:00 "
            + PREFIX_START_DATE + "08-08-2020 "
            + PREFIX_END_DATE + "15-12-2020 ";


    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * @param index of the lesson in the filtered lesson list to edit
     * @param editLessonDescriptor details to edit the lesson with
     */
    public EditLessonCommand(Index index, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(index);
        requireNonNull(editLessonDescriptor);

        this.index = index;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToEdit = lastShownList.get(index.getZeroBased());

        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.isSameLesson(editedLesson) && model.hasLesson(editedLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        // check for overlap with the lesson to be edited ignored
        if (Overlap.overlapWithOtherTimeSlots(model, lessonToEdit, editedLesson)) {
            throw new CommandException(LESSON_OVERLAP_CONSTRAINTS);
        }

        model.setLesson(lessonToEdit, editedLesson);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        model.updateFilteredCalendar(PREDICATE_SHOW_ALL_CALENDAR_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson));
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code lessonToEdit}
     * edited with {@code editLessonDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor) {
        assert lessonToEdit != null;

        Title updatedTitle = editLessonDescriptor.getTitle().orElse(lessonToEdit.getTitle());
        Description updatedDescription = editLessonDescriptor.getDescription().orElse(lessonToEdit.getDescription());
        DayOfWeek updatedDayOfWeek = editLessonDescriptor.getDayOfWeek().orElse(lessonToEdit.getDayOfWeek());
        LocalDate updatedStartDate = editLessonDescriptor.getStartDate().orElse(lessonToEdit.getStartDate());
        LocalDate updatedEndDate = editLessonDescriptor.getEndDate().orElse(lessonToEdit.getEndDate());
        LocalTime updatedStartTime = editLessonDescriptor.getStartTime().orElse(lessonToEdit.getStartTime());
        LocalTime updatedEndTime = editLessonDescriptor.getEndTime().orElse(lessonToEdit.getEndTime());
        Tag updatedTag = editLessonDescriptor.getTag().orElse(lessonToEdit.getTag());

        return new Lesson(updatedTitle, updatedTag, updatedDescription, updatedDayOfWeek,
                updatedStartTime, updatedEndTime, updatedStartDate, updatedEndDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLessonCommand)) {
            return false;
        }

        // state check
        EditLessonCommand e = (EditLessonCommand) other;
        return index.equals(e.index)
                && editLessonDescriptor.equals(e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private Title title;
        private Description description;
        private DayOfWeek dayOfWeek;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private Tag tag;

        public EditLessonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setDayOfWeek(toCopy.dayOfWeek);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setTag(toCopy.tag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, dayOfWeek,
                    startDate, endDate, startTime, endTime, tag);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public Optional<DayOfWeek> getDayOfWeek() {
            return Optional.ofNullable(dayOfWeek);
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public Optional<LocalDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public Optional<LocalDate> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LocalTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            // state check
            EditLessonDescriptor e = (EditLessonDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getDayOfWeek().equals(e.getDayOfWeek())
                    && getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate())
                    && getTag().equals(e.getTag());
        }
    }
}
