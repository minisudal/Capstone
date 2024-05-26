package DTO;

public class menuDTO {
    String menu;
    String ingredient;
    String recipe;

    public menuDTO(String menu, String ingredient, String recipe) {
        this.menu = menu;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
