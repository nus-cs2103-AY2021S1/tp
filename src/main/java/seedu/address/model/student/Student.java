package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.question.Question;

/**
 * Represents a Student in Reeve.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    private final Name name;
    private final Phone phone;
    private final School school;
    private final Year year;
    private final Admin admin;
    private final List<Question> questions = new ArrayList<>();

    /**
     *  name, phone, school, year, must be present and not null.
     */
    public Student(Name name, Phone phone, School school, Year year,
                   Admin admin, List<Question> questions) {
        requireAllNonNull(name, phone, school, year, admin);
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
        this.admin = admin;
        this.questions.addAll(questions);
    }

    private Student(Student copy) {
        requireNonNull(copy);
        this.name = copy.name;
        this.phone = copy.phone;
        this.school = copy.school;
        this.year = copy.year;;
        this.admin = copy.admin;
        this.questions.addAll(copy.questions);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public School getSchool() {
        return school;
    }

    public Year getYear() {
        return year;
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<Question> getQuestions() {
        return List.copyOf(questions);
    }

    public List<AdditionalDetail> getDetails() {
        return admin.getDetails();
    }

    /**
     * Returns true if both student of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getSchool().equals(getSchool())
                && otherStudent.getYear().equals(getYear());
    }

    public boolean containsQuestion(Question question) {
        return questions.stream().anyMatch(question::isSameQuestion);
    }

    /***
     * Creates a new student object with a newly added question at the end of the questions list.
     * This operation preserves the immutability of the Student class.
     */
    public Student addQuestion(Question question) {
        assert !containsQuestion(question);

        requireNonNull(question);
        Student replacement = new Student(this);
        replacement.questions.add(question);
        return replacement;
    }

    /**
     * Creates a new student object with a modified question replacing the previous question in the list.
     * This operation preserves the immutability of the Student class.
     */
    public Student setQuestion(Question target, Question newQuestion) {
        assert questions.contains(target);
        requireAllNonNull(target, newQuestion);

        Student replacement = new Student(this);
        int location = replacement.questions.indexOf(target);
        replacement.questions.set(location, newQuestion);
        return replacement;
    }

    /**
     * Creates a new student object with the specified question removed from the list.
     * This operation preserves the immutability of the Student class.
     */
    public Student deleteQuestion(Question target) {
        assert questions.contains(target);
        requireNonNull(target);

        Student replacement = new Student(this);
        replacement.questions.remove(target);
        return replacement;
    }

    /**
     * Returns true if both student have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getSchool().equals(getSchool())
                && otherStudent.getYear().equals(getYear())
                && otherStudent.getAdmin().equals(getAdmin())
                && otherStudent.getQuestions().equals(getQuestions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, school, year, questions, admin);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nSchool: ")
                .append(getSchool())
                .append("\nYear: ")
                .append(getYear())
                .append(getAdmin());

        if (!questions.isEmpty()) {
            builder.append("\nQuestions:\n");
            String questionList = questions.stream()
                    .map(Question::toString)
                    .collect(Collectors.joining("\n"));
            builder.append(questionList);
        }

        return builder.toString();
    }

}
