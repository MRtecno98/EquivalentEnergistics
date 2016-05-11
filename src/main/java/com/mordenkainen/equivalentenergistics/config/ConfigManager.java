package com.mordenkainen.equivalentenergistics.config;

import java.io.File;

import com.mordenkainen.equivalentenergistics.EquivalentEnergistics;
import com.mordenkainen.equivalentenergistics.registries.BlockEnum;
import com.mordenkainen.equivalentenergistics.registries.ItemEnum;

import cpw.mods.fml.common.Loader;

import net.minecraftforge.common.config.Configuration;

public class ConfigManager {
	public static Configuration config;
	public static float crystalEMCValue;
	public static boolean useEE3;
	
	private ConfigManager() {}
	
    public static void init(final File file) {
    	config = new Configuration(file);

        config.load();
        
        EquivalentEnergistics.integration.loadConfig(config);
        
        for (final BlockEnum current : BlockEnum.values()) {
        	current.loadConfig(config);
        }
        
        for (final ItemEnum current : ItemEnum.values()) {
        	current.loadConfig(config);
        }
        
        crystalEMCValue = (float)config.get("General", "CrystalEMC", 256.0).getDouble(256.0);
        
        if(Loader.isModLoaded("EE3")) {
    		useEE3 = true;
    	}
        useEE3 = config.get("General", "UseEE3", useEE3).getBoolean(useEE3);
        
        if (config.hasChanged()) {
       		config.save();
       	}
    }
}
