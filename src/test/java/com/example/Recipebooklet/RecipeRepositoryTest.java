package com.example.Recipebooklet;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Recipebooklet.domain.Category;
import com.example.Recipebooklet.domain.Recipe;
import com.example.Recipebooklet.domain.RecipeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository repository;
	
	//Finding out if it's possible to search a recipe by its desc.
	@Test
	public void findByDescriptionShouldReturnRecipe() {
		List<Recipe> recipes = repository.findByDescription("Redwine-sauce");
		
		assertThat(recipes).hasSize(1);
		assertThat(recipes.get(0).getDescription()).isEqualTo("Redwine-sauce");
		
	}
	//Testing is it possible to create new recipe entity correctly.
	@Test
	public void createNewBook() {
		Recipe recipe = new Recipe((long) 2, "Test Recipe", "Test", 20, "Test", new Category("Asian"));
		repository.save(recipe);
		assertThat(recipe.getId()).isNotNull();
	}
}
