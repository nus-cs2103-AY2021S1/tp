package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Template {
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
        return "Template{"
                + "name:'" + name + '\''
                + '\'' + ", calories:"
                + calories + '}';
    }

    public String parseToArgument() {
        return "n/" + getName() + " c/" + getCalories();
    }

    /**
     * Write the template list to the file
     * @param lists template list
     * @throws IOException
     */
    public static void writeToFile(List<Template> lists) throws IOException {
        FileWriter fw = new FileWriter("data/template.txt");
        String textToAdd = "";

        for (int i = 0; i < lists.size(); i++) {
            Template template = lists.get(i);
            textToAdd = textToAdd + template.parseToArgument() + System.lineSeparator();
        }

        fw.write(textToAdd);
        fw.close();
    }

    @Override
    public boolean equals(Object toCreate) {
        Template template = (Template) toCreate;
        return this.getName().equals(template.getName());
    }
}
