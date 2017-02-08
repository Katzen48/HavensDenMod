package com.havensden.utilities.proxy;

import com.havensden.utilities.manager.ModelManager;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent pEvent) 
	{
		super.preInit(pEvent);
	}

	@Override
	public void init(FMLInitializationEvent pEvent) 
	{
		super.init(pEvent);
		ModelManager.registerModels();
	}

	@Override
	public void postInit(FMLPostInitializationEvent pEvent) 
	{
		super.postInit(pEvent);
	}

}
