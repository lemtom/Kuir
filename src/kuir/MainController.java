package kuir;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import kuir.recipe.Recipe;

public class MainController {

	@FXML
	public TableView<Recipe> recipeTable;

	@FXML
	ObservableList<Recipe> recipeList;

	@FXML
	public void initialize() {
		recipeList = FXCollections.observableArrayList();
		recipeTable.setItems(recipeList);

		recipeTable.setRowFactory(tv -> {
			TableRow<Recipe> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Recipe rowData = row.getItem();
					//TODO: Implement viewing and editing
					FXMLLoader loader = new FXMLLoader(getClass().getResource("RecipeViewer.fxml"));
					Map<Class, Callable<?>> creators = new HashMap<>();
					creators.put(RecipeViewerController.class, new Callable<RecipeViewerController>() {
						@Override
						public RecipeViewerController call() throws Exception {
							return new RecipeViewerController(rowData);
						}
					});

					loader.setControllerFactory(getFactory(creators));

					Parent part;
					try {
						part = loader.load();

						Stage stage = new Stage();
						Scene scene = new Scene(part);
						stage.setScene(scene);
						stage.setTitle("View recipe");
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			return row;
		});
	}

	private Callback<Class<?>, Object> getFactory(Map<Class, Callable<?>> creators) {
		return new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> param) {
				Callable<?> callable = creators.get(param);
				if (callable == null) {
					try {
						return param.newInstance();
					} catch (InstantiationException | IllegalAccessException ex) {
						throw new IllegalStateException(ex);
					}
				} else {
					try {
						return callable.call();
					} catch (Exception ex) {
						throw new IllegalStateException(ex);
					}
				}
			}
		};
	}

	@FXML
	protected void addRecipe() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRecipe.fxml"));

		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(AddRecipeController.class, new Callable<AddRecipeController>() {
			@Override
			public AddRecipeController call() throws Exception {
				return new AddRecipeController(recipeList);
			}
		});

		loader.setControllerFactory(getFactory(creators));

		Parent part;
		try {
			part = loader.load();

			Stage stage = new Stage();
			Scene scene = new Scene(part);
			stage.setScene(scene);
			stage.setTitle("Add a recipe");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
