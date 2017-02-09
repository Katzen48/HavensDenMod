package com.havensden.utilities.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent pEvent)
	{
		com.havensden.utilities.manager.Blocks.createBlocks();
		com.havensden.utilities.manager.Items.createItems();
	}
	
	public void init(FMLInitializationEvent pEvent)
	{
		
	}
	
	public void postInit(FMLPostInitializationEvent pEvent)
	{
		
	}
}
