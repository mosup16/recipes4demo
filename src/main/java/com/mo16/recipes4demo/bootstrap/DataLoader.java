package com.mo16.recipes4demo.bootstrap;

import com.mo16.recipes4demo.model.*;
import com.mo16.recipes4demo.model.enums.Difficulty;
import com.mo16.recipes4demo.repositoris.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Data
public class DataLoader implements CommandLineRunner {

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private NotesRepository notesRepository;
    private IngredientRepository ingredientRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                      NotesRepository notesRepository, IngredientRepository ingredientRepository,
                      CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.notesRepository = notesRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      //  dropData();
        log.info("Loading BootStrap Data .........");
        loadData();
        log.info("Data has been loaded successfully");
    }

    private void dropData() {
        recipeRepository.deleteAll();
    }

    private void loadData() {
        Optional<UnitOfMeasure> cupUoMOptional = unitOfMeasureRepository.findByName("Cup");
        if (!cupUoMOptional.isPresent()) throw new RuntimeException(" -> Expected UoM 'cup' is missing ");

        Optional<UnitOfMeasure> OunceUoMOptional = unitOfMeasureRepository.findByName("Ounce");
        if (!OunceUoMOptional.isPresent()) throw new RuntimeException(" -> Expected UoM 'Ounce' is missing ");

        Optional<UnitOfMeasure> SpoonUoMOptional = unitOfMeasureRepository.findByName("Spoon");
        if (!SpoonUoMOptional.isPresent()) throw new RuntimeException(" -> Expected UoM 'Spoon' is missing ");

        Optional<UnitOfMeasure> TeaSpoonUoMOptional = unitOfMeasureRepository.findByName("TeaSpoon");
        if (!TeaSpoonUoMOptional.isPresent()) throw new RuntimeException(" -> Expected UoM 'TeaSpoon' is missing ");

        Optional<UnitOfMeasure> PintUoMOptional = unitOfMeasureRepository.findByName("Pint");
        if (!PintUoMOptional.isPresent()) throw new RuntimeException(" -> Expected UoM 'Pint' is missing ");

        Optional<UnitOfMeasure> DashUoMOptional = unitOfMeasureRepository.findByName("Dash");
        if (!DashUoMOptional.isPresent()) throw new RuntimeException(" -> Expected UoM 'Dash' is missing ");

        UnitOfMeasure cupUom = cupUoMOptional.get();
        UnitOfMeasure ounceUoM = OunceUoMOptional.get();
        UnitOfMeasure spoon = SpoonUoMOptional.get();
        UnitOfMeasure teaSpoon = TeaSpoonUoMOptional.get();
        UnitOfMeasure pintUom = PintUoMOptional.get();
        UnitOfMeasure dashUom = DashUoMOptional.get();


        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        if (!americanOptional.isPresent()) throw new RuntimeException(" -> Expected Category 'american' is missing ");

        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanOptional.isPresent()) throw new RuntimeException(" -> Expected Category 'Mexican' is missing ");

        Category american = americanOptional.get();
        Category mexican = mexicanOptional.get();


        Recipe guacamole = new Recipe();
        guacamole.setDescription("Guacamole");
        guacamole.setPrepTime(10L);
        guacamole.setCookTime(1L);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.getCategories().add(american);
        guacamole.setServings(4L);
        guacamole.setSource("Simply Recipes");

        guacamole.getIngredients().add(new Ingredient("recipe avocado", BigDecimal.valueOf(1L), guacamole, cupUom));
        guacamole.getIngredients().add(new Ingredient("kosher salt", BigDecimal.valueOf(2L), guacamole, teaSpoon));
        guacamole.getIngredients().add(new Ingredient("fresh limo juice", BigDecimal.valueOf(1L), guacamole, spoon));
        guacamole.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2L), guacamole, cupUom));
        guacamole.getIngredients().add(new Ingredient("cilantro", BigDecimal.valueOf(2L), guacamole, teaSpoon));


        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipe(guacamole);
        guacamoleNotes.setRecipeNotes("\"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\\n\" +\n" +
                "                \n" +
                "                \"Spicy Grilled Chicken Tacos\\n\" +\n" +
                "                \n" +
                "                \"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\\n\" +\n" );
        guacamole.setNotes(guacamoleNotes);
        guacamole.setDirection("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\\n\" +\n" +
                "                \n" +
                "                \n" +
                "                \n" +
                "                \"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\\n\" +\n" +
                "                \n" +
                "                \n" +
                "                \n" +
                "                \"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\\n\" +\n" +
                "                \n" +
                "                \"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\\n\" +\n" +
                "                \n" +
                "                \"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\\n\" +\n" +
                "                \n" +
                "                \"4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        recipeRepository.save(guacamole);

        Recipe taco = new Recipe();
        taco.setDescription("taco");
        taco.getCategories().add(mexican);
        taco.setDifficulty(Difficulty.MODERATE);
        taco.setPrepTime(20L);
        taco.setCookTime(9L);
        taco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        taco.setServings(4L);
        taco.setSource("Simply Recipes");
        taco.setDirection("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");


        Notes tacoNotes = new Notes();
        tacoNotes.setRecipe(taco);
        tacoNotes.setRecipeNotes("\"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\\n\" +\n" +
                "                \n" +
                "                \"Spicy Grilled Chicken Tacos\\n\" +\n" +
                "                \n" +
                "                \"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\\n\" +\n" );
        taco.setNotes(tacoNotes);

        taco.getIngredients().add(new Ingredient("dried orange", BigDecimal.valueOf(1L), taco, cupUom));
        taco.getIngredients().add(new Ingredient("salt", BigDecimal.valueOf(2L), taco, teaSpoon));
        taco.getIngredients().add(new Ingredient("sugar", BigDecimal.valueOf(1L), taco, spoon));
        taco.getIngredients().add(new Ingredient("olive oil ", BigDecimal.valueOf(2L), taco, cupUom));
        taco.getIngredients().add(new Ingredient("medium riped avocado", BigDecimal.valueOf(2L), taco, teaSpoon));

        recipeRepository.save(taco);

    }
}
