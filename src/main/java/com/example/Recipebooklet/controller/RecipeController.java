package com.example.Recipebooklet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Recipebooklet.domain.CategoryRepository;
import com.example.Recipebooklet.domain.Recipe;
import com.example.Recipebooklet.domain.RecipeRepository;

@Controller
public class RecipeController {

	@Autowired
	private RecipeRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	//Show all recipes
	@RequestMapping(value="/recipebooklet")
	public String recipeBooklet(Model model) {
		model.addAttribute("recipes", repository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return "recipebooklet";
	}
	//Show login-page
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	//RESTful service to get all recipes
	@RequestMapping(value="/recipes", method = RequestMethod.GET)
	public @ResponseBody List<Recipe> recipeBookletRest() {
		return(List<Recipe>)repository.findAll();
	}
	//RESTful service to get recipes using id
	@RequestMapping(value="/recipes/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id")Long id) {
		return repository.findById(id);
	}
	//Add new recipe using this code
	@RequestMapping(value="/add")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe()); //Creating new Recipe-entity
		model.addAttribute("categories", crepository.findAll()); //Searching for possible categories
		return "addRecipe";
	}
	//Save new recipe with this code
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("recipe") Recipe recipe) {
		repository.save(recipe);
		return "redirect:recipebooklet";
	}
	//Delete Recipe-entity
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteRecipe(@PathVariable("id")Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../recipebooklet";
	}
	//Edit Recipe-entity by using it's id as key
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", repository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editRecipe";
	}
}
