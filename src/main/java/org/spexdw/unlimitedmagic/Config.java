package org.spexdw.unlimitedmagic;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = UnlimitedMagic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue MAX_ENCHANTMENT_LEVEL;
    public static final ForgeConfigSpec.DoubleValue COST_MULTIPLIER;
    public static final ForgeConfigSpec.BooleanValue ALLOW_CONFLICTING_ENCHANTMENTS;

    static {
        BUILDER.push("UnlimitedMagic Configuration");

        MAX_ENCHANTMENT_LEVEL = BUILDER
                .comment("The maximum level for any enchantment (default: 10)")
                .defineInRange("maxEnchantmentLevel", 10, 1, 100);

        COST_MULTIPLIER = BUILDER
                .comment("Multiplier for enchantment costs (default: 2.0)")
                .defineInRange("costMultiplier", 2.0, 1.0, 10.0);

        ALLOW_CONFLICTING_ENCHANTMENTS = BUILDER
                .comment("Allow conflicting enchantments to be applied (default: false)")
                .define("allowConflictingEnchantments", false);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
        UnlimitedMagic.LOGGER.debug("Loaded UnlimitedMagic config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
        UnlimitedMagic.LOGGER.debug("UnlimitedMagic config just got changed on the file system!");
    }
}