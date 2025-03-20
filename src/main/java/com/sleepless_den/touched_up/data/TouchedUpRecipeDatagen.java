package com.sleepless_den.touched_up.data;

import com.sleepless_den.touched_up.registry.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;

import java.util.concurrent.*;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class TouchedUpRecipeDatagen extends VanillaRecipeProvider {

    public PackOutput pOutput;

    public TouchedUpRecipeDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
        this.pOutput = pOutput;
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderlookup) {

        shaped(RecipeCategory.MISC, TouchedUpItems.RADIAL_ENCHANTMENT_AMPLIFIER.get(), 1)
                .define('X', Items.CRYING_OBSIDIAN)
                .define('Y', Items.OBSIDIAN)
                .define('Z', Tags.Items.INGOTS_GOLD)
                .pattern("ZXZ")
                .pattern("XYX")
                .pattern("ZXZ")
                .unlockedBy("has_hallowed_gold", has(Items.CRYING_OBSIDIAN)).save(recipeOutput);
    }
}
