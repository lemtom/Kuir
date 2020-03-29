package kuir.recipe;

import java.time.LocalDate;
import java.util.List;

public class Recipe {
	
	public Recipe(String name) {
		setName(name);
		setCookTime(0);
		setPrepTime(0);
		setTotalTime(0);
	}
	/*Specific to Recipe*/
	Integer cookTime;
	String cookingMethod;
	//NutritionInformation nutrition
	Category recipeCategory;
	String recipeCuisine;
	List<Ingredient> recipeIngredient;
	String recipeInstructions;
	String recipeYield;
	RestrictedDiet suitableForDiet;
	
	/*Specific to HowTo*/
	Integer prepTime;
	Integer totalTime;
	
	/*Specific to CreativeWork*/
	String author;
	LocalDate datePublished;
	
	/*Specific to Thing*/
	String name;
	String description;
	String image;
	
	public Integer getCookTime() {
		return cookTime;
	}
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
	public String getCookingMethod() {
		return cookingMethod;
	}
	public void setCookingMethod(String cookingMethod) {
		this.cookingMethod = cookingMethod;
	}
	public Category getRecipeCategory() {
		return recipeCategory;
	}
	public void setRecipeCategory(Category recipeCategory) {
		this.recipeCategory = recipeCategory;
	}
	public String getRecipeCuisine() {
		return recipeCuisine;
	}
	public void setRecipeCuisine(String recipeCuisine) {
		this.recipeCuisine = recipeCuisine;
	}
	public List<Ingredient> getRecipeIngredient() {
		return recipeIngredient;
	}
	public void setRecipeIngredient(List<Ingredient> recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}
	public String getRecipeInstructions() {
		return recipeInstructions;
	}
	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}
	public String getRecipeYield() {
		return recipeYield;
	}
	public void setRecipeYield(String recipeYield) {
		this.recipeYield = recipeYield;
	}
	public RestrictedDiet getSuitableForDiet() {
		return suitableForDiet;
	}
	public void setSuitableForDiet(RestrictedDiet suitableForDiet) {
		this.suitableForDiet = suitableForDiet;
	}
	public Integer getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
