package seedu.address.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;


public class TutorialGroup {
    private final String id;
    //    private Module module;
    private HashMap<StudentId, Student> studentList;
    private LocalTime startTime;
    private LocalTime endTime;
    private int durationInHours;
    //  private ArrayList<Task> tutorialGroupTasks;

    /*
    public TutorialGroup(String id, Module module) {
        this.id = id;
        this.module = module;
        this.studentList = new HashMap<>();
    }
    */

    /**
     * Constructor for Tutorial Group
     * @param id
     */
    public TutorialGroup(String id) {
        this.id = id;
        this.studentList = new HashMap<>();
    }

    //GETTERS

    public String getId() {
        return this.id;
    }

    //    public Module getModule() { return this.module; }
    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDurationInHours() {
        return this.durationInHours;
    }

    /*
    public ArrayList<Student> getStudentList() {
        ArrayList<Student> returnList = new ArrayList<Student>();
        for (Student student: studentList.values()) {
            returnList.add(Student.clone(student));
        }
        return returnList;
    }

    public ArrayList<Task> getTutorialGroupTasks() {
        ArrayList<Task> returnList = new ArrayList<Task>();
        returnList.addAll(this.tutorialGroupTasks);
        return returnList;
    }
    */

    //SETTERS
    public void setLessonTime(LocalTime startTime, LocalTime endTime, int durationInHours) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInHours = durationInHours;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = new HashMap<StudentId, Student>(); //Refresh the student list
        for (Student student : studentList) {
            this.studentList.put(student.getStudentId(), student);
        }
    }

    //ADD

    /**
     * By right this method should just take in a studentId,
     * then we search the Library of students for the correct instance
     * But the Library has not been implemented yet so this will do for now
     *
     * @param student
     */
    public void addStudent(Student student) {
        this.studentList.put(student.getStudentId(), student);
    }
    //    public void addTask(Task task) {this.tutorialGroupTasks.add(task);}

    //DELETE
    public void deleteStudent(Student student) {
        this.studentList.remove(student.getStudentId());
    }
    //    public void deleteTask(Task task) {this.tutorialGroupTasks.remove(Task);}
    //    public void markTaskComplete(Task task) { this.tutorialGroupTasks.get(task).markComplete();}
}
