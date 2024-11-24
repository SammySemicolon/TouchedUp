package com.sleepless_den.touched_up.data;

import com.sleepless_den.touched_up.TouchedUpMod;
import com.sleepless_den.touched_up.registry.TouchedUpBlocks;
import com.sleepless_den.touched_up.registry.TouchedUpItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.WallSignBlock;
import net.neoforged.neoforge.common.data.LanguageProvider;
import team.lodestar.lodestone.helpers.DataHelper;

import java.util.HashSet;

public class TouchedUpLangDatagen extends LanguageProvider {
    public TouchedUpLangDatagen(PackOutput gen) {
        super(gen, TouchedUpMod.TOUCHED_UP, "en_us");
    }

    @Override
    protected void addTranslations() {
        var blocks = new HashSet<>(TouchedUpBlocks.BLOCKS.getEntries());
        var items = new HashSet<>(TouchedUpItems.ITEMS.getEntries());

        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && !(i.get() instanceof ItemNameBlockItem));
        DataHelper.takeAll(blocks, i -> i.get() instanceof WallSignBlock);
        blocks.forEach(b -> {
            String name = b.get().getDescriptionId().replaceFirst("block\\.touched_up\\.", "");
            name = DataHelper.toTitleCase(correctItemName(name), "_");
            addBlock(b, name);
        });

        items.forEach(i -> {
            String name = i.get().getDescriptionId().replaceFirst("item\\.touched_up\\.", "");
            name = DataHelper.toTitleCase(correctItemName(name), "_");
            addItem(i, name);
        });
    }

    public String correctItemName(String name) {
        if (name.contains("music_disc")) {
            return "music_disc";
        }
        if ((!name.endsWith("_bricks"))) {
            if (name.contains("bricks")) {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if ((!name.endsWith("_boards"))) {
            if (name.contains("boards")) {
                name = name.replaceFirst("boards", "board");
            }
        }
        if (name.contains("_fence") || name.contains("_button")) {
            if (name.contains("planks")) {
                name = name.replaceFirst("_planks", "");
            }
        }
        return makeProperEnglish(name);
    }
    public String makeProperEnglish(String name) {
        String[] replacements = new String[]{"ns_", "rs_", "ts_"};
        String properName = name;
        for (String replacement : replacements) {
            int index = properName.indexOf(replacement);
            if (index != -1) {
                properName = properName.replaceFirst("s_", "'s_");
                break;
            }
        }
        return properName;
    }
}