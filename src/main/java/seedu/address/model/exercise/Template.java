package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Template {
    public static final String filePath = "data/template.txt";

    private String name;
    private Integer calories;
    private Set<MuscleTag> muscleTags = new HashSet<>();
    private Set<ExerciseTag> tags = new HashSet<>();


    /**
     * Template constructor
     *
     * All fields must be non-null
     *
     * @param name        name of the template
     * @param calories    calories of the template
     */
    public Template(String name, Integer calories) {
        requireAllNonNull(name, calories);
        this.name = name;
        this.calories = calories;
    }

    public Set<MuscleTag> getMuscleTags() {
        return muscleTags;
    }

    public void setMuscleTags(Set<MuscleTag> muscleTags) {
        this.muscleTags = muscleTags;
    }

    public void setTags(Set<ExerciseTag> tags) {
        this.tags = tags;
    }

    public Template(String name, Integer calories, Set<MuscleTag> muscleTags, Set<ExerciseTag> tags) {
        this.name = name;
        this.calories = calories;
        this.muscleTags = muscleTags;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ExerciseTag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Object[] muscleTagToArray() {
        return muscleTags.toArray();
    }

    public Object[] tagToArray() {
        return tags.toArray();
    }

    public String muscleTagToString() {
        Object[] array = muscleTagToArray();
        String muscleTag = "";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                muscleTag = muscleTag + array[i];
            } else {
                muscleTag = muscleTag + array[i] + ", ";
            }
        }
        return muscleTag;
    }

    public String tagToString() {
        Object[] array = tagToArray();
        String tag = "";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                tag = tag + array[i];
            } else {
                tag = tag + array[i] + ", ";
            }
        }
        return tag;
    }

    public String parseMuscleTag() {
        Object[] array = muscleTagToArray();
        String muscleTag = "";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                muscleTag = muscleTag + "m/" + array[i];
            } else {
                muscleTag = muscleTag + "m/" + array[i] + " ";
            }
        }
        muscleTag = muscleTag.replaceAll("\\[", "").replaceAll("\\]", "");

        return muscleTag;
    }

    public String parseTag() {
        Object[] array = tagToArray();
        String tag = "";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                tag = tag + "t/" + array[i];
            } else {
                tag = tag + "t/" + array[i] + " ";
            }
        }
        tag = tag.replaceAll("\\[", "").replaceAll("\\]","");

        return tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    /**
     * toString method of the template class
     *
     * @return the string representation of the template
     */
    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", muscleTags=" + muscleTagToString() +
                ", tags=" + tagToString() +
                '}';
    }

    public String parseToArgument() {
        if (muscleTags.isEmpty()) {
            if (tags.isEmpty()) {
                return "n/" + getName() + " c/" + getCalories();
            } else {
                return "n/" + getName() + " c/" + getCalories() + " " + parseTag();
            }
        } else {
            if (tags.isEmpty()) {
                return "n/" + getName() + " c/" + getCalories() + " " + parseMuscleTag();
            } else {
                return "n/" + getName() + " c/" + getCalories() + " " + parseMuscleTag() + " "
                        + parseTag();
            }
        }
    }

    /**
     * Write the template list to the file
     * @param lists template list
     * @throws IOException
     */
    public static void writeToFile(List<Template> lists) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";

        for (int i = 0; i < lists.size(); i++) {
            Template template = lists.get(i);
            textToAdd = textToAdd + template.parseToArgument() + System.lineSeparator();
        }

        fw.write(textToAdd);
        fw.close();
    }

    public static String getFilePath() {
        return filePath;
    }

    @Override
    public boolean equals(Object toCreate) {
        Template template = (Template) toCreate;
        return this.getName().equals(template.getName());
    }
}
