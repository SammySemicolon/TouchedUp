package com.sleepless_den.touched_up.data;


import com.sleepless_den.touched_up.TouchedUpMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = TouchedUpMod.TOUCHED_UP, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var provider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        var itemModelsProvider = new TouchedUpItemModelDatagen(output, helper);
        var blockStatesProvider = new TouchedUpBlockStateDatagen(output, helper, itemModelsProvider);

        generator.addProvider(event.includeClient(), blockStatesProvider);
        generator.addProvider(event.includeClient(), itemModelsProvider);

        generator.addProvider(event.includeClient(), new TouchedUpLangDatagen(output));
    }
}
