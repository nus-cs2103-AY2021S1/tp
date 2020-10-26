package seedu.address.model.exercise;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Template {
    public String name;
    public String description;
    public Integer calories;

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

    @Override
    public String toString() {
        return "Template{" +
                "name:'" + name + '\'' +
                ", description:'" + description + '\'' +
                ", calories:" + calories +
                '}';
    }

    public String parseToArgument() {
        return "n/" + getName() + " d/" + getDescription() + " c/" + getCalories();
    }


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
//        return toCreate == this // short circuit if same object
//                || (this.getName().equals(template.getName()) &&
//                this.getDescription().equals(template.getDescription()) &&
//                this.getCalories().equals(template.getCalories())); // state check
        return this.getName().equals(template.getName());
    }
}
