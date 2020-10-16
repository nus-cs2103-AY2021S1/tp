package tp.cap5buddy.modules;

import tp.cap5buddy.grades.FinalGrade;
import tp.cap5buddy.grades.GradeList;

/**
 * Represents the Module creation class.
 */
public class Module {
    private final String name;
    private final String zoomLink;
    private GradeList gradeList;
    private FinalGrade finalGrade;

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     */
    public Module(String name, String zoomLink) {
        this.name = name;
        this.zoomLink = zoomLink;
        this.gradeList = new GradeList(name);
        this.finalGrade = null;
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     */
    public Module(String name) {
        this.name = name;
        this.zoomLink = null;
        this.gradeList = new GradeList(name);
        this.finalGrade = null;
    }

    /**
     * Returns the module name.
     * @return String module name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the zoom link of the module.
     * @return String zoom link.
     */
    public String getLink() {
        return this.zoomLink;
    }

    /**
     * Adds the zoom link for this module.
     * @param zoomLink zoom link.
     * @return Module a new Module with the input zoom link.
     */
    public Module addZoomLink(String zoomLink) {
        return new Module(this.getName(), zoomLink);
    }

    /**
     * Returns the grades accumulated for this module.
     *
     * @return grade list attached to this module.
     */
    public GradeList getGradeList() {
        return gradeList;
    }

    /**
     * Returns the final grade achieved for this module.
     *
     * @return final grade achieved for this module.
     */
    public FinalGrade getFinalGrade() {
        return finalGrade;
    }

    /**
     * Sets the final grade for this module.
     *
     * @param finalGrade the grade to be set.
     */
    public void setFinalGrade(FinalGrade finalGrade) {
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return String.format("The zoom link for %s is %s", getName(), getLink());
    }
}
