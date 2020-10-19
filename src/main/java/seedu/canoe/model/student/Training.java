package seedu.canoe.model.student;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import seedu.canoe.model.student.exceptions.DuplicateStudentException;


public class Training {

    public static final String MESSAGE_CONSTRAINTS = "Must be a valid date and time.";
    private final LocalDateTime dateTime;

    //Used a TreeSet with Comparator to sort Students by increasing Unique ID values.
    private final TreeSet<Student> students = new TreeSet<>(new StudentComparator());

    /**
     * Student Comparator class that implements the compare method to compare unique IDs.
     */
    class StudentComparator implements Comparator<Student> {
        public int compare(Student student1, Student student2) {
            return Integer.parseInt(student1.getId().value)
                    - Integer.parseInt(student2.getId().value);
        }
    }

    /**
     * Constructor with only LocalDateTime.
     *
     * @param dateTime of training
     */
    public Training(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Constructor with both LocalDateTime and a Set of Students.
     * Initialises Training with the specified set of students.
     *
     * @param dateTime of training
     * @param students set of students
     */
    public Training(LocalDateTime dateTime, Set<Student> students) {
        this.dateTime = dateTime;
        this.students.addAll(students);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    /**
     * Adds the specified student to the Training Session.
     * Also adds training's LocalDateTime to the student container
     *
     * @param student to be added
     */
    public void addStudent(Student student) {
        if (students.contains(student)) {
            throw new DuplicateStudentException();
        } else {
            this.students.add(student);
            student.addTraining(new Attend(getDateTime()));
        }
    }

    /**
     * Removes the specified student from the Training Session.
     * Also removes training's LocalDateTime from the student container
     *
     * @param student to be removed
     */
    public void removeStudent(Student student) {
        Set<Student> studentsCopy = new HashSet<>(students);
        for (Student studentCheck : studentsCopy) {
            if (student.getId().equals(studentCheck.getId())) {
                students.remove(studentCheck);
                removeDateTimeFromStudent(student);
            }
        }
    }

    /**
     * Removes the training's date time from the student.
     */
    public void removeDateTimeFromStudent(Student student) {
        student.removeTraining(new Attend(getDateTime()));
    }

    /**
     * Checks if specified student is already present inside of training list.
     *
     * @param student to be checked
     * @return true if student is present inside of training schedule.
     */
    public boolean hasStudent(Student student) {
        return getStudents().contains(student);
    }

    /**
     * Clears all students from the Training.
     * Also removes the training date from students' training schedules.
     */
    public void clearStudents() {
        for (Student student : students) {
            removeDateTimeFromStudent(student);
        }
        this.students.clear();
    }

    public boolean isSameTraining(Training training) {
        return dateTime.equals(training.getDateTime());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Training)) {
            return false;
        }

        Training otherTraining = (Training) other;
        return otherTraining.getDateTime().equals(getDateTime())
                && otherTraining.getStudents().equals(getStudents());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dateTime.hashCode();
        result = prime * result + students.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return dateTime.toString().substring(0, 10) + " " + dateTime.toString().substring(11);
    }
}
