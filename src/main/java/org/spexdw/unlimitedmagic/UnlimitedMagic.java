package org.spexdw.unlimitedmagic;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(UnlimitedMagic.MOD_ID)
public class UnlimitedMagic {
    public static final String MOD_ID = "unlimitedmagic";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public UnlimitedMagic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "unlimitedmagic-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }
}