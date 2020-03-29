package kuir;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kuir.recipe.Category;
import kuir.recipe.Ingredient;
import kuir.recipe.Recipe;
import kuir.recipe.RestrictedDiet;
import kuir.recipe.Unit;

public class AddRecipeController {
	@FXML
	public ComboBox<RestrictedDiet> diet;
	@FXML
	public ComboBox<Category> category;
	@FXML
	public ComboBox<Unit> ingredientUnit1;
	@FXML
	public TextField name;
	@FXML
	public TextField cuisine;
	@FXML
	public TextField yield;
	@FXML
	public Spinner<Integer> cookTime;
	@FXML
	public Spinner<Integer> prepTime;
	@FXML
	public TextArea description;
	@FXML
	public TextArea instructions;
	@FXML
	public VBox ingredients;
	@FXML
	public Button addButton;
	@FXML
	public Button saveButton;

	ObservableList<Recipe> recipeList;

	public AddRecipeController(ObservableList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}

	@FXML
	public void initialize() {
		diet.getItems().addAll(RestrictedDiet.values());
		category.getItems().addAll(Category.values());
		addIngredientField();
	}

	protected void addIngredientField() {
		SplitPane ingredientPane = new SplitPane();
		ingredientPane.setDividerPositions(0.10188087774294671, 0.30407523510971785);
		Spinner<Double> ingredientAmount = new Spinner<>(0.0, 2000, 0, 0.25);
		ComboBox<Unit> ingredientUnit = new ComboBox<>();
		ingredientUnit.setPrefWidth(150.0);
		ingredientUnit.getItems().addAll(Unit.values());
		TextField ingredientName = new TextField();
		ingredientPane.getItems().add(ingredientAmount);
		ingredientPane.getItems().add(ingredientUnit);
		ingredientPane.getItems().add(ingredientName);
		ingredients.getChildren().add(ingredientPane);
	}

	@FXML
	protected void addIngredientButton() {
		addIngredientField();
		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.sizeToScene();

	}

	@FXML
	protected void saveRecipe() {
		if (name.getText() == null || name.getText().trim().isEmpty()) {
			throwError("You need to at least fill in a name!");
		} else {
			recipeList.add(compileRecipe());
			Stage stage = (Stage) saveButton.getScene().getWindow();
			stage.close();
		}
	}

	private void throwError(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}

	private Recipe compileRecipe() {
		Recipe recipe = new Recipe(name.getText().trim());
		if (category.getValue() == null) {
			recipe.setRecipeCategory(Category.None);
		} else {
			recipe.setRecipeCategory(category.getValue());
		}

		if (cuisine.getText() != null || !cuisine.getText().trim().isEmpty()) {
			recipe.setRecipeCuisine(cuisine.getText());
		}

		if (diet.getValue() == null) {
			recipe.setSuitableForDiet(RestrictedDiet.None);
		} else {
			recipe.setSuitableForDiet(diet.getValue());
		}
		if (yield.getText() != null || !yield.getText().trim().isEmpty()) {
			recipe.setRecipeYield(yield.getText());
		}
		recipe.setPrepTime(prepTime.getValue());
		recipe.setCookTime(cookTime.getValue());
		recipe.setTotalTime(prepTime.getValue() + cookTime.getValue());

		List<SplitPane> rawIngredients = new ArrayList<>();
		ingredients.getChildren().forEach(node -> {
			if (node instanceof SplitPane) {
				rawIngredients.add((SplitPane) node);
			}
		});
		recipe.setRecipeIngredient(parseIngredients(rawIngredients));

		if (description.getText() != null || !description.getText().trim().isEmpty()) {
			recipe.setDescription(description.getText());
		}
		if (instructions.getText() != null || !instructions.getText().trim().isEmpty()) {
			recipe.setRecipeInstructions(instructions.getText());
		}
		return recipe;
	}

	private List<Ingredient> parseIngredients(List<SplitPane> rawIngredients) {
		List<Ingredient> processedIngredients = new ArrayList<>();
		for (SplitPane rawIngredient : rawIngredients) {
			Node nameNode = rawIngredient.getItems().get(2);
			String ingredientName = ((TextField) nameNode).getText();
			if (ingredientName != null && !ingredientName.trim().isEmpty()) {
				Ingredient ingredient = new Ingredient(ingredientName);
				Node unitNode = rawIngredient.getItems().get(1);
				Unit ingredientUnit = ((ComboBox<Unit>) unitNode).getValue();
				if (ingredientUnit != null) {
					ingredient.setUnit(ingredientUnit);
				}
				Node amountNode = rawIngredient.getItems().get(0);
				Double ingredientAmount = ((Spinner<Double>) amountNode).getValue();
				if (ingredientAmount > 0) {
					ingredient.setAmount(ingredientAmount);
				}
				processedIngredients.add(ingredient);
			}
		}
		return processedIngredients;
	}
}
