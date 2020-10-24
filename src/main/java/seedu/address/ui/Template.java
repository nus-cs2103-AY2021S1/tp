package seedu.address.ui;

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
}
