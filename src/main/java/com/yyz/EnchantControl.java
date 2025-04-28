package com.yyz;

import com.yyz.config.EnchantmentControlConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class EnchantControl implements ModInitializer {
    private static EnchantmentControlConfig config;
    @Override
    public void onInitialize() {
        config = EnchantmentControlConfig.loadConfig(new File(FabricLoader.getInstance().getConfigDir() + "/enchantments_control.json"));
    }
    public static EnchantmentControlConfig getConfig() {
        return config;
    }
}
