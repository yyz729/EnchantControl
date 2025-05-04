package com.yyz;

import com.yyz.config.EnchantmentControlConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EnchantControl implements ModInitializer {
    private static EnchantmentControlConfig config;
    @Override
    public void onInitialize() {
        config = EnchantmentControlConfig.loadConfig(new File(FabricLoader.getInstance().getConfigDir() + "/enchantments_control.json"));
    }
    public static EnchantmentControlConfig getConfig() {
        return config;
    }


    public static boolean enchant_control$isAvailableForEnchantedBookOffer(Enchantment enchantment) {
        Identifier id = Registries.ENCHANTMENT.getId(enchantment);
        return convertStringSetToIdentifierSet(EnchantControl.getConfig().AvailableForEnchantedBookOffer).contains(id);
    }
    public static boolean enchant_control$isAvailableForRandomSelection(Enchantment enchantment) {
        Identifier id = Registries.ENCHANTMENT.getId(enchantment);
        return convertStringSetToIdentifierSet(EnchantControl.getConfig().AvailableForRandomSelection).contains(id);
    }
    public static boolean enchant_control$isCursed(Enchantment enchantment) {
        Identifier id = Registries.ENCHANTMENT.getId(enchantment);
        return convertStringSetToIdentifierSet(EnchantControl.getConfig().Cursed).contains(id);
    }
    public static boolean enchant_control$isTreasure(Enchantment enchantment) {
        Identifier id = Registries.ENCHANTMENT.getId(enchantment);
        return convertStringSetToIdentifierSet(EnchantControl.getConfig().Treasure).contains(id);
    }

    private static Set<Identifier> convertStringSetToIdentifierSet(Set<String> stringSet) {
        return stringSet.stream()
                .map(s -> {
                    try {
                        return new Identifier(s);
                    } catch (InvalidIdentifierException e) {
                        System.err.println("Invalid Identifier: " + s);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
