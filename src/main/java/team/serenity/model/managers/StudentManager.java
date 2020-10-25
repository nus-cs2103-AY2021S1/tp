package team.serenity.model.managers;

import static java.util.Objects.requireNonNull;
import static team.serenity.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import team.serenity.model.group.Group;
import team.serenity.model.group.Student;
import team.serenity.model.group.exceptions.GroupNotFoundException;
import team.serenity.model.util.UniqueList;

public class StudentManager implements ReadOnlyStudentManager{

    private final Map<Group, UniqueList<Student>> mapToListOfStudents;

    /**
     * Instantiates a new LessonManager
     */
    public StudentManager() {
        this.mapToListOfStudents = new HashMap<>();
    }

    /**
     * Creates a LessonManager using the Lesson in the {@code toBeCopied}
     */
    public StudentManager(ReadOnlyStudentManager toBeCopied) {
        this.mapToListOfStudents = new HashMap<>();
        resetData(toBeCopied);
    }

    // Methods that overrides the whole student map

    /**
     * Replaces the contents of the student map with {@code newStudentMap}.
     * {@code newStudentMap} must not contain duplicate students.
     */
    public void setStudents(Map<Group, UniqueList<Student>> newStudentMap) {
        this.mapToListOfStudents.clear();
        this.mapToListOfStudents.putAll(newStudentMap);
    }

    /**
     * Resets existing data of this {@code StudentManager} with {@code newData}
     */
    public void resetData(ReadOnlyStudentManager newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentMap());
    }

    @Override
    public Map<Group, UniqueList<Student>> getStudentMap() {
        return this.mapToListOfStudents;
    }

    //Student-level methods

    /**
     * Adds the specified {@code Student} to the specified {@code Group}.
     * @param group
     * @param student
     */
    public void addStudentToGroup(Group group, Student student) throws GroupNotFoundException{
        requireAllNonNull(group, student);
        Optional<UniqueList<Student>> studentsOptional = Optional.ofNullable(this.mapToListOfStudents.get(group));
        if (studentsOptional.isPresent()) {
            studentsOptional.get().add(student);
        } else {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Replaces listOfStudents from a particular {@code Group}
     * @param group Group of interest
     * @param students new list of students to replace with
     */
    public void addListOfStudentsToGroup(Group group, UniqueList<Student> students) {
        requireAllNonNull(group, students);
        this.mapToListOfStudents.put(group, students);
    }

    /**
     * Checks if the specified {@code Student} exists in the {@code Group}.
     * @param group
     * @param student
     * @return whether Student exists in the Group
     */
    public boolean checkIfStudentExistsInGroup(Group group, Student student) throws GroupNotFoundException {
        requireAllNonNull(group, student);
        Optional<UniqueList<Student>> studentsOptional = Optional.ofNullable(this.mapToListOfStudents.get(group));
        if (studentsOptional.isPresent()) {
            return studentsOptional.get().contains(student);
        } else {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Gets listOfStudents from a particular {@code Group}.
     * @param group Group to check for
     * @return All students from a particular group
     */
    public UniqueList<Student> getListOfStudentsFromGroup(Group group) throws GroupNotFoundException{
        requireNonNull(group);
        Optional<UniqueList<Student>> studentList = Optional.ofNullable(this.mapToListOfStudents.get(group));
        if (studentList.isPresent()) {
            return studentList.get();
        } else {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Replaces listOfStudents stored in {@code Group} with {@code newListOfStudents}.
     * @param group
     * @param newListOfStudents
     */
    public void setListOfStudentsToGroup(Group group, UniqueList<Student> newListOfStudents) {
        requireAllNonNull(group, newListOfStudents);
        this.mapToListOfStudents.put(group, newListOfStudents);
    }


    public void deleteLessonFromGroup(Group group, Student student) throws GroupNotFoundException {
        requireAllNonNull(group, student);
        Optional<UniqueList<Student>> studentList = Optional.ofNullable(this.mapToListOfStudents.get(group));
        if (studentList.isPresent()) {
            studentList.get().remove(student);
        } else {
            throw new GroupNotFoundException();
        }
    }

}
