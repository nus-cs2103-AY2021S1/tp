package tp.acecs2103.storage;

import java.time.LocalDate;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.commons.exceptions.IllegalValueException;
import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Deadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.OfficialDeadline;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.TP;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.TaskCategory;
import tp.acecs2103.model.task.Topic;
import tp.acecs2103.model.task.WeekNumber;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedTask.class);

    private String index;
    private String weekNumber;
    private String description;
    private String officialDeadline;
    private String customizedDeadline;
    private String remark;
    private String category;
    private boolean isCustomized;
    private boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("index") String index,
                           @JsonProperty("weekNumber") String weekNumber,
                           @JsonProperty("description") String description,
                           @JsonProperty("officialDeadline") String officialDeadline,
                           @JsonProperty("customizedDeadline") String customizedDeadline,
                           @JsonProperty("remark") String remark,
                           @JsonProperty("category") String category,
                           @JsonProperty("customized") boolean isCustomized,
                           @JsonProperty("doneStatus") boolean isDone) {
        this.index = index;
        this.weekNumber = weekNumber;
        this.description = description;
        this.officialDeadline = officialDeadline;
        this.customizedDeadline = customizedDeadline;
        this.remark = remark;
        this.category = category;
        this.isCustomized = isCustomized;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task task) {
        index = task.getIndex().value;
        weekNumber = task.getWeekNumber().value;
        description = task.getDescription().value;
        if (task.getOfficialDeadline() != null) {
            officialDeadline = task.getOfficialDeadline().value;
        } else {
            officialDeadline = null;
        }
        if (task.getCustomizedDeadline() != null) {
            customizedDeadline = task.getCustomizedDeadline().value;
        } else {
            customizedDeadline = null;
        }
        if (task.getRemark() != null) {
            remark = task.getRemark().value;
        } else {
            remark = null;
        }

        category = TaskCategory.categoryToString(task.getCategory());

        isCustomized = task.isCustomized();

        isDone = task.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {

        if (index == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "index"));
        }
        if (!Index.isValidIndex(index)) {
            throw new IllegalValueException(Index.MESSAGE_CONSTRAINTS);
        }
        Index modelIndex = new Index(index);

        if (weekNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "weekNumber"));
        }
        if (!WeekNumber.isValidWeekNumber(weekNumber)) {
            throw new IllegalValueException(WeekNumber.MESSAGE_CONSTRAINTS);
        }
        WeekNumber modelWeekNumber = new WeekNumber(weekNumber);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        Description modelDescription = new Description(description);


        OfficialDeadline modelOfficialDeadline;
        if (officialDeadline == null) {
            modelOfficialDeadline = null;
        } else {
            if (!Deadline.isValidDeadline(officialDeadline)) {
                throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
            }
            modelOfficialDeadline = new OfficialDeadline(officialDeadline, LocalDate.parse(officialDeadline));
        }

        CustomizedDeadline modelCustomizedDeadline;
        if (customizedDeadline == null) {
            modelCustomizedDeadline = null;
        } else {
            if (!Deadline.isValidDeadline(customizedDeadline)) {
                throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
            }
            modelCustomizedDeadline = new CustomizedDeadline(customizedDeadline, LocalDate.parse(customizedDeadline));
        }

        Remark modelRemark = new Remark(remark);

        if (TaskCategory.isStringAdmin(category)) {
            return new Admin(modelIndex, modelWeekNumber, modelDescription,
                    modelOfficialDeadline, modelCustomizedDeadline,
                    modelRemark, isCustomized, isDone);
        }

        if (TaskCategory.isStringTopic(category)) {
            return new Topic(modelIndex, modelWeekNumber, modelDescription,
                    modelOfficialDeadline, modelCustomizedDeadline,
                    modelRemark, isCustomized, isDone);
        }

        if (TaskCategory.isStringIP(category)) {
            return new IP(modelIndex, modelWeekNumber, modelDescription,
                    modelOfficialDeadline, modelCustomizedDeadline,
                    modelRemark, isCustomized, isDone);
        }

        if (TaskCategory.isStringTP(category)) {
            return new TP(modelIndex, modelWeekNumber, modelDescription,
                    modelOfficialDeadline, modelCustomizedDeadline,
                    modelRemark, isCustomized, isDone);
        }

        return new Task(modelIndex, modelWeekNumber, modelDescription,
                modelOfficialDeadline, modelCustomizedDeadline,
                modelRemark, isCustomized, isDone);
    }
}

