package seedu.address.model.food;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Food {

    // Identity field names
    private String name;
    private Protein protein;
    private Carbohydrate carbs;
    private Fat fat;

    // Generic Constructor

    public Food(String name, Protein protein, Carbohydrate carbs, Fat fat) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    // Constructor for convenience

    public Food(String name, int proteinAmount, int carbsAmount, int fatAmount) {
        this(name,new Protein(proteinAmount),new Carbohydrate(carbsAmount),new Fat(fatAmount));
    }
    // getters : make when needed

    // isSameFood : make when needed

    // Displays
    // name + PCF details + total calories


    @Override
    public String toString() {
        return "Food:"+this.name +
            ", protein=" + protein.getAmount() +
            ", carbs=" + carbs.getAmount() +
            ", fat=" + fat.getAmount();
    }
    
}
