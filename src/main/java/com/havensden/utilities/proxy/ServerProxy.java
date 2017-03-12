package com.havensden.utilities.proxy;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.config.ConfigHandler;
import com.havensden.utilities.database.MySqlConnection;
import com.havensden.utilities.money.BankSystem;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy
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
	}

	@Override
	public void postInit(FMLPostInitializationEvent pEvent) 
	{
		super.postInit(pEvent);
	}
}
