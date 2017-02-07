package com.havensden.utilities;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = HavensDenUtilities.MODID, version = HavensDenUtilities.VERSION)
public class HavensDenUtilities
{
    public static final String MODID = "HavensDen";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	System.out.println("Mod loaded");
    }
}
