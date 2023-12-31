package com.jetpacker06.goballistic;

import com.jetpacker06.goballistic.content.GBTab;
import com.jetpacker06.goballistic.datagen.DataGenerators;
import com.jetpacker06.goballistic.datagen.GBLang;
import com.jetpacker06.goballistic.datagen.GBTagGen;
import com.jetpacker06.goballistic.packet.GBPacketHandling;
import com.jetpacker06.goballistic.register.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(GoBallistic.MOD_ID)
public class GoBallistic {
    public static final String MOD_ID = "goballistic";
    public static final String NAME = "Create: Go Ballistic";
    private static final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID).registerEventListeners(eventBus);

    public GoBallistic() {
        REGISTRATE.creativeModeTab(() -> GBTab.GBT);

        GBItems.load();
        GBBlocks.load();
        GBBlockEntities.load();
        GBFluids.load();
        GBEntities.load();

        GBRecipeTypes.Registers.register(eventBus);
        GBSoundEvents.register(eventBus);

        GBLang.load();
        GBPacketHandling.init();

        eventBus.addListener(EventPriority.LOWEST, this::gatherData);
        eventBus.addListener(EventPriority.LOWEST, this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void gatherData(GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerators.gatherData(event);
            GBTagGen.datagen();
        }
    }

    public void commonSetup(FMLCommonSetupEvent event) {
    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
