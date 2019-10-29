package com.example.Recipebooklet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Recipebooklet.domain.Category;
import com.example.Recipebooklet.domain.CategoryRepository;
import com.example.Recipebooklet.domain.Recipe;
import com.example.Recipebooklet.domain.RecipeRepository;
import com.example.Recipebooklet.domain.User;
import com.example.Recipebooklet.domain.UserRepository;

@SpringBootApplication
public class RecipebookletApplication {
	private static final Logger log = LoggerFactory.getLogger(RecipebookletApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RecipebookletApplication.class, args);
		
	}
	@Bean
	public CommandLineRunner runner(RecipeRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			//Logging some key information
			log.info("Saving recipes to database");
			//Adding some recipes to database
			crepository.save(new Category("Starter"));
			crepository.save(new Category("Main Course"));
			crepository.save(new Category("Dessert"));
			crepository.save(new Category("Sauce"));
			crepository.save(new Category("Side Dish"));
			
			repository.save(new Recipe(0, "Redwine-sauce", "Just test text", 90,
			"Red wine 1,5l, Redwine vinegar 1dl, Sugar 100g, Onion 1pcs, Carrot 1pcs, Celery 1pcs, Garlic 6 cloves, Thyme, Rosemary", crepository.findByName("Sauce").get(0)));
			
			User user1 = new User((long)1, "Ilpo", "$2a$10$bqVW4Ikr3OkoVpbW4/D0ZOFZPBgMOSqFSiyOEY/fQN3huTA.ChszC", "ADMIN");
			User user2 = new User((long)2, "Guest", "$2a$10$r7xISJMxa7E/oZfeRluwXuoJwTxElCG4qrXV5NvR6URNXB9I25Fc6", "USER");
			
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Fetch the recipes to console");
			for (Recipe recipe : repository.findAll()) {
				log.info(recipe.toString());
			}
		};
	}
}
