package seedu.address.model.exercise;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Template {
    private String name;
    private String description;
    private Integer calories;

    /**
     * Template constructor
     *
     * @param name        name of the template
     * @param description description of the template
     * @param calories    calories of the template
     */
    public Template(String name, String description, Integer calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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
                + ", description:'" + description
                + '\'' + ", calories:"
                + calories + '}';
    }

    public String parseToArgument() {
        return "n/" + getName() + " d/" + getDescription() + " c/" + getCalories();
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
