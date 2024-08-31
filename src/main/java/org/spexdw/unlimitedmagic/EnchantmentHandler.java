package org.spexdw.unlimitedmagic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = UnlimitedMagic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EnchantmentHandler {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.isEmpty() || right.isEmpty()) {
            return;
        }

        Map<Enchantment, Integer> leftEnchants = EnchantmentHelper.getEnchantments(left);
        Map<Enchantment, Integer> rightEnchants = EnchantmentHelper.getEnchantments(right);

        boolean changed = false;

        for (Map.Entry<Enchantment, Integer> entry : rightEnchants.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();

            if (leftEnchants.containsKey(enchantment)) {
                int currentLevel = leftEnchants.get(enchantment);
                if (currentLevel < level) {
                    leftEnchants.put(enchantment, level);
                    changed = true;
                } else if (currentLevel == level && currentLevel < enchantment.getMaxLevel()) {
                    leftEnchants.put(enchantment, currentLevel + 1);
                    changed = true;
                }
            } else {
                leftEnchants.put(enchantment, level);
                changed = true;
            }
        }

        if (changed) {
            ItemStack output = left.copy();
            EnchantmentHelper.setEnchantments(leftEnchants, output);
            event.setOutput(output);
            event.setCost(calculateCost(leftEnchants));
        }
    }

    private static int calculateCost(Map<Enchantment, Integer> enchantments) {
        int totalCost = 0;
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            totalCost += entry.getKey().getMinCost(entry.getValue()) * 2;
        }
        return Math.max(1, totalCost);
    }
}