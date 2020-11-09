package tp.acecs2103.testutil;

import java.time.LocalDate;

import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.OfficialDeadline;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.TP;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.Topic;
import tp.acecs2103.model.task.WeekNumber;


/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    private static final Index DEFAULT_INDEX = new Index("0101");
    private static final WeekNumber DEFAULT_WEEKNUMBER = new WeekNumber("1");
    private static final Description DEFAULT_DESCRIPTION = new Description("Description");
    private static final OfficialDeadline DEFAULT_OFFICIALDEADLINE = new OfficialDeadline("2020-10-10",
            LocalDate.of(2020, 10, 10));
    private static final CustomizedDeadline DEFAULT_CUSTOMIZEDDEADLINE = new CustomizedDeadline("2020-10-10",
            LocalDate.of(2020, 10, 10));
    private static final Remark DEFAULT_REMARK = new Remark("remark");
    private static final boolean DEFAULT_ISCUSTOMIZED = true;
    private static final boolean DEFAULT_ISDONE = false;

    private Index index;
    private WeekNumber weekNumber;
    private Description description;
    private OfficialDeadline officialDeadline;
    private CustomizedDeadline customizedDeadline;
    private Remark remark;
    private boolean isCustomized;
    private boolean isDone;



    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public TaskBuilder() {
        index = DEFAULT_INDEX;
        weekNumber = DEFAULT_WEEKNUMBER;
        description = DEFAULT_DESCRIPTION;
        officialDeadline = DEFAULT_OFFICIALDEADLINE;
        customizedDeadline = DEFAULT_CUSTOMIZEDDEADLINE;
        remark = DEFAULT_REMARK;
        isCustomized = DEFAULT_ISCUSTOMIZED;
        isDone = DEFAULT_ISDONE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        index = taskToCopy.getIndex();
        weekNumber = taskToCopy.getWeekNumber();
        description = taskToCopy.getDescription();
        officialDeadline = taskToCopy.getOfficialDeadline();
        customizedDeadline = taskToCopy.getCustomizedDeadline();
        remark = taskToCopy.getRemark();
        isCustomized = taskToCopy.isCustomized();
        isDone = taskToCopy.isDone();
    }

    /**
     * Sets the {@code Index} of the {@code Task} that we are building.
     */
    public TaskBuilder withIndex(String index) {
        this.index = new Index(index);
        return this;
    }

    /**
     * Sets the {@code weekNumber} of the {@code Task} that we are building.
     */
    public TaskBuilder withWeekNumber(String weekNumber) {
        this.weekNumber = new WeekNumber(weekNumber);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code Task} that we are building.
     */
    public TaskBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code officialDeadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withOfficialDeadline(String officialDeadline) {
        if (officialDeadline == null) {
            this.officialDeadline = null;
        } else {
            this.officialDeadline = new OfficialDeadline(officialDeadline, LocalDate.parse(officialDeadline));
        }
        return this;
    }

    /**
     * Sets the {@code customizedDeadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withCustomizedDeadline(String customizedDeadline) {
        if (customizedDeadline == null) {
            this.customizedDeadline = null;
        } else {
            this.customizedDeadline = new CustomizedDeadline(customizedDeadline, LocalDate.parse(customizedDeadline));
        }
        return this;
    }

    /**
     * Sets the {@code isCustomized} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsCustomized(boolean isCustomized) {
        this.isCustomized = isCustomized;
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Builds a {@code Admin} task.
     */
    public Admin buildAdmin() {
        return new Admin(index, weekNumber, description,
                officialDeadline, customizedDeadline, remark, isCustomized, isDone);
    }

    /**
     * Builds a {@code Topic} task.
     */
    public Topic buildTopic() {
        return new Topic(index, weekNumber, description,
                officialDeadline, customizedDeadline, remark, isCustomized, isDone);
    }

    /**
     * Builds a {@code Ip} task.
     */
    public IP buildIp() {
        return new IP(index, weekNumber, description,
                officialDeadline, customizedDeadline, remark, isCustomized, isDone);
    }

    /**
     * Builds a {@code Tp} task.
     */
    public TP buildTp() {
        return new TP(index, weekNumber, description,
                officialDeadline, customizedDeadline, remark, isCustomized, isDone);
    }

    /**
     * Builds a {@code Default IP} task.
     */
    public IP build() {
        return new IP(index, weekNumber, description,
                officialDeadline, customizedDeadline, remark, !isCustomized, isDone);
    }


}
